package com.github.tamadalab.beacher;

import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class FormatterTest {
    @Test
    public void Testbuild() throws Exception{
        Formatter formatter = new DefaultFormatter();
        formatter.build(Format.Json);
        formatter.build(Format.Default);
        formatter.build(Format.Xml);
        formatter.build(Format.Yaml);
    }
}
