package org.me.musicscalegenerator;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundPlayer{
    
    SoundPlayer(String fileName) throws Exception{
        
        //grabs audio wav files in the project as a RESOURCE, not a FILE, so 
        //that they will be compatible with the executable jar. 
        AudioInputStream ais = AudioSystem.getAudioInputStream
            (getClass().getResource("/org/me/musicscalegenerator/"
                    + "SoundResources/" + fileName));
        
        //opens and plays the sound
        Clip clip = AudioSystem.getClip();
        clip.open(ais);
        clip.start();
    }
}