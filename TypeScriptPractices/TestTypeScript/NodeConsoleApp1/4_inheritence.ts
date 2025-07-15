class Person {
    name: String;

    constructor(name: String) {
        this.name = name;
    }

    display(): void {
        console.log("The name of the person is: " + this.name)
    }
}

class Employee extends Person {
    id: number;

    constructor(name: String, id: number) {
        super(name);
        this.id = id;
    }

    displayEmployee(): void {
        this.display();
        console.log("The employee id is: ", this.id);
    }
}

let emp = new Employee("Jagadees", 779);
emp.displayEmployee();