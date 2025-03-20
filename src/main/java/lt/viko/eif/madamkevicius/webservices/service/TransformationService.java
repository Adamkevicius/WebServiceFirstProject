package lt.viko.eif.madamkevicius.webservices.service;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import lt.viko.eif.madamkevicius.webservices.model.Appointments;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class TransformationService {
    public void transformToXml(Appointments appointments, String fileName) {
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(Appointments.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(appointments, System.out.printf("Objects transformed successfully and saved to file with name: %s\n", fileName));
            marshaller.marshal(appointments, new File(fileName));
        } catch (JAXBException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

}
