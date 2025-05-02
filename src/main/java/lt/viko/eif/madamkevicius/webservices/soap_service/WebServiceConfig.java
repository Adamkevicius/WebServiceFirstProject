package lt.viko.eif.madamkevicius.webservices.soap_service;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

/**
 * This class is responsible for the configuration of the message dispatcher
 * servlet, WSDL definitions, and xsd schema resources.
 *
 * <p>
 *      It enables the web service and binds the
 *      necessary parts for handling SOAP messages.
 *      This class is annotated with {@link EnableWs} that enables web services,
 *      {@link Configuration} to indicate that is a Sping configuration class.
 * </p>
 * @author Matvej
 */
@EnableWs
@Configuration
public class WebServiceConfig {

    /**
     * This method creates MessageDispatcherServlet bean and registers it with the {@link ApplicationContext}
     * and sets wsdl transformation to true.
     * @param context {@link ApplicationContext} object that contains the Spring application context.
     * @return {@link ServletRegistrationBean} object that contains the MessageDispatcherServlet with its configuration
     * and url mapping.
     */
    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(
            ApplicationContext context) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(context);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    /**
     * This method creates a new DefaultWsdl11Definition object and sets its properties.
     * <p>
     *     The bean name <code>appointmentsPdf</code> defines the wsdl name.
     * </p>
     * <p>
     *     Method also sets the port type name, location URI, target namespace, and XSD schema.
     * </p>
     * @param appointmentSchema {@link XsdSchema} object that contains the XSD schema.
     * @return {@link DefaultWsdl11Definition} object that contains the WSDL definition with its configuration.
     * @see <a href="http://localhost:8080/ws/appointmentsPdf.wsdl">
     *          Created WSDL schema (to see the schema, you must enable the API).
     *     </a>
     */
    @Bean(name = "appointmentsPdf")
    public DefaultWsdl11Definition appointmentDefinition(XsdSchema appointmentSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("AppointmentsPdfPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace(AppointmentsPdfEndpoint.NAMESPACE_URI);
        wsdl11Definition.setSchema(appointmentSchema);
        return wsdl11Definition;
    }

    /**
     * This method gets XSD schema from the {@link ClassPathResource} class and creates a new SimpleXsdSchema object.
     * @return {@link SimpleXsdSchema} object that contains the XSD schema.
     */
    @Bean
    public XsdSchema appointmentsSchema() {
        return new SimpleXsdSchema(new ClassPathResource("appointmentsPdf.xsd"));
    }
}
