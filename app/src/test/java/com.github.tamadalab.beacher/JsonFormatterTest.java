package com.github.tamadalab.beacher;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonFormatterTest {
    @Test
    public void testPrintEachTest() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        JsonFormatter j = new JsonFormatter();
        BuildToolDef def = new BuildToolDef();
        int index = 1;
        def.name = "Apache Ant";
        Path path =Path.of("/code/java/file/report.txt");
        BuildTool result = new BuildTool(path,def);
        j.printEach(index,result);
        assertEquals(",{\"file-path\":\"/code/java/file/report.txt\",\"tool-name\":\"Apache Ant\"}", out.toString());
        //result.pathについてまだ
    }
    @Test
    public void testPrintFooter() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        JsonFormatter j = new JsonFormatter();
        j.printFooter();
        assertEquals("]}\n", out.toString());
    }
    @Test
    public void testPrintDefHeader() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        JsonFormatter j = new JsonFormatter();
        j.printDefHeader();
        assertEquals("[", out.toString());
    }
    @Test
    public void testPrintDefFooter() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        JsonFormatter j = new JsonFormatter();
        j.printDefFooter();
        assertEquals("]\n", out.toString());
    }
    @Test
    public void printTest(){
        JsonFormatter j = new JsonFormatter();
        Path target = Path.of("/code/java/file/report.txt");
        List<BuildTool> result = new ArrayList<>();
        BuildToolDef def = new BuildToolDef();
        BuildTool build = new BuildTool(target,def);
        def.name = "Apache Ant";
        def.url = "https://ant.apache.org/";
        def.buildFiles = new ArrayList<>();
        def.buildFiles.add("build.xml");
        def.buildFiles.add("ivy.xml");
        result.add(build);
        j.print(target,result);
        //カバレッジ実行の結果正しく実行できている
    }
    @Test
    public void printDefsTest(){
        JsonFormatter j = new JsonFormatter();
        BuildToolDef def = new BuildToolDef();
        List<BuildToolDef> defs = new ArrayList<>();
        def.name = "Apache Ant";
        def.url = "https://ant.apache.org/";
        def.buildFiles = new ArrayList<>();
        def.buildFiles.add("build.xml");
        defs.add(def);
        j.printDefs(defs);
    }

}

