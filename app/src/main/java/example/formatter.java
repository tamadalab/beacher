package Beacher;

import java.awt;
import java.util.function.UnaryOperator;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

public interface Formatter {

    List<String> DefaultFormatter = new ArrayList<>();
    List<String> JsonFormatter = new ArrayList<>();
    List<String> XmlFormatter = new ArrayList<>();
    List<String> YamlFormatter = new ArrayList<>();
    List<integer> Item = new ArrayList<>();
    List Print_Head = new ArrayList<>();
    List<BuildTooldef> def = new ArrayList<>();
    // List <BuildTooldefs> defs = new ArrayList<>();

    public String print_header;
    public String print_defs_header;
    public String print_footer;
    public String print_defs_footer;
    public String print_def;
    public String print_each;
    public int item;
    public Path base;// インスタンスいる

    default String Item() {
        if (item == 0) {
            item = 0;
        } else {
            item = 1;
        }
        return print_each;
    }

    default String Match() {
        if (Format == Json) {
            return JsonFormatter;
        } else if (Format == Default) {
            return DefaultFormatter;
        } else if (Format == Xml) {
            return XmlFormatter;
        } else if (Format == Yaml) {
            return YamlFormatter;
        }
    }
}

class DefaultFormatter implements Formatter {

    public void print_header(Path base, BuildTooldef def) {
        System.out.println("%s"this.base);// thisのところにもらってきたクラス名を入れると動くはず
    }

    public void print_each(Path base, BuildTooldef def) {
            System.out.println("%s:" this.base.display, this.def.name);//いーち
        }

    public void print_def(Path base, BuildTooldef def) {
            System.out.println("%s: %s" this.def.name, this.def.build_files.join(","));//def
        }
}

class JsonFormatter implements Formatter {

    public void print_header(Path base) {
        System.out.println("base:%s,build-tools:[", base.display()).unwrap();// header
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
        System.out.println("<build-tools><base>%s</base>", base.display);
    }

    public void print_footer() {
        System.out.println("</build-tools>");// footer
    }

    public void print_each(BuildTooldef def){
             System.out.println("<build-tool><file-path>%s</file-path><tool-name>%s</tool-name></build-tool>"result.path.display(),def.name);//each
        }

    public void def_header() {
        System.out.println("<?xml version=\"1.0\"?>");// def_header
        System.out.println("<build-tool-defs>");
    }

    public void def_footer() {
        System.out.println("</build-tool-defs>");// def_fotter
    }

    public void print_def(BuildTooldef def) {
        System.out.println("<build-tool-def><name>%s</name><url>%s</url><build-files>", def.name, def.url);// print_def
    }
}

class YamlFormatter implements Formatter {

    public void print_header(Path base) {
        System.out.println("base: %s", base.display);// print_header
    }

    public void print_each(Path base, BuildTooldef def) {
        System.out.println("  - file-path: %s", result.path.display());// print_each
        System.out.println("    tool-name: %s", result.def.name);
    }

    public void print_def_header() {
        System.out.println("build-tools-defs");// print_def_header
    }

    public void print_def(BuildTooldef def) {
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
