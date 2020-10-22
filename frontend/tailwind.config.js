const defaultTheme = require('tailwindcss/defaultTheme')
// const colors = require('tailwindcss/defaultTheme')

// console.log(colors)
module.exports = {
  future: {
    // removeDeprecatedGapUtilities: true,
    // purgeLayersByDefault: true,
    // defaultLineHeights: true,
    // standardFontWeights: true
  },
  purge: [],
  theme: {
    extend: {
      colors: {
        gray: {
          '100': '#F5F5F5',
          '200': '#EEEEEE',
          '300': '#E0E0E0',
          '400': '#BDBDBD',
          '500': '#9E9E9E',
          '600': '#757575',
          '700': '#616161',
          '800': '#424242',
          '850': '#323232',
          '900': '#212121',
        },
        grayblue: {
          '100': '#CFD8DC',
          '200': '#B0BEC5',
          '300': '#90A4AE',
          '400': '#78909C',
          '500': '#607D8B',
          '600': '#546E7A',
          '700': '#455A64',
          '800': '#37474F',
          '900': '#263238',
        }
      },
      fontFamily: {
        sans: [
          'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', 'Tahoma', 'sans-serif'
        ]
      }
    }
  },
  variants: {},
  plugins: [
    // require('@tailwindcss/typography')
  ]
}

