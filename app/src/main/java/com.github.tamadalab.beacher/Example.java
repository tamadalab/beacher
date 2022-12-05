package com.github.tamadalab.beacher;
/**
 * beacherのmain部分.
 */

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.IllegalArgumentException;
import java.lang.InternalError;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.github.tamadalab.beacher.Beacher;
import com.github.tamadalab.beacher.BothTargetSpecified;
import com.github.tamadalab.beacher.BuildTool;
import com.github.tamadalab.beacher.BuildToolDef;
import com.github.tamadalab.beacher.Cli;
import com.github.tamadalab.beacher.Formatter;
import com.github.tamadalab.beacher.NoProjectSpecified;
import com.github.tamadalab.beacher.ProjectNotFound;
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
        if(extractFileName(target) != null)
        {
            for(BuildToolDef def : defs)
            {
                for(String buildfile : def.buildFiles)
                {
                    if(extractFileName(target) == buildfile)
                    {
                        return def;
                    }
                }
            }
        }
        return null;
    }

    public List<BuildTool> findBuildTools(Path target, List<BuildToolDef> defs,  boolean no_ignore) throws IllegalArgumentException, IOException
    { // できた
        List<BuildTool> buildTools = new ArrayList<>();

        if(target.toFile().isFile()) throw new IllegalArgumentException(); // 例外処理(IllegalArgumentException)

        File[] targets = target.toFile().listFiles();
        for(File aTarget : targets)
        {
            if(aTarget.isDirectory())
            {
                // 再帰bildtoolsとbuildtoolsは合わせる
                buildTools.addAll(findBuildTools(aTarget.toPath(), defs, no_ignore));

            }
            else
            {
                BuildToolDef def = findBuildToolsImpl(aTarget.toPath(), defs);
                if(def != null)
                {
                    buildTools.add(new BuildTool(aTarget.toPath(), def));
                }
            }
        }
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