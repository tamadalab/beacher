package com.github.tamadalab.beacher;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DefaultFormatter implements Formatter {
    public void printDefFooter() {
        return;
    }

    public void printDefHeader() {
        return;
    }

    public void printFooter() {
        return;
    }

    public void printEach(BuildTool aBuildTool) {

    }

    public void printHeader(Path target) {

    }

    public void printHeader(BuildToolDef def, Path base) {
        System.out.printf("%s%n", base.toString());
    }

    public void printEach(Path base, BuildTool result) {
        System.out.printf("%s: %s%n", base.toString(), result.def.name);
    }

    public void printDef(BuildToolDef def) {
        System.out.printf("%s: ", def.name);
        System.out.println(String.join(", ", def.buildFiles));
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