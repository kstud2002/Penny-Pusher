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
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public final class TitleBoard extends JPanel {

    //Spielfeld
    private final ImageIcon tbg = new ImageIcon("src/main/java/ch/bbbaden/resources/titlebackground.png");
    private final Image titlebackground = tbg.getImage();
    private final ImageIcon title = new ImageIcon("src/main/java/ch/bbbaden/resources/pennypusher.png");
    private final Image pennypusher = title.getImage();

    //Buttons
    private final JButton btnBack = new JButton("Back to Lobby");
    private final JButton btnStart = new JButton("Start");

    //Labels
    private final JLabel lblKontostand = new JLabel("Kontostand");
    private final JLabel lblName = new JLabel("Name");
    //private final JLabel lblTitle = new JLabel("Penny Pusher");

    public TitleBoard() {
        initBoard();
        buttons();
    }

    private void initBoard() {
        //Spielbrett initialisieren
        setBackground(Color.white);
        setFocusable(true);

        //Buttons initialisieren
        add(btnBack);
        add(btnStart);

        //Labels initialisieren
        add(lblKontostand);
        add(lblName);
        //add(lblTitle);

        this.setSize(100, 100);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);

        Toolkit.getDefaultToolkit().sync();
    }

    public void buttons() {
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComponent comp = (JComponent) e.getSource();
                Window win = SwingUtilities.getWindowAncestor(comp);
                win.dispose();

                GameApplication ex = new GameApplication();
                ex.setVisible(true);
            }
        });
    }

    private void doDrawing(Graphics g) {

        //Graphics initialisieren
        Graphics2D g2d = (Graphics2D) g;

        RenderingHints rh
                = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        //g2d.scale(1.8, 1.8);
        g2d.setFont(new Font("Arial", Font.BOLD, 30));
        g2d.setRenderingHints(rh);
        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(Color.WHITE);

        g2d.drawImage(titlebackground, 0, 0, this.getWidth(), this.getHeight(), this);
        g2d.drawImage(pennypusher, this.getWidth() / 2 - pennypusher.getWidth(null) / 2, this.getHeight() / 4, this);

        //Buttons platzieren
        btnStart.setBounds(this.getHeight() / 2, 3 * this.getHeight() / 4, this.getHeight() / 3, this.getHeight() / 8);
        //btnStart.setBounds(300, 450, 200, 75);
        btnStart.setFont(new Font("Arial", Font.BOLD, this.getHeight() / 20));

        btnBack.setBounds(this.getHeight() / 30, this.getHeight() / 20, this.getHeight() / 4, this.getHeight() / 8);
        btnBack.setFont(new Font("Arial", Font.BOLD, this.getHeight() / 40));

        lblKontostand.setLocation(this.getHeight(), this.getHeight() / this.getHeight() / 20);
        lblKontostand.setForeground(Color.WHITE);
        //lblKontostand.setBorder(LineBorder.createBlackLineBorder());
        lblKontostand.setFont(new Font("Arial", Font.BOLD, this.getHeight() / 20));

        lblName.setLocation(this.getHeight(), this.getHeight() / 10);
        lblName.setForeground(Color.WHITE);
        //g2d.fillRect(lblName.getX(), lblName.getY(), lblName.getWidth(), lblName.getHeight());
        //lblName.setBorder(LineBorder.createBlackLineBorder());
        lblName.setFont(new Font("Arial", Font.BOLD, this.getHeight() / 20));
        //        lblTitle.setLocation(50, 200);
        //        lblTitle.setForeground(Color.WHITE);
        //        //g2d.fillRect(lblTitle.getX(), lblTitle.getY(), lblTitle.getWidth(), lblTitle.getHeight());
        //        //lblTitle.setBorder(LineBorder.createBlackLineBorder());
        //        lblTitle.setFont(new Font("Arial", Font.BOLD, 100));

    }
}
