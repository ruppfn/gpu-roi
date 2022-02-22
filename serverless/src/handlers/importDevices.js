"use strict";

const axios = require("axios");
const AWS = require("aws-sdk");

const dynamo = new AWS.DynamoDB.DocumentClient();

const DEVICES_URL = process.env.DEVICES_URL || "";

const BATCH_SIZE = 25;

module.exports.handler = async () => {

    const res = await axios.get(DEVICES_URL);

    const {devices} = res.data;

    const gpus = devices.filter(device => device.category !== "ASIC" && device.paying > 0);

    const convertedGpus = gpus.map(gpu => convertGpuToRequiredObject(gpu));

    const promises = []
    let items = [];
    let batchNumber = 0;

    for (const gpu of convertedGpus) {
        items.push(gpu);

        if (items.length === BATCH_SIZE) {
            console.log(`Saving Batch ${batchNumber}`);
            promises.push(saveToDynamoDb(items));

            batchNumber++;
            items = [];
        }
    }

    if (items.length > 0) {
        console.log(`Saving Batch ${batchNumber}`);
        promises.push(saveToDynamoDb(items));
    }

    if (promises.length > 0) {
        console.log(`awaiting ${promises.length} writes to DynamoDB`);
        await Promise.all(promises);
    }

    return {
        statusCode: 200,
        body: JSON.stringify(
            {
                message: "Devices updated!"
            },
            null,
            2
        ),
    };
};

const convertGpuToRequiredObject = (gpu) => {
    return {
        DeviceId: gpu.id,
        Paying: gpu.paying,
        Name: gpu.name,
        Power: gpu.power,
        Speeds: JSON.parse(gpu.speeds)
    };
}

const saveToDynamoDb = async (items) => {
    const putRequests = items.map(item => ({
        PutRequest: {
            Item: item
        }
    }));

    const batchRequest = {
        RequestItems: {
            "Devices": putRequests
        }
    };

    await dynamo.batchWrite(batchRequest).promise();
};
