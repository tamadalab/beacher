package com.github.tamadalab.beacher;

import java.util.List;
import java.util.ArrayList;

import java.nio.file.Path;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.IOError;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BuildToolDef
{
    public String name;

    @JsonProperty("build-files")
    public List<String> buildFiles;

    public String url;

    public List<BuildToolDef> parse(Path path)
    {
        try
        {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(new File(path.toString()), new TypeReference<List<BuildToolDef>>(){});
        }
        catch (IOException anException)
        {
            anException.printStackTrace();
            return new ArrayList<>();
        }

    }

    public List<BuildToolDef> parseFromAsset()
    {
        try
        {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(new File("defs/buildtools.json"),new TypeReference<List<BuildToolDef>>(){});
        }
        catch (FileNotFoundException anException)
        {
            System.out.println("buildtools.json: file not found");
            return new ArrayList<>();
        }
        catch (IOException anException)
        {
            anException.printStackTrace();
            return new ArrayList<>();
        }
    }

}
    