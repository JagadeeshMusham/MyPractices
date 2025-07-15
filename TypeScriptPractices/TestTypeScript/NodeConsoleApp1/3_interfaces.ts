//example with interfaces
interface iStudent {
    firstName: string;
    lastName: String;

    getFullName(): String;
}

let student: iStudent = {
    firstName : "Jagadeesh",
    lastName : "Musham",
    getFullName(): String {
        return this.firstName + " " + this.lastName;
    }
}

console.log(student.getFullName());
