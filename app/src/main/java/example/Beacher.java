package example;

import java.util.List;
import java.util.ArrayList;
import java.nio.file.Path;

public class Beacher extends BuildToolDef
{
    public List<BuildToolDef> merge_build_tools(List<BuildToolDef> first, List<BuildToolDef> second)
    {
        List<BuildToolDef> result = new ArrayList<BuildToolDef>();
        for (BuildToolDef item : first) result.add(item);
        for (BuildToolDef item : second) result.add(item);

        return result;
    }

    public List<BuildToolDef> Construct(Path defs,Path append)
    {
        List<BuildToolDef> def = new ArrayList<BuildToolDef>();
        if(!(defs==null)) def = parse(defs);
        else def = parse_from_asset();

        List<BuildToolDef> result = new ArrayList<BuildToolDef>();
        if (!(defs==null))
        {
            List<BuildToolDef> additional_defs = parse(append);
            result = merge_build_tools(def, additional_defs);
        }
        else
        {
            result = def;
        }

        return result;
    }


}
