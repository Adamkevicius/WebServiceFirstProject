package lt.viko.eif.madamkevicius.webservices.model;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;

import java.util.Date;

@Entity
@Table(name = "appointment")
@XmlAccessorType(XmlAccessType.FIELD)
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date date;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "appointment_type_id", referencedColumnName = "id")
    @XmlElement
    private ApointmentType apointmentType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    @XmlElement
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    @XmlElement
    private Patient patient;

    public Appointment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ApointmentType getApointmentType() {
        return apointmentType;
    }

    public void setApointmentType(ApointmentType apointmentType) {
        this.apointmentType = apointmentType;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "\nAppointment:" +
                "\n\tid:" + id +
                "\n\tdate:" + date +
                apointmentType + doctor + patient;
    }
}
