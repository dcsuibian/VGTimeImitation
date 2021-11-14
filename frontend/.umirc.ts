import { defineConfig } from 'umi';

export default defineConfig({
  nodeModulesTransform: {
    type: 'none',
  },
  proxy: {
    '/api': {
      target: 'http://localhost:30119/api',
      changeOrigin: true,
      pathRewrite: { '^/api': '' },
    },
  },
  // mfsu: {},
  antd: false,
  fastRefresh: {},
  title:'游戏时光 - vgtime.com',
  outputPath:'../backend/src/main/resources/static'
});
