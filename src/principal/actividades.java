package principal;

import principal.ciclo.Hamilton;
import java.util.ArrayList;
import principal.CONJUNTO_camarilla.camarilla;
import principal.Emparejamientos.Hungaro;
import principal.caminos.Camino_transitable;
import principal.caminos.dijkstra;
import principal.recorridos.BFSmodificado;
import principal.recorridos.DFS;
import principal.redesFlujo.EdmondsKarp;

public class actividades {

    int[][] grafo = {
        {0, 5, 4, 5, 2, 0, 0},
        {5, 0, 1, 0, 0, 0, 7},
        {4, 1, 0, 2, 0, 0, 0},
        {5, 0, 2, 0, 3, 0, 3},
        {2, 0, 0, 3, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0},
        {0, 7, 0, 3, 0, 0, 0}};
    ArrayList<Integer> listaAux;
    int tamano;
    public actividades() {
        tamano=grafo.length;
    }
    public int getCantidadVertices(){
      return grafo.length;
    }
    public int cantMoviles() {
        camarilla c = new camarilla(grafo);
        return c.maxClique();
    }

    public boolean siExisteCiclo() {
        Hamilton c = new Hamilton();
        if (c.hamilton_path() == 1) {
            return true;
        }
        return false;
    }

    public String rutasTransitabilidad(int inicio, int fin) {
        BFSmodificado bfs = new BFSmodificado(grafo);
        listaAux = bfs.recorridoAmplitud(inicio, fin);
        return transformar(listaAux, inicio);
    }

    private String transformar(ArrayList<Integer> listaAux, int inicio) {
        String res = "  \n";
        for (int i = 1; i < listaAux.size(); i++) {
            res = res + (" de " + inicio + " a " + listaAux.get(i) + ": puede llegar \n");
        }
        return res;
    }

    public String rutasAccesibles() {
        DFS dfs = new DFS(grafo);
        listaAux = dfs.recorridoProfunidad(0);
        return transforma2(listaAux);
    }

    private String transforma2(ArrayList<Integer> listaAux) {
        String res = "  \n";
        for (int i = 0; i < listaAux.size(); i++) {
            res = res + (listaAux.get(i) + ": es transitable \n");
        }
        return res;
    }
    public String tarifasChofer(){
        return transformarHungaro(grafo);
    }
    private String transformarHungaro(int[][] Antiguo) {
        Hungaro hum=new Hungaro();
        double[][] nuevoGrafo = new double[Antiguo.length][Antiguo.length];
        for (int i = 0; i < Antiguo.length; i++) {
            for (int j = 0; j <Antiguo.length; j++) {
                nuevoGrafo[i][j] = Antiguo[i][j];
            }
        }
        return hum.getPrecioTarifas(nuevoGrafo);
    }

    public String hallarRutasPasajero() {
        int mayor=0;
        for (int i = 0; i <grafo.length; i++) {
            for (int j = 0; j <grafo.length; j++) {
                if(mayor<grafo[i][j]){
                    mayor=grafo[i][j];
                }
            }
        }
        return rutaAlterna(grafo.length,mayor);
    }

    private String rutaAlterna(int length, int mayor) {
        dijkstra dist=new dijkstra(length, mayor);
        
        return dist.getDatos();
    }

    public ArrayList<Integer> obtenerCamino(int dat1) {
        Camino_transitable bfsModi=new Camino_transitable(grafo);
        ArrayList<Integer> lista=bfsModi.recorrido(dat1);
        return lista;
    }

    public int getFlujoMax(int dat1) {
        EdmondsKarp edmon=new EdmondsKarp();
        
        return edmon.FlujoMaximo(grafo, grafo, dat1);
    }

}
