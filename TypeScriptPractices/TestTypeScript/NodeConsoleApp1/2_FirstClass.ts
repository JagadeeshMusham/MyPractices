class Greeting {
    greet() : void {
        console.log("This is first .ts example with classes");
    }

    add(a: number, b: number): number {
        return a + b;
    }
}

var greet = new Greeting();
greet.greet();
console.log("The addition of 3 and 4 is: ", greet.add(3,4))