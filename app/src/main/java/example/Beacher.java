package example;

import java.util.List;
import java.util.ArrayList;
import java.nio.file.Path;

public class Beacher extends BuildToodDef
{
    public List<BuildToodDef> merge_build_tools(List<BuildToodDef> first, List<BuildToodDef> second)
    {
        List<BuildToodDef> result = new ArrayList<BuildToodDef>();
        for (BuildToodDef item : first) result.add(item);
        for (BuildToodDef item : second) result.add(item);

        return result;
    }

    public List<BuildToodDef> Construct(Path defs,Path append)
    {
        List<BuildToodDef> def = new ArrayList<BuildToodDef>();
        if(!(defs==null)) def = parse(defs);
        else def = parse_from_asset();

        List<BuildToodDef> result = new ArrayList<BuildToodDef>();
        if (!(defs==null))
        {
            List<BuildToodDef> additional_defs = parse(append);
            result = merge_build_tools(def, additional_defs);
        }
        else
        {
            result = def;
        }

        return result;
    }


}
