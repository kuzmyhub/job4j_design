package ru.job4j.io;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test (expected = IllegalArgumentException.class)
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Petr Arsentev"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenPairWithoutKey() {
        String path = "./data/pair_without_key.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenPairWithoutValue() {
        String path = "./data/pair_without_value.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenPairWithoutKeyAndValue() {
        String path = "./data/pair_without_key_and_value.properties";
        Config config = new Config(path);
        config.load();
    }
}