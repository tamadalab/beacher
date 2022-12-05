/*
 * beacherのコマンドラインのオプションのためのソースコードです。
 */
package com.github.tamadalab.beacher;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.nio.file.Path;
import java.util.List;
import java.lang.Runnable;
import java.io.IOException;

@Command(name = "beacher", mixinStandardHelpOptions = true, version = "beacher 0.1",
         description = "A tool for detecting build tools of the projects")
public class Cli implements Runnable{

    @Option(names = "-@", paramLabel = "INPUT", description = "Specify the file contains project path list. If INPUT is dash ('-'), read from STDIN.")
    String project_list;

    @Option(names = "--append-defs", paramLabel = "DEFS_JSON", description = "Specify the additional definitions of the build tools.")
    Path append_defs;

    @Option(names = {"-d","--definition"}, paramLabel = "DEFS_JSON", description = "Specify the definition of the build tools.")
    Path definition;

    @Option(names = {"-f","--format"}, paramLabel = "FORMAT", description = "Specify the output format [default: Default] [possible values: Default, Json, Xml, Yaml]")
    Format format = Format.Default;

    @Option(names = {"-L","--list-defs"}, description = "Print the build tools' definition list")
    boolean list_defs;

    @Option(names = "--no-ignore", description = "Do not respect ignore files (.ignore, .gitignore, etc.)")
    boolean no_ignore;

    @Parameters(paramLabel = "PROJECTs", description = "The target project directories for beacher.")
    List<Path> dirs;

    public void run(){
        new Example().run(this);
    }

    public void validate() throws BothTargetSpecified, NoProjectSpecified{
        if(project_list != null && !dirs.isEmpty()){
            throw new BothTargetSpecified();
        }
        else if(!list_defs && project_list == null && dirs.isEmpty()){
            throw new NoProjectSpecified();
        }
        return;
    }
}