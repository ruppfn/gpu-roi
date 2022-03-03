const axios = require("axios");

module.exports.getUsdPrice = async (url) => {
    const res = await axios.get(url);
    const { venta } = res.data;

    return parseFloat(venta);
};
