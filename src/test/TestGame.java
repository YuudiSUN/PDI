package test;

import gui.Interface; 

public class TestGame {
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Interface(); 
            }
        });
    }
}
	