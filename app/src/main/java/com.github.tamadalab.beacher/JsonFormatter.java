package com.github.tamadalab.beacher;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class JsonFormatter implements Formatter {

    public void printEach(BuildTool result) {
        System.out.print("Ok");
    }

    public void printEach(int index, BuildTool result) {
        if(index > 0) {
            System.out.print(",");
        }
        System.out.printf("{\"file-path\":\"%s\",\"tool-name\":\"%s\"}",result.path.toString(),result.def.name);
        System.out.print("printEachOk");
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
        for (String afileName : def.buildFiles) {
            System.out.printf("<file-name>%s</file-name>", def);
        }
        System.out.println("</build-files></build-tool-def>");
    }

    public void print(Path target, List<BuildTool> result) {
        this.printHeader(target);
        int index=0;
        // if(result.isEmpty())
        // {
        //     System.out.println("reslut is null");
        // }
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