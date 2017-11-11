package principal.CONJUNTO_camarilla;

import java.util.*;

public class camarilla {

    int grafo[][];
    private ArrayList<listas> vertice;
    private ArrayList<Integer> relaciones = new ArrayList<Integer>();

    public camarilla(int[][]graf) {
        grafo = transformarGrafo(graf);
        vertice = new ArrayList<listas>();
        
    }
    public int maxClique() {
        int mayor = 0;// mayor cantidad de relacion posible clique
        if (grafo != null) {//si mi grafo no esta vacio
            //anade todos los primeros vertices de relacion
            for (int i = 0; i < grafo.length; i++) {
                vertice.add(new listas(i));
                for (int j = 0; j < grafo.length; j++) {
                    if (grafo[i][j] == 1) {
                        vertice.get(i).anadir(j);
                    }
                }
            }

            for (int i = 0; i < vertice.size(); i++) {
                for (int j = 0; j < vertice.size(); j++) {
                    if (i != j) {
                        buscarRelacion(vertice.get(i), vertice.get(j));
                    }
                }
            }
            eliminarAmbiguedad(0);
            for (int i = 0; i < relaciones.size(); i++) {
                if (mayor < relaciones.get(i)) {
                    mayor = relaciones.get(i);
                }
            }
        }
        return mayor-1;
    }

    private void eliminarAmbiguedad(int pos) {
        if (pos < relaciones.size() - 1) {
            for (int i = pos + 1; i < relaciones.size(); i++) {
                if (relaciones.get(pos) == relaciones.get(i)) {
                    relaciones.remove(pos);
                    eliminarAmbiguedad(i);
                }
            }
            eliminarAmbiguedad(pos + 1);
        }
    }

    private void buscarRelacion(listas lista1, listas lista2) {
        int cont = 0;
        int cont1 = 0;
        for (int i = 0; i < lista1.tamano(); i++) {
            for (int j = 0; j < lista2.tamano(); j++) {
                if (lista1.get(i) == lista2.get(j)) {
                    cont++;
                    cont1++;
                }
            }
        }
        if (cont == lista1.tamano() && cont != 0) {
            relaciones.add(cont);
        } else if (cont1 == lista1.tamano() - 1 && cont != 0) {
            relaciones.add(cont);
        }
    }

    private int[][] transformarGrafo(int[][] graf) {
        int[][]nuevo=new int[graf.length][graf.length];
        for (int i = 0; i < graf.length; i++) {
            for (int j = 0; j < graf.length; j++) {
                if(graf[i][j]!=0){
                    nuevo[i][j]=1;
                }
            }
        }
        return nuevo;
    }
}
