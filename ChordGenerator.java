package org.me.musicscalegenerator;

import java.util.ArrayList;

public class ChordGenerator extends ScaleGenerator {
   
    //gets correct indexes for noteObjs based on startng index and chord formula
    public ArrayList<String> getChordNoteIndexes(int start, String intervals){
        
        ArrayList<String> indexes = new ArrayList<>();
        
        indexes.add(0, String.valueOf(start));
        
        for (int i = 1; i <=  intervals.length(); i++){
            indexes.add(i, String.valueOf((Integer.parseInt
                (Character.toString(intervals.charAt(i - 1)))) + 
                    Integer.parseInt(indexes.get(i - 1))));
        }
        
        return indexes;
    }
        //pulls only the necessary notes for the chord from the full scale
        public ArrayList<String> extractChordNotes(ArrayList<String> scale, 
                String chordFormula){
        
        ArrayList<String> extractedNotes = new ArrayList<>();
        extractedNotes.add(0, scale.get(0));
        
        switch (chordFormula){
            case "43":
            case "34":
            case "44":
            case "33":{
                extractedNotes.add(1, scale.get(2));
                extractedNotes.add(2, scale.get(4));
                break;
            }   
            case "433":
            case "434":
            case "343":
            case "333":   
            case "334":
            case "442":{
                extractedNotes.add(1, scale.get(2));
                extractedNotes.add(2, scale.get(4));
                extractedNotes.add(3, scale.get(6));
                break;
            }    
            case "342": 
            case "432":{
                extractedNotes.add(1, scale.get(2));
                extractedNotes.add(2, scale.get(4));
                extractedNotes.add(3, scale.get(5));
                break;
            }
            case "52":{
                extractedNotes.add(1, scale.get(3));
                extractedNotes.add(2, scale.get(4));
                break;
            }
            case "25":{
                extractedNotes.add(1, scale.get(1));
                extractedNotes.add(2, scale.get(4));
                break;
                
            }
            case "7":{
                extractedNotes.add(1, scale.get(4));
                break;
            }
                
        }
        
        return convertChordNotes(extractedNotes, chordFormula);
    }
    
    //changes pitches of some notes in the chord based on chord selection
    public ArrayList<String> convertChordNotes(ArrayList<String> extractedNotes,
            String formula){
        
        switch(formula){
            case "52":
            case "25":
            case "7":
            case "434":
            case "432":
            case "43": 
                break;              
            case "342":
            case "34":{
                extractedNotes.set(1, flatNote(extractedNotes.get(1)));
                break;
            }
            case "433": {
                extractedNotes.set(3, flatNote(extractedNotes.get(3)));
                break;
            }
            case "343":{
                extractedNotes.set(1, flatNote(extractedNotes.get(1)));
                extractedNotes.set(3, flatNote(extractedNotes.get(3)));
                break;
            }
            case "33":{
                extractedNotes.set(1, flatNote(extractedNotes.get(1)));
                extractedNotes.set(2, flatNote(extractedNotes.get(2)));
                break;
            }
            case "333":{
                extractedNotes.set(1, flatNote(extractedNotes.get(1)));
                extractedNotes.set(2, flatNote(extractedNotes.get(2)));
                extractedNotes.set(3, doubleFlatNote(extractedNotes.get(3)));
                break;
            }
            case "334":{
                extractedNotes.set(1, flatNote(extractedNotes.get(1)));
                extractedNotes.set(2, flatNote(extractedNotes.get(2)));
                extractedNotes.set(3, flatNote(extractedNotes.get(3)));
                break;
            }
            case "44":{
                extractedNotes.set(2, sharpNote(extractedNotes.get(2)));
                break;
            }
            case "442":{
                extractedNotes.set(2, sharpNote(extractedNotes.get(2)));
                extractedNotes.set(3, flatNote(extractedNotes.get(3)));
                break;
            }
            default:
                break;
                
            
        }
        
        return extractedNotes;
    }
    
    //flattens a note
    private String flatNote(String note){
        
        if (note.contains("b"))
            note = note.concat("b");
        else if(note.length() == 3 && note.contains("#"))
            note = note.substring(0, 1);
        else if (note.charAt(1) == ' ')
            note = note.replace(' ', 'b');
        else if(note.charAt(1) == '#')
            note = note.replace('#', ' ');
        
        return note;
    }
    
    //double flattens a note
    private String doubleFlatNote(String note){
        
        if (note.contains("b"))
            note = note.concat("b");
        else if(note.length() == 3 && note.contains("#"))
            note = note.replace("##", " ");
        else if (note.charAt(1) == ' ')
            note = note.replace(" ", "bb");
        else if(note.charAt(1) == '#')
            note = note.replace('#', 'b');
        
        return note;
    }
    
    //sharps a note
    private String sharpNote(String note){
         
        if (note.contains("#"))
            note = note.concat("#");
        else if(note.length() == 3 && note.contains("b"))
            note = note.substring(0, 1);
        else if (note.charAt(1) == ' ')
            note = note.replace(' ', '#');
        else if(note.charAt(1) == 'b')
            note = note.replace('b', ' ');
        
        return note;
    }
}