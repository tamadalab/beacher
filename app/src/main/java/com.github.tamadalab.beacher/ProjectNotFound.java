/*
 * 例外処理をしています。
 */
package com.github.tamadalab.beacher;

public class ProjectNotFound extends Exception{
    public ProjectNotFound(String msg){
        super(msg + ": project directory not found");
    }
}