const AWS = require("aws-sdk");

const dynamo = new AWS.DynamoDB.DocumentClient();

const savePrice = async (type, price) => {
    console.debug(`Saving Price ${type}:${price}`);

    await dynamo.put({
        TableName: "Prices",
        Item: makeDynamoItem(type, price)
    }).promise();
};

const makeDynamoItem = (type, price) => {
    return {
        Type: type,
        Price: price
    };
};

const saveUsdPrice = async (price) => {
    await savePrice("USD", price);
};

const saveBtcPrice = async (price) => {
    await savePrice("BTC", price);
};

module.exports = {
    saveUsdPrice,
    saveBtcPrice
};