package com.github.tamadalab.beacher;

import org.junit.Test;

import java.nio.file.Path;
import java.util.List;

import static org.junit.Assert.*;

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
        example.performEach(Path.of("."), defs, false, null);
    }
}
