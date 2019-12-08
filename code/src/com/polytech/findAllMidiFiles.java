package com.polytech;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class to find the midi files in the "code" folder
 */
public class findAllMidiFiles {


    /**
     * Returns a string containing the name of all the midi files in the "code" folder
     * @return
     */
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

    /**
     * Returns an array with the name of all the midi files in th "code" folder
     * @param file the folder
     * @param result lists of midi files names
     * @return list of midi files names
     */
    public static ArrayList<String> midiFilesArrayList(File file, ArrayList<String> result){
        if(file.isDirectory()){
            File[] temp = file.listFiles();
            if(temp!=null)
                for(File f:temp)
                    midiFilesArrayList(f,result);
        }
        else if(file.isFile()){
            if("mid".equals(file.getName().substring(file.getName().lastIndexOf(".")+1).toLowerCase())){
                result.add(file.getName().substring(0,file.getName().length()-4));
                return result;
            }
        }
        return result;
    }
}
