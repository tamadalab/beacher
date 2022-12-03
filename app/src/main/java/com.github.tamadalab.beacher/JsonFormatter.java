package com.github.tamadalab.beacher;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class JsonFormatter implements Formatter {

    public void printEach(BuildTool aBuildTool) {

    }

    public void printHeader(Path base) {
        System.out.printf("base:%s,build-tools:[%n", base.toString());
    }

    public void printFooter() {
        System.out.println("]}}");
    }

    public void printDefHeader() {
        System.out.println("[");
    }

    public void printDefFooter() {
        System.out.println("]");
    }

    public void printDef(BuildToolDef def) {
        System.out.printf("<build-tool-def><name>%s</name><url>%s</url><build-files>", def.name, def.url);
        for (BuildToolDef aBuildToolDef : def) {
            System.out.printf("<file-name>%s</file-name>", def);
        }
        System.out.println("</build-files></build-tool-def>");
    }

    public void print(Path target, List<BuildTool> result) {
        this.printHeader(target);
        for (BuildTool aBuildTool : result) {
            this.printEach(aBuildTool);
        }
        this.printFooter();
    }

    public void printDefs(List<BuildTooldef> defs) {
        this.printDefHeader();
        for (BuildToolDef aBuildToolDef : defs) {
            this.printDef(aBuildToolDef);
        }
        this.printDefFooter();
    }
}