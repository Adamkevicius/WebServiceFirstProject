package lt.viko.eif.madamkevicius.webservices.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import lt.viko.eif.madamkevicius.webservices.model.Appointments;
import org.apache.fop.apps.*;
import org.springframework.stereotype.Service;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

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
        } catch (JAXBException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(json);
    }

    public void transformXmlToPdf(String xsltFile, String xmlFile, String pdfFile) throws IOException {
        File xslt = new File(xsltFile);

        StreamSource xslSource = new StreamSource(new File(xmlFile));

        FopFactory factory = FopFactory.newInstance(new File(".").toURI());

        FOUserAgent foUserAgent = factory.newFOUserAgent();

        OutputStream out;
        out = new FileOutputStream(pdfFile);

        try {
            Fop fop = factory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer(new StreamSource(xslt));

            Result result = new SAXResult(fop.getDefaultHandler());

            transformer.transform(xslSource, result);
        } catch (FOPException | TransformerException e) {
            throw new RuntimeException(e);
        } finally {
            out.close();
        }


    }
}

