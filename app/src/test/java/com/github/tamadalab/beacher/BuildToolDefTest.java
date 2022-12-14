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
        List<BuildToolDef> defs = aBuildToolDef.parse(Path.of("./src/main/resources/buildtools.json"));
        assertTrue(defs.size() == 24);
    }

    @Test
    public void testParseOther() throws Exception
    {
        BuildToolDef aBuildToolDef = new BuildToolDef();
        List<BuildToolDef> defs = aBuildToolDef.parse(Path.of("../testdata/append_def.json"));
        assertTrue(defs.size() == 2);
    }

    @Test
    public void testParseFromAsset() throws Exception
    {
        BuildToolDef aBuildToolDef = new BuildToolDef();
        List<BuildToolDef> defs = aBuildToolDef.parseFromAsset();
        assertTrue(defs.size() == 24);
    }
}
