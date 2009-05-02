/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package musicquiz;

import java.io.File;
import java.util.Hashtable;

/**
 *
 * @author jens
 */
public class Database {

    private File dir;
    private Hashtable songs;

    Database() {
        songs = new Hashtable();
    }

    public void setMusicDir(String musicdir) {
        dir = new File(musicdir);
    }

    public File getMusicDir() {
        return dir;
    }

    public void scanDir() {
        if (dir == null) {
            return;
        }

        try {
            treeWalk(dir);
        } catch (Exception e) {
        }
    }

    private void treeWalk(File file) {
        if (file.isDirectory()) {
            File[] children = file.listFiles();
            for (int i = 0; i < children.length; i++) {
                treeWalk(children[i]);
            }
        } else {
            if (file.getName().endsWith(".mp3")) { // @todo supported file types
                songs.put(songs.size() + 1, new Song(file));
            }
        }
    }

    public Hashtable getSongs() {
        return songs;
    }

    public int getSongsCount() {
        return songs.size();
    }
}
