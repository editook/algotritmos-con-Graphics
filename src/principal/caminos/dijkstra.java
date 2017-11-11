package principal.caminos;

import java.io.*;
import java.util.*;

public class dijkstra{

  int[][] matrizAdy;
  int nNodos;
  List conj_S = new ArrayList();
  List conjComp_S = new ArrayList();
  List caminos = new ArrayList();
  String tmp;
  int tamano;
String salida="\n";
  public dijkstra(int numNodos,int max){
     salida=salida+"el nodo inicial sera 0=A\n";
     tamano=max;
    matrizAdy = new int[numNodos][numNodos];
    int aux=0;

    nNodos=numNodos;
        aux=1;
    if(aux==1)
      cargaDesdeTeclado();
    do{
        aux=0;//nodo inicio
      
    }while(aux<0 || aux>nNodos-1);
    matrizAdy[aux][aux]=0;
    resuelve(aux);
  }

  private void cargaDesdeTeclado(){
    boolean res;
    for(int cuenta=1;cuenta<=nNodos;cuenta++)
      for(int cnt=1;cnt<=nNodos;cnt++){
        if(cnt!=cuenta){
            int resp=(int)(Math.random()*tamano);
            if(resp==1){
                res=true;
                salida=salida+("nodo "+(char)(cuenta+64)+" al nodo "+(char)(cnt+64)+"\n");
                matrizAdy[cuenta-1][cnt-1]=(int)(Math.random()*tamano);///
            }
            res=(matrizAdy[cuenta-1][cnt-1]<0?true:false);
            matrizAdy[cuenta-1][cnt-1]=(matrizAdy[cuenta-1][cnt-1]==0?-1:matrizAdy[cuenta-1][cnt-1]);
          
        
          if(res)
            cnt--;
        }
        else
          matrizAdy[cuenta-1][cuenta-1]=-1;
      }
  }

  private void resuelve(int origen){
    int nod;
    int minimo;
    int aux;
    int nodCambio=0;
    int intento;
    for(int i=0;i<nNodos;i++){
      if(i!=origen)
        conjComp_S.add(""+i);
      else
        conj_S.add(""+i);
      caminos.add("");
    }
    //Aplicando ciclo 
    for(int i=0;i<nNodos;i++){
      minimo=-1;
      for(int j=0;j<conjComp_S.size();j++){
        nod=Integer.valueOf((String)(conjComp_S.get(j))).intValue();
        aux=minimo(nod);
        if(minimo==-1 || (aux<minimo && aux!=-1)){
          minimo=aux;
          nodCambio=j;
        }
      }
      if(minimo!=-1){
        conj_S.add(""+(String)(conjComp_S.get(nodCambio)));
        conjComp_S.remove(nodCambio);
      }
    }
    //Imprimiendo resultados
    salida=salida+"\n salidas\n";
    for(int k=0;k<caminos.size();k++)
      if(k!=origen){
        tmp=(String)(caminos.get(k))+(char)(k+65);
        caminos.set(k,tmp);
      }
    for(int j=0;j<caminos.size();j++)
      if(j!=origen){
        intento=0;
        tmp=(String)(caminos.get(j));
          while(tmp.charAt(0)!=(char)(origen+65) && intento<10){
            aux=tmp.charAt(0)-65;
            tmp=((String)(caminos.get(aux)))+tmp.substring(1,tmp.length());
            if(++intento==10)
              tmp="*"+tmp;
          };
        imprimeCamino(tmp,j,origen);
      }
  }

  private int minimo(int dest){
    int min=-1;
    int nod=0;
    int nodOrig=-1;
    int aux;
    for(int i=0;i<conj_S.size();i++){
      nod=Integer.valueOf((String)(conj_S.get(i))).intValue();
      if(matrizAdy[nod][nod]!=-1 && matrizAdy[nod][dest]!=-1)
        aux=matrizAdy[nod][nod]+matrizAdy[nod][dest];
      else
        aux=-1;
      if((aux<min && aux!=-1)||min==-1){
        min=aux;
        nodOrig=nod;
      }
    }
    if(min!=-1){
      matrizAdy[dest][dest]=min;
      caminos.set(dest,""+(char)(nodOrig+65));
    }
    return min;
  }

  private void imprimeCamino(String cam, int nod, int o){
    if(cam.charAt(0)=='*')
        salida=salida+("\n no hay camino de: "+(char)(o+65)+" a: "+cam.charAt(cam.length()-1)+"\n");
    else{
      for(int i=0;i<cam.length();i++)
        salida=salida+(" "+cam.charAt(i)+(i==cam.length()-1?" ":" "));
        salida=salida+("  precio: "+matrizAdy[nod][nod]+"\n");
    }
  }

    public String getDatos() {
        return salida;
    }
  }
