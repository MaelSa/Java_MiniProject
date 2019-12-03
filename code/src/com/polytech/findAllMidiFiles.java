package com.polytech;
import java.io.File;
import java.util.ArrayList;

public class findAllMidiFiles {
    public static void main(String args[]){
        String result = "";
        result = midiFilesString(new File("code"), "");
        System.out.println(result);
    }

    public static String midiFilesString(File file,String result){
        if(file.isDirectory()){
            File[] temp = file.listFiles();
            if(temp!=null)
                for(File f:temp)
                    find(f,result);
        }
        else if(file.isFile()){
            if("mid".equals(file.getName().substring(file.getName().lastIndexOf(".")+1).toLowerCase())){
                System.out.println(file.getName().substring(0,file.getName().length()-4));
                result += file.getName().substring(0,file.getName().length()-4);
                result += "\n";
                return result;
            }
        }
        return result;
    }

    public static ArrayList<String> midiFilesArrayList(File file, ArrayList<String> result){
        if(file.isDirectory()){
            File[] temp = file.listFiles();
            if(temp!=null)
                for(File f:temp)
                    midiFilesArrayList(f,result);
        }
        else if(file.isFile()){
            if("mid".equals(file.getName().substring(file.getName().lastIndexOf(".")+1).toLowerCase())){
                System.out.println(file.getName().substring(0,file.getName().length()-4));
                result.add(file.getName().substring(0,file.getName().length()-4));
                return result;
            }
        }
        return result;
    }
}
