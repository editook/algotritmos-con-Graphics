package principal.caminos;

import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class Camino_transitable {

    private int[][] grafo;
    private boolean[] visitadoAmplitud;

    public Camino_transitable(int[][] grafo) {
        this.grafo = grafo;
        visitadoAmplitud = new boolean[grafo.length];
    }
    public int[][] getGrafo() {
        return grafo;
    }
    public ArrayList<Integer> recorrido(int t){
        return recorridoAmplitud(0, t);
    }
    public ArrayList<Integer> recorridoAmplitud(int s, int t) {
        ArrayList<Integer> camino = new ArrayList<Integer>();
        visitadoAmplitud[s] = true;
        Queue<Integer> cola = new LinkedList<Integer>();
        camino.add(s);
        cola.offer(s);
        while (!cola.isEmpty()) {
            int j = cola.poll();
            for (int i = 0; i < grafo.length; i++) {
                if (grafo[j][i] != 0 && visitadoAmplitud[i] == false) {
                    cola.offer(i);
                    camino.add(i);
                    
                    visitadoAmplitud[i] = true;
                }
            }

        }
        return camino;
    }
}
