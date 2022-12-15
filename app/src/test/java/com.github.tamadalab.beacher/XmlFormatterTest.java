package com.github.tamadalab.beacher;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class XmlFormatterTest {
    @Test
    public void testPrintHeader() {
        XmlFormatter xml = new XmlFormatter();
        Path base = Path.of("/code/java/file/report.txt");
        Path sb = Path.of("/code/java/file/report.txt");
        assertEquals(sb, xml.printHeader(base));
    }

    @Test
    public void testPrintEach() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        XmlFormatter xml = new XmlFormatter();
        BuildToolDef def = new BuildToolDef();
        def.name = "gradle";
        Path path = Path.of("/code/java/file/report.txt");
        BuildTool result = new BuildTool(path,def);
        xml.printEach(result);
        assertEquals("<build-tool><file-path>/code/java/file/report.txt</file-path><tool-name>gradle</tool-name></build-tool>",
                out.toString());
    }

    @Test
    public void testPrintFooter() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        XmlFormatter xml = new XmlFormatter();
        xml.printFooter();
        assertEquals("</build-tools>\n", out.toString());
    }

    @Test
    public void testPrintDefHeader() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        XmlFormatter xml = new XmlFormatter();
        xml.printDefHeader();
        assertEquals("<?xml version=\"1.0\"?>\n<build-tool-defs>", out.toString());
    }

    @Test
    public void testPrintDefFooter() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        XmlFormatter xml = new XmlFormatter();
        xml.printDefFooter();
        assertEquals("</build-tool-defs>\n", out.toString());
    }

    @Test
    public void testPrintDef() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        XmlFormatter xml = new XmlFormatter();
        BuildToolDef def = new BuildToolDef();
        def.name = "Apache Ant";
        def.url = "https://ant.apache.org/";
        def.buildFiles = new ArrayList<>();
        def.buildFiles.add("build.xml");
        def.buildFiles.add("ivy.xml");
        xml.printDef(def);
    }
    @Test
    public void testPrint(){
        XmlFormatter xml = new XmlFormatter();
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
        xml.print(target,result);
        //カバレッジ実行の結果正しく実行できている
    }

    @Test
    public void testPrintDefs(){
        XmlFormatter xml = new XmlFormatter();
        BuildToolDef def = new BuildToolDef();
        List<BuildToolDef> defs = new ArrayList<>();
        def.name = "Apache Ant";
        def.url = "https://ant.apache.org/";
        def.buildFiles = new ArrayList<>();
        def.buildFiles.add("build.xml");
        defs.add(def);
        xml.printDefs(defs);
    }
}
