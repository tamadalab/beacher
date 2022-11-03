package src;
//beatherのmain部分を作成します.

public class Example extends Object
{
    public static void BuildTool(){

    }
    public void open_impl()
    {

    }
    public void parse_project_list()
    {

    }
    public void parse_tagerts()
    {

    }
    public void extract_file_name()
    {

    }
    public void find_build_tools_impl()
    {
        
    }
    public void find_build_tools(target, defs, no-ignore)
    {
        Vector build_tools;
        for()
        {
            if()
            {
                if some_def = find_build_tools_impl(entry.path(), defs)
                {build_tools.push(BuildTool(entry.path()))}
            }
        }

    }
    public void perform_each(target, defs, no_ignore, formatter, dest)
    {
        if
        {}
        else
        {
            if this.find_build_tools(target, defs, no_ignore)
            {}
        }

    }
    public void perfrom(opts, dest)
    {
        defs = construct(opts_definition, opts_append_defs); //beacherのconstructへ
        formatter;
        if opts.list_defs // があれば?
        {
            formatter.print_defs(dest, defs);
        }
        else
        {
            targets = this.parse_tagerts(opts_project_list, opts_dirs);
            // 拡張for文 (target : targets)
            for(target : targets){
                //if　
                if err = this.perform_each(target, defs, opts_no_ignore, formatter, dest)
                {}
            }
        } 

    }
    public static void main(String... args)
    {
        opts; // List ?
        // if some_err = opts.validate()
            System.out.println("%s", some_err)
        dest; // 
        this.perfrom(opts, dest);
    }
}