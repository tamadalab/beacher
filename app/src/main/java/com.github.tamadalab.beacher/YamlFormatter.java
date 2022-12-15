package com.github.tamadalab.beacher;

import java.nio.file.Path;
import java.util.List;

public class YamlFormatter implements Formatter {

    public void printEach(int index, BuildTool aBuildTool) {

    }
    
    public void printDefFooter() {

    }

    public void printFooter() {

    }

    public Path printHeader(Path base) {
        System.out.println("base: " + base.toString());
        return base;
    }

    public void printEach(BuildTool result) {
        System.out.println("  - file-path: " + result.path.toString());
        System.out.println("    tool-name: " + result.def.name);
    }

    public void printDefHeader() {
        System.out.println("build-tools-defs");
    }

    public void printDef(BuildToolDef def) {
        int index = 0;
        System.out.println("  - name: " + def.name);
        System.out.println("    url: " + def.url);
        System.out.println("    file-names:");

        for (String afileName : def.buildFiles) {
            this.printFileName(index, afileName);
            index++;
        }
    }

    public void printFileName(int index, String afileName) {
        if (index == 0) {
            System.out.print("      -");
        } else {
            System.out.print("       ");
        }
        System.out.println(afileName);
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