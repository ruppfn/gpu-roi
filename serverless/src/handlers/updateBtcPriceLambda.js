"use strict";

const {updateBtcPrice} = require("../useCases/updateBtcPrice");

const BTC_URL = process.env.BTC_URL || "";

console.log(`BTC_URL: ${BTC_URL}`);

module.exports.handler = async () => {

    await updateBtcPrice(BTC_URL);

    return {
        statusCode: 200,
        body: JSON.stringify(
            null,
            null,
            2
        ),
    };
};
