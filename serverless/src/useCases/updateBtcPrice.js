const {getBtcPriceFromApi} = require("./getBtcPrice");
const {saveBtcPrice} = require("./savePrice");

module.exports.updateBtcPrice = async (baseUrl) => {
    console.debug("Updating BTC Price")
    const price = await getBtcPriceFromApi(baseUrl);
    await saveBtcPrice(price);
};
