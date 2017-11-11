package principal.ciclo;

public class Hamilton {
    int n=0;
    public Hamilton(){
        
    }
    public  int hamilton_path() {
        int i, j;
        int[] mask;
        int n = 0;
        mask = new int[n];

        if (n <= 1) {
            return (1);
        }

        for (i = 0; i < n; i++) {
            mask[i] = 1;
        }

        for (i = 0; i < n; i++) {
            for (j = i + 1; j < n; j++) {
                if (ciclo(n, n, i, j, mask)) {
                    return (1);
                }
            }
        }
        return (0);
    }

    public int hamilton_cycle() {
        int[] mask;
        int n = 0;
        int i, j;

        mask = new int[n];

        if (n <= 2) {
            return (0);
        }

        for (i = 0; i < n; i++) {
            mask[i] = 1;
        }

        for (i = 0; i < n; i++) {
            for (j = i + 1; j < n; j++) {

                if (ciclo(n, n, i, j, mask)) {
                    return (1);
                }

            }
        }
        return (0);
    }

    private boolean ciclo(int n, int nv, int u, int v, int[] mask) {
        int i;

        mask[u] = 0;

        for (i = 0; i < n; i++) {
            if ((mask[i] == 1) && i != v) {
                if (ciclo(n, nv - 1, i, v, mask)) {
                    mask[u] = 1;
                    return (true);
                }
            }
        }
        mask[u] = 1;
        return (false);
    }
}
