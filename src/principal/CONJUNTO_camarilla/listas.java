/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal.CONJUNTO_camarilla;

import java.util.*;

public class listas {

    private ArrayList<Integer> listas = new ArrayList<Integer>();

    public listas(int vertice) {
        listas.add(vertice);
    }

    public void anadir(int dat) {
        listas.add(dat);
    }

    public int get(int pos) {
        return listas.get(pos);
    }

    public int tamano() {
        return listas.size();
    }
}
