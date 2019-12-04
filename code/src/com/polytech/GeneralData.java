package com.polytech;

import javafx.util.Pair;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class GeneralData implements java.io.Serializable{
    private HashMap<String, Integer> players_info;
    private HashMap<String, Integer> songs_info;

    public GeneralData(){
        players_info = new HashMap<String, Integer>();
        songs_info = new HashMap<String, Integer>();
    }

    public void add_song(String songname){
        //int size = songname.length();
        //String clean_name = songname.substring(size - 5, size - 1);
        this.songs_info.put(songname, 1);
        //System.out.println("Song" + clean_name + " added to song info");
    }

    public void add_player(String player){

        this.players_info.put(player, 1);
    }

    public void update_song_player(String song, String player){
        String clean_song_name = song.substring(song.length() -5, song.length() - 1);
        if (!this.players_info.containsKey(player)){
            add_player(player);
        }
        else{
            this.players_info.put(player,this.players_info.get(player) + 1);
        }
        if (!this.songs_info.containsKey(song)){
            add_song(song);
        }
        else{
            this.songs_info.put(song, this.songs_info.get(song) + 1);
        }
        //find a way to update both hashtables
    }

    public ArrayList<String> getMostPlayedSong(){
        ArrayList<String> pair = new ArrayList<String>();
        Integer maxPlayedNumber = 0;
        String maxPlayedSong = "";
        String key;
        Integer value;
        for (Map.Entry<String, Integer> entry : this.songs_info.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
            if(value > maxPlayedNumber){
                maxPlayedNumber = value;
                maxPlayedSong = key;
            }
            }
        pair.add(maxPlayedSong);
        pair.add(maxPlayedNumber.toString());
        return pair;
        }

    public ArrayList<String> getMostActivePlayer(){
        ArrayList<String> pair = new ArrayList<String>();
        Integer maxPlayedNumber = 0;
        String maxPlayername = "";
        String key;
        Integer value;
        for (Map.Entry<String, Integer> entry : this.players_info.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
            if(value > maxPlayedNumber){
                maxPlayedNumber = value;
                maxPlayername = key;
            }
        }
        pair.add(maxPlayername);
        pair.add(maxPlayedNumber.toString());
        return pair;
    }
    }


