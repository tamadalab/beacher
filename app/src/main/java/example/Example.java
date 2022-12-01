package src;
/**
 * beacherのmain部分を作成します.
 */

import java.nio.file;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.IllegalArgumentException;
import java.util.ArrayList;
import java.util.List;

import static BuildToolsDef.*;
import picocli.CommandLine;
import example.Cli;
import static example.Format.*;


// public class BuildTool
// {
//     Path path; //PathBuff
//     BuildToolDef def;

//     public BuildTool(Path path, BuildToolDef def)
//     {
//         this.path = path;
//         this.def = def;
//     }
// }

public class Example extends Object
{  
    public BufferedReader openImpl(String file) throws IOEexception 
    { // できた
        if(Objects.equals(file, "_"))
        {
            return new BufferedReader(new InputStreamReader(System.in));
        }
        return new BufferedReader(new FileReader(file));
    }
    public List<Path> parseProjectList(String list_file)
    { // できた
        BufferedReader file = openImpl(list_file);
        List<Path> lines = new ArrayList<Path>();
        String aString = null;
        while((aString = file.readline()) != null)
        {
            lines.add((Path)aString);
        }
        return lines;
    }
    public List<Path> parseTargets(String project_list, List<Path> dirs)
    { // できた 
        if(project_list != null)
        {
            return this.parseProjectList(project_list);
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
            return (String)target.getFileName();
        }
        return null;
    }
    public BuildToolDef findBuildToolsImpl(Path target, List<BuildToolDef> defs)
    { // できた
        if(extractFileName(target) != null)
        {
            for(BuildToolDef def : defs)
            {
                for(String buildfile : def.build_files)
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
    public List<BuildTool> findBuildTools(Path target, List<BuildToolDef> defs,  boolean no_ignore) throws IllegalArgumentException, IOEexception
    { // エラー処理がいる？
        List<BuildTool> buildTools = new ArrayList<>();

        // 例外
        if(target.isFile()) throw new IllegalArgumentException();

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
            throw new ProjectNotFound((String)target); // エラー処理(ProjectNotFound)
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
        List<BuildToolDef> defs = beacher.construct(opts.definition, opts.append_defs); //beacherのconstructへ

        Formatter aFormatter = Formatter.build(opts.format);
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
    public void run() // できた
    {
        Cli opts = new Cli();
        // if(opts.format == Json){
        //     // System.out.println("OK");
        // }
        // this.opts = ; // cliから受け取る
        // opts.validate = opts.validate();
        opts.validate();
        // List<String> dest = new BufferedWriter(new OutputStreamWriter(System.out)); // listで再現?
        this.perfrom(opts);
    }
    public static void main(String... arguments) // できた
    {
        int exitCode = new CommandLine(new Cli()).execute(arguments);
        System.exit(exitCode);

    }
}

