"use strict";

const AWS = require("aws-sdk");

const dynamo = new AWS.DynamoDB.DocumentClient();

module.exports.handler = async () => {

    const dynamoResponse = await dynamo.scan({
        TableName: "Devices"
    }).promise();

    console.log(`Found ${dynamoResponse.Count} devices`);

    const devices = dynamoResponse.Items;

  return {
    statusCode: 200,
    body: JSON.stringify(
      {
        devices
      },
      null,
      2
    ),
  };
};
