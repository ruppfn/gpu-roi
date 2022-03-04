"use strict";

const { getDevicesFromDynamo } = require("../useCases/getDevices");

module.exports.handler = async () => {

    const devices = await getDevicesFromDynamo();

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
