const { getUsdPrice } = require("./getUsdPrice");
const { saveUsdPrice } = require("./savePrice");

module.exports.updateUsdPrice = async (baseUrl) => {
    const price = await getUsdPrice(baseUrl);
    await saveUsdPrice(price);
};
