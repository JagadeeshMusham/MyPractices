function example() {
    let x = 10;
    if (x > 5) {
        var y = 20;
        let z = 30;
        console.log(y); // ✅ Accessible here
        console.log(z); // ✅ Accessible here
    }
    console.log(y); // ✅ Accessible here
    //console.log(z); // ❌ Error: z is not defined
}

example();
