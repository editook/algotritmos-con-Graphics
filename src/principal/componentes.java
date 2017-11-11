package principal;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import principal.vehiculo.vista;

public class componentes {

    JFrame panel;
    JButton limiteTransitabilidad, cantidadMovilZona, zonaCircular, TarifaVial, RutaMovil, SistemaGPRS, posiblesRutasAccesibles, lugaresTrans;
    JLabel carro, separador, rutaTaxi, operadora, rojo, verde, aux;
    Dibujar1 dibu = new Dibujar1();
    actividades act = new actividades();
    vista vist = new vista();
    int estado = 0;
    JLabel[] vehiculos;
    ejecutador accion = new ejecutador();

    componentes(JFrame panel) {
        this.panel = panel;
        limiteTransitabilidad = new JButton();
        cantidadMovilZona = new JButton();
        zonaCircular = new JButton();
        TarifaVial = new JButton();
        RutaMovil = new JButton();
        SistemaGPRS = new JButton();
        posiblesRutasAccesibles = new JButton();
        lugaresTrans = new JButton();
        carro = new JLabel();
        separador = new JLabel();
        rutaTaxi = new JLabel();
        operadora = new JLabel();
        verde = new JLabel();
        rojo = new JLabel();
        botones();
        accion.start();
        vist.estados(1);
    }

