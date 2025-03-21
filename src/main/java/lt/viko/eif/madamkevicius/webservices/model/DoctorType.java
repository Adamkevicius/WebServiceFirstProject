package lt.viko.eif.madamkevicius.webservices.model;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@Entity
@Table(name = "doctor_type")
@XmlAccessorType(XmlAccessType.FIELD)
public class DoctorType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String type;

    public DoctorType() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "\n\t\t\t\t\t\tDoctor type:" +
                "\n\t\t\t\t\t\t\tid:" + id +
                "\n\t\t\t\t\t\t\ttype:" + type;
    }
}
