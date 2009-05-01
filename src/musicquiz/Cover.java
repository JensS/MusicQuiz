/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package musicquiz;

import java.awt.*;

/**
 *
 * @author jens
 */
public class Cover extends Canvas {

    private Image img = null;

    Cover(String sFile) {
        img = getToolkit().getImage(sFile);
        MediaTracker mt = new MediaTracker(this);
        mt.addImage(img, 0);
        try {
            mt.waitForAll();
        } catch (InterruptedException ex) {
        }
    }

    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, this);
    }

    public Dimension getPreferredSize() {
        return new Dimension(img.getWidth(this), img.getHeight(this));
    }

    public Dimension getMinimumSize() {
        return getPreferredSize();
    }
}
