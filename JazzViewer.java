/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jazzviewer;

/**
 *
 * @author Morgan
 */
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
import java.io.*;
import com.google.gson.*;

public class JazzViewer {
    
    //Pitcher Chords, based on their letters:
    public static final HashMap<String, Integer[]> PITCHER_CHORDS;
    
    
    static{
        PITCHER_CHORDS = new HashMap<>();
        
        PITCHER_CHORDS.put("a", new Integer[] {7, 10, 14, 17});
        PITCHER_CHORDS.put("b", new Integer[] {4, 7, 11, 14, 18});
        PITCHER_CHORDS.put("c", new Integer[] {0, 4, 7, 9, 14});
        PITCHER_CHORDS.put("d", new Integer[] {4, 7, 11, 18});
        PITCHER_CHORDS.put("e", new Integer[] {7, 11, 14, 17, 21});
        PITCHER_CHORDS.put("f", new Integer[] {10, 17});
        PITCHER_CHORDS.put("g", new Integer[] {11, 14, 17, 21});
        PITCHER_CHORDS.put("h", new Integer[] {7, 10, 17});
        PITCHER_CHORDS.put("i", new Integer[] {7, 12, 14, 17, 21});
        PITCHER_CHORDS.put("j", new Integer[] {0, 4, 7, 11, 18, 21});
        PITCHER_CHORDS.put("k", new Integer[] {4, 7, 11, 18, 21});
        PITCHER_CHORDS.put("l", new Integer[] {0, 4, 7, 11});
        PITCHER_CHORDS.put("m", new Integer[] {0, 4, 7, 11, 18});
        PITCHER_CHORDS.put("n", new Integer[] {0, 4, 7, 11, 14});
        PITCHER_CHORDS.put("o", new Integer[] {7, 12, 14, 17});
        PITCHER_CHORDS.put("p", new Integer[] {0, 4, 7, 11, 14, 18, 21});
        PITCHER_CHORDS.put("q", new Integer[] {4, 7, 11, 14, 18, 21});
        PITCHER_CHORDS.put("r", new Integer[] {0, 4, 7, 9});
        PITCHER_CHORDS.put("s", new Integer[] {4, 7, 9});
        PITCHER_CHORDS.put("t", new Integer[] {4, 7, 9, 2});
        PITCHER_CHORDS.put("u", new Integer[] {10, 14, 17});
        PITCHER_CHORDS.put("v", new Integer[] {0, 4, 7, 11, 14, 18});
        PITCHER_CHORDS.put("w", new Integer[] {7, 12, 14, 17, 21, 28});
        PITCHER_CHORDS.put("x", new Integer[] {7, 11, 17, 21});
        PITCHER_CHORDS.put("y", new Integer[] {7, 12, 14, 17, 28});
        PITCHER_CHORDS.put("z", new Integer[] {11, 17, 21});
        PITCHER_CHORDS.put("-", new Integer[] {7, 11, 17, 28});
    }
    
    //Batter scale degrees, based on the letters in their game events:
    public static final HashMap<String, Integer> BATTER_SCALEDEGREES;
    static{
        BATTER_SCALEDEGREES = new HashMap<>();
        
        BATTER_SCALEDEGREES.put("a",1);
        BATTER_SCALEDEGREES.put("b",2);
        BATTER_SCALEDEGREES.put("c",3);
        BATTER_SCALEDEGREES.put("d",4);
        BATTER_SCALEDEGREES.put("e",5);
        BATTER_SCALEDEGREES.put("f",6);
        BATTER_SCALEDEGREES.put("g",7);
        BATTER_SCALEDEGREES.put("h",8);
        BATTER_SCALEDEGREES.put("i",9);
        BATTER_SCALEDEGREES.put("j",10);
        BATTER_SCALEDEGREES.put("k",11);
        BATTER_SCALEDEGREES.put("l",12);
        BATTER_SCALEDEGREES.put("m",13);
        BATTER_SCALEDEGREES.put("n",14);
        BATTER_SCALEDEGREES.put("o",15);
        BATTER_SCALEDEGREES.put("p",16);
        BATTER_SCALEDEGREES.put("q",2);
        BATTER_SCALEDEGREES.put("r",3);
        BATTER_SCALEDEGREES.put("s",4);
        BATTER_SCALEDEGREES.put("t",5);
        BATTER_SCALEDEGREES.put("u",6);
        BATTER_SCALEDEGREES.put("v",7);
        BATTER_SCALEDEGREES.put("w",8);
        BATTER_SCALEDEGREES.put("x",9);
        BATTER_SCALEDEGREES.put("y",10);
        BATTER_SCALEDEGREES.put("z",11);
    }
    
