package lt.viko.eif.madamkevicius.webservices.model;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlType;

@Entity
@Table(name = "patient")
//@XmlType(propOrder =  {"id", "uid"})
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String uid;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private String gender;
    private String phone_number;

    public Patient() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    @Override
    public String toString() {
        return "\n\t\t\t\t\t\t\tPatient:" +
                "\n\t\t\t\t\t\t\t\tid:" + id +
                "\n\t\t\t\t\t\t\t\tuid:" + uid +
                "\n\t\t\t\t\t\t\t\tpassword:" + password +
                "\n\t\t\t\t\t\t\t\temail:" + email +
                "\n\t\t\t\t\t\t\t\tfirstname:" + firstName +
                "\n\t\t\t\t\t\t\t\tlastname:" + lastName +
                "\n\t\t\t\t\t\t\t\tage=" + age +
                "\n\t\t\t\t\t\t\t\tgender:" + gender +
                "\n\t\t\t\t\t\t\t\tphone_number=" + phone_number;
    }
}
