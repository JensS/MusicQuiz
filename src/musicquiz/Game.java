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
    private Database db;
    private Preferences prefs;
    private Date starttime;
    private Question currentQuestion;

    private boolean startable = false;

    Game() {
        db = new Database();
    }

    boolean isStartable()
    {
        if (getPreferences().get("music-dir", null) != null)
        {
            db.setMusicDir(getPreferences().get("music-dir", ""));
            db.scanDir();
            if (db.getSongsCount() > 4)
                startable = true;
        }
        return startable;
    }

    void startGame()
    {
        starttime = new Date();
        //MusicQuizApp.getApplication().getMainView().
    }

    void prepareNewQuestion()
    {
        currentQuestion = new Question(this);
    }

    Question getQuestion()
    {
        if(currentQuestion == null)
            prepareNewQuestion();
        return currentQuestion;
    }

    Database getDbObj()
    {
        return db;
    }

    
    Preferences getPreferences()
    {
        if (prefs == null)
        {
            prefs = Preferences.userNodeForPackage(getClass());
        }
        return prefs;
    }

    void stopGame() {
    }

    void displayResults() {
        
    }
}
