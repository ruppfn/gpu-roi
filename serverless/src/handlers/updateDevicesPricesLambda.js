const { updateDevicesPrices } = require("../useCases/updateDevicesPrices");

const DEVICES_URL = process.env.DEVICES_URL;

module.exports.handler = async () => {
    await updateDevicesPrices(DEVICES_URL);
};
