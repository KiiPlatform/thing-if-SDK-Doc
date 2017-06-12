#!/bin/sh

tsc
js-beautify -s 2 src.js > tmp.js
sed -e "s/_ts/_js/g" tmp.js > src.js
rm tmp.js
