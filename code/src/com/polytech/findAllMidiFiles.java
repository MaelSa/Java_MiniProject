package com.polytech;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class findAllMidiFiles {
    public static void main(String args[]){
        String result = "";
        String r2;
        List<String> r3 = new ArrayList<String>();
        ArrayList<String> ree = new ArrayList<String>();
        ree = midiFilesArrayList(new File("code"), ree);
        //System.out.println(ree);
        int j = 0;
        while(j < ree.size()){
            result += ree.get(j) + "\n";
            j ++;
        }
        //System.out.println(result);
        String str[] = result.split("\n");
        r3 = Arrays.asList(str);
        System.out.println(r3);
        //List<String> al = new ArrayList<String>();
        //al = Arrays.asList(str);
        //System.out.println(al);

    }

    public static String midiFilesString(){
        String resultString = "";
        ArrayList<String> resultList = new ArrayList<String>();
        resultList = midiFilesArrayList(new File("code"), resultList);
        int j = 0;
        while (j < resultList.size()) {
            resultString += resultList.get(j) + "\n";
            j ++;
        }
        return resultString;
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
                //System.out.println(file.getName().substring(0,file.getName().length()-4));
                result.add(file.getName().substring(0,file.getName().length()-4));
                return result;
            }
        }
        return result;
    }
}
