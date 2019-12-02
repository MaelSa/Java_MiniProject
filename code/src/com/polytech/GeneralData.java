package com.polytech;

import java.util.Hashtable;

public class GeneralData implements java.io.Serializable{
    private Hashtable<String, Integer> players_info;
    private Hashtable<String, Integer> songs_info;

    public GeneralData(){
        players_info = new Hashtable<String, Integer>();
        songs_info = new Hashtable<String, Integer>();
    }

    public void add_song(String songname){
        int size = songname.length();
        String clean_name = songname.substring(size - 5, size - 1);
        this.songs_info.put(clean_name, 0);
        System.out.println("Song" + clean_name + " added to song info");
    }

    public void add_player(String player){
        this.players_info.put(player, 0);
    }

    public void update_song_player(String song, String player){
        String clean_song_name = song.substring(song.length() -5, song.length() - 1);
        if (!this.players_info.contains(player)){
            add_player(player);
        }
        if (!this.songs_info.contains(song)){
            add_song(song);
        }
        //find a way to update both hashtables
    }


}
