package Beacher;

import java.awt;
import java.util.function.UnaryOperator;

interface Formatter {
    ArrayList<String> DefaultFormatter = new ArrayList<>();
    ArrayList<String> JsonFormatter = new ArrayList<>();
    ArrayList<String> XmlFormatter = new ArrayList<>();
    ArrayList<String> YamlFormatter = new ArrayList<>();

    public String print_header;
    public String print_defs_header;
    public String print_footer;
    public String print_defs_footer;
    public String print_def;
    public String print_each;

}

class DefaultFormatter implements Formatter {

    public void print_header() {
            System.out.println("%s" base.display);//ヘッダー
        }

    public void print_each() {
            System.out.println("%s:" base.display, def.name);//いーち
        }

    public void print_def() {
            System.out.println("%s: %s" def.name, def.build_files.join(","));//def
        }
}

class JsonFormatter implements Formatter {

    public void print_header() {
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
    public void print_header() {
        System.out.println("<?xml version=\"1.0\"?>");// header
        System.out.println("<build-tools><base>%s</base>", base.display);
    }

    public void print_footer() {
        System.out.println("</build-tools>");// footer
    }

    public void print_each(){
             System.out.println("<build-tool><file-path>%s</file-path><tool-name>%s</tool-name></build-tool>"result.path.display(),def.name);//each
        }

    public void def_header() {
        System.out.println("<?xml version=\"1.0\"?>");// def_header
        System.out.println("<build-tool-defs>");
    }

    public void def_footer() {
        System.out.println("</build-tool-defs>");// def_fotter
    }

    public void print_def() {
        System.out.println("<build-tool-def><name>%s</name><url>%s</url><build-files>", def.name, def.url);// print_def
    }
}

class YamlFormatter implements Formatter {

    public void print_header() {
        System.out.println("base: %s", base.display);// print_header
    }

    public void print_each() {
        System.out.println("  - file-path: %s", result.path.display());// print_each
        System.out.println("    tool-name: %s", result.def.name);
    }

    public void print_def_header() {
        System.out.println("build-tools-defs");// print_def_header
    }

    public void print_def() {
        System.out.println("  - name: %s", def.name);// print_def
        System.out.println("    url: %s", def.url);
        System.out.println("    file-names:");
        // ここからeachのやつ使う
    }
}

public class Main(){
 switch(
        case  JsonFormatter;
        return Jsonformatter;
        break;
        case Default;
        return DefaultFormatter;
        break;

       )

}
