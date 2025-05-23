package org.musham.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "student")
public class Student {
    @Id
    private String id;

    @Field("roll_no")
    private String rollNo;

    @Field("name")
    private String sName;

    @Field("age")
    private int sAge;

//    @Field("address")
    private Address address; // Embedding Address

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getsAge() {
        return sAge;
    }

    public void setsAge(int sAge) {
        this.sAge = sAge;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", rollNo='" + rollNo + '\'' +
                ", sName='" + sName + '\'' +
                ", sAge=" + sAge +
                ", address=" + address +
                '}';
    }
}
