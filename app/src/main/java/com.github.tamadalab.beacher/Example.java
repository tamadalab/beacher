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
                    if(Objects.equals(extractFileName(target), buildfile))
                    {
                        
                        return def;
                    }
                }
            }
        }
        return null;
    }

    public List<BuildTool> findBuildTools(Path target, List<BuildToolDef> defs,  boolean noIgnore) throws IllegalArgumentException, IOException
    {
        List<BuildTool> buildTools = new ArrayList<>();

        if(target.toFile().isFile()) throw new IllegalArgumentException();

        File afile = target.toFile();
        File[] targets = afile.listFiles();
        for(File aTarget : targets)
        {
            if(aTarget.isDirectory())
            {
                buildTools.addAll(findBuildTools(aTarget.toPath(), defs, noIgnore));

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


    public List<BuildTool> performEach(Path target, List<BuildToolDef> defs, boolean no_ignore) throws ProjectNotFound, IOException
    {
        List<BuildTool> result = new ArrayList<BuildTool>();
        if(!target.toFile().exists())
        {
            throw new ProjectNotFound(target.toString());
        }
        else
        {
            try
            {
               result = findBuildTools(target, defs, no_ignore);
            }
            catch(IllegalArgumentException error)
            {
                System.out.println(target+": is not Directory.");
            }
            
        }
        return result;
    }

    public void printer(Cli opts, List<BuildToolDef> defs, Path target, List<BuildTool> result)
    {
        Formatter aFormatter = new DefaultFormatter();
        aFormatter = aFormatter.build(opts.format);
        if(opts.listDefs)
        {
            aFormatter.printDefs(defs);
        }
        else
        {
            aFormatter.print(target, result);
        }
    }
    public void defsPrinter(Cli opts, List<BuildToolDef> defs)
    {
        Formatter aFormatter = new DefaultFormatter();
        aFormatter = aFormatter.build(opts.format);
        aFormatter.printDefs(defs);
    }
    public void connectOutput(Path target, List<BuildToolDef> defs, Cli opts) throws IOException
    {
        List<BuildTool> result = null;
        if (target != null)
        {
            try
            {
                result = this.performEach(target, defs, opts.noIgnore);
            }
            catch (ProjectNotFound error)
            {
                System.out.println(error.getMessage());
            }
        }
        if(result != null) this.printer(opts, defs, target, result);
    }
    public void extractTarget(List<Path> targets, List<BuildToolDef> defs, Cli opts) throws IOException
    {
        for(Path target : targets)
        {
            if(target.equals(Path.of("")));
            else this.connectOutput(target, defs, opts);
        }
    }

    private List<BuildToolDef> readDefinitionsFile(Beacher aBeacher, Cli opts) throws IOException
    {
        try
        {
            return aBeacher.construct(opts.definition, opts.appendDefs);
        }
        catch(FileNotFoundException error)
        { // beacher.javaからの例外処理
            throw new InternalError(" buildtools.json is not Found.");
        }
    }

    public void perform(Cli opts) throws IOException
    {
        Beacher aBeacher = new Beacher();
        List<BuildToolDef> defs = readDefinitionsFile(aBeacher, opts);
        List<Path> targets = this.parseTargets(opts.projectList, opts.dirs);

        if(targets.isEmpty() && opts.listDefs) this.defsPrinter(opts, defs);
        this.extractTarget(targets, defs, opts);
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