    //For modulating the raw pitcher chords based on their team's key
    public static final HashMap<Integer, String> PITCHER_CHORDS_NOTES;
    static{
        PITCHER_CHORDS_NOTES = new HashMap<>();
        
        PITCHER_CHORDS_NOTES.put(0,"C2");
        PITCHER_CHORDS_NOTES.put(1,"C#2");
        PITCHER_CHORDS_NOTES.put(2,"D2");
        PITCHER_CHORDS_NOTES.put(3,"D#2");
        PITCHER_CHORDS_NOTES.put(4,"E2");
        PITCHER_CHORDS_NOTES.put(5,"F2");
        PITCHER_CHORDS_NOTES.put(6,"F#2");
        PITCHER_CHORDS_NOTES.put(7,"G2");
        PITCHER_CHORDS_NOTES.put(8,"G#2");
        PITCHER_CHORDS_NOTES.put(9,"A2");
        PITCHER_CHORDS_NOTES.put(10,"A#2");
        PITCHER_CHORDS_NOTES.put(11,"B2");
        PITCHER_CHORDS_NOTES.put(12,"C3");
        PITCHER_CHORDS_NOTES.put(13,"C#3");
        PITCHER_CHORDS_NOTES.put(14,"D3");
        PITCHER_CHORDS_NOTES.put(15,"D#3");
        PITCHER_CHORDS_NOTES.put(16,"E3");
        PITCHER_CHORDS_NOTES.put(17,"F3");
        PITCHER_CHORDS_NOTES.put(18,"F#3");
        PITCHER_CHORDS_NOTES.put(19,"G3");
        PITCHER_CHORDS_NOTES.put(20,"G#3");
        PITCHER_CHORDS_NOTES.put(21,"A3");
        PITCHER_CHORDS_NOTES.put(22,"A#3");
        PITCHER_CHORDS_NOTES.put(23,"B3");
        PITCHER_CHORDS_NOTES.put(24,"C4");
        PITCHER_CHORDS_NOTES.put(25,"C#4");
        PITCHER_CHORDS_NOTES.put(26,"D4");
        PITCHER_CHORDS_NOTES.put(27,"D#4");
        PITCHER_CHORDS_NOTES.put(28,"E4");
        PITCHER_CHORDS_NOTES.put(29,"F4");
        PITCHER_CHORDS_NOTES.put(30,"F#4");
        PITCHER_CHORDS_NOTES.put(31,"G4");
        PITCHER_CHORDS_NOTES.put(32,"G#4");
        PITCHER_CHORDS_NOTES.put(33,"A4");
        PITCHER_CHORDS_NOTES.put(34,"A#4");
        PITCHER_CHORDS_NOTES.put(35,"B4");
        PITCHER_CHORDS_NOTES.put(36,"C5");
        PITCHER_CHORDS_NOTES.put(37,"C#5");
        PITCHER_CHORDS_NOTES.put(38,"D5");
        PITCHER_CHORDS_NOTES.put(39,"D#5");
        PITCHER_CHORDS_NOTES.put(40,"E5");
        PITCHER_CHORDS_NOTES.put(41,"F5");
        PITCHER_CHORDS_NOTES.put(42,"F#5");
        PITCHER_CHORDS_NOTES.put(43,"G5");
        PITCHER_CHORDS_NOTES.put(44,"G#5");
        PITCHER_CHORDS_NOTES.put(45,"A5");
        PITCHER_CHORDS_NOTES.put(46,"A#5");
        PITCHER_CHORDS_NOTES.put(47,"B5");
        
    }
    
