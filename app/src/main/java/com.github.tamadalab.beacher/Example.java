package com.github.tamadalab.beacher;
/**
 * beacherのmain部分.
 */

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.IllegalArgumentException;
import java.lang.InternalError;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import picocli.CommandLine;


public class Example extends Object
{  
    public BufferedReader openImpl(String file) throws IOException 
    {
        if(Objects.equals(file, "-"))
        {
            return new BufferedReader(new InputStreamReader(System.in));
        }
        return new BufferedReader(new FileReader(file));
    }

    public List<Path> parseProjectList(String listFile) throws IOException
    {
        BufferedReader file = openImpl(listFile);
        List<Path> lines = new ArrayList<Path>();
        String aString = null;
        while((aString = file.readLine()) != null)
        {
            lines.add(Paths.get(aString));
        }
        return lines;
    }
    
    public List<Path> parseTargets(String projectList, List<Path> dirs) throws IOException
    {
        if(projectList != null)
        {
            return this.parseProjectList(projectList);
        }
        else
        {
            return dirs;
        }
    }

    public String extractFileName(Path target)
    {
        if(target.getFileName() != null)
        {
            return target.getFileName().toString();
        }
        return null;
    }

    public BuildToolDef findBuildToolsImpl(Path target, List<BuildToolDef> defs)
    {
        if(defs.isEmpty()) System.out.println("defs is empty");
        if(extractFileName(target) != null)
        {
            System.out.println("implIfOk");
            for(BuildToolDef def : defs)
            {
                for(String buildfile : def.buildFiles)
                {
                    if(Objects.equals(extractFileName(target),buildfile))
                    {
                        
                        return def;
                    }
                }
            }
        }
        System.out.println("implnull");
        return null;
    }

    public List<BuildTool> findBuildTools(Path target, List<BuildToolDef> defs,  boolean no_ignore) throws IllegalArgumentException, IOException
    {
        List<BuildTool> buildTools = new ArrayList<>();

        if(target.toFile().isFile()) throw new IllegalArgumentException(); // 例外処理(IllegalArgumentException)
        //System.out.println(target.toString());
        File afile = target.toFile();
        File[] targets = afile.listFiles();
        //System.out.println(targets);
        // if(targets.length == 0){
        //     System.out.println("targets is empty");
        // }
        for(File aTarget : targets)
        {
            if(aTarget.isDirectory())
            {
                //System.out.println("ifOk");
                // 再帰bildtoolsとbuildtoolsは合わせる
                buildTools.addAll(findBuildTools(aTarget.toPath(), defs, no_ignore));

            }
            else
            {
                //System.out.println("elseOk");
                BuildToolDef def = findBuildToolsImpl(aTarget.toPath(), defs);
                Path p = Path.of("/code/java/file/report.txt");
                BuildTool result = new BuildTool(p,def);
                if(def != null)
                {
                    System.out.println("elseOk");
                    buildTools.add(new BuildTool(aTarget.toPath(), def));
                }
            }
        }

        //System.out.println(buildTools);
        return buildTools;
    }

    public void performEach(Path target, List<BuildToolDef> defs, boolean no_ignore, Formatter aFormatter) throws ProjectNotFound, IOException
    {
        if(!target.toFile().exists())
        {
            throw new ProjectNotFound(target.toString()); // 例外処理(ProjectNotFound)
        }
        else
        {
            try
            {
                List<BuildTool> result = findBuildTools(target, defs, no_ignore);
                aFormatter.print(target, result);
            }
            catch(IllegalArgumentException error)
            { // findBuildToolsからの例外処理
                System.out.println(target+": is not Directory.");
            }
            
        }
    }

    public void perform(Cli opts) throws IOException
    {
        Beacher aBeacher = new Beacher();
        List<BuildToolDef> defs = new ArrayList<>();

        try
        {
            defs = aBeacher.construct(opts.definition, opts.append_defs); //beacherのconstructへ
        }
        catch(FileNotFoundException error)
        { // beacher.javaからの例外処理
            throw new InternalError(" buildtools.json is not Found.");
        }
        Formatter aFormatter = new DefaultFormatter();
        aFormatter = aFormatter.build(opts.format);
        if(opts.list_defs)
        {
            aFormatter.printDefs(defs);
        }
        List<Path> targets = this.parseTargets(opts.project_list, opts.dirs);
        if(targets == null) return;
        for(Path target : targets)
        {
            try
            {
                this.performEach(target, defs, opts.no_ignore, aFormatter);
            }
            catch(ProjectNotFound error)
            {
                System.out.println(error.getMessage());
            }
        }
        return;
    }

    public void run(Cli opts)
    {
        try
        {
            opts.validate();
            this.perform(opts);
        }
        catch(BothTargetSpecified error)
        { // Cliからの例外処理
            System.out.println(error.getMessage());
        }
        catch(NoProjectSpecified error)
        { // Cliからの例外処理
            System.out.println(error.getMessage());
        }
        catch(IOException error)
        {
            error.printStackTrace();
        }
    }

    public static void main(String... arguments) throws IOException
    {
        int exitCode = new CommandLine(new Cli()).execute(arguments);
        System.exit(exitCode);

    }
}