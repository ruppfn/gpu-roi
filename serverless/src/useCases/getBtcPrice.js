const axios = require("axios");
const AWS = require("aws-sdk");

const dynamo = new AWS.DynamoDB.DocumentClient();

const getBtcPriceFromApi = async (url) => {
    const res = await axios.get(url);
    const { price } = res.data;

    return parseFloat(price);
};

const getBtcPriceFromDynamo = async () => {
    const dynamoResponse = await dynamo.query({
        TableName: "Prices",
        KeyConditionExpression: "#type = :type",
        ExpressionAttributeNames: {
            "#type": "Type"
        },
        ExpressionAttributeValues: {
            ":type": "BTC"
        }
    }).promise();

    return dynamoResponse.Items[0];
};

module.exports = {
    getBtcPriceFromApi,
    getBtcPriceFromDynamo
}
