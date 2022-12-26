I wanted to try out something new, therefore I tried to set up a small TypeScript project that can be used to solve some Advent of Code riddles.

What is needed to run the code:
Node.js and NPM need to be installed.
Execute:
$> npm install --global typescript

Each .ts file is self-contained, and can be compiled with:
$> tsc -target es2016 sample.ts
A .js file with the same name will be generated, which can be run with:
$> node sample.js

Both can be combined as follows:
$> tsc -target es2016 sample.ts && node sample.js
This is especially helpful if you want to be sure that the most current version of some code is run at all times. The TypeScript compiler seems to be very fast, so it does not add too much overhead.

For further fun, the resulting .js file can be minified with: https://www.toptal.com/developers/javascript-minifier
It can then be beautified again with: https://beautifier.io/
This can sometimes yield code that is shorter, but still nicely understandable, in other words, it can be a good learning experience, even though in most cases, the resulting code is less nice than the original one.
