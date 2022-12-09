package com.github.tamadalab.beacher;

import java.nio.file.Path;
import java.util.Formatter;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExampleTest {
    @Test
    public void testReadBuildToolDefs() throws Exception {
        Beacher beacher = new Beacher();
        List<BuildToolDef> defs = beacher.construct(Path.of("../defs/buildtools.json"), null);
        // assertEquals(21, defs.size());
        assertTrue(defs.size() > 0);
    }

    @Test
    public void testFindBuildFiles() throws Exception {
        Beacher beacher = new Beacher();
        Example example = new Example();

        var defs = beacher.construct(Path.of("../defs/buildtools.json"), null);
        example.performEach(Path.of("."), defs, false);
    }
    @Test
    public void testBuildFormatter() throws Exception {
        Formatter formatter = new Formatter();
        Cli opts = new Cli();
        Example example = new Example();
        Beacher beacher = new Beacher();
        var defs = beacher.construct(Path.of("../defs/buildtools.json"), null);
        var result = example.performEach(Path.of("."), defs, false);
        example.definitionPrint(opts, defs, Path.of("."), result);
    }
}