package principal.clausura;

/*Reseña de aplicación del algoritmo (Para que se usa)
Obtiene la mejor ruta entre todo par de nodos
Trabaja con la matriz D inicializada con las distancias directas entre todo par de nodos
La iteración se produce sobre nodos intermedios, es decir, para todo elemento de la matriz se prueba si lo mejor para ir de i a j es a través de un nodo intermedio elegido o 
como estaba anteriormente, y esto se prueba con todos los nodos de la red .
Una vez probados todos los nodos de la red como nodos intermedios, la matriz resultante da la mejor distancia entre todo par de nodos*/
public class warshall {

    static int[][] warshall;
    static int n = 0;

    public static void main(String[] args) {//prueba realizada 
        int[][] grafo = {//matriz adyacencia
            {0, 1, 0, 1, 0},
            {0, 0, 1, 0, 0},
            {0, 1, 0, 1, 0},
            {0, 0, 0, 0, 1},
            {0, 0, 0, 0, 0}};
        warshall = grafo;
        n = grafo.length;
        for (int k = 0; k <= n - 1; k++) {
            for (int i = 0; i <= n - 1; i++) {
                for (int j = 0; j <= n - 1; j++) {
                    warshall[i][j] = funcionwar(i, j, k);
                    System.out.print(warshall[i][j]);
                }
                System.out.println();
            }
            System.out.println();
        }

        System.out.println("Matriz de adyacencia: ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(warshall[i][j]);
            }
            System.out.println();
        }
    }

    public static int funcionwar(int i, int j, int k) {
        if ((warshall[i][j] == 1) || ((warshall[i][k] == 1) && (warshall[k][j] == 1))) {
            return 1;
        } else {
            return 0;
        }
    }
}
