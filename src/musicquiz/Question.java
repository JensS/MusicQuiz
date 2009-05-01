/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package musicquiz;


/**
 *
 * @author jens
 */
public class Question {
    private Song song;

    Question(Song song)
    {
        this.song = song;
    }

    String getQuestionString()
    {
        return song.getSongname();
    }

    Cover getCover()
    {
        return new Cover(MusicQuizApp.getInstance().getClass().getResource("musicquiz.png").getPath());
    }

    Song getSong()
    {
        return song;
    }
}
