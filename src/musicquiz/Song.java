/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package musicquiz;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import org.farng.mp3.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import javazoom.jl.player.Player;

/**
 *
 * @author jens
 */
public class Song {

    private Player player;
    private File file;
    private HashMap info;

    Song(File file) {
        this.file = file;
        info = new HashMap();
    }

    private void getInfo() {
        if (info.size() == 0) {
            try {
                MP3File mp3file = new MP3File(file);
                if (mp3file.hasID3v1Tag())
                {
                    info.put("title", mp3file.getID3v1Tag().getSongTitle());
                    info.put("artist", mp3file.getID3v1Tag().getLeadArtist());
                }
                else
                {
                    info.put("title", mp3file.getID3v2Tag().getSongTitle());
                    info.put("artist", mp3file.getID3v2Tag().getLeadArtist());
                }
            } catch (IOException ex) {
            } catch (TagException ex) {
            }
        }
    }

    String getSongname() {
        getInfo();
        return (String) info.get("title");
    }

    String getArtist() {
        getInfo();
        return (String) info.get("artist");
    }

    void play(int n_seconds) {
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            player = new Player(bis);
        } catch (Exception e) {
            System.out.println("Problem playing file " + file.getAbsolutePath());
            System.out.println(e);
        }

        new Thread() {
            public void run() {
                try {
                    player.play();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }.start();
    }

    void stopPlayer() {
        if (player != null) {
            player.close();
        }
    }
}
