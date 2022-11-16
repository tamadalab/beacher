package example;

import java.util.List;
import java.util.ArrayList;
import java.nio.file.Path;

import java.io.File;
import java.io.IOException;
 

import com.fasterxml.jackson.databind.ObjectMapper;



public class BuildToolDef
{
    public String name;
    public List<String> build_files;
    public String url;


    public List<BuildToolDef> parse(Path path)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        File buildtools = new File(path.toString());

        return objectMapper.readValue(buildtools, new TypeReference<List<BuildToolDef>>());

    }

    public List<BuildToolDef> parse_from_asset()
    {

    }
}