"use strict";

const { importDevices } = require("../useCases/importDevices");
const DEVICES_URL = process.env.DEVICES_URL || "";

module.exports.handler = async () => {

    await importDevices(DEVICES_URL);

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
