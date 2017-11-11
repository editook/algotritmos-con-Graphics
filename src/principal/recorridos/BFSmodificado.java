/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal.recorridos;

import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
// cantidad de veses que puede compartir un vertice

public class BFSmodificado {

    private int[][] grafo;
    private boolean[] visitadoAmplitud;

    public BFSmodificado(int[][] grafo) {
        this.grafo = grafo;
        visitadoAmplitud = new boolean[grafo.length];
    }

    public int[][] getGrafo() {
        return grafo;
    }

    public ArrayList<Integer> recorridoAmplitud(int nodoI, int compartir) //algoritmo BFS hasta donde puede ir movil
    {
        int compartidos = 0;
        ArrayList<Integer> recorridos = new ArrayList<Integer>();
        visitadoAmplitud[nodoI] = true;
        Queue<Integer> cola = new LinkedList<Integer>();
        recorridos.add(nodoI);
        cola.offer(nodoI);
        while (!cola.isEmpty()) {
            int j = cola.poll();//decolar
            if (compartidos == compartir) {
                removeAll(cola);
            } else {
                for (int i = 0; i < grafo.length; i++) {
                    if (grafo[j][i] !=0 && visitadoAmplitud[i] == false) {
                        cola.offer(i);//encolar
                        recorridos.add(i);
                        visitadoAmplitud[i] = true;
                    }
                }
                compartidos++;
            }
        }
        return recorridos;
    }

    private void removeAll(Queue<Integer> cola) {
        while (!cola.isEmpty()) {
            cola.poll();
        }
    }
}
