package com.github.tamadalab.beacher;

import java.nio.file.Path;
import java.util.List;

public class JsonFormatter implements Formatter {

    public void printEach(BuildTool result) {
    }

    public void printEach(int index, BuildTool result) {
        if(index > 0) {
            System.out.print(",");
        }
        System.out.printf("{\"file-path\":\"%s\",\"tool-name\":\"%s\"}",result.path.toString(),result.def.name);
    }

    public Path printHeader(Path base) {
        System.out.printf("{\"base\":\"%s\",\"build-tools\":[", base.toString());
        return base;
    }

    public void printFooter() {
        System.out.println("]}");
    }

    public void printDefHeader() {
        System.out.print("[");
    }

    public void printDefFooter() {
        System.out.println("]");
    }

    public void printDef(BuildToolDef def) {
        System.out.printf("{\"name\":\"%s\",\"url\":\"%s\",\"build-files\":[", def.name, def.url);
        for (String afileName : def.buildFiles) {
            System.out.printf("\"%s\"", afileName);
        }
        System.out.print("]},");
    }

    public void print(Path target, List<BuildTool> result) {
        this.printHeader(target);
        int index=0;
        for (BuildTool aBuildTool : result) {
            this.printEach(index,aBuildTool);
            index++;
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