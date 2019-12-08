package com.polytech;

import javafx.util.Pair;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * Class to store the data for most played song and most active player
 */
public class GeneralData implements java.io.Serializable{
    private HashMap<String, Integer> players_info;
    private HashMap<String, Integer> songs_info;

    /**
     * Constructor inializing empty data lists
     */
    public GeneralData(){
        players_info = new HashMap<String, Integer>();
        songs_info = new HashMap<String, Integer>();
    }

    /**
     * Adds the songname to the datas, with a number of played time equals to 1
     * @param songname name of the added song
     */
    public void add_song(String songname){
        this.songs_info.put(songname, 1);
    }

    /**
     * Adds the players name to the data, with a number of played songs equals to 1
     * @param player name of the added player
     */
    public void add_player(String player){

        this.players_info.put(player, 1);
    }

    /**
     * If the player is already in the data,
     * it increments the number of songs they played, otherwise it adds
     * the player's name to the data. Same goes for the songs.
     * @param song the name of the song
     * @param player the name of the player
     */
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
    }

    /**
     * Gets the most played song in the data
     * @return the name of the most played song
     */
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

    /**
     * Gets the most active player in the data
     * @return the name of the most active player
     */
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


