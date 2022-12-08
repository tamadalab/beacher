package com.github.tamadalab.beacher;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DefaultFormatter implements Formatter {

    public void printEach(int index, BuildTool aBuildTool) {
        return;
    }

    public void printDefFooter() {
        return;
    }

    public void printDefHeader() {
        return;
    }

    public void printFooter() {
        return;
    }

    public void printHeader(Path base) {
        System.out.printf("%s%n", base.toString());
    }

    public void printEach(BuildTool result) {
        System.out.print("printEachOkDefault");
        System.out.printf("%s: %s%n", result.path.toString(), result.def.name);
    }

    public void printDef(BuildToolDef def) {
        System.out.printf("%s: ", def.name);
        System.out.println(String.join(", ", def.buildFiles));
    }

    public void print(Path target, List<BuildTool> result) {
        System.out.println("printOk");
        this.printHeader(target);
        for (BuildTool aBuildTool : result) {
            System.out.println("forOkDefault");
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