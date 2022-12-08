package com.github.tamadalab.beacher;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class XmlFormatter implements Formatter {

    public void printEach(int index, BuildTool aBuildTool) {
        return;
    }

    public void printHeader(Path base) {
        System.out.println("<?xml version=\"1.0\"?>");
        System.out.printf("<build-tools><base>%s</base>%n", base.toString());
    }

    public void printFooter() {
        System.out.println("</build-tools>");
    }

    public void printEach(BuildTool result) {
        System.out.printf("<build-tool><file-path>%s</file-path><tool-name>%s</tool-name></build-tool>",
                result.path.toString(), result.def.name);
    }

    public void printDefHeader() {
        System.out.println("<?xml version=\"1.0\"?>");
        System.out.print("<build-tool-defs>");
    }

    public void printDefFooter() {
        System.out.println("</build-tool-defs>");
    }

    public void printDef(BuildToolDef def) {
        System.out.printf("<build-tool-def><name>%s</name><url>%s</url><build-files>", def.name, def.url);
        for (String afileName : def.buildFiles) {
            System.out.printf("<file-name>%s</file-name>", afileName);
        }
        System.out.print("</build-files></build-tool-def>");
    }

    public void print(Path target, List<BuildTool> result) {
        this.printHeader(target);
        for (BuildTool aBuildTool : result) {
            this.printEach(aBuildTool);
        }
        this.printFooter();
    }

    public void printDefs(List<BuildToolDef> defs) {
        this.printDefHeader();
        for (BuildToolDef aBuildToolDef : defs) {
            this.printDef(aBuildToolDef);
        }
        this.printDefFooter();
    }

}