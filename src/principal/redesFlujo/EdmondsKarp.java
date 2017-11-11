package principal.redesFlujo;

import java.util.*;

public class EdmondsKarp {

    int sum;

    public EdmondsKarp() {
        sum = 0;
    }

    public int edmondsKarp(int[][] grafo1, int[][] grafo2, int s, int t) {//inicio s; t=destino
        int n = grafo2.length;

        int[][] nuevoGrafo = new int[n][n];
        while (true) {
            int[] Parents = new int[n];
            Arrays.fill(Parents, -1);//cambia los valores por -1(todos)
            Parents[s] = s;

            int[] nodo = new int[n];
            nodo[s] = Integer.MAX_VALUE;//max num que puede poner

            Queue<Integer> pila = new LinkedList<Integer>(); // BFS 
            pila.offer(s);//anadimos
            sum = sum + s;
            LOOP://saltador
            while (!pila.isEmpty()) {
                int x = pila.poll();
                for (int y : grafo1[x]) {
                    if (grafo2[x][y] - nuevoGrafo[x][y] > 0 && Parents[y] == -1) {
                        sum = sum + x;
                        Parents[y] = x;
                        nodo[y] = Math.min(nodo[x], grafo2[x][y] - nuevoGrafo[x][y]);

                        if (y != t) {
                            pila.offer(y);
                        } else {
                            while (Parents[y] != y) {
                                x = Parents[y];
                                nuevoGrafo[x][y] += nodo[t];
                                nuevoGrafo[y][x] -= nodo[t];
                                y = x;

                            }

                            break LOOP;

                        }
                    }
                }
            }
            if (Parents[t] != -1) {

                for (int x : nuevoGrafo[s]) {
                    sum = sum + x;
                }
                return sum;
            }
        }
    }

    public int FlujoMaximo(int matriz1[][], int matriz2[][], int t) {
        return edmondsKarp(matriz1, matriz2, 0, t);
    }
}