    //Team Keys:
    public static final HashMap<String, String> TEAM_KEYS;
    static{
        TEAM_KEYS = new HashMap<>();
        
        //TODO: reassign later based on vibes?
        //sunbeams
        TEAM_KEYS.put("f02aeae2-5e6a-4098-9842-02d2273f25c7","C Major");
        //shoe thieves
        TEAM_KEYS.put("bfd38797-8404-4b38-8b82-341da28b1f83","C Minor");
        //magic
        TEAM_KEYS.put("7966eb04-efcc-499b-8f03-d13916330531","C# Major");
        //tacos
        TEAM_KEYS.put("878c1bf6-0d21-4659-bfee-916c8314d69c","C# Minor");
        //spies
        TEAM_KEYS.put("9debc64f-74b7-4ae1-a4d6-fce0144b6ea5", "D Major");
        //dale
        TEAM_KEYS.put("b63be8c2-576a-4d6e-8daf-814f8bcea96f", "D Minor");
        //jazz hands
        TEAM_KEYS.put("a37f9158-7f82-46bc-908c-c9e2dda7c33b", "D# Major");
        //breath mints
        TEAM_KEYS.put("adc5b394-8f76-416d-9ce9-813706877b84", "D# Minor");
        //fridays
        TEAM_KEYS.put("979aee4a-6d80-4863-bf1c-ee1a78e06024", "E Major");
        //firefighters
        TEAM_KEYS.put("ca3f1c8c-c025-4d8e-8eef-5be6accbeb16", "E Minor");
        //moist talkers
        TEAM_KEYS.put("eb67ae5e-c4bf-46ca-bbbc-425cd34182ff", "F Major");
        //worms
        TEAM_KEYS.put("bb4a9de5-c924-4923-a0cb-9d1445f1ee5d", "F Minor");
        //wild wings
        TEAM_KEYS.put("57ec08cc-0411-4643-b304-0e80dbc15ac7", "F# Major");
        //lift
        TEAM_KEYS.put("c73b705c-40ad-4633-a6ed-d357ee2e2bcf", "F# Minor");
        //millennials
        TEAM_KEYS.put("36569151-a2fb-43c1-9df7-2df512424c82", "G Major");
        //pies
        TEAM_KEYS.put("23e4cbc1-e9cd-47fa-a35b-bfa06f726cb7", "G Minor");
        //crabs
        TEAM_KEYS.put("8d87c468-699a-47a8-b40d-cfb73a5660ad", "G# Major");
        //tigers
        TEAM_KEYS.put("747b8e4a-7e50-4638-a973-ea7950a3e739", "G# Minor");
        //georgias
        TEAM_KEYS.put("d9f89a8a-c563-493e-9d64-78e4f9a55d4a", "A Major");
        //garages
        TEAM_KEYS.put("105bc3ff-1320-4e37-8ef0-8d595cb95dd0", "A Minor");
        //steaks
        TEAM_KEYS.put("b024e975-1c4a-4575-8936-a3754a08806a", "A# Major");
        //mechanics
        TEAM_KEYS.put("46358869-dce9-4a01-bfba-ac24fc56f57e", "A# Minor");
        //flowers
        TEAM_KEYS.put("3f8bbb15-61c0-4e3f-8e4a-907a5fb1565e", "B Major");
        //lovers
        TEAM_KEYS.put("b72f3061-f573-40d7-832a-5ad475bd7909", "B Minor");
    }
    
