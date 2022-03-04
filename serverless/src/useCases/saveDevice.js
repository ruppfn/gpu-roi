const AWS = require("aws-sdk");

const dynamo = new AWS.DynamoDB.DocumentClient();

module.exports.saveDevice = async (device) => {
    return dynamo.put({
        TableName: "Devices",
        Item: device
    }).promise();
};
