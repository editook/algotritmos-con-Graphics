package principal.Emparejamientos;

public class Hungaro {

    public static double encontrarLargo(double[][] array) {
        double menosLargo = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] > menosLargo) {
                    menosLargo = array[i][j];
                }
            }
        }
        return menosLargo;
    }

    public static double[][] transpaso(double[][] matri) {
        double[][] lista = new double[matri[0].length][matri.length];
        for (int i = 0; i < lista.length; i++) {
            for (int j = 0; j < lista[i].length; j++) {
                lista[i][j] = matri[j][i];
            }
        }
        return lista;
    }

    public static double[][] copia(double[][] original) {
        double[][] copy = new double[original.length][original[0].length];
        for (int i = 0; i < original.length; i++) {
            System.arraycopy(original[i], 0, copy[i], 0, original[i].length);
        }

        return copy;
    }

    //HUNGARo
    public static int[][] Humgaro(double[][] graf, String sumatoria) {
        double[][] cost = copia(graf);

        if (sumatoria.equalsIgnoreCase("max")) {
            double maxWeight = encontrarLargo(cost);
            for (int i = 0; i < cost.length; i++) {
                for (int j = 0; j < cost[i].length; j++) {
                    cost[i][j] = (maxWeight - cost[i][j]);
                }
            }
        }
        double maxCost = encontrarLargo(cost);

        int[][] ma = new int[cost.length][cost[0].length];
        int[] fila = new int[cost.length];
        int[] columna = new int[cost[0].length];
        int[] zero_RC = new int[2];
        int parada = 1;
        boolean done = false;
        while (done == false) {
            switch (parada) {
                case 1:
                    parada = fase1(parada, cost);
                    break;
                case 2:
                    parada = Hungaro.fase2(parada, cost, ma, fila, columna);
                    break;
                case 3:
                    parada = fase3(parada, ma, columna);
                    break;
                case 4:
                    parada = fase4(parada, cost, ma, fila, columna, zero_RC);
                    break;
                case 5:
                    parada = fase5(parada, ma, fila, columna, zero_RC);
                    break;
                case 6:
                    parada = fase6(parada, cost, fila, columna, maxCost);
                    break;
                case 7:
                    done = true;
                    break;
            }
        }

        int[][] assignment = new int[ma.length][2];
        for (int i = 0; i < ma.length; i++) {
            for (int j = 0; j < ma[i].length; j++) {
                if (ma[i][j] == 1) {
                    assignment[i][0] = i;
                    assignment[i][1] = j;
                }
            }
        }
        return assignment;
    }

    public static int fase1(int parad, double[][] distancia) {
        double minval;

        for (int i = 0; i < distancia.length; i++) {
            minval = distancia[i][0];
            for (int j = 0; j < distancia[i].length; j++) {
                if (minval > distancia[i][j]) {
                    minval = distancia[i][j];
                }
            }
            for (int j = 0; j < distancia[i].length; j++) {
                distancia[i][j] = distancia[i][j] - minval;
            }
        }

        parad = 2;
        return parad;
    }

    public static int fase2(int parada, double[][] cost, int[][] m, int[] fila, int[] columna) {
        for (int i = 0; i < cost.length; i++) {
            for (int j = 0; j < cost[i].length; j++) {
                if ((cost[i][j] == 0) && (columna[j] == 0) && (fila[i] == 0)) {
                    m[i][j] = 1;
                    columna[j] = 1;
                    fila[i] = 1;
                }
            }
        }

        clearCovers(fila, columna);

        parada = 3;
        return parada;
    }

    public static int fase3(int parada, int[][] matriz, int[] columnas) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] == 1) {
                    columnas[j] = 1;
                }
            }
        }

        int contador = 0;
        for (int j = 0; j < columnas.length; j++) {
            contador = contador + columnas[j];
        }

        if (contador >= matriz.length) {
            parada = 7;
        } else {
            parada = 4;
        }

        return parada;
    }

    public static int fase4(int parada, double[][] cost, int[][] matri, int[] listado, int[] col, int[] valor) {
        int[] nuevaColumna = new int[2];
        boolean done = false;
        while (done == false) {
            nuevaColumna = findUncoveredZero(nuevaColumna, cost, listado, col);
            if (nuevaColumna[0] == -1) {
                done = true;
                parada = 6;
            } else {
                matri[nuevaColumna[0]][nuevaColumna[1]] = 2;
                boolean res = false;
                for (int j = 0; j < matri[nuevaColumna[0]].length; j++) {
                    if (matri[nuevaColumna[0]][j] == 1) {
                        res = true;
                        nuevaColumna[1] = j;
                    }
                }

                if (res == true) {
                    listado[nuevaColumna[0]] = 1;
                    col[nuevaColumna[1]] = 0;
                } else {
                    valor[0] = nuevaColumna[0];
                    valor[1] = nuevaColumna[1];
                    done = true;
                    parada = 5;
                }
            }
        }

        return parada;
    }

    public static int[] findUncoveredZero(int[] listas, double[][] cost, int[] x, int[] y) {
        listas[0] = -1;
        listas[1] = 0;

        int i = 0;
        boolean done = false;
        while (done == false) {
            int j = 0;
            while (j < cost[i].length) {
                if (cost[i][j] == 0 && x[i] == 0 && y[j] == 0) {
                    listas[0] = i;
                    listas[1] = j;
                    done = true;
                }
                j = j + 1;
            }
            i = i + 1;
            if (i >= cost.length) {
                done = true;
            }
        }

        return listas;
    }

    public static int fase5(int step, int[][] mask, int[] df, int[] col, int[] zero_RC) {

        int count = 0;
        int[][] path = new int[(mask[0].length * mask.length)][2];
        path[count][0] = zero_RC[0];
        path[count][1] = zero_RC[1];

        boolean done = false;
        while (done == false) {
            int r = findStarInCol(mask, path[count][1]);
            if (r >= 0) {
                count = count + 1;
                path[count][0] = r;
                path[count][1] = path[count - 1][1];
            } else {
                done = true;
            }

            if (done == false) {
                int c = findPrimeInRow(mask, path[count][0]);
                count = count + 1;
                path[count][0] = path[count - 1][0];
                path[count][1] = c;
            }
        }

        convertPath(mask, path, count);
        clearCovers(df, col);
        erasePrimes(mask);

        step = 3;
        return step;

    }

    public static int findStarInCol(int[][] mask, int col) {
        int r = -1;
        for (int i = 0; i < mask.length; i++) {
            if (mask[i][col] == 1) {
                r = i;
            }
        }

        return r;
    }

    public static int findPrimeInRow(int[][] mask, int fila) {
        int c = -1;
        for (int j = 0; j < mask[fila].length; j++) {
            if (mask[fila][j] == 2) {
                c = j;
            }
        }

        return c;
    }

    public static void convertPath(int[][] mat, int[][] aux, int cont) {
        for (int i = 0; i <= cont; i++) {
            if (mat[(aux[i][0])][(aux[i][1])] == 1) {
                mat[(aux[i][0])][(aux[i][1])] = 0;
            } else {
                mat[(aux[i][0])][(aux[i][1])] = 1;
            }
        }
    }

    public static void erasePrimes(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == 2) {
                    mat[i][j] = 0;
                }
            }
        }
    }

    public static void clearCovers(int[] fila, int[] columna) {
        for (int i = 0; i < fila.length; i++) {
            fila[i] = 0;
        }
        for (int j = 0; j < columna.length; j++) {
            columna[j] = 0;
        }
    }

    public static int fase6(int parada, double[][] precio, int[] fil, int[] columna, double maxCost) {
        double minval = encontrarPequeno(precio, fil, columna, maxCost);
        for (int i = 0; i < fil.length; i++) {
            for (int j = 0; j < columna.length; j++) {
                if (fil[i] == 1) {
                    precio[i][j] = precio[i][j] + minval;
                }
                if (columna[j] == 0) {
                    precio[i][j] = precio[i][j] - minval;
                }
            }
        }

        parada = 4;
        return parada;
    }

    public static double encontrarPequeno(double[][] costo, int[] fila, int[] columna, double maxCost) {
        double minval = maxCost;
        for (int i = 0; i < costo.length; i++) {
            for (int j = 0; j < costo[i].length; j++) {
                if (fila[i] == 0 && columna[j] == 0 && (minval > costo[i][j])) {
                    minval = costo[i][j];
                }
            }
        }

        return minval;
    }

    public String getPrecioTarifas(double [][]grafo){
        String salida="\n";
        String sumatotal = "max";
        double[][] grafito;
        grafito = grafo;
        if (grafito.length > grafito[0].length) {
            grafito = transpaso(grafito);
        }
        int[][] NuevaMatriz = new int[grafito.length][2];
        NuevaMatriz = Humgaro(grafito, sumatotal);
        
        System.out.println("el costo por cada ruta es: (" + sumatotal + "imo)\n");
        salida=salida+("el costo del uso por toda la zona es:  (" + sumatotal + "imo)\n");
        double sum = 0;
        for (int i = 0; i < NuevaMatriz.length; i++) {
            System.out.printf("ruta de (%d,%d) = %.2f bs\n", (NuevaMatriz[i][0] + 1), (NuevaMatriz[i][1] + 1),grafito[NuevaMatriz[i][0]][NuevaMatriz[i][1]]);
            sum = sum + grafito[NuevaMatriz[i][0]][NuevaMatriz[i][1]];
        }
        salida=salida+sum+" bs\n";
        salida=salida+"\nver los resultados de loos precios en la consola";
        return salida;
    }
}
