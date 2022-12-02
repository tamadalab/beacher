package com.github.tamadalab.beacher;

import java.util.List;
import java.util.ArrayList;

import java.nio.file.Path;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Beacher extends BuildToolDef
{
    public List<BuildToolDef> mergeBuildTools(List<BuildToolDef> first, List<BuildToolDef> second)
    {
        List<BuildToolDef> result = new ArrayList<BuildToolDef>();
        for (BuildToolDef item : first)
        {
            result.add(item);
        }
        for (BuildToolDef item : second)
        {
            result.add(item);
        }

        return result;
    }

    public List<BuildToolDef> construct(Path defs,Path append) throws FileNotFoundException,IOException
    {
        List<BuildToolDef> def = new ArrayList<BuildToolDef>();
        if(defs!=null)
        {
            def = super.parse(defs);
        }
        else
        {
            def = super.parseFromAsset();
        }

        List<BuildToolDef> result = new ArrayList<BuildToolDef>();
        if (append!=null)
        {
            List<BuildToolDef> additionalDefs = super.parse(append);
            result = this.mergeBuildTools(def, additionalDefs);
        }
        else
        {
            result = def;
        }

        return result;
    }

}
