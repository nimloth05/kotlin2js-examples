module.exports = function (config) {
    config.set({
            frameworks: ["mocha"],
            reporters: ["mocha"],
            files: [
                {pattern: 'build/classes/kotlin/test/*_test.js', watched: false}
            ],
            exclude: [],
            colors: false,
            autoWatch: false,
            browsers: [
                'PhantomJS'
                // , 'Chrome'
            ],
            singleRun: true,

            preprocessors: {
                'build/classes/kotlin/test/*_test.js': ['webpack']
            },

            webpack: {
                mode: "development",
                resolve: {
                    "modules": [
                        "build/kotlin-js-min/main/",
                        "node_modules"
                    ]
                }
            }


        }
    )
};