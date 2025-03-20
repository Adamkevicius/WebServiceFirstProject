package lt.viko.eif.madamkevicius.webservices.service;

import org.springframework.stereotype.Service;

@Service
public interface Transformable {

    void transformToXml(Class<?> cls, String fileName);
}
