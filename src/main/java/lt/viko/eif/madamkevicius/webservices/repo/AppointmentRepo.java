package lt.viko.eif.madamkevicius.webservices.repo;

import lt.viko.eif.madamkevicius.webservices.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepo extends JpaRepository<Appointment, Integer> {
}
