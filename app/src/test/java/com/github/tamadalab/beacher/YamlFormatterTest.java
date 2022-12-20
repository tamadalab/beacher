package com.github.tamadalab.beacher;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class YamlFormatterTest {
    @Test
    public void printHeaderTest() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        YamlFormatter yaml = new YamlFormatter();
        Path base = Path.of("/code/java/file/report.txt");
        yaml.printHeader(base);
    }

    @Test
    public void printEachTest() throws IOException{
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        YamlFormatter yaml = new YamlFormatter();
        BuildToolDef def = new BuildToolDef();
        Path base = Path.of("/code/java/file/report.txt");
        def.name = "Apache Ant";
        BuildTool result = new BuildTool(base,def);
        yaml.printEach(result);
        assertEquals("  - file-path: /code/java/file/report.txt\n    tool-name: Apache Ant\n", out.toString());
    }



    @Test
    public void printDefHeaderTest() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        YamlFormatter yaml = new YamlFormatter();
        yaml.printDefHeader();
        assertEquals("build-tools-defs\n", out.toString());
    }

    @Test
    public void printDefTest() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        YamlFormatter yaml = new YamlFormatter();
        BuildToolDef def = new BuildToolDef();
        def.name = "Apache Ant";
        def.url = "https://ant.apache.org/";
        def.buildFiles = new ArrayList<>();
        def.buildFiles.add("build.xml");
        def.buildFiles.add("ivy.xml");
        yaml.printDef(def);
        assertEquals("  - name: Apache Ant\n    url: https://ant.apache.org/\n    file-names:\n      -build.xml\n       ivy.xml\n", out.toString());
        //カバレッジ実行の結果printFuileNameも正しく実行できている
    }
    @Test
    public void printTest(){
        YamlFormatter yaml = new YamlFormatter();
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
        yaml.print(target,result);
        //カバレッジ実行の結果正しく実行できている
    }
    @Test
    public void printDefsTest(){
        YamlFormatter yaml = new YamlFormatter();
        BuildToolDef def = new BuildToolDef();
        List<BuildToolDef> defs = new ArrayList<>();
        def.name = "Apache Ant";
        def.url = "https://ant.apache.org/";
        def.buildFiles = new ArrayList<>();
        def.buildFiles.add("build.xml");
        defs.add(def);
        yaml.printDefs(defs);
    }
}
