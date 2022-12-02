package com.github.tamadalab.beacher;

import example.Format;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Path;
import java.util.EnumSet;
//import java.util.StringJoiner;

public interface Formatter {

    void printEach(BuildTool aBuildTool);

    void printHeader(Path target);

    void printFooter();

    void printDef(BuildToolDef aBuildTooldef);

    void printDefHeader();

    void printDefFooter();

    void print(Path target, List<BuildTool> result);

    /*
     * this.printHeader(target);
     * for(BuildTool aBuildTool : result){
     * this.printEach(aBuildTool);
     * }
     * this.printFooter();
     */

    void printDefs(List<BuildTooldef> defs);

    /*
     * this.printDefHeader();
     * for(BuildToolDef aBuildToolDef: defs){
     * this.printDef(aBuildToolDef);
     * }
     * this.printDefFooter();
     */

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