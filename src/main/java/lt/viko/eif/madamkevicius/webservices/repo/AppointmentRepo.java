package lt.viko.eif.madamkevicius.webservices.repo;

import lt.viko.eif.madamkevicius.webservices.model.Appointment;
import lt.viko.eif.madamkevicius.webservices.model.Appointments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Integer> {

    List<Appointment> findAppointmentsByPatient_UidAndPatient_Password(String uid, String password);

    boolean existsAppointmentByPatient_UidAndPatient_Password(String uid, String password);
}
