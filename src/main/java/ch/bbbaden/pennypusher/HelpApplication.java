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
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class HelpApplication extends JFrame {

    private final ImageIcon img = new ImageIcon("src/main/java/ch/bbbaden/resources/coin6.png");

    public HelpApplication() {

        initUI();
    }

    private void initUI() {
        add(new HelpBoard());

        setSize(800, 600);

        setIconImage(img.getImage());

        setTitle("Help");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
    }

}
