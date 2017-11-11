
package principal;

import java.awt.Image;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import principal.Dibujar1;
public class Dibujar1 {
    URL url;
    public void dibujar(JLabel label,String objeto){
    url=getClass().getResource("imagen/"+objeto+".png");
       ImageIcon imagen = new ImageIcon(url);
        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(label.getWidth(),label.getHeight(), Image.SCALE_DEFAULT));
        label.setIcon(icono);
    }
}
