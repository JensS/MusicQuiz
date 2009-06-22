/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package musicquiz;

import java.util.prefs.*;

/**
 *
 * @author jens
 */
public class Game {

    private Database db;
    private Preferences prefs;
    private long time_start_ms;
    private long time_avg_ms;
    private Question currentQuestion;
    private int correctAnswersCount = 0;
    private int falseAnswersCount = 0;
    private boolean startable = false;

    Game() {
        db = new Database();
    }

    boolean isStartable() {
        if (getPreferences().get("music-dir", null) != null) {
            db.setMusicDir(getPreferences().get("music-dir", ""));
            db.scanDir();
            if (db.getSongsCount() > 4) {
                startable = true;
            }
        }
        return startable;
    }

    void startGame() {
        correctAnswersCount = falseAnswersCount = 0;
        time_avg_ms = time_start_ms = 0;
    }

    void prepareNewQuestion() {
        currentQuestion = new Question(this);
        if (time_avg_ms > 0)
        {
            time_avg_ms = (time_avg_ms + (System.currentTimeMillis() - time_start_ms)) / 2;
        }
        else
        {
            time_avg_ms = (System.currentTimeMillis() - time_start_ms);
        }
        time_start_ms = System.currentTimeMillis();
        System.out.println("Durchschnittszeit: " + (time_avg_ms /1000));
    }

    Question getQuestion() {
        if (currentQuestion == null) {
            prepareNewQuestion();
        }
        return currentQuestion;
    }

    Database getDbObj() {
        return db;
    }

    Preferences getPreferences() {
        if (prefs == null) {
            prefs = Preferences.userNodeForPackage(getClass());
        }
        return prefs;
    }

    void stopGame() {
        currentQuestion = null;
    }

    boolean isAQuestionLoaded()
    {
        return !(currentQuestion == null);
    }

    void displayResults() {
    }

    void validateAnswer(int button) {
        if (getQuestion().getCorrectSongNumber() == button) {
            correctAnswersCount++;
            System.out.println("Ja");
        } else {
            falseAnswersCount++;
            System.out.println("Nee");
        }
    }
}
