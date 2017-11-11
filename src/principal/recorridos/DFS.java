/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal.recorridos;

import java.util.ArrayList;

public class DFS {

    public int[][] g;
    private boolean[] visitiadoProfunidad;

    public DFS(int [][]grafo) {
        g=grafo;
        visitiadoProfunidad = new boolean[grafo.length];
    }

    public int[][] getG() {
        return g;
    }

    public ArrayList<Integer> recorridoProfunidad(int nodoI) {
        ArrayList<Integer> recorridos = new ArrayList<Integer>();
        visitiadoProfunidad[nodoI] = true;
        ArrayList<Integer> cola = new ArrayList<Integer>();
        recorridos.add(nodoI);
        cola.add(nodoI);
        while (!cola.isEmpty()) {
            int j = cola.remove(0);
            for (int i = 0; i < g.length; i++) {
                if (g[j][i] !=0 && !visitiadoProfunidad[i]) {
                    cola.add(i);
                    recorridos.addAll(recorridoProfunidad(i));
                    visitiadoProfunidad[i] = true;
                }
            }
        }
        return recorridos;
    }
}
