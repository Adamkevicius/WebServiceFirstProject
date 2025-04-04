package lt.viko.eif.madamkevicius.webservices.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
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

    public void transformToPOJO(String fileName) {
        String json;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Appointments.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            File file = new File(fileName);

            Appointments appointments = (Appointments) unmarshaller.unmarshal(file);

            ObjectMapper mapper = new ObjectMapper();

            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(appointments);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(json);
    }
}
