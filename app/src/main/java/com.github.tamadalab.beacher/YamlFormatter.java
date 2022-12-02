//package com.github.tamadalab.beacher;
package example;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class YamlFormatter implements Formatter {
    public void printDefFooter() {
        return;
    }

    public void printDef(BuildToolDef aBuildTooldef) {
        return;
    }

    public void printFooter() {
        return;
    }

    public void printEach(BuildTool aBuildTool) {
        return;
    }

    public void printHeader(Path base) {
        System.out.printf("base: %s%n", base.toString());// printHeader
    }

    public void printEach(Path base, BuildToolDef def, BuildTool result) {
        System.out.printf("  - file-path: %s", result.path.toString());// printEach
        System.out.println("    tool-name: %s", def.name);
    }

    public void printDefHeader() {
        System.out.println("build-tools-defs");// printDefHeader
    }

    public void printDef(BuildToolDef def, Path target) {
        int index = 0;
        System.out.println("  - name: %s", def.name);// printDef
        System.out.println("    url: %s", def.url);
        System.out.println("    file-names:");

        for (BuildToolDef aBuildTooldef : def) {
            this.printFileName(index, aBuildTooldef.BuildFiles);// filenameが何かわからない、indexはこれで増えるのか
            index++;
        }
    }

    public void printFileName() {
        int index;
        String fileName;
        if (index == 0) {
            System.out.print("      -");
        } else {
            System.out.print("       ");
        }
        System.out.printf("%s%n", fileName);
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