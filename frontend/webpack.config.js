module.exports = {
  module: {
    rules: [
      {
        test: /\.pcss$/,
        use: [
          'style-loader',
          'css-loader',
          {
            loader: 'postcss-loader',
            options: {
              postcssOptions: {
                plugins: [
                  require('postcss-import'),
                  require('tailwindcss'),
                  require('autoprefixer'),
                  require('postcss-url'),
                ]
              }
            }
          },
        ]
      }
    ]
  }
};
