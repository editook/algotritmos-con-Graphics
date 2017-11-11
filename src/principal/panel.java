/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import java.awt.Color;
import javax.swing.JFrame;

/**
 *
 * @author Usuario
 */
public class panel {

    JFrame panel;

    public panel() {
        panel = new JFrame();
        panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.getContentPane().setBackground(Color.WHITE);
        panel.getContentPane().setLayout(null);
        panel.setResizable(true);
        componentes comp = new componentes(panel);
        panel.pack();
        panel.setBounds(515, 0, 900, 490);
        //panel.setLocationRelativeTo(null);
        panel.setVisible(true);
    }
}
