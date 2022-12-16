/*
 * 例外処理をしています。
 */
package com.github.tamadalab.beacher;

public class NoProjectSpecified extends Exception{
    public NoProjectSpecified(){
        super("no projects are specified");
    }
}