const { getDevicesFromDynamo } = require("./getDevices");
const { saveDevice } = require("./saveDevice");

const { getAveragePriceForDevice } = require("./devicesScraping");

module.exports.updateDevicesPrices = async (baseUrl) => {
    const devices = await getDevicesFromDynamo();

    const updatePromises = [];

    for (const device of devices) {
        const promise = updatePrice(baseUrl, device);
        updatePromises.push(promise);
    }

    await Promise.allSettled(updatePromises);
};

const updatePrice = async (baseUrl, device) => {
    const price = await getAveragePriceForDevice(baseUrl, device.Name);

    if (isNaN(price)) return;

    device.Price = price;
    await saveDevice(device);
};
