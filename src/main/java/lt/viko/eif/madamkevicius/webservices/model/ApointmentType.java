package lt.viko.eif.madamkevicius.webservices.model;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@Entity
@Table(name = "appointment_type")
@XmlAccessorType(XmlAccessType.FIELD)
public class ApointmentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String apointmentType;
    private boolean isPaid;

    public ApointmentType() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApointmentType() {
        return apointmentType;
    }

    public void setApointmentType(String apointmentType) {
        this.apointmentType = apointmentType;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    @Override
    public String toString() {
        return "\n\t\tAppointment_type:" +
                "\n\t\t\tid:" + id +
                "\n\t\t\ttype:" + apointmentType +
                "\n\t\t\tis_paid:" + isPaid;
    }
}
