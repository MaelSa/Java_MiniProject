package com.polytech;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TestGeneralData {
    public void first() throws Exception{
        GeneralData generalData = new GeneralData();
        generalData.add_player("Mael");
        //generalData.getMostActivePlayer();
        FileOutputStream fileOutputStream = new FileOutputStream("code/generalData.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(generalData);
        objectOutputStream.flush();
        objectOutputStream.close();

    }
    public void second() throws Exception{
        GeneralData generalData;
        FileInputStream fileInputStream = new FileInputStream("code/generalData.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        generalData = (GeneralData) objectInputStream.readObject();
        objectInputStream.close();
        generalData.getMostActivePlayer();
    }

    public static void main(String[] args) throws Exception{
        TestGeneralData testGeneralData = new TestGeneralData();
        testGeneralData.first();
        testGeneralData.second();

    }
}
