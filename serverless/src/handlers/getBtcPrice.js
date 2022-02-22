"use strict";

const axios = require("axios");
const AWS = require("aws-sdk");

const dynamo = new AWS.DynamoDB.DocumentClient();

const BTC_URL = process.env.BTC_URL || "";

console.log(`BTC_URL: ${BTC_URL}`);

module.exports.handler = async () => {

    const price = await getBtcPrice(BTC_URL);

    const priceItem = {
        Type: "BTC",
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

const getBtcPrice = async (url) => {
    const res = await axios.get(url);
    const { price } = res.data;

    return parseFloat(price);
};
