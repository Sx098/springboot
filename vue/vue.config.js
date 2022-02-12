module.exports = {
    devServer: {
        proxy: {
            '/api': {
                target: 'http://localhost:8888/', //接口域名
                changeOrigin: true,             //是否跨域
                pathRewrite:{
                    '^/api':''
                }

            }
        }
    }
};