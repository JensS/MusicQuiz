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
    private long time_sum_ms;
    private Question currentQuestion;
    private int correctAnswersCount = 0;
    private int falseAnswersCount = 0;
    private boolean startable = false;
    private int level;
    private int answers_to_complete_level;

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

    int getPlaybackTime() {
        return (int) 60 - 10*level;
    }

    void startGame() {
        correctAnswersCount = falseAnswersCount = 0;
        level = 1;
        answers_to_complete_level = 2;
        time_sum_ms = time_start_ms = 0;
    }

    void prepareNewQuestion() {
        currentQuestion = new Question(this);
        if ((correctAnswersCount + falseAnswersCount) > 0)
            time_sum_ms += System.currentTimeMillis() - time_start_ms;
        time_start_ms = System.currentTimeMillis();
    }
    
    int getAverageTimeSeconds()
    {
        if ((correctAnswersCount + falseAnswersCount) == 0)
            return 0;
        return (int) (time_sum_ms / (correctAnswersCount + falseAnswersCount) / 1000);
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

    int getLevel() {
        return level;
    }
    
    void stopGame() {
        currentQuestion = null;
    }

    boolean isAQuestionLoaded()
    {
        return !(currentQuestion == null);
    }

    void validateAnswer(int button) {
        if (getQuestion().getCorrectSongNumber() == button) {
            correctAnswersCount++;
            answers_to_complete_level--;
            if (answers_to_complete_level == 0)
            {
                level++;
                answers_to_complete_level = 2 * level;
            }
            System.out.println("Ja");
        } else {
            falseAnswersCount++;
            System.out.println("Nee");
        }
    }
}
