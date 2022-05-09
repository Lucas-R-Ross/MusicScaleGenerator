package org.me.musicscalegenerator;

import java.util.ArrayList;

/**
 *
 * @author Luke
 */
public class ScaleGenerator {
    
    //values used to build scale
    final static int NOTES_PER_SCALE = 7;
    final static int A = 65;

 
    //Notes and Accidentals - set up ASCII values
    final static char B = A+1;
    final static char D = B+2;
    final static char E = D+1;
    final static char SHARP = 35;   
    final static char FLAT = 98;
    final static char NATURAL = 32; 
    final static char DOUBLE_SHARP = 94;
    final static char DOUBLE_FLAT = 34;
    
    //Scale building formulas
    //2 represents a whole step. 1 represents a half step.  
    //3 represents a whole and a half.
    final static String HARMONIC = "2122131";
    final static String MELODIC = "2122221";
    final static String IONIAN = "2212221";
    final static String DORIAN = "2122212";
    final static String PHRYGIAN = "1222122";
    final static String LYDIAN = "2221221";
    final static String MIXOLYDIAN = "2212212";
    final static String AEOLIAN = "2122122";
    final static String LOCRIAN = "1221222";
    
    //returns scale with notes in correct order using a starting note.
    //accidentals are not added yet
    public ArrayList buildScale(String key){
   
        char keyAccidental = key.length() == 2 ? key.charAt(1) : NATURAL;
        
        if (key.length() == 1)
            key = key.concat(Character.toString(keyAccidental));
        
        char keyNote = key.charAt(0);
        
        ArrayList<String> scale = fillSecondaryScale(keyNote);
        
        scale.set(0, key);
        
        return scale;
   
    }   
    
    //get formula for distance between notes for all possible scales
    public String setScaleType(String scaleType){
        
        if (scaleType.equalsIgnoreCase("ionian (major)"))
            scaleType = IONIAN;
        else if (scaleType.equalsIgnoreCase("dorian"))
            scaleType = DORIAN;
        else if (scaleType.equalsIgnoreCase("phrygian"))
            scaleType = PHRYGIAN;
        else if (scaleType.equalsIgnoreCase("lydian"))
            scaleType = LYDIAN;
        else if (scaleType.equalsIgnoreCase("mixolydian"))
            scaleType = MIXOLYDIAN;
        else if (scaleType.equalsIgnoreCase("aeolian (minor)"))
            scaleType = AEOLIAN;
        else if (scaleType.equalsIgnoreCase("locrian"))
            scaleType = LOCRIAN;
        else if (scaleType.equalsIgnoreCase("harmonic minor"))
            scaleType = HARMONIC;
        else if (scaleType.equalsIgnoreCase("melodic minor"))
            scaleType = MELODIC;

        return scaleType;
    }
    
    //logic to correctly place accidentals on notes based on the distance 
    //between them
    public ArrayList getAccidentals(String scaleType, ArrayList<String> scale){
        
        int noteDistance;
        char currentAccidental;
        char nextAccidental = 0;
        char currentNote;
        
        for (int i = 0; i < NOTES_PER_SCALE; i++){
            
            noteDistance = Integer.parseInt(Character.toString
                (scaleType.charAt(i)));
            
            currentAccidental = scale.get(i).charAt(1);
            currentNote = scale.get(i).charAt(0);
            
            switch (noteDistance){   
                case 1:{
                     if (currentNote == B || currentNote == E){
                        nextAccidental = currentAccidental;    
                     }
                     else{
                         if (currentAccidental == NATURAL)
                             nextAccidental = FLAT;
                         if (currentAccidental == SHARP) 
                             nextAccidental = NATURAL; 
                         if (currentAccidental == FLAT)
                             nextAccidental = DOUBLE_FLAT;
                         if (currentAccidental == DOUBLE_SHARP)
                             nextAccidental = SHARP;
                     }
                     break;
                }
                case 2:{
                    if (currentNote == B || currentNote == E){
                        if (currentAccidental == NATURAL)
                            nextAccidental = SHARP;
                        if (currentAccidental == SHARP)
                            nextAccidental = DOUBLE_SHARP;
                        if (currentAccidental == FLAT)
                            nextAccidental = NATURAL;
                        if (currentAccidental == DOUBLE_FLAT)
                            nextAccidental = FLAT;
                        if (currentAccidental == DOUBLE_SHARP)
                            nextAccidental = SHARP;
                    }
                    else
                        nextAccidental = currentAccidental;
                    break;
                }
                case 3:{
                    if (currentNote == B || currentNote == E){
                        if (currentAccidental == FLAT)
                            nextAccidental = SHARP;
                        if (currentAccidental == NATURAL)
                            nextAccidental = DOUBLE_SHARP;
                        if (currentAccidental == DOUBLE_FLAT)
                            nextAccidental = NATURAL;      
                    }
                    else{
                        if (currentAccidental == NATURAL)
                            nextAccidental = SHARP;
                        if (currentAccidental == FLAT)
                            nextAccidental = NATURAL;
                        if (currentAccidental == SHARP)
                            nextAccidental = DOUBLE_SHARP;
                        if (currentAccidental == DOUBLE_FLAT)
                            nextAccidental = FLAT;
                        if (currentAccidental == DOUBLE_SHARP)
                            nextAccidental = currentAccidental;
                    }         
                }
                default:
                    break;
            }
            if (i < scale.size() - 1)
                scale.set(i + 1, scale.get(i + 1) + 
                        Character.toString(nextAccidental));      
        }
        
        return scale;
     
    }
    
    //returns a list of note names A-G
    private ArrayList fillPrimaryScale(){
    ArrayList<String> notes = new ArrayList<>();
        
        for (int i = 0; i < NOTES_PER_SCALE; i++){
            notes.add(i,Character.toString((char)(A + i)));     
        }
        
        return notes;
    }
    
    //uses circular logic to step through the primary scale based on starting
    //note, and return a new scale starting and ending on the correct note
    private ArrayList fillSecondaryScale(char key){
    ArrayList<String> notes = fillPrimaryScale();
        int start = notes.indexOf(Character.toString(key));
        ArrayList<String> scale = new ArrayList<>();
       
        String note;
        for (int i = 0; i < NOTES_PER_SCALE ; i++){
            
            note = (notes.get((start + i) % NOTES_PER_SCALE));  
            scale.add(i, note);
        } 
        
        return scale;
    }
    
    //the last step in the scale building process is to replace the double 
    //accidental symbols
    public ArrayList getFinalScale(ArrayList<String> notes){
        
        for (int i = 0; i < NOTES_PER_SCALE; i++){
            if(notes.get(i).charAt(1) == DOUBLE_SHARP)
                notes.set(i, notes.get(i).substring(0, 1).concat("##"));
            else if (notes.get(i).charAt(1) == DOUBLE_FLAT)
                notes.set(i, notes.get(i).substring(0, 1).concat("bb"));
        }
        
        return notes;      
    } 
  
}//end class