    //method for filling a hashmap with mappings
    public static void mapScaleDegrees(HashMap<Integer, String> empty, 
            String teamId){
        String teamKey;
        if(TEAM_KEYS.containsKey(teamId)){
            teamKey = TEAM_KEYS.get(teamId);
        }else{
            teamKey = "Default";
        }
        switch(teamKey){
            //Default: Bebop dominant C
            default -> {
                empty.put(1, "C4");
                empty.put(2, "D4");
                empty.put(3, "E4");
                empty.put(4, "F4");
                empty.put(5, "G4");
                empty.put(6, "A4");
                empty.put(7, "A#4");
                empty.put(8, "B4");
                empty.put(9, "C5");
                empty.put(10, "D5");
                empty.put(11, "E5");
                empty.put(12, "F5");
                empty.put(13, "G5");
                empty.put(14, "A5");
                empty.put(15, "A#5");
                empty.put(16, "B5");
            }
            case "A Major" -> {
                empty.put(1, "A3");
                empty.put(2, "B3");
                empty.put(3, "C#4");
                empty.put(4, "D4");
                empty.put(5, "E4");
                empty.put(6, "F4");
                empty.put(7, "F#4");
                empty.put(8, "G#4");
                empty.put(9, "A4");
                empty.put(10, "B5");
                empty.put(11, "C#5");
                empty.put(12, "D5");
                empty.put(13, "E5");
                empty.put(14, "F5");
                empty.put(15, "F#5");
                empty.put(16, "G#5");
            }
            case "A# Major" -> {
                empty.put(1, "A#3");
                empty.put(2, "C4");
                empty.put(3, "D4");
                empty.put(4, "D#4");
                empty.put(5, "F4");
                empty.put(6, "F#4");
                empty.put(7, "G4");
                empty.put(8, "A4");
                empty.put(9, "A#4");
                empty.put(10, "C5");
                empty.put(11, "D5");
                empty.put(12, "D#5");
                empty.put(13, "F5");
                empty.put(14, "F#5");
                empty.put(15, "G5");
                empty.put(16, "A5");
            }
            case "B Major " -> {
                empty.put(1, "B3");
                empty.put(2, "C#4");
                empty.put(3, "D#4");
                empty.put(4, "E4");
                empty.put(5, "F#4");
                empty.put(6, "G4");
                empty.put(7, "G#4");
                empty.put(8, "A#4");
                empty.put(9, "B4");
                empty.put(10, "C#5");
                empty.put(11, "D#5");
                empty.put(12, "E5");
                empty.put(13, "F#5");
                empty.put(14, "G5");
                empty.put(15, "G#5");
                empty.put(16, "A#5");
            }
            case "C Major" -> {
                empty.put(1, "C4");
                empty.put(2, "D4");
                empty.put(3, "E4");
                empty.put(4, "F4");
                empty.put(5, "G4");
                empty.put(6, "G#4");
                empty.put(7, "A4");
                empty.put(8, "B4");
                empty.put(9, "C5");
                empty.put(10, "D5");
                empty.put(11, "E5");
                empty.put(12, "F5");
                empty.put(13, "G5");
                empty.put(14, "G#5");
                empty.put(15, "A5");
                empty.put(16, "B5");
            }
            case "C# Major" -> {
                empty.put(1, "C#3");
                empty.put(2, "D#3");
                empty.put(3, "F3");
                empty.put(4, "F#3");
                empty.put(5, "G#3");
                empty.put(6, "A3");
                empty.put(7, "A#3");
                empty.put(8, "C4");
                empty.put(9, "C#4");
                empty.put(10, "D#4");
                empty.put(11, "F4");
                empty.put(12, "F#4");
                empty.put(13, "G#4");
                empty.put(14, "A4");
                empty.put(15, "A#4");
                empty.put(16, "C4");
            }
            case "D Major" -> {
                empty.put(1, "D3");
                empty.put(2, "E3");
                empty.put(3, "F#3");
                empty.put(4, "G3");
                empty.put(5, "A3");
                empty.put(6, "A#3");
                empty.put(7, "B3");
                empty.put(8, "C#4");
                empty.put(9, "D4");
                empty.put(10, "E4");
                empty.put(11, "F#4");
                empty.put(12, "G4");
                empty.put(13, "A4");
                empty.put(14, "A#4");
                empty.put(15, "B4");
                empty.put(16, "C#5");
            }
            case "D# Major" -> {
                empty.put(1, "D#3");
                empty.put(2, "F3");
                empty.put(3, "G3");
                empty.put(4, "G#3");
                empty.put(5, "A#3");
                empty.put(6, "B3");
                empty.put(7, "C4");
                empty.put(8, "D4");
                empty.put(9, "D#4");
                empty.put(10, "F4");
                empty.put(11, "G4");
                empty.put(12, "G#4");
                empty.put(13, "A#4");
                empty.put(14, "B4");
                empty.put(15, "C5");
                empty.put(16, "D5");
            }
            case "E Major" -> {
                empty.put(1, "E3");
                empty.put(2, "F#3");
                empty.put(3, "G#3");
                empty.put(4, "A3");
                empty.put(5, "B3");
                empty.put(6, "C4");
                empty.put(7, "C#4");
                empty.put(8, "D#4");
                empty.put(9, "E4");
                empty.put(10, "F#4");
                empty.put(11, "G#4");
                empty.put(12, "A4");
                empty.put(13, "B4");
                empty.put(14, "C5");
                empty.put(15, "C#5");
                empty.put(16, "D#5");
            }
            case "F Major" -> {
                empty.put(1, "F3");
                empty.put(2, "G3");
                empty.put(3, "A3");
                empty.put(4, "A#3");
                empty.put(5, "C4");
                empty.put(6, "C#4");
                empty.put(7, "D4");
                empty.put(8, "E4");
                empty.put(9, "F4");
                empty.put(10, "G4");
                empty.put(11, "A4");
                empty.put(12, "A#4");
                empty.put(13, "C5");
                empty.put(14, "C#5");
                empty.put(15, "D5");
                empty.put(16, "E5");
            }
            case "F# Major" -> {
                empty.put(1, "F#3");
                empty.put(2, "G#3");
                empty.put(3, "A#3");
                empty.put(4, "B3");
                empty.put(5, "C#4");
                empty.put(6, "D4");
                empty.put(7, "D#4");
                empty.put(8, "F4");
                empty.put(9, "F#4");
                empty.put(10, "G#4");
                empty.put(11, "A#4");
                empty.put(12, "B4");
                empty.put(13, "C#5");
                empty.put(14, "D5");
                empty.put(15, "D#5");
                empty.put(16, "R5");
            }
            case "G Major" -> {
                empty.put(1, "G3");
                empty.put(2, "A3");
                empty.put(3, "B3");
                empty.put(4, "C4");
                empty.put(5, "D4");
                empty.put(6, "D#4");
                empty.put(7, "E4");
                empty.put(8, "F#4");
                empty.put(9, "G4");
                empty.put(10, "A4");
                empty.put(11, "B4");
                empty.put(12, "C5");
                empty.put(13, "D5");
                empty.put(14, "D#5");
                empty.put(15, "E5");
                empty.put(16, "F#5");
            }
            case "G# Major" -> {
                empty.put(1, "G#3");
                empty.put(2, "A#3");
                empty.put(3, "C4");
                empty.put(4, "C#4");
                empty.put(5, "D#4");
                empty.put(6, "E4");
                empty.put(7, "F4");
                empty.put(8, "G4");
                empty.put(9, "G#4");
                empty.put(10, "A#4");
                empty.put(11, "C5");
                empty.put(12, "C#5");
                empty.put(13, "D#5");
                empty.put(14, "E5");
                empty.put(15, "F5");
                empty.put(16, "G5");
            }
            case "A Minor" ->{
                empty.put(1, "A3");
                empty.put(2, "B3");
                empty.put(3, "C4");
                empty.put(4, "D4");
                empty.put(5, "E4");
                empty.put(6, "F#4");
                empty.put(7, "G#4");
                empty.put(8, "A4");
                empty.put(9, "B4");
                empty.put(10, "C5");
                empty.put(11, "D5");
                empty.put(12, "E5");
                empty.put(13, "F#5");
                empty.put(14, "G#5");
                empty.put(15, "A5");
                empty.put(16, "B5");
            }
            case "A# Minor" -> {
                empty.put(1, "A#3");
                empty.put(2, "C4");
                empty.put(3, "C#4");
                empty.put(4, "D#4");
                empty.put(5, "F4");
                empty.put(6, "G4");
                empty.put(7, "A4");
                empty.put(8, "A#4");
                empty.put(9, "C5");
                empty.put(10, "C#5");
                empty.put(11, "D#5");
                empty.put(12, "F5");
                empty.put(13, "G5");
                empty.put(14, "A5");
                empty.put(15, "A#5");
                empty.put(16, "C6");
            }
            //Not enough samples here to get full range in, so looping on the 16th
            case "B Minor" -> {
                empty.put(1, "B3");
                empty.put(2, "C#4");
                empty.put(3, "D4");
                empty.put(4, "E4");
                empty.put(5, "F#4");
                empty.put(6, "G#4");
                empty.put(7, "A#4");
                empty.put(8, "B4");
                empty.put(9, "C#5");
                empty.put(10, "D5");
                empty.put(11, "E5");
                empty.put(12, "F#5");
                empty.put(13, "G#5");
                empty.put(14, "A#5");
                empty.put(15, "B5");
                empty.put(16, "C#4");
            }
            case "C Minor" -> {
                empty.put(1, "C4");
                empty.put(2, "D4");
                empty.put(3, "D#4");
                empty.put(4, "F4");
                empty.put(5, "G4");
                empty.put(6, "A4");
                empty.put(7, "B4");
                empty.put(8, "C5");
                empty.put(9, "D5");
                empty.put(10, "D#5");
                empty.put(11, "F5");
                empty.put(12, "G5");
                empty.put(13, "A5");
                empty.put(14, "B5");
                empty.put(15, "C6");
                empty.put(16, "D4");
            }
            case "C# Minor" -> {
                empty.put(1, "C#3");
                empty.put(2, "D#3");
                empty.put(3, "E3");
                empty.put(4, "F#3");
                empty.put(5, "G#3");
                empty.put(6, "A#3");
                empty.put(7, "C4");
                empty.put(8, "C#4");
                empty.put(9, "D#4");
                empty.put(10, "E4");
                empty.put(11, "F#4");
                empty.put(12, "G#4");
                empty.put(13, "A#4");
                empty.put(14, "C5");
                empty.put(15, "C#5");
                empty.put(16, "D#5");
            }
            case "D Minor" -> {
                empty.put(1, "D3");
                empty.put(2, "E3");
                empty.put(3, "F3");
                empty.put(4, "G3");
                empty.put(5, "A3");
                empty.put(6, "B3");
                empty.put(7, "C#4");
                empty.put(8, "D4");
                empty.put(9, "E4");
                empty.put(10, "F4");
                empty.put(11, "G4");
                empty.put(12, "A4");
                empty.put(13, "B4");
                empty.put(14, "C#5");
                empty.put(15, "D5");
                empty.put(16, "E5");
            }
            case "D# Minor" -> {
                empty.put(1, "D#3");
                empty.put(2, "F3");
                empty.put(3, "F#3");
                empty.put(4, "G#3");
                empty.put(5, "A#3");
                empty.put(6, "C4");
                empty.put(7, "D4");
                empty.put(8, "D#4");
                empty.put(9, "F4");
                empty.put(10, "F#4");
                empty.put(11, "G#4");
                empty.put(12, "A#4");
                empty.put(13, "C5");
                empty.put(14, "D5");
                empty.put(15, "D#5");
                empty.put(16, "F5");
            }
            case "E Minor" -> {
                empty.put(1, "E3");
                empty.put(2, "F#3");
                empty.put(3, "G3");
                empty.put(4, "A3");
                empty.put(5, "B3");
                empty.put(6, "C#4");
                empty.put(7, "D#4");
                empty.put(8, "E4");
                empty.put(9, "F#4");
                empty.put(10, "G4");
                empty.put(11, "A4");
                empty.put(12, "B4");
                empty.put(13, "C#5");
                empty.put(14, "D#5");
                empty.put(15, "E5");
                empty.put(16, "F#5");
            }
            case "F Minor" -> {
                empty.put(1, "F3");
                empty.put(2, "G3");
                empty.put(3, "G#3");
                empty.put(4, "A#3");
                empty.put(5, "C4");
                empty.put(6, "D4");
                empty.put(7, "E4");
                empty.put(8, "F4");
                empty.put(9, "G4");
                empty.put(10, "G#4");
                empty.put(11, "A#4");
                empty.put(12, "C5");
                empty.put(13, "D5");
                empty.put(14, "E5");
                empty.put(15, "F5");
                empty.put(16, "G5");
            }
            case "F# Minor" -> {
                empty.put(1, "F#3");
                empty.put(2, "G#3");
                empty.put(3, "A3");
                empty.put(4, "B3");
                empty.put(5, "C#4");
                empty.put(6, "D#4");
                empty.put(7, "F4");
                empty.put(8, "F#4");
                empty.put(9, "G#4");
                empty.put(10, "A4");
                empty.put(11, "B4");
                empty.put(12, "C#5");
                empty.put(13, "D#5");
                empty.put(14, "F5");
                empty.put(15, "F#5");
                empty.put(16, "G#5");
            }
            case "G Minor" -> {
                empty.put(1, "G3");
                empty.put(2, "A3");
                empty.put(3, "A#3");
                empty.put(4, "C4");
                empty.put(5, "D4");
                empty.put(6, "E4");
                empty.put(7, "F#4");
                empty.put(8, "G4");
                empty.put(9, "A4");
                empty.put(10, "A#4");
                empty.put(11, "C5");
                empty.put(12, "D5");
                empty.put(13, "E5");
                empty.put(14, "F#5");
                empty.put(15, "G5");
                empty.put(16, "A5");
            }
            case "G# Minor" -> {
                empty.put(1, "G#3");
                empty.put(2, "A#3");
                empty.put(3, "B3");
                empty.put(4, "C#4");
                empty.put(5, "D#4");
                empty.put(6, "F4");
                empty.put(7, "G4");
                empty.put(8, "G#4");
                empty.put(9, "A#4");
                empty.put(10, "B4");
                empty.put(11, "C#5");
                empty.put(12, "D#5");
                empty.put(13, "F5");
                empty.put(14, "G5");
                empty.put(15, "G#5");
                empty.put(16, "A#5");
            }
        }
    }
    
