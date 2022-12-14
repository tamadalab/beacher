package com.github.tamadalab.beacher;

import java.util.List;

import javax.imageio.IIOException;

import java.util.ArrayList;

import java.nio.file.Path;
import java.io.File;
import java.net.URL;
import java.io.IOException;
import java.io.FileNotFoundException;

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

    public List<BuildToolDef> parseFromAsset() throws FileNotFoundException,IOException
    {
        ObjectMapper objectMapper = new ObjectMapper();
        URL aFileUrl = getClass().getClassLoader().getResource("buildtools.json");
        return objectMapper.readValue(aFileUrl, new TypeReference<List<BuildToolDef>>(){});
    }
}