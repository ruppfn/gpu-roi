const avg = (arr) => {
    return arr.reduce((a, b) => a + b, 0) / arr.length;
}

module.exports = {
    avg
};
