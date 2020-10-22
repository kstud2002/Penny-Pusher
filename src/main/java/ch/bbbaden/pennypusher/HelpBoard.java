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
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public final class HelpBoard extends JPanel {

    //Spielfeld
    private final ImageIcon tbg = new ImageIcon("src/main/java/ch/bbbaden/resources/helpscreen.png");
    private final Image playingfield = tbg.getImage();

    private final JButton btnClose = new JButton("Schliessen");

    public HelpBoard() {
        initBoard();
        buttons();
    }

    private void initBoard() {
        //Spielbrett initialisieren
        setBackground(Color.white);
        setFocusable(true);

        //Buttons initialisieren
        add(btnClose);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);

        Toolkit.getDefaultToolkit().sync();
    }

    public void buttons() {

        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComponent comp = (JComponent) e.getSource();
                Window win = SwingUtilities.getWindowAncestor(comp);
                win.dispose();
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

        g2d.drawImage(playingfield, 0, 0, this);

        //Buttons platzieren
        //btnStart.setBounds(this.getHeight()/2, 3*this.getHeight()/4, this.getHeight()/3, this.getHeight()/8);
        btnClose.setBounds(300, 450, 200, 75);
        btnClose.setFont(new Font("Arial", Font.BOLD, 30));

    }

}
