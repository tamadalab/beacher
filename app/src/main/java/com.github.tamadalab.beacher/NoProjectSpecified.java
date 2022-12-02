/*
 * 例外処理をしています。
 */
package example;

public class NoProjectSpecified extends Exception{
    public NoProjectSpecified(){
        super("no projects are specified");
    }
}
