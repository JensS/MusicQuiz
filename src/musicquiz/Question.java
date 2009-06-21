/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package musicquiz;

import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author jens
 */
public class Question {
    private HashMap songs;
    private int correctSong;

    Question(Game gameObj) {
        for (int i = 0; i < 4; i++)
        {
            //songs.put(i, gameObj.getDbObj().getSongs().get(gameObj.getRandomObj().nextInt(gameObj.getDbObj().getSongs().size())));
        }
    }

    String getQuestionString()
    {
        return (String) "What Song do you hear?";
    }

    Cover getCover()
    {
        return new Cover(MusicQuizApp.getInstance().getClass().getResource("musicquiz.png").getPath());
    }

    private Song getSong(int n)
    {
        return (Song) songs.get(n);
    }

    String getAnswer1Txt()
    {
        return getSong(0).getSongname();
    }
    String getAnswer2Txt()
    {
        return getSong(1).getSongname();
    }
    String getAnswer3Txt()
    {
        return getSong(2).getSongname();
    }
    String getAnswer4Txt()
    {
        return getSong(3).getSongname();
    }
}
