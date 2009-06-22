/*
 * MusicBoxSettings.java
 *
 * Created on 26.04.2009, 19:00:09
 */
package musicquiz;

import org.jdesktop.application.Action;
import javax.swing.JFileChooser;
import java.io.File;

/**
 * @author <j.sage@bgx-mail.de> Jens Sage
 * @author <manuel@bgx-mail.de> Manuel Prömel
 */
public class MusicQuizSettings extends javax.swing.JDialog {
    private Game game;
    final JFileChooser fc;

    public MusicQuizSettings(java.awt.Frame parent, Game gameObj) {
        super(parent);
        this.game = gameObj;

        initComponents();

        fc = new JFileChooser();
        fc.setCurrentDirectory(new File(game.getPreferences().get("music-dir", fc.getCurrentDirectory().toString())));
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        dirChooserBtn.setText((String) game.getPreferences().get("music-dir", "Select Dir"));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        dirChooserBtn = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(musicquiz.MusicQuizApp.class).getContext().getResourceMap(MusicQuizSettings.class);
        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(musicquiz.MusicQuizApp.class).getContext().getActionMap(MusicQuizSettings.class, this);
        dirChooserBtn.setAction(actionMap.get("fileChooseBtnClicked")); // NOI18N
        dirChooserBtn.setText(resourceMap.getString("fileChooserBtn.text")); // NOI18N
        dirChooserBtn.setName("fileChooserBtn"); // NOI18N

        saveButton.setAction(actionMap.get("saveBtnClicked")); // NOI18N
        saveButton.setText(resourceMap.getString("saveButton.text")); // NOI18N
        saveButton.setName("saveButton"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                        .addGap(45, 45, 45)
                        .addComponent(dirChooserBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(dirChooserBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(saveButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed

    }//GEN-LAST:event_formWindowClosed

    @Action
    public void fileChooseBtnClicked() {
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            game.getPreferences().put("music-dir", fc.getSelectedFile().getAbsolutePath());
        }

        dirChooserBtn.setText((String) game.getPreferences().get("music-dir", "Select Dir"));
        pack();
    }

    @Action
    public void saveBtnClicked() {
        game.isStartable();
        dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton dirChooserBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton saveButton;
    // End of variables declaration//GEN-END:variables
}
