package com.github.tamadalab.beacher;

import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BuildToolDefTest
{
    @Test
    public void testParse() throws Exception
    {
        BuildToolDef aBuildToolDef = new BuildToolDef();
        List<BuildToolDef> defs = aBuildToolDef.parse(Path.of("../defs/buildtools.json"));
        assertTrue(defs.size() == 24);
        //assertTrue(defs.size() == 0);
    }

    @Test
    public void testDefinition() throws Exception
    {
        BuildToolDef aBuildToolDef = new BuildToolDef();
        List<BuildToolDef> defs = aBuildToolDef.parse(Path.of("../defs/buildtools_sample.json"));
        assertTrue(defs.size() == 12);
        //assertTrue(defs.size() == 0);
    }

    // @Test
    // public void testParseFromAsset() throws Exception
    // {
    //     BuildToolDef aBuildToolDef = new BuildToolDef();
    //     List<BuildToolDef> defs = aBuildToolDef.parseFromAsset();
    //     assertTrue(defs.size() == 24);
    // }
}
