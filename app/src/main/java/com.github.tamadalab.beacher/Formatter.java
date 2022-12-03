package com.github.tamadalab.beacher;

import example.Format;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Path;
import java.util.EnumSet;

public interface Formatter {

    void printEach(BuildTool aBuildTool);

    void printHeader(Path target);

    void printFooter();

    void printDef(BuildToolDef aBuildTooldef);

    void printDefHeader();

    void printDefFooter();

    void print(Path target, List<BuildTool> result);

    void printDefs(List<BuildTooldef> defs);

    default Formatter build(Format aFormat) {
        Formatter aFormatter;
        switch (aFormat) {
            case Json:
                aFormatter = new JsonFormatter();
                break;
            case Default:
                aFormatter = new DefaultFormatter();
                break;
            case Xml:
                aFormatter = new XmlFormatter();
                break;
            case Yaml:
                aFormatter = new YamlFormatter();
                break;
        }
        return aFormatter;
    }
}