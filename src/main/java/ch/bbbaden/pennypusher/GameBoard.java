/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.pennypusher;

/**
 *
 * @author kians
 */
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public final class GameBoard extends JPanel {

    //Spielfeld
    private final int[][] playingfield = new int[6][13];
    private int kontostand = 100;
    private boolean mouse = false;
    private int highlightX;
    private int highlightl = this.getHeight() / 8;
    private int highlightm = 11 * this.getHeight() / 24;
    private int highlightr = 19 * this.getHeight() / 24;

    private final ImageIcon bg = new ImageIcon("src/main/java/ch/bbbaden/resources/playingfield.png");
    private final Image background = bg.getImage();
    private final ImageIcon c1 = new ImageIcon("src/main/java/ch/bbbaden/resources/coin1.png");
    private final Image coin1 = c1.getImage();
    private final ImageIcon c2 = new ImageIcon("src/main/java/ch/bbbaden/resources/coin2.png");
    private final Image coin2 = c2.getImage();
    private final ImageIcon c3 = new ImageIcon("src/main/java/ch/bbbaden/resources/coin3.png");
    private final Image coin3 = c3.getImage();
    private final ImageIcon c4 = new ImageIcon("src/main/java/ch/bbbaden/resources/coin4.png");
    private final Image coin4 = c4.getImage();
    private final ImageIcon c5 = new ImageIcon("src/main/java/ch/bbbaden/resources/coin5.png");
    private final Image coin5 = c5.getImage();
    private final ImageIcon c6 = new ImageIcon("src/main/java/ch/bbbaden/resources/coin6.png");
    private final Image coin6 = c6.getImage();

    Random rnd = new Random();
    private int coins;
    private int win;
    private int loss;
    private boolean move;

    //Buttons
    private final JButton btnLeft = new JButton("Left");
    private final JButton btnMid = new JButton("Middle");
    private final JButton btnRight = new JButton("Right");
    private final JButton btnStart = new JButton("Start");
    private final JButton btnHelp = new JButton("?");

    //Labels
    private final JLabel lblKontostand = new JLabel("Kontostand: ");
    private final JLabel lblWin = new JLabel("Win: ");
    private final JLabel lblLoss = new JLabel("Loss: ");

    public GameBoard() {
        initBoard();
        buttons();
    }

    private void initBoard() {
        //Spielbrett initialisieren
        setBackground(Color.white);
        setFocusable(true);

        //Münzen generieren
        generateCoins();

        //Buttons initialisieren
        add(btnLeft);
        add(btnMid);
        add(btnRight);
        add(btnStart);
        add(btnHelp);

        //Labels initialisieren
        add(lblKontostand);
        add(lblWin);
        add(lblLoss);
    }

    private void generateCoins() {
        //Array mit Münzen füllen
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 11; j++) {
                playingfield[i + 1][j + 1] = new Random().nextInt(6) + 1;
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
        highlight(g);

        Toolkit.getDefaultToolkit().sync();
    }

    private void highlight(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh
                = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);
        g2d.setStroke(new BasicStroke(1));

        if (mouse) {
            g2d.setColor(Color.red);
            for (int i = highlightX; i <= highlightX + (5 * this.getHeight() / 12) - 10; i += (this.getHeight() / 12)) {
                g2d.drawRect(i, this.getHeight() / 12, this.getHeight() / 12, this.getHeight() / 12);
            }
        }
        repaint();
    }

    public void buttons() {

        btnLeft.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent me) {
                mouse = true;
                highlightX = highlightl;
            }

            @Override
            public void mouseExited(MouseEvent me) {
                mouse = false;
            }
        });

        btnMid.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent me) {
                mouse = true;
                highlightX = highlightm;
            }

            @Override
            public void mouseExited(MouseEvent me) {
                mouse = false;
            }
        });

        btnRight.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent me) {
                mouse = true;
                highlightX = highlightr;
            }

            @Override
            public void mouseExited(MouseEvent me) {
                mouse = false;
            }
        });

        btnLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kontostand--;
                loss++;
                playingfield[0][new Random().nextInt(5)] += 1;
                btnLeft.setEnabled(false);
                repaint();
            }
        });

        btnMid.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kontostand--;
                loss++;
                playingfield[0][new Random().nextInt(5) + 4] += 1;
                btnMid.setEnabled(false);
                repaint();
            }
        });

        btnRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kontostand--;
                loss++;
                playingfield[0][new Random().nextInt(5) + 8] += 1;
                btnRight.setEnabled(false);
                repaint();
            }
        });

        btnHelp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HelpApplication ex = new HelpApplication();
                ex.setVisible(true);
            }
        });

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 13; i++) {

                    if (playingfield[0][i] != 0) {
                        playingfield[0][i] = 0;
                        playingfield[1][i]++;

                        if (playingfield[1][i] >= rnd.nextInt(6) + 1 || playingfield[1][i] >= 6) {
                            try {
                                move = true;
                                moreThanSix(1, i, playingfield[1][i]);
                                if (playingfield[2][i] >= rnd.nextInt(6) + 1 || playingfield[1][i] >= 6) {
                                    move = true;
                                    moreThanSix(2, i, playingfield[2][i]);
                                    if (playingfield[3][i] >= rnd.nextInt(6) + 1 || playingfield[3][i] >= 6) {
                                        move = true;
                                        moreThanSix(3, i, playingfield[3][i]);
                                        if (playingfield[4][i] >= rnd.nextInt(6) + 1 || playingfield[3][i] >= 6) {
                                            move = true;
                                            moreThanSix(4, i, playingfield[4][i]);
                                        }
                                    }
                                }
                            } catch (InterruptedException ex) {
                                Logger.getLogger(GameBoard.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                    if (i == 11) {
                        for (int j = 1; j < 12; j++) {
                            if (playingfield[0][j] != 0) {
                                i = j - 1;
                            }
                        }
                    }
                }
                for (int i = 0; i < 13; i++) {
                    if (playingfield[5][i] != 0) {
                        kontostand += playingfield[5][i];
                        win += playingfield[5][i];
                        playingfield[5][i] = 0;
                    }
                }
                for (int i = 0; i <= 5; i++) {
                    playingfield[i][0] = 0;
                    playingfield[i][12] = 0;
                }

                //repaint();
                btnLeft.setEnabled(true);
                btnMid.setEnabled(true);
                btnRight.setEnabled(true);
            }
        });
    }

    private void moreThanSix(int x, int y, int coin) throws InterruptedException {
        paintImmediately(0, 0, this.getWidth(), this.getHeight());

        for (int i = 1; i < 6; i++) {
            if (playingfield[i][0] != 0) {
                playingfield[i][0] = 0;
            } else if (playingfield[i][12] != 0) {
                playingfield[i][12] = 0;
            }
        }
        if (coin > 6 || move && x != 5) {
            do {
                if ((playingfield[x][y] - 1) <= 0) {
                    playingfield[x][y] = 2;
                }
                coins = rnd.nextInt(playingfield[x][y] - 1) + 1;
            } while (playingfield[x][y] - coins >= 6);

            move = false;

            paintImmediately(0, 0, this.getWidth(), this.getHeight());
            Thread.sleep(250);

            playingfield[x][y] -= coins;

            for (int j = 0; j < coins;) {
                int direction = rnd.nextInt(7);
                if (direction < 2 || y == 0 || y == 12 || x == 0) {
                    playingfield[x + 1][y] += 1;
                    paintImmediately(0, 0, this.getWidth(), this.getHeight());
                    j++;
                } else if (direction == 2) {
                    playingfield[x - 1][y] += 1;
                    paintImmediately(0, 0, this.getWidth(), this.getHeight());
                    j++;
                } else {
                    if (rnd.nextBoolean()) {
                        playingfield[x][y + 1] += 1;
                        paintImmediately(0, 0, this.getWidth(), this.getHeight());
                        j++;
                    } else {
                        playingfield[x][y - 1] += 1;
                        paintImmediately(0, 0, this.getWidth(), this.getHeight());
                        j++;
                    }
                }
            }
            moreThanSix(x + 1, y, playingfield[x + 1][y]);
            paintImmediately(0, 0, this.getWidth(), this.getHeight());
            if (x != 0) {
                moreThanSix(x - 1, y, playingfield[x - 1][y]);
            }
            paintImmediately(0, 0, this.getWidth(), this.getHeight());
            if (y != 12) {
                moreThanSix(x, y + 1, playingfield[x][y + 1]);
            }
            paintImmediately(0, 0, this.getWidth(), this.getHeight());
            if (y != 0) {
                moreThanSix(x, y - 1, playingfield[x][y - 1]);
            }
            paintImmediately(0, 0, this.getWidth(), this.getHeight());
        }
    }

    private void doDrawing(Graphics g) {

        highlightl = this.getHeight() / 8;
        highlightm = 11 * this.getHeight() / 24;
        highlightr = 19 * this.getHeight() / 24;

        //Buttons platzieren
        btnLeft.setBounds(this.getHeight() / 8, 5 * this.getHeight() / 8, this.getHeight() / 3, this.getHeight() / 8);
        btnLeft.setFont(new Font("Arial", Font.PLAIN, this.getHeight() / 20));

        btnMid.setBounds(this.getHeight() / 2, 5 * this.getHeight() / 8, this.getHeight() / 3, this.getHeight() / 8);
        btnMid.setFont(new Font("Arial", Font.PLAIN, this.getHeight() / 20));

        btnRight.setBounds(7 * this.getHeight() / 8, 5 * this.getHeight() / 8, this.getHeight() / 3, this.getHeight() / 8);
        btnRight.setFont(new Font("Arial", Font.PLAIN, this.getHeight() / 20));

        btnStart.setBounds(this.getHeight() / 2, 19 * this.getHeight() / 24, this.getHeight() / 3, this.getHeight() / 8);
        btnStart.setFont(new Font("Arial", Font.PLAIN, this.getHeight() / 20));

        btnHelp.setBounds(73 * this.getWidth() / 80, this.getHeight() / 60, this.getHeight() / 12, this.getHeight() / 12);
        btnHelp.setFont(new Font("Arial", Font.BOLD, 11 * this.getHeight() / 300));

        lblKontostand.setLocation(this.getHeight() / 8, this.getHeight() / 60);
        lblKontostand.setText("Kontostand: " + kontostand);
        lblKontostand.setFont(new Font("Arial", Font.PLAIN, this.getHeight() / 20));

        lblWin.setLocation(5 * this.getHeight() / 8, this.getHeight() / 60);
        lblWin.setText("Win: " + win);
        lblWin.setFont(new Font("Arial", Font.PLAIN, this.getHeight() / 20));

        lblLoss.setLocation(24 * this.getHeight() / 25, this.getHeight() / 60);
        lblLoss.setText("Loss: " + loss);
        lblLoss.setFont(new Font("Arial", Font.PLAIN, this.getHeight() / 20));

        //Graphics initialisieren
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh
                = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setFont(new Font("Arial", Font.BOLD, 30));
        g2d.setRenderingHints(rh);
        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(Color.black);

        //Grid zeichnen
        int x = this.getHeight() / 8;
        int y = this.getHeight() / 12;

        g2d.drawImage(background, x, y, 13 * this.getHeight() / 12, this.getHeight() / 2, this);

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 13; j++) {

                switch (playingfield[i][j]) {
                    case 0:

                        break;
                    case 1:
                        g2d.drawImage(coin1, x, y, this.getHeight() / 12, this.getHeight() / 12, this);
                        break;
                    case 2:
                        g2d.drawImage(coin2, x, y, this.getHeight() / 12, this.getHeight() / 12, this);
                        break;
                    case 3:
                        g2d.drawImage(coin3, x, y, this.getHeight() / 12, this.getHeight() / 12, this);
                        break;
                    case 4:
                        g2d.drawImage(coin4, x, y, this.getHeight() / 12, this.getHeight() / 12, this);
                        break;
                    case 5:
                        g2d.drawImage(coin5, x, y, this.getHeight() / 12, this.getHeight() / 12, this);
                        break;
                    case 6:
                        g2d.drawImage(coin6, x, y, this.getHeight() / 12, this.getHeight() / 12, this);
                        break;
                    default:
                        if (playingfield[i][j] > 6) {
                            g2d.drawImage(coin6, x, y, this.getHeight() / 12, this.getHeight() / 12, this);
                        }
                }
//                if (playingfield[i][j] != 0) {
//                    g2d.drawString(String.valueOf(playingfield[i][j]), x + 20, y + 40);
//                }
                x += this.getHeight() / 12;
            }
            y += this.getHeight() / 12;
            x = this.getHeight() / 8;
        }
    }
}
