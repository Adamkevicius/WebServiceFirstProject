package lt.viko.eif.madamkevicius.webservices.soap_service;

import io.spring.appointments.getappointmentpdf.GetPdfRequest;
import io.spring.appointments.getappointmentpdf.GetPdfResponse;
import lt.viko.eif.madamkevicius.webservices.model.Appointment;
import lt.viko.eif.madamkevicius.webservices.model.Appointments;
import lt.viko.eif.madamkevicius.webservices.repo.AppointmentRepo;
import lt.viko.eif.madamkevicius.webservices.repo.PatientRepo;
import lt.viko.eif.madamkevicius.webservices.service.TransformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Endpoint
public class AppointmentsPdfEndpoint {

    public static final String NAMESPACE_URI = "http://spring.io/appointments/getAppointmentPdf";

    private final AppointmentRepo appointmentRepo;

    private final PatientRepo patientRepo;

    private final Appointments appointments;

    private final TransformationService transformationService;

    @Autowired
    public AppointmentsPdfEndpoint(AppointmentRepo appointmentRepo, PatientRepo patientRepo, Appointments appointments, TransformationService transformationService) {
        this.appointmentRepo = appointmentRepo;
        this.patientRepo = patientRepo;
        this.appointments = appointments;
        this.transformationService = transformationService;
    }

    @PayloadRoot(
            namespace = NAMESPACE_URI,
            localPart = "getPdfRequest")
    @ResponsePayload
    public GetPdfResponse getPdfResponse(@RequestPayload GetPdfRequest request) {
        GetPdfResponse response = new GetPdfResponse();
        String xmlFile = "appointmentsPdf.xml";
        String pdfFile = "appointments.pdf";
        byte[] pdfBytes;

        List<Appointment> appointmentList = appointmentRepo
                .findAppointmentsByPatient_UidAndPatient_Password(
                        request.getUid(),
                        request.getPassword()
                );

        boolean exists = patientRepo
                .existsPatientByUidAndPassword(
                        request.getUid(),
                        request.getPassword()
                );

        if (!exists) {
            pdfBytes = "Invalid credentials".getBytes();
            response.setAppointmentsPdf(pdfBytes);
        } else {

            if (appointmentList.isEmpty()) {
                System.out.println("Data is empty");
                response.setAppointmentsPdf(new byte[0]);
                return response;
            } else {
                appointments.setAppointments(appointmentList);

                transformationService.transformToXml(appointments, xmlFile);

                try {
                    transformationService.transformXmlToPdf(
                            "appointments-to-pdf.xslt",
                            xmlFile,
                            pdfFile
                    );

                    File file = new File(pdfFile);
                    pdfBytes = Files.readAllBytes(file.toPath());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                response.setAppointmentsPdf(pdfBytes);
            }
        }

        return response;
    }

    boolean existsPatient(String uid, String password) {
        return patientRepo.existsPatientByUidAndPassword(uid, password);
    }
}