    //put chord in the defending team's key
    public static String chordModulation(String key, Integer[] chord){
        String toModulate = key.substring(0,2);
        String chordOutput = "[ ";
        switch(toModulate){
            default -> {
                //don't need to modulate here!
                for(int x = 0; x < chord.length; x++){
                    chordOutput+=PITCHER_CHORDS_NOTES.get(chord[x]);
                    chordOutput+=" ";
                }
            }
            case "C " -> {
                //don't need to modulate here
                for(int x = 0; x < chord.length; x++){
                    chordOutput+=PITCHER_CHORDS_NOTES.get(chord[x]);
                    chordOutput+=" ";
                }
            }
            case "C#" -> {
                for(int x = 0; x < chord.length; x++){
                    chord[x]++;
                    chordOutput+=PITCHER_CHORDS_NOTES.get(chord[x]);
                    chordOutput+=" ";
                }
            }
            case "D " -> {
                for(int x = 0; x < chord.length; x++){
                    chord[x]+=2;
                    chordOutput+=PITCHER_CHORDS_NOTES.get(chord[x]);
                    chordOutput+=" ";
                }
            }
            case "D#" -> {
                for(int x = 0; x < chord.length; x++){
                    chord[x]+=3;
                    chordOutput+=PITCHER_CHORDS_NOTES.get(chord[x]);
                    chordOutput+=" ";
                }
            }
            case "E " -> {
                for(int x = 0; x < chord.length; x++){
                    chord[x]+=4;
                    chordOutput+=PITCHER_CHORDS_NOTES.get(chord[x]);
                    chordOutput+=" ";
                }
            }
            case "F " -> {
                for(int x = 0; x < chord.length; x++){
                    chord[x]+=5;
                    chordOutput+=PITCHER_CHORDS_NOTES.get(chord[x]);
                    chordOutput+=" ";
                }
            }
            case "F#" -> {
                for(int x = 0; x < chord.length; x++){
                    chord[x]+=6;
                    chordOutput+=PITCHER_CHORDS_NOTES.get(chord[x]);
                    chordOutput+=" ";
                }
            }
            case "G " -> {
                for(int x = 0; x < chord.length; x++){
                    chord[x]+=7;
                    chordOutput+=PITCHER_CHORDS_NOTES.get(chord[x]);
                    chordOutput+=" ";
                }
            }
            case "G#" -> {
                for(int x = 0; x < chord.length; x++){
                    chord[x]+=8;
                    chordOutput+=PITCHER_CHORDS_NOTES.get(chord[x]);
                    chordOutput+=" ";
                }
            }
            case "A " -> {
                for(int x = 0; x < chord.length; x++){
                    chord[x]+=9;
                    chordOutput+=PITCHER_CHORDS_NOTES.get(chord[x]);
                    chordOutput+=" ";
                }
            }
            case "A#" -> {
                for(int x = 0; x < chord.length; x++){
                    chord[x]+=10;
                    chordOutput+=PITCHER_CHORDS_NOTES.get(chord[x]);
                    chordOutput+=" ";
                }
            }
            case "B " -> {
                for(int x = 0; x < chord.length; x++){
                    chord[x]+=11;
                    chordOutput+=PITCHER_CHORDS_NOTES.get(chord[x]);
                    chordOutput+=" ";
                }
            }
        }
        chordOutput+="]";
        return chordOutput;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
            // TODO code application logic here
            
            
            // TODO let users input season/day/team
        String gameId = "5efd94fd-875f-423e-a47f-cc464d5684e6";
        try {
            // open connection
            URL url = new URL("https://api.sibr.dev/chronicler/v1/games/updates/?game=" + 
                    gameId + "&order=asc&count=1000");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            
            //get response code
            int status = con.getResponseCode();
            
            //use bufferreader to read json into content
            BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while((inputLine = in.readLine()) != null){
                content.append(inputLine);
            }
            con.disconnect();
            
            String allGameContent = content.toString();
            
            //gson to be the actor on various things
            Gson gson = new Gson();
            
            JsonObject bigBucket = gson.fromJson(allGameContent, JsonObject.class);
            
            JsonArray data = bigBucket.getAsJsonArray("data");
            
            //pull data from the 1 index
            JsonObject setupDataArray = data.get(1).getAsJsonObject();
            
            JsonObject setupData = setupDataArray.getAsJsonObject("data");
            
            //pull the team & pitcher info
            String homeTeam = setupData.get("homeTeamName").getAsString();
            String awayTeam = setupData.get("awayTeamName").getAsString();
            
            String homeTeamId = setupData.get("homeTeam").getAsString();
            String awayTeamId = setupData.get("awayTeam").getAsString();
            
            String homePitcher = setupData.get("homePitcherName").getAsString();
            String awayPitcher = setupData.get("awayPitcherName").getAsString();
            
            //create the translations from scale degrees to notes for home & away
            HashMap<Integer, String> homeTeamNotesScaleDegrees = new HashMap<>();
            HashMap<Integer, String> awayTeamNotesScaleDegrees = new HashMap<>();
            
            mapScaleDegrees(homeTeamNotesScaleDegrees, homeTeamId);
            mapScaleDegrees(awayTeamNotesScaleDegrees, awayTeamId);
            
            //determine what to modulate the pitcher chords by for home & away
            
            //keep looping until done with game events
            int arrayId = 2;
            
            List<String> gameEventList = new ArrayList<>();
            List<Integer> baserunnerList = new ArrayList<>();
            List<Boolean> inningTop = new ArrayList<>();
            
            //instantiate the elements being used
            
            JsonObject gameEventsArray;
            JsonObject gameEvents;
            
            String event;
            
            boolean gameComplete = false;
            
            while(arrayId < data.size()){
                //pull data from index
                gameEventsArray = data.get(arrayId).getAsJsonObject();
                gameEvents = gameEventsArray.getAsJsonObject("data");
                
                event = gameEvents.get("lastUpdate").getAsString();
                
                gameComplete = gameEvents.get("gameComplete").getAsBoolean();
                
                gameEventList.add(event);
                
                baserunnerList.add(gameEvents.get("basesOccupied").getAsJsonArray().size());
                
                inningTop.add(gameEvents.get("topOfInning").getAsBoolean());
                
                arrayId++;
            }
            
            if(!gameComplete){
                String nextPage = bigBucket.get("nextPage").getAsString();
                
                URL newRequest = new URL("https://api.sibr.dev/chronicler/v1/games/updates/?game=" + 
                    gameId + "&order=asc&count=1000&page=" + nextPage);
                
                HttpURLConnection con2 = (HttpURLConnection) newRequest.openConnection();
                con2.setRequestMethod("GET");

                //get response code
                int status2 = con.getResponseCode();

                //use bufferreader to read json into content
                BufferedReader in2 = new BufferedReader(
                    new InputStreamReader(con2.getInputStream()));
                String inputLine2;
                StringBuffer content2 = new StringBuffer();
                while((inputLine2 = in2.readLine()) != null){
                    content2.append(inputLine2);
                }
                con2.disconnect();

                String allGameContent2 = content2.toString();

                JsonObject bigBucket2 = gson.fromJson(allGameContent2, JsonObject.class);

                JsonArray data2 = bigBucket2.getAsJsonArray("data");
                
                arrayId = 0;
                
                while(arrayId < data2.size()){
                    //pull data from index
                    gameEventsArray = data2.get(arrayId).getAsJsonObject();
                    gameEvents = gameEventsArray.getAsJsonObject("data");

                    event = gameEvents.get("lastUpdate").getAsString();

                    gameEventList.add(event);
                    
                    baserunnerList.add(gameEvents.get("basesOccupied").getAsJsonArray().size());
                    
                    inningTop.add(gameEvents.get("topOfInning").getAsBoolean());
                    
                    arrayId++;
                    
                }
            }
            
            //this mismatch should never happen. if it does, panic.
            if(gameEventList.size() != baserunnerList.size() && 
                    baserunnerList.size() != inningTop.size()){
                System.out.println("uhhhhh");
            }
            
            
            //All game events are captured, as are teams & pitchers!
            //Now to translate them into jazz
            
            
            String activeEvent;
            int baserunnerNumber;
            String activePitcher;
            String defendingTeam;
            String defendingTeamId;
            HashMap defendingTeamMap;
            String defendingTeamKey;
            
            int eventNote;
            int homePitcherChordIterator=0;
            int awayPitcherChordIterator=0;
            Integer[] pitcherChord;
            
            for(int i=0; i<gameEventList.size();i++){
                activeEvent = gameEventList.get(i);
                System.out.println(activeEvent);
                baserunnerNumber = baserunnerList.get(i);
                //string bass part depends on how many baserunners there currently are
                System.out.print("Active baserunners: ");
                System.out.println(baserunnerNumber);
                
                //fill out info depending on if top or bottom of inning
                if(inningTop.get(i)){
                    defendingTeam = homeTeam;
                    defendingTeamId = homeTeamId;
                    activePitcher = homePitcher;
                    defendingTeamMap = homeTeamNotesScaleDegrees;
                    if(TEAM_KEYS.containsKey(homeTeamId)){
                        defendingTeamKey = TEAM_KEYS.get(homeTeamId);
                    }else{
                        defendingTeamKey = "Default";
                    }
                    
                }else{
                    defendingTeam = awayTeam;
                    defendingTeamId = awayTeamId;
                    activePitcher = awayPitcher;
                    defendingTeamMap = awayTeamNotesScaleDegrees;
                    if(TEAM_KEYS.containsKey(awayTeamId)){
                        defendingTeamKey = TEAM_KEYS.get(awayTeamId);
                    }else{
                        defendingTeamKey = "Default";
                    }
                }
                
                //print setup info, this isn't actually used
                System.out.print("Defending team: ");
                System.out.println(defendingTeam);
                System.out.print("Active pitcher: ");
                System.out.println(activePitcher);
                System.out.print("Active Key: ");
                System.out.println(TEAM_KEYS.get(defendingTeamId));
                
                //Roll through event text:
                //this would get piped into sax synth
                System.out.print("Event/Saxophone melody: ");
                for(int j = 0; j<activeEvent.length(); j++){
                    char e = Character.toLowerCase(activeEvent.charAt(j));
                    if(Character.isLetter(e)){
                        eventNote = BATTER_SCALEDEGREES.get(Character.toString(e));
                    }else{
                        eventNote = (int) (Math.random()*15) + 1;
                    }
                    System.out.print(defendingTeamMap.get(eventNote));
                    System.out.print(" ");
                }
                System.out.println();
                
                //Roll through the pitcher name:
                //this would get piped into the piano synth
                char p;
                if(inningTop.get(i)){
                    p = Character.toLowerCase(homePitcher.charAt(homePitcherChordIterator));
                }else{
                    p = Character.toLowerCase(awayPitcher.charAt(awayPitcherChordIterator));
                }
                pitcherChord = PITCHER_CHORDS.get(Character.toString(p)).clone();
                System.out.print("Pitcher letter: ");
                System.out.println(p);
                System.out.print("Pitcher letter chord: ");
                System.out.println(chordModulation(defendingTeamKey, pitcherChord));
                
                if(inningTop.get(i)){
                    homePitcherChordIterator = (homePitcherChordIterator + 1) % homePitcher.length();
                    p = Character.toLowerCase(homePitcher.charAt(homePitcherChordIterator));
                    while(!"abcdefjhijklmnopqrstuvwxyz-".contains(Character.toString(p))){
                        homePitcherChordIterator = (homePitcherChordIterator + 1) % homePitcher.length();
                        p = Character.toLowerCase(activePitcher.charAt(homePitcherChordIterator));
                    }
                }else{
                    awayPitcherChordIterator = (awayPitcherChordIterator + 1) % awayPitcher.length();
                    p = Character.toLowerCase(awayPitcher.charAt(awayPitcherChordIterator));
                    while(!"abcdefjhijklmnopqrstuvwxyz-".contains(Character.toString(p))){
                        awayPitcherChordIterator = (awayPitcherChordIterator + 1) % awayPitcher.length();
                        p = Character.toLowerCase(activePitcher.charAt(awayPitcherChordIterator));
                    }
                }
            }
            
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(JazzViewer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JazzViewer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
