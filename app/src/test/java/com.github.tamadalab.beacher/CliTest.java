package com.github.tamadalab.beacher;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;

public class CliTest {

    @Test
    void validate() throws Exception{
        Cli aCli = new Cli();
        //aCli.projectList = (".");
        aCli.dirs.add(Path.of("."));
        aCli.validate();
    }
}
