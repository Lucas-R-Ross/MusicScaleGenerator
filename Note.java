package org.me.musicscalegenerator;

/**
 *
 * @author Luke
 */
public class Note {
    
    //every note will know its associated sound and glow label for the key it
    //represents
    private String soundFile;
    private javax.swing.JLabel glowObject;
    
    //light up the key
    public void highlightNote(boolean shouldLight){
        glowObject.setVisible(shouldLight);
            
    }
    
    //play the note sound
    public void playNote(){
       try{
           new SoundPlayer(soundFile);
        //SoundPlayer.play(soundFile);
       }
       catch(Exception e){
           
       }
    }
    
    //set the note's sound file using string
    public void setSoundFile(String fileName){
        soundFile = fileName;
    }
    
    //set the note's glow label
    public void setGlowObject(javax.swing.JLabel temp){
        glowObject = temp;
    }
}