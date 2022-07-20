package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.StringReader;
import java.io.StringWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        final Office office = new Office(false, 2,
                new OfficeNumber("I-110"),
                new String[] {"Zhenya, Sasha"});
        JAXBContext context = JAXBContext.newInstance(Office.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(office, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }

        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Office result = (Office) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
