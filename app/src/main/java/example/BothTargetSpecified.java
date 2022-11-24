/*
 * 例外処理をしています。
 */
package example;

public class BothTargetSpecified extends Exception{
    public BothTargetSpecified(){
        super("both project list and directories are specified");
    }
}