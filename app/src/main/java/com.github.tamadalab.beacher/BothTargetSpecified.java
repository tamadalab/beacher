/*
 * 例外処理をしています。
 */
package com.github.tamadalab.beacher;

public class BothTargetSpecified extends Exception{
    public BothTargetSpecified(){
        super("both project list and directories are specified");
    }
}
/* 
catch(BothTargetSpecified e){
    System.out.println(e.getMessage());
}
*/