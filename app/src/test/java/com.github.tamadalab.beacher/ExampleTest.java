package com.github.tamadalab.beacher;

import java.nio.file.Path;
import java.util.Formatter;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExampleTest {
    @Test
    public void testReadBuildToolDefs() throws Exception
    {
        Beacher beacher = new Beacher();
        List<BuildToolDef> defs = beacher.construct(Path.of("../defs/buildtools.json"), null);
        // assertEquals(21, defs.size());
        assertTrue(defs.size() > 0);
    }

    @Test
    public void testFindBuildFiles() throws Exception
    {
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
    @Test
    public void testParseTargets() throws Exception
    {
        Example example = new Example();
        Cli opts = new Cli();
        var targets = example.parseTargets(null, opts.dirs);
        example.extractTarget(targets);
    }
}