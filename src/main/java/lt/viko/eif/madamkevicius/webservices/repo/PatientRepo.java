package lt.viko.eif.madamkevicius.webservices.repo;

import lt.viko.eif.madamkevicius.webservices.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Integer> {
    boolean existsPatientByUidAndPassword(String uid, String password);
}
