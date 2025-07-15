// Generics in TypeScript
function printArray<T>(arr: T[]): void {
    for (let i = 0; i < arr.length; i++) {
        console.log(arr[i]);
    }
}

// Using the generic function
printArray([1, 2, 3]);          // Array of numbers
printArray(["a", "b", "c"]);    // Array of strings
