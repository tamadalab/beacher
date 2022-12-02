/*
 * BuildToolの構成
 */
package com.github.tamadalab.beacher;

import java.nio.file.Path;
import example.BuildToolDef;

public class BuildTool {
    Path path; //PathBuff
    BuildToolDef def;

    public BuildTool(Path path, BuildToolDef def)
    {
        this.path = path;
        this.def = def;
    }
}

