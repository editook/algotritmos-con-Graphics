package principal.vehiculo;

import java.awt.Color;
import javax.swing.JFrame;

public class vista {
    
    JFrame panel;
    actividad comp;
    
    public vista() {
        panel = new JFrame();
        panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.getContentPane().setBackground(Color.BLACK);
        panel.getContentPane().setLayout(null);
        panel.setResizable(true);
        comp = new actividad(panel);
        panel.pack();
        panel.setBounds(0, 0, 515, 420);
        //panel.setLocationRelativeTo(null);
        panel.setVisible(true);
        
    }
    
    public void estados(int est) {
        comp.estadoConductor(est);
    }
}
