package com.github.tamadalab.beacher;
/*
 * BuildToolの構成
 */

import java.nio.file.Path;
import com.github.tamadalab.beacher.BuildToolDef;

public class BuildTool {
    Path path; //PathBuff
    BuildToolDef def;

    public BuildTool(Path path, BuildToolDef def)
    {
        this.path = path;
        this.def = def;
    }
}