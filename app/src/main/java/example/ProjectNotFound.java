/*
 * 例外処理をしています。
 */
package example;

public class ProjectNotFound extends Exception{
    public ProjectNotFound(String msg){
        super(msg + ": project directory not found");
    }
}