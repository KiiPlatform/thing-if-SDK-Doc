#!/bin/sh

typings install
tsc
js-beautify -s 2 src.js -o tmp.js
sed -e "s/_ts/_js/g" tmp.js > src.js
rm tmp.js
