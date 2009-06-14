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
import org.farng.mp3.*;

import java.util.Map;

/**
 *
 * @author jens
 */
public class Song {
    private File file;
    private String artist, title;
    private boolean loaded = false;

    Song(File file) {
        this.file = file;
    }

    private void getProperties() {
        if (!loaded) {
            MP3File mp3file;
            try {
                mp3file = new MP3File(file);
                title = mp3file.getID3v2Tag().getSongTitle();
                artist = mp3file.getID3v2Tag().getLeadArtist();
            } catch (IOException ex) {
                Logger.getLogger(Song.class.getName()).log(Level.SEVERE, null, ex);
            } catch (TagException ex) {
                Logger.getLogger(Song.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        loaded=true;
    }

    String getSongname() {
        return (String) (title.isEmpty() ? "MP3 = KACKE!" : title);
    }

    String getArtist() {
        return (String) artist;
    }
}
