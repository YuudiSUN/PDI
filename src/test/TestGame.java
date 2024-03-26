package test;

import game.Interface; 

public class TestGame {
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Interface(); 
            }
        });
    }
}
	