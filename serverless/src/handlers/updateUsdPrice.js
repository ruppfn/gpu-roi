"use strict";

const AWS = require("aws-sdk");
const { getUsdPrice } = require("../utils/getUsdPrice");

const dynamo = new AWS.DynamoDB.DocumentClient();

const USD_URL = process.env.USD_URL || "";

console.log(`USD_URL: ${USD_URL}`);

module.exports.handler = async () => {

    const price = await getUsdPrice(USD_URL);

    const priceItem = {
        Type: "USD",
        Price: price
    };

    console.log(priceItem);

    await dynamo.put({
        TableName: "Prices",
        Item: priceItem
    }).promise();

    return {
        statusCode: 200,
        body: JSON.stringify(
            {
                price: price
            },
            null,
            2
        ),
    };
};
