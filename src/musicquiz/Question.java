/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package musicquiz;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author jens
 */
public class Question {

    private int[] songsList;
    private int correctSong;
    private Game gameObj;
    private Random randomObj;

    Question(Game gameObj) {
        this.gameObj = gameObj;
        songsList = new int[4];
        randomObj = new Random();
        for (int i = 0; i < 4; i++) {
            songsList[i] = randomObj.nextInt(gameObj.getDbObj().getSongs().size());
        }
        correctSong = randomObj.nextInt(4);
    }

    String getQuestionString() {
        return (String) "What Song do you hear?";
    }

    Cover getCover() {
        return new Cover(MusicQuizApp.getInstance().getClass().getResource("musicquiz.png").getPath());
    }

    private Song getSong(int n) {
        return (Song) gameObj.getDbObj().getSongs().get(songsList[n]);
    }

    Song getCorrectSong() {
        return getSong(correctSong);
    }

    String getAnswer1Txt() {
        return getSong(0).getSongname();
    }

    String getAnswer2Txt() {
        return getSong(1).getSongname();
    }

    String getAnswer3Txt() {
        return getSong(2).getSongname();
    }

    String getAnswer4Txt() {
        return getSong(3).getSongname();
    }
}
