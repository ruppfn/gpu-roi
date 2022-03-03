const axios = require("axios");

const getBtcPriceFromApi = async (url) => {
    const res = await axios.get(url);
    const { price } = res.data;

    return parseFloat(price);
};

module.exports = {
    getBtcPriceFromApi
}
