package com.github.tamadalab.beacher;
/*
 * BuildToolの構成
 */

import java.nio.file.Path;

public class BuildTool {
    Path path;
    BuildToolDef def;

    public BuildTool(Path path, BuildToolDef def)
    {
        this.path = path;
        this.def = def;
    }
}