package com.github.nekitos911.utils;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import lombok.experimental.UtilityClass;

import java.io.StringReader;

@UtilityClass
public class XmlMarshaller {
    @SuppressWarnings("unchecked")
    public<T> T unmarshal(String xml, Class<T> cls) {
        try (var reader = new StringReader(xml)) {
            JAXBContext jaxbContext = JAXBContext.newInstance(cls);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (T)jaxbUnmarshaller.unmarshal(reader);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
