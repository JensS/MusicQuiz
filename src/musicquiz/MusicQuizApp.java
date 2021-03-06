/*
 * MusicQuizApp.java
 */

package musicquiz;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * @author <j.sage@bgx-mail.de> Jens Sage
 * @author <manuel@bgx-mail.de> Manuel Prömel
 */
public class MusicQuizApp extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        show(new MusicQuizView(this));
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of MusicQuizApp
     */
    public static MusicQuizApp getApplication() {
        return Application.getInstance(MusicQuizApp.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        launch(MusicQuizApp.class, args);
    }
}
