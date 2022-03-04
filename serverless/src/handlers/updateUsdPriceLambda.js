"use strict";

const { updateUsdPrice } = require("../useCases/updateUsdPrice");

const USD_URL = process.env.USD_URL || "";

console.log(`USD_URL: ${USD_URL}`);

module.exports.handler = async () => {

    await updateUsdPrice(USD_URL);

    return {
        statusCode: 200,
        body: JSON.stringify(
            null,
            null,
            2
        ),
    };
};
