package com.github.tamadalab.beacher;

import java.nio.file.Path;
import java.util.Formatter;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExampleTest {
    @Test
    public void testReadBuildToolDefs() throws Exception
    { // defsが正しく受け取れているかどうか
        Beacher beacher = new Beacher();
        List<BuildToolDef> defs = beacher.construct(Path.of("../defs/buildtools.json"), null);
        assertTrue(defs.size() > 0);
    }

    @Test
    public void testFindBuildFiles() throws Exception
    { // performEachが正確に動作するかどうか
        Cli opts = new Cli();
        Example example = new Example();
        Beacher beacher = new Beacher();
        var defs = beacher.construct(Path.of("../defs/buildtools.json"), null);
        example.performEach(Path.of("."), defs, false);
    }
    @Test
    public void testDefinitionPrint() throws Exception
    {
        Cli opts = new Cli();
        Example example = new Example();
        Beacher beacher = new Beacher();
        var defs = beacher.construct(Path.of("../defs/buildtools.json"), null);
        var result = example.performEach(Path.of("."), defs, false);
        example.definitionPrint(opts, defs, Path.of("."), result);
    }
}
