/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.pennypusher;

import java.awt.EventQueue;

/**
 *
 * @author kians
 */
public class Starter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            TitleApplication ex = new TitleApplication();
            ex.setVisible(true);
        });
    }

}
