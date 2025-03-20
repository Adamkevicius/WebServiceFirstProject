package lt.viko.eif.madamkevicius.webservices.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement
public class Appointments {

    private List<Appointment> appointments;

    public Appointments() {
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    @XmlElement(name="appointment")
    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
}
