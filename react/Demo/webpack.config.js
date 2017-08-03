/**
 * Created by lenovo on 2017/3/25.
 */
var path = require('path');
module.exports = {
    entry: path.resolve(__dirname, './src/app/main.js'),
    output: {
        path: path.resolve(__dirname, "./src/build"), //文件打包后存放在哪个目录下
        filename: "[name].bundle.js", //打包后的文件名
        publicPath: "/src/build/" //公共路径
    },
    module: {
        loaders: [
            {
                test: /\.jsx?$/,
                loader: 'babel-loader',
                query: {
                    //添加两个presents 使用这两种presets处理js或者jsx文件
                    presets: ['es2015', 'react']
                }
            },
            {
                test: /\.css$/, // Only .css files
                loader: 'style-loader!css-loader' // Run both loaders
            },
            {
                test: /\.(png|jpg)$/,
                loader: 'url-loader?limit=25000'
            }
        ]
    },
};