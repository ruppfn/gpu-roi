const axios = require("axios");

module.exports.getBtcPrice = async (url) => {
    const res = await axios.get(url);
    const { price } = res.data;

    return parseFloat(price);
};
