package ru.job4j.serialization.xml;

import com.sun.xml.bind.v2.runtime.JAXBContextImpl;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement (name = "office")
@XmlAccessorType
public class Office {

    @XmlAttribute
    private boolean isEmpty;

    @XmlAttribute
    private int staff;

    @XmlElement(name = "officeNumber")
    private OfficeNumber officeNumber;

    @XmlElementWrapper(name = "whose")
    @XmlElement(name = "who")
    private String[] whose;

    public Office() {
    }

    public Office(boolean isEmpty, int staff,
                  OfficeNumber officeNumber, String[] whose) {
        this.isEmpty = isEmpty;
        this.staff = staff;
        this.officeNumber = officeNumber;
        this.whose = whose;
    }

    @Override
    public String toString() {
        return "Office{"
                + "isEmpty=" + isEmpty
                + ", staff=" + staff
                + ", officeNumber=" + officeNumber
                + ", who=" + Arrays.toString(whose)
                + '}';
    }
}
