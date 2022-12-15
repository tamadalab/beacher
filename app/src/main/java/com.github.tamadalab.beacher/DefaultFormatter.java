package com.github.tamadalab.beacher;

import java.nio.file.Path;
import java.util.List;

public class DefaultFormatter implements Formatter {

    public void printEach(int index, BuildTool aBuildTool) {

    }

    public void printDefFooter() {

    }

    public void printDefHeader() {

    }

    public void printFooter() {

    }

    public Path printHeader(Path base) {
        System.out.printf("%s%n", base.toString());
        return base;
    }

    public void printEach(BuildTool result) {
        System.out.printf("  %s: %s%n", result.path.toString(), result.def.name);
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