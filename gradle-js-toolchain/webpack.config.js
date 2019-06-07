var webpack = require("webpack");
var path = require("path");
var outputMin = "build/kotlin-js-min/main/";

module.exports = {
    mode: "development",
    context: path.resolve(__dirname, outputMin),
    entry: {
        jsToolchain: "./jsToolchain.js"
    },
    output: {
        path: path.resolve(__dirname, "build"),
        filename: "[name].bundle.js",
        chunkFilename: "[id].bundle.js",
        publicPath: "/"
    },
    devtool: 'inline-source-map',
    resolve: {
        "modules": [
            outputMin,
            "node_modules"
        ]
    },
    plugins: [
    ]
};
