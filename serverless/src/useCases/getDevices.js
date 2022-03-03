const AWS = require("aws-sdk");

const dynamo = new AWS.DynamoDB.DocumentClient();

const getDevicesFromDynamo = async () => {
    console.debug("Scanning Devices table");

    const dynamoResponse = await dynamo.scan({
        TableName: "Devices"
    }).promise();

    console.debug(`Found ${dynamoResponse.Count} devices`);

    return dynamoResponse.Items;
};

module.exports = {
    getDevicesFromDynamo
}
