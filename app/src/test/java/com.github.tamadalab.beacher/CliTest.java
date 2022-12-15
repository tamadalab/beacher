package com.github.tamadalab.beacher;

import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CliTest {
    @Test
    void validateTest() throws BothTargetSpecified, NoProjectSpecified{
        Cli cli = new Cli();
        List<Path> adirs = new ArrayList<>();
        adirs.add(Path.of("."));
        cli.dirs = adirs;
        cli.validate();
    }
}
