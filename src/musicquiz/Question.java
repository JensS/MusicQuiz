/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package musicquiz;

import java.util.Random;

/**
 * @author <j.sage@bgx-mail.de> Jens Sage
 * @author <manuel@bgx-mail.de> Manuel Prömel
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

            //valid song?
            while ((getSong(i) == null) || (getSong(i).getSongname() == null) || getSong(i).getSongname().equals(""))
            {
                songsList[i] = randomObj.nextInt(gameObj.getDbObj().getSongs().size());
            }
        }
        correctSong = randomObj.nextInt(4);
    }

    String getQuestionString() {
        return (String) "Which Song do you hear?";
    }

    private Song getSong(int n) {
        return (Song) gameObj.getDbObj().getSongs().get(songsList[n]);
    }

    Song getCorrectSong() {
        return getSong(correctSong);
    }

    int getCorrectSongNumber() {
        return correctSong+1;
    }

    String getAnswer1Txt() {
        return getSong(0).getArtist() + " - " + getSong(0).getSongname();
    }

    String getAnswer2Txt() {
        return getSong(1).getArtist() + " - " + getSong(1).getSongname();
    }

    String getAnswer3Txt() {
        return getSong(2).getArtist() + " - " + getSong(2).getSongname();
    }

    String getAnswer4Txt() {
        return getSong(3).getArtist() + " - " + getSong(3).getSongname();
    }
}
