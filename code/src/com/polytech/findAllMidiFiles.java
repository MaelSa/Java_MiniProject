package com.polytech;

import java.io.File;
import java.lang.reflect.Field;

public class findAllMidiFiles {
    public static void main(String args[]){
        find(new File("C:\\Users\\11437\\javaMiniProject\\Java_MiniProject\\code"));
    }
    public static void find(File file){

        if(file.isDirectory()){
            File[] temp = file.listFiles();
            if(temp!=null)
                for(File f:temp)
                    find(f);
        }
        if(file.isFile()){
            if("mid".equals(file.getName().substring(file.getName().lastIndexOf(".")+1).toLowerCase())){
//                String s = file
                System.out.println(file.getName().substring(0,file.getName().length()-4));
            }
        }
    }

}