    private void botones() {
        //focos estados rojo verde
        rojo.setBounds(810, 350, 50, 50);
        dibu.dibujar(rojo, "rojo");
        panel.getContentPane().add(rojo);
        verde.setBounds(810, 400, 50, 50);
        dibu.dibujar(verde, "verde");
        panel.getContentPane().add(verde);
        rojo.setEnabled(false);
        verde.setEnabled(false);
        //cuantas calles asu redonda solo puede transitar o circular .. lugares donde puede llegar
        //RECORIDOS: BFS modificado
        limiteTransitabilidad.setBackground(Color.ORANGE);
        limiteTransitabilidad.setText("ver limite de transitibilidad");
        limiteTransitabilidad.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent evt) {
                caminoViableMouseReleased(evt);
            }

        });
        limiteTransitabilidad.setBounds(600, 400, 200, 50);
        panel.getContentPane().add(limiteTransitabilidad);
        //la cantidad de moviles que puede ver en una zona 
        //conjunto camarilla : CAMARILLA
        cantidadMovilZona.setBackground(Color.LIGHT_GRAY);
        cantidadMovilZona.setText("moviles por zona");
        cantidadMovilZona.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent evt) {
                cantidadMovilZonaMouseReleased(evt);
            }

        });
        cantidadMovilZona.setBounds(600, 350, 200, 50);
        panel.getContentPane().add(cantidadMovilZona);
        //si es una zona circular osea si es posible que el movil puede rotar sobre la misma zona
        //CICLO: grafo ciclo o ciclo de HAMILTON
        //luz verde si, luz roja no
        zonaCircular.setBackground(Color.GREEN);
        zonaCircular.setText("zona circular?");
        zonaCircular.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent evt) {
                zonaCircularMouseReleased(evt);
            }

        });
        zonaCircular.setBounds(600, 300, 200, 50);
        panel.getContentPane().add(zonaCircular);
        //tarifa vial q precio que se cobrara por la ruta del radio movil
        //Emparejamientos :algoritmo hungaro Munkres
        TarifaVial.setBackground(Color.orange);
        TarifaVial.setText("tarifa a cobrar");
        TarifaVial.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent evt) {
                TarifaVialMouseReleased(evt);
            }

        });
        TarifaVial.setBounds(5, 400, 200, 50);
        panel.getContentPane().add(TarifaVial);
        //ruta movil del punto incio hasta los sitios que puede llegar o transitar
        // caminos viables posibles
        // CAMINO: algoritmo caminos minimos DIJKSTRA
        RutaMovil.setBackground(Color.gray);
        RutaMovil.setText("movil solicitado");
        RutaMovil.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent evt) {
                RutaMovilMouseReleased(evt);
            }

        });
        RutaMovil.setBounds(400, 350, 200, 50);
        panel.getContentPane().add(RutaMovil);

        // dada un punto inicio  por que caminos PUEDE recorrer 
        //para llegar a su destino
        //CAMINOS :CAMINO_TRANSITABLE
        SistemaGPRS.setBackground(Color.BLUE);
        SistemaGPRS.setText("buscar rutas GPRS");
        SistemaGPRS.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent evt) {
                SistemaGPRSMouseReleased(evt);
            }

        });
        SistemaGPRS.setBounds(400, 300, 200, 50);
        panel.getContentPane().add(SistemaGPRS);

        //puntos donde son transitables
        //recorridos DFS
        lugaresTrans.setBackground(Color.MAGENTA);
        lugaresTrans.setText("lugares transitables");
        lugaresTrans.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent evt) {
                lugaresTransMouseReleased(evt);
            }
        });
        lugaresTrans.setBounds(400, 400, 200, 50);
        panel.getContentPane().add(lugaresTrans);
        //todos los posibles caminos para poder llegar a un punto en concreto de s.. => ...t
        // redesFlujo: edmonds Karp
        posiblesRutasAccesibles.setBackground(Color.RED);
        posiblesRutasAccesibles.setText("buscar rutas a destino");
        posiblesRutasAccesibles.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent evt) {
                posiblesRutasAccesiblesMouseReleased(evt);
            }

        });
        posiblesRutasAccesibles.setBounds(5, 350, 200, 50);
        panel.getContentPane().add(posiblesRutasAccesibles);

        //fondo
        carro.setBounds(250, 350, 100, 70);
        dibu.dibujar(carro, "carro0");
        panel.getContentPane().add(carro);
        //linea
        separador.setBounds(380, 0, 10, 500);
        dibu.dibujar(separador, "fondo");
        panel.getContentPane().add(separador);

        //fondo de ruta del taxista
        rutaTaxi.setBounds(0, 0, 380, 350);
        dibu.dibujar(rutaTaxi, "calle");
        panel.getContentPane().add(rutaTaxi);
        //fondo de ruta de la  central
        operadora.setBounds(385, 0, 500, 275);
        dibu.dibujar(operadora, "mac");
        panel.getContentPane().add(operadora);

    }

    private void posiblesRutasAccesiblesMouseReleased(MouseEvent evt) {//buscar rutas a destino
        int FlujoMaX;
        
        String dato = JOptionPane.showInputDialog(null, "quisiera saber cuanto me costaria \n que me lleve a mi trabajo\n"
                + "POR FAVOR DE ME LA UBICACION DE SU TRABAJO: ");
        if (1 == JOptionPane.YES_NO_CANCEL_OPTION) {
                int dat1 = Integer.parseInt(dato);
                if (dat1 < act.getCantidadVertices()) {
                    FlujoMaX = act.getFlujoMax(dat1);
                    JOptionPane.showConfirmDialog(null, "\n estimado usuario de informamos que nosotros como empresa radioMovil"
                            + " \n con el 5% de seguridad que brindamos: \n \n el flujo maximo es de: " + FlujoMaX +" bs", "señor usuario", 1);
                }
            }
    }

    private void SistemaGPRSMouseReleased(MouseEvent evt) {//buscar rutas accesibles GPRS
        ArrayList<Integer> camino;
        String dato = JOptionPane.showInputDialog(null, "quisiera saber si de un puntoX(vertice) de mi zona.\n hasta donde puede llegar el movil? \n DIGITE EEL PUNTO por favor :");
        if (!dato.isEmpty()) {
            if (1 == JOptionPane.YES_NO_CANCEL_OPTION) {
                int dat1 = Integer.parseInt(dato);
                if (dat1 < act.getCantidadVertices()) {
                    camino = act.obtenerCamino(dat1);
                    JOptionPane.showConfirmDialog(null, "\n estimado usuario de informamos que desde el punto que nos"
                            + " acaba de \n mencionar nuestro movil puede llegar a estos puntos : \n " + camino, "señor usuario", 1);
                }
            }
        } else {
            JOptionPane.showConfirmDialog(null, "atencion", "error de marcacion vuelva a intentarlo", 0);
        }
    }

    private void TarifaVialMouseReleased(MouseEvent evt) {//tarifa a cobrar
        String salida = act.tarifasChofer();
        JOptionPane.showMessageDialog(null, ".................." + salida);
    }

    private void RutaMovilMouseReleased(MouseEvent evt) {//rutas movil
        String ruta = act.hallarRutasPasajero();
        vist.estados(5);
        JOptionPane.showMessageDialog(null, "" + ruta);

    }

    private void zonaCircularMouseReleased(MouseEvent evt) {//movil rondando o esta en mov
        boolean salida = act.siExisteCiclo();
        //JOptionPane.showMessageDialog(null, "rotativo: " + salida);
        if (salida == true) {
            verde.setEnabled(true);
        } else {
            rojo.setEnabled(true);
        }
        estado = 1;
    }

    private void caminoViableMouseReleased(MouseEvent evt) {//ver limite de transitibilidad
        String salida = act.rutasTransitabilidad(2, 6);
        JOptionPane.showMessageDialog(null, "lugares a donde puede llegar: " + salida);
    }

    private void cantidadMovilZonaMouseReleased(MouseEvent evt) {//moviles por zona
        int salida = act.cantMoviles();
        cargarVehiculos(salida);

        //JOptionPane.showMessageDialog(null, "cantidad de moviles por zona es: " + salida);
    }

    private void lugaresTransMouseReleased(MouseEvent evt) {//puntos donde son transitables
        String salida = act.rutasAccesibles();
        JOptionPane.showMessageDialog(null, "sitios donde un movil puede llegar: " + salida);
    }

    private void cargarVehiculos(int salida) {
        vehiculos = new JLabel[salida];
        int y = 275;
        int x = 550;
        for (int i = 0; i < vehiculos.length; i++) {
            aux = new JLabel();
            aux.setBounds(x, y, 50, 25);
            dibu.dibujar(aux, "carro0");
            vehiculos[i] = aux;
            x = x + 50;
        }

        for (int i = 0; i < vehiculos.length; i++) {
            panel.getContentPane().add(vehiculos[i]);
        }
        panel.repaint();
        estado = 2;
    }

    public class ejecutador extends Thread {

        @Override
        public void run() {
            while (true) {
                System.out.print("");
                while (estado != 0) {
                    if (estado == 1) {
                        while (estado == 1) {
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException ex) {
                                System.out.println("marco mal");
                            }
                            rojo.setEnabled(false);
                            verde.setEnabled(false);
                            estado = 0;
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException ex) {
                                System.out.println("marco mal");
                            }
                        }
                    }
                    if (estado == 2) {
                        while (estado == 2) {
                            try {
                                Thread.sleep(4000);
                            } catch (InterruptedException ex) {
                                System.out.println("vehiculo mal");
                            }
                            for (int i = 0; i < vehiculos.length; i++) {
                                panel.remove(vehiculos[i]);
                            }
                            panel.repaint();
                            estado = 0;
                        }
                    }

                }
            }
        }
    }
}
