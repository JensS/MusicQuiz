/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package musicquiz;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.farng.mp3.*;

/**
 *
 * @author jens
 */
public class Song {

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
                info.put("title", mp3file.getID3v2Tag().getSongTitle());
                info.put("artist", mp3file.getID3v2Tag().getLeadArtist());
            } catch (IOException ex) {
            } catch (TagException ex) {
            }
        }
    }

    String getSongname() {
        getInfo();
        return  (String) info.get("title");
    }

    String getArtist() {
        getInfo();
        return  (String) info.get("artist");
    }
}
