package com.github.tamadalab.beacher;

import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BeacherTest
{
    Beacher aBeacher = new Beacher();
    BuildToolDef aBuildToolDef = new BuildToolDef();

    @Test
    public void testMergeConstruct() throws Exception
    { 
        List<BuildToolDef> constructDefs = aBeacher.construct(Path.of("../defs/buildtools.json"), Path.of("../testdata/append_def.json"));
        assertTrue(constructDefs.size() == 26);
    }

    @Test
    public void tsetAssetConstruct() throws Exception
    {
        List<BuildToolDef> constructDefs = aBeacher.construct(Path.of("../defs/buildtools.json"), null);
        assertTrue(constructDefs.size() == 24);
    }

    // @Test
    // public void testAppedConstruct() throws Exception
    // {
    //     List<BuildToolDef> constructDefs = aBeacher.construct(null, Path.of("../testdata/append_def.json"));
    //     assertTrue(constructDefs.size() == 2);
    // }
}
