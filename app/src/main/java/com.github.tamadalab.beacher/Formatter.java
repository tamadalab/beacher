package com.github.tamadalab.beacher;

import java.util.List;
import java.nio.file.Path;

public interface Formatter {

    void printEach(BuildTool aBuildTool);

    void printEach(int index, BuildTool aBuildTool);

    Path printHeader(Path target);

    void printFooter();

    void printDef(BuildToolDef aBuildTooldef);

    void printDefHeader();

    void printDefFooter();

    void print(Path target, List<BuildTool> result);

    void printDefs(List<BuildToolDef> defs);

    default Formatter build(Format aFormat) {
        Formatter aFormatter = new DefaultFormatter();
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