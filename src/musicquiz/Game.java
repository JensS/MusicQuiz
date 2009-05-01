/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package musicquiz;

import java.util.ArrayList;
import java.util.Random;

import java.util.prefs.*;
import java.util.Date;

/**
 *
 * @author jens
 */
public class Game {
    private ArrayList used_songs;
    private Database db;
    private Preferences prefs;

    private Random random;

    private Date starttime;

    private boolean startable = false;

    Game() {
        used_songs = new ArrayList();
        random = new Random();
        db = new Database();
    }

    boolean isStartable()
    {
        if (getPreferences().get("music-dir", null) != null)
        {
            db.setMusicDir(getPreferences().get("music-dir", ""));
            db.scanDir();
            if (db.getSongsCount() != 0)
                startable = true;

            //MusicQuizApp.getApplication().getMainView().
        }
        return startable;
    }

    void startGame()
    {
        starttime = new Date();
    }

    Question getQuestion()
    {
        if (!isStartable())
            return null;
        
        int id = random.nextInt(db.getSongsCount());

        return new Question((Song) db.getSongs().get(id));
    }

    Preferences getPreferences()
    {
        if (prefs == null)
        {
            prefs = Preferences.userNodeForPackage(getClass());
        }
        return prefs;
    }
}
