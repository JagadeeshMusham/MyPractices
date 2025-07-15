"use strict";
class Person {
    constructor(name) {
        this.name = name;
    }
    display() {
        console.log("The name of the person is: " + this.name);
    }
}
class Employee extends Person {
    constructor(name, id) {
        super(name);
        this.id = id;
    }
    displayEmployee() {
        this.display();
        console.log("The employee id is: ", this.id);
    }
}
let emp = new Employee("Jagadees", 779);
emp.displayEmployee();
//# sourceMappingURL=4_inheritence.js.map