package principal.vehiculo;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;

class actividad {

    JFrame frame;
    Dibujar dibu = new Dibujar();
    JLabel taxi, pasajero, maparuta;
    recorrerAutomatico recorrer = new recorrerAutomatico();
    int estadoMovil = 0;//signmifica parado
    int dejarPasajero=0;
    public actividad(JFrame panel) {
        frame = panel;
        taxi = new JLabel();
        pasajero = new JLabel();
        maparuta = new JLabel();
        cargar();
        recorrer.start();
    }

    private void cargar() {
        pasajero.setBounds(5, 5, 50, 50);
        dibu.dibujar(pasajero, "pasajero");
        frame.getContentPane().add(pasajero);
        pasajero.setVisible(false);

        taxi.setBounds(20, 320, 60, 30);
        dibu.dibujar(taxi, "taxi");
        frame.getContentPane().add(taxi);

        maparuta.setBounds(0, 0, 500, 400);
        dibu.dibujar(maparuta, "maparuta");
        frame.getContentPane().add(maparuta);

        frame.repaint();
    }

    public void estadoConductor(int i) {
        estadoMovil = i;
        if (i == 5) {
            estadoMovil=0;
            taxi.setLocation(35,320);
            dibu.dibujar(taxi, "taxi");
            pasajero.setLocation(415, 50);
            estadoMovil=5;
            pasajero.setVisible(true);
        }
    }

    public class recorrerAutomatico extends Thread {

        int px1, py1, px2, py2, px3, py3, px4, py4;
        int pasajeroX, pasajeroY;

        public recorrerAutomatico() {
            px1 = 415;
            py1 = 320;
            px2 = 415;
            py2 = 50;
            px3 = 35;
            py3 = 70;
            px4 = 35;
            py4 = 320;
            pasajeroX = 435;
            pasajeroY = 50;
        }

        @Override
        public void run() {
            while (true) {
                System.out.print("");
                while (estadoMovil != 0) {

                    if (estadoMovil == 1) {
                        while (estadoMovil == 1) {
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(actividad.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            if (taxi.getX() < px1) {
                                taxi.setLocation((taxi.getX() + 1), taxi.getY());
                                break;
                            }
                            if (taxi.getX() == px1) {
                                taxi.setBounds(px1 + 20, py1 - 20, 30, 60);
                                dibu.dibujar(taxi, "taxi1");
                                estadoMovil = 2;
                                break;

                            }

                        }

                    }

                    if (estadoMovil == 2) {
                        while (estadoMovil == 2) {
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(actividad.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            if (taxi.getY() > py2) {
                                taxi.setLocation((taxi.getX()), taxi.getY() - 1);
                            }
                            if (taxi.getY() == py2) {
                                taxi.setBounds(px2, py2, 60, 30);
                                dibu.dibujar(taxi, "taxi");
                                estadoMovil = 3;
                            }

                        }
                    }
                    if (estadoMovil == 3) {
                        while (estadoMovil == 3) {
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(actividad.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            if (taxi.getX() > px3) {
                                taxi.setLocation((taxi.getX() - 1), taxi.getY());
                            }
                            if (taxi.getX() == px3) {
                                taxi.setBounds(px3, py3, 30, 60);
                                dibu.dibujar(taxi, "taxi1");
                                estadoMovil = 4;
                            }
                        }
                    }
                    if (estadoMovil == 4) {
                        while (estadoMovil == 4) {
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(actividad.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            if (taxi.getY() < py4) {
                                taxi.setLocation((taxi.getX()), taxi.getY() + 1);
                            }
                            if (taxi.getY() == py4) {
                                taxi.setBounds(px4, py4, 60, 30);
                                dibu.dibujar(taxi, "taxi");
                                estadoMovil = 1;
                            }
                        }
                    }
                    if (estadoMovil == 5) {
                        while (estadoMovil == 5) {
                            if (taxi.getX() < pasajeroX) {
                                taxi.setBounds(taxi.getX() + 1,taxi.getY() ,60,30);
                                dibu.dibujar(taxi, "taxi");
                                break;
                            } else if (taxi.getX() > pasajeroX) {
                                taxi.setBounds(taxi.getX() - 1,taxi.getY() ,60,30);
                                dibu.dibujar(taxi, "taxi");
                                break;
                            } else if (taxi.getY() < pasajeroY) {
                                taxi.setBounds(taxi.getX(),taxi.getY()+1 ,30,60);
                                dibu.dibujar(taxi, "taxi1");
                                break;
                            } else if (taxi.getY() > pasajeroY) {
                                taxi.setBounds(taxi.getX(),taxi.getY()-1 ,30,60);
                                dibu.dibujar(taxi, "taxi1");
                                break;
                            }
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(actividad.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            if(taxi.getX()==pasajeroX && taxi.getY()==pasajeroY){
                                pasajero.setVisible(false);
                                taxi.setBounds(taxi.getX(),taxi.getY(),60,30);
                                dibu.dibujar(taxi,"taxi");
                                dejarPasajero=1;
                                while (dejarPasajero==1) {                                    
                               try {
                                Thread.sleep(10);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(actividad.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            if (taxi.getX() > px3) {
                                taxi.setLocation((taxi.getX() - 1), taxi.getY());
                            }
                            if (taxi.getX() == px3) {
                                pasajero.setLocation(px3-40, py3);
                                pasajero.setVisible(true);
                                try {
                                Thread.sleep(1000);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(actividad.class.getName()).log(Level.SEVERE, null, ex);
                            }
                                
                                
                                try {
                                Thread.sleep(1000);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(actividad.class.getName()).log(Level.SEVERE, null, ex);
                            }
                                taxi.setBounds(px3, py3, 30, 60);
                                dibu.dibujar(taxi, "taxi1");
                                estadoMovil = 4;
                                dejarPasajero=0;
                                pasajero.setVisible(false);
                            }
                                }
                            }
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(actividad.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
            }
        }
    }

}
