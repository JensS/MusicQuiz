/*
 * MusicQuizView.java
 */
package musicquiz;

import java.awt.Color;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.util.TimerTask;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 * The application's main frame.
 */
public class MusicQuizView extends FrameView {

    private Game game;
    private MusicQuizSettings settingsFrame;
    private JButton markedBtn;

    public MusicQuizView(SingleFrameApplication app) {
        super(app);

        initComponents();

        game = new Game();
        startGameBtn.setEnabled(game.isStartable());

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new javax.swing.Timer(messageTimeout, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {

            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String) (evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer) (evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
        setComponent(homePanel);
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = MusicQuizApp.getApplication().getMainFrame();
            aboutBox = new MusicQuizAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        MusicQuizApp.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        quizPanel = new javax.swing.JPanel();
        questionLabel = new javax.swing.JLabel();
        coverCanvas = new java.awt.Canvas();
        answersContainerPanel = new javax.swing.JPanel();
        answer1Btn = new javax.swing.JButton();
        answer3Btn = new javax.swing.JButton();
        answer2Btn = new javax.swing.JButton();
        answer4Btn = new javax.swing.JButton();
        quitQuizBtn = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        homePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        startGameBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        quizPanel.setName("quizPanel"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(musicquiz.MusicQuizApp.class).getContext().getResourceMap(MusicQuizView.class);
        questionLabel.setFont(resourceMap.getFont("questionLabel.font")); // NOI18N
        questionLabel.setText(resourceMap.getString("questionLabel.text")); // NOI18N
        questionLabel.setName("questionLabel"); // NOI18N

        coverCanvas.setName("coverCanvas"); // NOI18N

        answersContainerPanel.setName("answersContainerPanel"); // NOI18N

        answer1Btn.setText(resourceMap.getString("answer1Btn.text")); // NOI18N
        answer1Btn.setName("answer1Btn"); // NOI18N
        answer1Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                answer1BtnMousePressed(evt);
            }
        });

        answer3Btn.setText(resourceMap.getString("answer3Btn.text")); // NOI18N
        answer3Btn.setName("answer3Btn"); // NOI18N
        answer3Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                answer3BtnMousePressed(evt);
            }
        });

        answer2Btn.setText(resourceMap.getString("answer2Btn.text")); // NOI18N
        answer2Btn.setName("answer2Btn"); // NOI18N
        answer2Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                answer2BtnMousePressed(evt);
            }
        });

        answer4Btn.setText(resourceMap.getString("answer4Btn.text")); // NOI18N
        answer4Btn.setName("answer4Btn"); // NOI18N
        answer4Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                answer4BtnMousePressed(evt);
            }
        });

        javax.swing.GroupLayout answersContainerPanelLayout = new javax.swing.GroupLayout(answersContainerPanel);
        answersContainerPanel.setLayout(answersContainerPanelLayout);
        answersContainerPanelLayout.setHorizontalGroup(
            answersContainerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(answersContainerPanelLayout.createSequentialGroup()
                .addGroup(answersContainerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(answer1Btn)
                    .addComponent(answer3Btn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 278, Short.MAX_VALUE)
                .addGroup(answersContainerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(answer2Btn, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(answer4Btn, javax.swing.GroupLayout.Alignment.TRAILING)))
        );
        answersContainerPanelLayout.setVerticalGroup(
            answersContainerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(answersContainerPanelLayout.createSequentialGroup()
                .addGroup(answersContainerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(answer1Btn)
                    .addComponent(answer2Btn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(answersContainerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(answer3Btn, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(answer4Btn, javax.swing.GroupLayout.Alignment.TRAILING)))
        );

        quitQuizBtn.setText(resourceMap.getString("quitQuizBtn.text")); // NOI18N
        quitQuizBtn.setName("quitQuizBtn"); // NOI18N
        quitQuizBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                quitBtnPressedAction(evt);
            }
        });

        javax.swing.GroupLayout quizPanelLayout = new javax.swing.GroupLayout(quizPanel);
        quizPanel.setLayout(quizPanelLayout);
        quizPanelLayout.setHorizontalGroup(
            quizPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(quizPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(quizPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(answersContainerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(coverCanvas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(questionLabel)
                    .addComponent(quitQuizBtn, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        quizPanelLayout.setVerticalGroup(
            quizPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(quizPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(quizPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(coverCanvas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(questionLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(answersContainerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addComponent(quitQuizBtn)
                .addContainerGap())
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(musicquiz.MusicQuizApp.class).getContext().getActionMap(MusicQuizView.class, this);
        jMenuItem1.setAction(actionMap.get("settingsBtnPressed")); // NOI18N
        jMenuItem1.setIcon(resourceMap.getIcon("settingsMenuItem.icon")); // NOI18N
        jMenuItem1.setText(resourceMap.getString("settingsMenuItem.text")); // NOI18N
        jMenuItem1.setName("settingsMenuItem"); // NOI18N
        fileMenu.add(jMenuItem1);

        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 214, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        homePanel.setName("homePanel"); // NOI18N

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        startGameBtn.setAction(actionMap.get("startQuizPressed")); // NOI18N
        startGameBtn.setText(resourceMap.getString("startButton.text")); // NOI18N
        startGameBtn.setName("startButton"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        javax.swing.GroupLayout homePanelLayout = new javax.swing.GroupLayout(homePanel);
        homePanel.setLayout(homePanelLayout);
        homePanelLayout.setHorizontalGroup(
            homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homePanelLayout.createSequentialGroup()
                .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(homePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(homePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2))
                    .addGroup(homePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(startGameBtn)))
                .addContainerGap(150, Short.MAX_VALUE))
        );
        homePanelLayout.setVerticalGroup(
            homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(startGameBtn)
                .addContainerGap(164, Short.MAX_VALUE))
        );

        setComponent(quizPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void quitBtnPressedAction(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_quitBtnPressedAction
        if (game.isAQuestionLoaded()) {
            game.getQuestion().getCorrectSong().stopPlayer();
        }
        game.stopGame();
        game.displayResults();
        setComponent(homePanel);
        getFrame().pack();
    }//GEN-LAST:event_quitBtnPressedAction

    private void questionAnswered(int answer_n) {
        markedBtn = getButton(game.getQuestion().getCorrectSongNumber());
        markedBtn.setForeground(Color.GREEN);
        game.validateAnswer(answer_n);
        game.getQuestion().getCorrectSong().stopPlayer();
        java.util.Timer timer = new java.util.Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                MusicQuizView.this.game.prepareNewQuestion();
                MusicQuizView.this.assignQuestion();
                MusicQuizView.this.markedBtn.setForeground(null);
            }
        }, 1000);
    }

    private JButton getButton(int number) {
        switch (number) {
            case 1:
                return answer1Btn;
            case 2:
                return answer2Btn;
            case 3:
                return answer3Btn;
            case 4:
                return answer4Btn;
            default:
                return null;
        }
    }

    private void answer2BtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_answer2BtnMousePressed
        questionAnswered(2);
    }//GEN-LAST:event_answer2BtnMousePressed

    private void answer1BtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_answer1BtnMousePressed
        questionAnswered(1);
    }//GEN-LAST:event_answer1BtnMousePressed

    private void answer3BtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_answer3BtnMousePressed
        questionAnswered(3);
    }//GEN-LAST:event_answer3BtnMousePressed

    private void answer4BtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_answer4BtnMousePressed
        questionAnswered(4);
    }//GEN-LAST:event_answer4BtnMousePressed

    @Action
    public void startQuizPressed() {
        game.startGame();
        setComponent(quizPanel);
        assignQuestion();
    }

    public void assignQuestion() {
        questionLabel.setText(game.getQuestion().getQuestionString());
        answer1Btn.setText(game.getQuestion().getAnswer1Txt());
        answer2Btn.setText(game.getQuestion().getAnswer2Txt());
        answer3Btn.setText(game.getQuestion().getAnswer3Txt());
        answer4Btn.setText(game.getQuestion().getAnswer4Txt());
        getFrame().pack();
        playSong();
    }

    private void playSong() {
        game.getQuestion().getCorrectSong().play(15); //@todo use preferences... ?
    }

    @Action
    public void settingsBtnPressed() {
        if (settingsFrame == null) {
            JFrame mainFrame = MusicQuizApp.getApplication().getMainFrame();
            settingsFrame = new MusicQuizSettings(mainFrame, game);
            settingsFrame.setLocationRelativeTo(mainFrame);
        }
        MusicQuizApp.getApplication().show(settingsFrame);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton answer1Btn;
    private javax.swing.JButton answer2Btn;
    private javax.swing.JButton answer3Btn;
    private javax.swing.JButton answer4Btn;
    private javax.swing.JPanel answersContainerPanel;
    private java.awt.Canvas coverCanvas;
    private javax.swing.JPanel homePanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel questionLabel;
    private javax.swing.JButton quitQuizBtn;
    private javax.swing.JPanel quizPanel;
    private javax.swing.JButton startGameBtn;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    // End of variables declaration//GEN-END:variables
    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;
    private JDialog aboutBox;
}
