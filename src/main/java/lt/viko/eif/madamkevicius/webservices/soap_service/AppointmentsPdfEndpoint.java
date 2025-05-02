package lt.viko.eif.madamkevicius.webservices.soap_service;

import io.spring.appointments.getappointmentpdf.GetPdfRequest;
import io.spring.appointments.getappointmentpdf.GetPdfResponse;
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

/**
 * This is the Endpoint class representing an endpoint of SOAP web service.
 * <p>
 *     This class is annotated with {@link Endpoint} to indicate that it serves as a SOAP endpoint.
 *     It processes requests from users,
 *     fetches the appointments from a database and returns the response as a PDF file.
 * </p>
 * @author Matvej
 */
@Endpoint
public class AppointmentsPdfEndpoint {

    public static final String NAMESPACE_URI = "http://spring.io/appointments/getAppointmentPdf";

    public static final String XML_FILE = "appointmentsPdf.xml";

    public static final String PDF_FILE = "appointments.pdf";

    private final AppointmentRepo appointmentRepo;

    private final PatientRepo patientRepo;

    private final Appointments appointments;

    private final TransformationService transformationService;

    /**
     * AppointmentsPdfEndpoint constructor injects repositories, transformationService and appointments classes
     * using the annotation {@link Autowired}.
     * @param appointmentRepo {@link AppointmentRepo} repository used to access appointment data.
     * @param patientRepo {@link PatientRepo} repository used to access patient data.
     * @param appointments {@link Appointments} class that sets and gets a list of appointments.
     * @param transformationService {@link TransformationService} used to transform data from different formats.
     */
    @Autowired
    public AppointmentsPdfEndpoint(
            AppointmentRepo appointmentRepo, PatientRepo patientRepo,
            Appointments appointments, TransformationService transformationService) {
        this.appointmentRepo = appointmentRepo;
        this.patientRepo = patientRepo;
        this.appointments = appointments;
        this.transformationService = transformationService;
    }


    /**
     * This method handles SOAP web service which generates a PDF file of user's appointments.
     * <p>
     *     The method verifies the user's uid,
     *     and password fetches their appointments from a database if they exist,
     *     transforms it to a PDF file and returns as a byte array in response.
     * </p>
     * @param request {@link GetPdfRequest} gets user's uid and password.
     * @throws RuntimeException if an I/O occurs during PDF generation
     *                          or/and reading all bytes from a file.
     * @return <ul>
     *         <li>if a user does not exist, returns "Invalid credentials" as a byte array.</li>
     *         <li>if a user exists and have an appointment returns a PDF file with his appointments.</li>
     *         <li>otherwise, returns an empty array of bytes.</li>
     *         </ul>
     */
    @PayloadRoot(
            namespace = NAMESPACE_URI,
            localPart = "getPdfRequest")
    @ResponsePayload
    public GetPdfResponse getPdfResponse(@RequestPayload GetPdfRequest request) {
        GetPdfResponse response = new GetPdfResponse();
        byte[] pdfBytes;

        boolean exists = patientRepo
                .existsPatientByUidAndPassword(
                        request.getUid(),
                        request.getPassword()
                );

        if (!exists) {
            pdfBytes = "Invalid credentials".getBytes();
            response.setAppointmentsPdf(pdfBytes);
        } else {
            appointments.setAppointments(
                    appointmentRepo
                    .findAppointmentsByPatient_UidAndPatient_Password(
                            request.getUid(),
                            request.getPassword()
                    )
            );

            if (appointments.getAppointments().isEmpty()) {
                System.out.println("Data is empty");
                response.setAppointmentsPdf(new byte[0]);
                return response;
            } else {
                transformationService.transformToXml(appointments, XML_FILE);

                try {
                    transformationService.transformXmlToPdf(
                            "appointments-to-pdf.xslt",
                            XML_FILE,
                            PDF_FILE
                    );

                    pdfBytes = Files.readAllBytes(
                            new File(PDF_FILE).toPath()
                    );
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                response.setAppointmentsPdf(pdfBytes);
            }

        }

        return response;
    }
}
