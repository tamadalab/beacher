package Beacher;

import java.util.ArrayList;
import java.util.List;
import Cil.java;
import bteacher.java;
import java.nio.file.Path;

public interface formatter {

    List<String> DefaultFormatter = new ArrayList<>();
    List<String> JsonFormatter = new ArrayList<>();
    List<String> XmlFormatter = new ArrayList<>();
    List<String> YamlFormatter = new ArrayList<>();
    List<integer> Item = new ArrayList<>();
    List Print_Head = new ArrayList<>();
    List<BuildTooldef> def = new ArrayList<>();
    // List <BuildTooldefs> defs = new ArrayList<>();

    /*
     * public String print_header;
     * public String print_defs_header;
     * public String print_footer;
     * public String print_defs_footer;
     * public String print_def;
     * public String print_each;
     */
    /* public int item = 0;// インスタンスいる */

    /*
     * default String Item() {
     * if (item == 0) {
     * item = 0;
     * } else {
     * item = 1;
     * }
     * return print_each;
     * }
     */

    default Formatter build(Format aFormat) {
        if (aFormat == Format.Json) {
            return JsonFormatter;
        } else if (aFormat == Format.Default) {
            return DefaultFormatter;
        } else if (aFormat == Format.Xml) {
            return XmlFormatter;
        } else if (aFormat == Format.Yaml) {
            return YamlFormatter;
        }
    }
}

class DefaultFormatter implements Formatter {

    public void print_header(BuildTooldef def, Path base) {
        System.out.printf("%s%n", base.toString());// thisのところにもらってきたクラス名を入れると動くはず
    }

    public void print_each(Path base, BuildTool result) {
        System.out.printf("%s: %s%n", base.toString(), result.def.name);// いーち
    }

    public void print_def(Path base, BuildToolDef def) {
        System.out.printf("%s: %s%n", def.name, def.build_files.join(","));// def
    }
}

class JsonFormatter implements Formatter {

    public void print_header(Path base) {
        System.out.printf("base:%s,build-tools:[%n", base.toString());// header
    }

    public void print_footer() {
        System.out.println("]}}");// footer
    }

    public void print_def_header() {
        System.out.println("[");// def_header
    }

    public void print_def_footer() {
        System.out.println("]");// def_footer
    }
}

class XmlFormatter implements Formatter {
    public void print_header(Path base) {
        System.out.println("<?xml version=\"1.0\"?>");// header
        System.out.printf("<build-tools><base>%s</base>%n", base.toString());
    }

    public void print_footer() {
        System.out.println("</build-tools>");// footer
    }

    public void print_each(BuildTool result, BuildToolDef def) {
        System.out.println("<build-tool><file-path>%s</file-path><tool-name>%s</tool-name></build-tool>",
                result.path.toString, def.name);// each
    }

    public void def_header() {
        System.out.println("<?xml version=\"1.0\"?>");// def_header
        System.out.println("<build-tool-defs>");
    }

    public void def_footer() {
        System.out.println("</build-tool-defs>");// def_fotter
    }

    public void print_def(BuildToolDef def) {
        System.out.println("<build-tool-def><name>%s</name><url>%s</url><build-files>", def.name, def.url);// print_def
    }
}

class YamlFormatter implements Formatter {

    public void print_header(Path base) {
        System.out.printf("base: %s%n", base.toString());// print_header
    }

    public void print_each(Path base, BuildToolDef def, BuildTool result) {
        System.out.println("  - file-path: %s", result.path.toString());// print_each
        System.out.println("    tool-name: %s", def.name);
    }

    public void print_def_header() {
        System.out.println("build-tools-defs");// print_def_header
    }

    public void print_def(BuildToolDef def) {
        System.out.println("  - name: %s", def.name);// print_def
        System.out.println("    url: %s", def.url);
        System.out.println("    file-names:");
        // ここからeachのやつ使う
    }

    public void print_file_name() {
        int index;
        str file_name;
        if (index == 0) {
            System.out.print("      -");
        } else {
            System.out.print("       ");
        }
        System.out.println("%d", file_name);
    }

    public static void main(String[] args) {

        /*
         * public void Formatter(){
         * ArrayList Print_Head = new ArrayList<>();
         * int item;
         * if(item == 0){
         * item = 0;
         * }else{
         * item = 1;
         * }
         * return print_each;
         * }
         */
    }
}
