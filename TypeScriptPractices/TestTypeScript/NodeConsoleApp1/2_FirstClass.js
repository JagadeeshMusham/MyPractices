"use strict";
class Greeting {
    greet() {
        console.log("This is first .ts example with classes");
    }
    add(a, b) {
        return a + b;
    }
}
var greet = new Greeting();
greet.greet();
console.log("The addition of 3 and 4 is: ", greet.add(3, 4));
//# sourceMappingURL=2_FirstClass.js.map