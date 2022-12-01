package example;
/**
 * beacherのmain部分を作成します.
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
import java.lang.IllegalArgumentException;
import java.util.ArrayList;
import java.util.List;
// import java.util.logging.Formatter;
import java.util.Objects;

import example.BuildTool;
import example.Beacher;
import example.BuildToolDef;
import example.Cli;
import example.Formatter;
import example.ProjectNotFound;
import picocli.CommandLine;


public class Example extends Object
{  
    public BufferedReader openImpl(String file) throws IOException 
    { // できた
        if(Objects.equals(file, "_"))
        {
            return new BufferedReader(new InputStreamReader(System.in));
        }
        return new BufferedReader(new FileReader(file));
    }
    public List<Path> parseProjectList(String listFile)
    { // できた
        BufferedReader file = openImpl(listFile);
        List<Path> lines = new ArrayList<Path>();
        String aString = null;
        while((aString = file.readLine()) != null)
        {
            lines.add(Paths.get(aString));
        }
        return lines;
    }
    public List<Path> parseTargets(String projectList, List<Path> dirs)
    { // できた 
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
    { // できた
        if(target.getFileName() != null)
        {
            return target.getFileName().toString();
        }
        return null;
    }
    public BuildToolDef findBuildToolsImpl(Path target, List<BuildToolDef> defs)
    { // できた
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
    { // エラー処理がいる？
        List<BuildTool> buildTools = new ArrayList<>();

        // 例外
        if(target.toFile().isFile()) throw new IllegalArgumentException();

        File[] targets = target.toFile().listFiles();
        for(File aTarget : targets) // target内のファイルを調べていく targetの中身のファイルまでのPath取り出して
        {
            if(aTarget.isDirectory()) // 
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
    public void performEach(Path target, List<BuildToolDef> defs, boolean no_ignore, Formatter aFormatter) throws ProjectNotFound
    { // エラーまだ
        if(!target.toFile().exists()) // targetがなければ...
        {
            throw new ProjectNotFound(target.toString()); // エラー処理(ProjectNotFound)
        }
        else
        {
            try
            {
                List<BuildTool> result = findBuildTools(target, defs, no_ignore);
                aFormatter.print(target, result);
            }
            catch(IllegalArgumentException e)
            {
                System.out.println("ディレクトリではなくファイルが渡されました.");
            }
            
        }
        

    }
    public Integer perform(Cli opts)
    {
        Beacher aBeacher;
        List<BuildToolDef> defs = aBeacher.construct(opts.definition, opts.append_defs); //beacherのconstructへ

        Formatter aFormatter;
        aFormatter = aFormatter.build(opts.format);
        if(opts.list_defs) // があれば
        {
            aFormatter.print_defs(defs);
        }
        List<Path> targets = this.parseTargets(opts.project_list, opts.dirs);
        for(Path target : targets)    // targetsの要素をtargetとしてループ
        {
            // if(this.performEach(target, defs, opts.no_ignore, aFormatter))
            // {}

            this.performEach(target, defs, opts.no_ignore, aFormatter);
        }
        return 0;

    }
    public void run(Cli opts) // できた
    {
        opts.validate();
        this.perform(opts);
    }
    public static void main(String... arguments) // できた
    {
        int exitCode = new CommandLine(new Cli()).execute(arguments);
        System.exit(exitCode);

    }
}

