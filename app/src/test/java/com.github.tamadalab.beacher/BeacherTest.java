package com.github.tamadalab.beacher;

import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BeacherTest
{
    @Test
    public void testConstruct() throws Exception
    {
        Beacher aBeacher = new Beacher();
        BuildToolDef aBuildToolDef = new BuildToolDef();

        List<BuildToolDef> constructDefs = aBeacher.construct(Path.of("../defs/buildtools_sample.json"), Path.of("../defs/buildtools_append_sample.json"));
        
        assertTrue(constructDefs.size() == 24);
    }
}
