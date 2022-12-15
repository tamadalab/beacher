package com.github.tamadalab.beacher;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
/*BuildToolについての問題
  XmlFormatter以外の出力の問題
  拡張for分についての問題
 */
public class DefaultFormatterTest {
    @Test
    public void testPrintHeader() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        DefaultFormatter defa = new DefaultFormatter();
        Path base = Path.of("code/java/file/report.txt");
        defa.printHeader(base);
        assertEquals("code/java/file/report.txt\n", out.toString());
    }

    @Test
    public void testPrintEach() {
        DefaultFormatter defa = new DefaultFormatter();
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
        defa.printEach(build);
    }

    @Test
    public void testPrintDef() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        DefaultFormatter defa = new DefaultFormatter();
        BuildToolDef def = new BuildToolDef();
        ArrayList<String> buildfile = new ArrayList<>();
        def.name = "Apache Ant";
        def.buildFiles = new ArrayList<>();
        def.buildFiles.add("build.xml");
        def.buildFiles.add("ivy.xml");
        defa.printDef(def);
        //buildToolDefのコメントアウトを解除すれば可能
    }

    @Test
    public void printTest() {
        DefaultFormatter yaml = new DefaultFormatter();
        Path target = Path.of("/code/java/file/report.txt");
        List<BuildTool> result = new ArrayList<>();
        BuildToolDef def = new BuildToolDef();
        BuildTool build = new BuildTool(target, def);
        def.name = "Apache Ant";
        def.url = "https://ant.apache.org/";
        def.buildFiles = new ArrayList<>();
        def.buildFiles.add("build.xml");
        def.buildFiles.add("ivy.xml");
        result.add(build);
        yaml.print(target, result);
        //カバレッジ実行の結果正しく実行できている
    }

    @Test
    public void printDefsTest() {
        DefaultFormatter defa = new DefaultFormatter();
        BuildToolDef def = new BuildToolDef();
        List<BuildToolDef> defs = new ArrayList<>();
        def.name = "Apache Ant";
        def.url = "https://ant.apache.org/";
        def.buildFiles = new ArrayList<>();
        def.buildFiles.add("build.xml");
        defs.add(def);
        defa.printDefs(defs);
    }
}
