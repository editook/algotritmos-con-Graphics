package principal.componentesConexos;
 //componentes fuertemente conexas de un grafo.
import java.util.*;
public class Tarjan {

    private int V,preCount;
    private int[] grafos;
    private boolean[] nodovisitado;
    private List<Integer>[] grafo;
    private List<List<Integer>> lista;
    private Stack<Integer> cola;
    public List<List<Integer>> getSCComponents(List<Integer>[] graf) {
        V = graf.length;
        this.grafo = graf;
        grafos = new int[V];
        nodovisitado = new boolean[V];
        cola = new Stack<Integer>();
        lista = new ArrayList<>();

        for (int v = 0; v < V; v++) {
            if (!nodovisitado[v]) {
                dfs(v);
            }
        }

        return lista;
    }
    public void dfs(int v) {
        grafos[v] = preCount++;
        nodovisitado[v] = true;
        cola.push(v);
        int min = grafos[v];
        for (int w : grafo[v]) {
            if (!nodovisitado[w]) {
                dfs(w);
            }
            if (grafos[w] < min) {
                min = grafos[w];
            }
        }
        if (min < grafos[v]) {
            grafos[v] = min;
            return;
        }
        List<Integer> component = new ArrayList<Integer>();
        int w;
        do {
            w = cola.pop();
            component.add(w);
            grafos[w] = V;
        } while (w != v);
        lista.add(component);
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int V = scan.nextInt();
        List<Integer>[] g = new List[V];
        for (int i = 0; i < V; i++) {
            g[i] = new ArrayList<Integer>();
        }
        int E = scan.nextInt();
        for (int i = 0; i < E; i++) {
            int x = scan.nextInt();
            int y = scan.nextInt();
            g[x].add(y);
        }

        Tarjan t = new Tarjan();
        List<List<Integer>> scComponents = t.getSCComponents(g);
        System.out.println(scComponents);
    }

}
