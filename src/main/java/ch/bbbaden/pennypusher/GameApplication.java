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
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class GameApplication extends JFrame {

    private final ImageIcon img = new ImageIcon("src/main/java/ch/bbbaden/resources/coin6.png");

    public GameApplication() {

        initUI();
    }

    private void initUI() {
        add(new GameBoard());

        setSize(1440, 1080);
        
        setIconImage(img.getImage());

        setTitle("Penny Pusher");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

}
