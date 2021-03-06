package ru.job4j.serialization.json;

public class URLAddress {
    private final String address;

    public URLAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "URLAddress{"
                + "address='" + address + '\''
                + '}';
    }
}
