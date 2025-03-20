package lt.viko.eif.madamkevicius.webservices.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String uid;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private int age;
    private String phoneNumber;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "doctor_doctor_type",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_type_id")
    )
    private List<DoctorType> doctorTypes;

    public Doctor() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLastLame() {
        return lastName;
    }

    public void setLastLame(String lastLame) {
        this.lastName = lastLame;
    }

    public List<DoctorType> getDoctorTypes() {
        return doctorTypes;
    }

    public void setDoctorTypes(List<DoctorType> doctorTypes) {
        this.doctorTypes = doctorTypes;
    }

    @Override
    public String toString() {
        return "\n\t\t\tDoctor:" +
                "\n\t\t\t\tid:" + id +
                "\n\t\t\t\tuid:" + uid +
                "\n\t\t\t\tpassword:" + password +
                "\n\t\t\t\temail:" + email +
                "\n\t\t\t\tfirstname:" + firstName +
                "\n\t\t\t\tlastname:" + lastName +
                "\n\t\t\t\tage=" + age +
                "\n\t\t\t\tphone_number=" + phoneNumber +
                "\n\t\t\t\tdoctor_types=" + doctorTypes;
    }
}






