import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class Gui2 implements MouseListener {

    MyFrame game;
    SubPanels time, score, exit;
    Background motherPanel;
    StartScreen startScreen;
    JPanel first, second, third, temp;
    long startTime;
    PaneledGame blocks;
    BlockGrid gameBlockgrid;
    int bombTimer, transTimer;
    Random r;
    Clip clip;
    int level;
    List<Runnable> delegate;
    final int a = 0;

    public Gui2() {
        delegate=new ArrayList<>();
        r = new Random(System.currentTimeMillis());
        bombTimer = r.nextInt(10000);
        transTimer = r.nextInt(10000);
        game = new MyFrame();
        delegate.add(game::createDoubleBufferStrategy);
        initStartScreen();
    }

    public void initStartScreen() {
        startScreen = new StartScreen(this);
        startScreen.setBounds(game.getBounds().x, game.getBounds().y + 30, game.getWidth(), game.getHeight() - 30);
        game.add(startScreen);
        game.setVisible(true);
        delegate.remove(0).run();
    }

    public void initGame() {
        level = startScreen.getCheckedRadio();
        game.remove(startScreen);
        game.setVisible(false);
        gameBlockgrid = new BlockGrid(9, level);
        time = new SubPanels("00:00");
        score = new SubPanels(String.valueOf(gameBlockgrid.getScore()));
        GridBagConstraints c;
        startTime = System.currentTimeMillis();
        motherPanel = new Background();
        motherPanel.setLayout(new GridBagLayout());
        motherPanel.setBounds(game.getBounds().x, game.getBounds().y + 30, game.getWidth(), game.getHeight() - 30);
        //	motherPanel.setLocation(game.getWidth(),30);
        first = new JPanel(new GridBagLayout());
        second = new JPanel(new GridBagLayout());
        third = new JPanel(new GridBagLayout());
        exit = new SubPanels("Exit");
        exit.addMouseListener(this);
        blocks = new PaneledGame(gameBlockgrid);
        blocks.addMouseListener(this);

        timeUpdater(time);
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.weighty = 1;
        c.weightx = 6;
        motherPanel.add(first, c);
        first.setBackground(new Color(0, 0, 0, a));
        c.weightx = 1;
        c.gridx = 1;
        motherPanel.add(second, c);
        second.setBackground(new Color(0, 0, 0, a));
        // //c.fill=GridBagConstraints.VERTICAL;
        c.gridx = 0;
        c.gridy = 0;
        c.weighty = 8;
        c.weightx = 6;
        first.add(blocks, c);
        blocks.setBackground(new Color(0, 0, 0, a));
        c.gridy = 1;
        c.weighty = 1;
        c.weightx = 1;

        first.add(third, c);
        third.setBackground(new Color(0, 0, 0, a));
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        third.add(exit, c);
        exit.setBackground(new Color(0, 0, 0, a));
        c.gridx = 1;
        c.weightx = 5;
        temp = new JPanel();
        temp.setBackground(new Color(0, 0, 0, a));
        //temp.setOpaque(true);
        third.add(temp, c);

        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        second.add(score, c);
        score.setBackground(new Color(0, 0, 0, a));
        c.gridx = 0;
        c.gridy = 1;
        second.add(time, c);
        time.setBackground(new Color(0, 0, 0, a));
        c.gridx = 0;
        c.gridy = 2;
        c.weighty = 8;
        temp = new JPanel();
        //temp.setOpaque(true);
        temp.setBackground(new Color(0, 0, 0, a));
        second.add(temp, c);
        game.add(motherPanel);
        //game.setUndecorated(true);
        loadSound();
        bombSpawnerStart();
        transSpawnerStart();
        game.setVisible(true);
        clip.start();
    }

    private void transSpawnerStart() {
        // TODO Auto-generated method stub
        Thread tTR = new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    Thread.sleep(transTimer);
                    gameBlockgrid.getGrid()[gameBlockgrid.getTransX()][gameBlockgrid.getTransY()].setTransparent(false);
                    gameBlockgrid.spawnTransparent();
                    transTimer = (r.nextInt(10000) + 3000) % 13000;
                    motherPanel.repaint();
                    blocks.repaint();
                    //File f=new File("res/trans.wav");
                    AudioInputStream as = AudioSystem.getAudioInputStream(getClass().getResource("res/trans.wav"));
                    Clip cl = AudioSystem.getClip();
                    cl.open(as);
                    cl.start();
                    transSpawnerStart();
                } catch (InterruptedException | UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        });
        tTR.start();
    }

    private void bombSpawnerStart() {
        // TODO Auto-generated method stub
        Thread tB = new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    Thread.sleep(bombTimer);
                    gameBlockgrid.getGrid()[gameBlockgrid.getBombX()][gameBlockgrid.getBombY()].setBomb(false);
                    gameBlockgrid.spawnBomb();
                    bombTimer = (r.nextInt(10000) + 3000) % 13000;
                    //System.out.println(bombTimer);
                    motherPanel.repaint();
                    //blocks.repaint();
                    //File f=new File("res/bomb.wav");
                    AudioInputStream as = AudioSystem.getAudioInputStream(getClass().getResource("res/bomb.wav"));
                    Clip cl = AudioSystem.getClip();
                    cl.open(as);
                    cl.start();
                    bombSpawnerStart();
                } catch (UnsupportedAudioFileException | IOException | InterruptedException | LineUnavailableException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        });
        tB.start();
    }

    private void loadSound() {
        // TODO Auto-generated method stub

        try {
            //File f=new File("res/aza.wav");
            AudioInputStream aIO = AudioSystem.getAudioInputStream(getClass().getResource("res/aza.wav"));
            clip = AudioSystem.getClip();
            clip.open(aIO);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            //clip.start();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        // timeUpdater();
        if (e.getSource().equals(blocks)) {
            int x = blocks.getMousePosition().x / (blocks.getWidth() / (gameBlockgrid.getSize()));
            int y = blocks.getMousePosition().y / (blocks.getHeight() / (gameBlockgrid.getSize()));
            //System.out.println("x: " + x + ",y: " + y);
            if (x < gameBlockgrid.getSize() && y < gameBlockgrid.getSize()) {
                //		System.out.println(gameBlockgrid.getGrid()[y][x].isVisited());
                if (gameBlockgrid.getGrid()[y][x].isBomb())
                    gameBlockgrid.remove(y, x);
                else
                    gameBlockgrid.remove(y, x, true);
                gameBlockgrid.setScore(gameBlockgrid.getScore() + (gameBlockgrid.getDestroyed()
                        * (gameBlockgrid.getDestroyed() - 1)));
                gameBlockgrid.setDestroyed(0);
                //BlockGrid.grid2=x.getGrid();
                gameBlockgrid.dropping();
                //System.out.println("this is dropped: "+BlockGrid.dropped);
                if (gameBlockgrid.isDropped()) {
                    blocks.removeMouseListener(this);
                    lunchDropping();
                }
                score.setWhat(String.valueOf(gameBlockgrid.getScore()));
                motherPanel.repaint();
                //gameBlockgrid.printTheGrid();
            }


//			if (y == size && x < 2) {
//				System.exit(0);
//			}
        }
        if (e.getSource().equals(exit)) {
            System.exit(0);
        }
    }

    private void lunchDropping() {
        // TODO Auto-generated method stub
        if (thereDroppings()) {
            //	System.out.println("this lunched");
            //blocks.setFact(blocks.getFact()+0.11f);
            blocks.setFact(1f);
            //blocks.paint(blocks.getGraphics());
            motherPanel.repaint();
            blocks.repaint();
            Thread x = new Thread(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    try {
                        Thread.sleep(300);
                        lunchDropping();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });
            x.start();
        } else {
            gameBlockgrid.setDropped(false);
            blocks.setFact(0);
            blocks.addMouseListener(this);
        }
    }

    private boolean thereDroppings() {
        // TODO Auto-generated method stub
        for (int i = 0; i < gameBlockgrid.getGrid().length; i++) {
            for (int j = 0; j < gameBlockgrid.getGrid()[0].length; j++) {
                if (gameBlockgrid.getGrid()[i][j].getDropping() > 0)
                    return (true);
            }
        }
        return false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    public void timeUpdater(SubPanels c) {
        // TODO Auto-generated method stub
        //motherPanel.repaint();
        //c.repaint();
        Thread w = new Thread(() ->
        {
            try {
                Thread.sleep(1000);
                c.setWhat(String.valueOf(String.format(
                        "%02d:%02d",
                        ((System.currentTimeMillis() - startTime) / 1000) / 60,
                        (System.currentTimeMillis() - startTime) / 1000 % 60)));
                timeUpdater(c);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        });
        w.start();

    }

}
