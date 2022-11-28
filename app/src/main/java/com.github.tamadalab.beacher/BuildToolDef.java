package com.github.tamadalab.beacher;

import java.util.List;
import java.util.ArrayList;

import java.nio.file.Path;
import java.io.File;
import java.io.IOException;
 
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BuildToolDef
{
    public String name;

    @JsonProperty("build-files")
    public List<String> buildFiles;

    public String url;

    public List<BuildToolDef> parse(Path path) throws IOException
    {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(path.toString()), new TypeReference<List<BuildToolDef>>(){});
    }

    public List<BuildToolDef> parseFromAsset()
    {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File("defs/buildtools.json"),new TypeReference<List<BuildToolDef>>(){});
    }
}