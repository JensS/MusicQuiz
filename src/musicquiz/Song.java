/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package musicquiz;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.*;

import java.util.Map;

/**
 *
 * @author jens
 */
public class Song {
    private File file;
    private Map properties;

    Song(File file) {
        this.file = file;
    }

    private Map getProperties() {
        if (properties == null) {
            try {
                properties = AudioSystem.getAudioFileFormat(file).properties();
            } catch (UnsupportedAudioFileException ex) {
                Logger.getLogger(Song.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Song.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return properties;
    }

    String getSongname() {
        return (String) getProperties().get("title");
    }

    String getArtist() {
        return (String) getProperties().get("author");
    }
}
