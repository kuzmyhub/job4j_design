package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "officeNumber")
public class OfficeNumber {

    @XmlAttribute
    private String number;

    public OfficeNumber() {
    }

    public OfficeNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "OfficeNumber{"
                + "number='" + number + '\''
                + '}';
    }
}
