package com.github.tamadalab.beacher;

import org.junit.jupiter.api.Test;
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
