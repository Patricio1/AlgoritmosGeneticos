
package algoritmogenetico;

import static Maximizacion.SampleController.individuos;
import static Maximizacion.SampleController.numCromosomas;
import static Maximizacion.SampleController.numIndividuos;
import com.sun.image.codec.jpeg.TruncatedFileException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AlgoritmoGenetico {
        public static int numIndividuos = 6;
	public static int numCromosomas = 6;
        public static double suma=0;
        public static int [][] individuos = new int [numIndividuos][numCromosomas];
        public static String valorBinario="";
        public static String ValorCromosoma[]= new String[numIndividuos];
        public static double ValorDecimal[]= new double[numIndividuos];
        public static double ValorFuncion[]= new double[numIndividuos];
        public static double AptitudRelativa[]= new double[numIndividuos];
        public static double AptitudAcumulada[]= new double[numIndividuos];
        public static String Rangos[]= new String[numIndividuos];
        
        public static DecimalFormat decimal= new DecimalFormat("0.00");
    public static void main(String[] args) {
       //ConvertirABinario("256");
       //100000000
      //System.out.println(ConvertirADecimal("011000000"));
       // System.out.println(convertirABinario(256));
//        double a,b,c,d;
//        Scanner entrada= new Scanner(System.in);
//        System.out.println("Datos de la funcion: ");
//        
//        System.out.print("X3: ");
//        a=entrada.nextDouble();
//        
//        System.out.print("X2: ");
//        b=entrada.nextDouble();
//        
//        System.out.print("X: ");
//        c=entrada.nextDouble();
//        
//        System.out.print("c: ");
//        d=entrada.nextDouble();
        
//      double x=funcion(6, 2, -6, 4, 7, 3);
//         System.out.println(x);
        //000111 ==> 7.0 240054.0 
     
//        double numeroBinarioEnDecimal=0;
//        for(int i=0;i<numIndividuos;i++){
//            for(int j=0;j<numCromosomas;j++){
//              
//                System.out.print(""+individuos[i][j]);
//                numeroBinario+=individuos[i][j];
//                 
//            }
//            numeroBinarioEnDecimal=ConvertirADecimal(numeroBinario);
//             suma+=funcion(6, 2, -6, 4, numeroBinarioEnDecimal, 3);
//            System.out.print(" ==> "+numeroBinarioEnDecimal+" "+funcion(6, 2, -6, 4, numeroBinarioEnDecimal, 3) ) ;
//            System.out.println(" ");
//            numeroBinario="";
//        }
//        
//       System.out.println(suma);
        llenar();
       
        for(int i=0; i<ValorCromosoma.length; i++){
            
     System.out.println(ValorCromosoma[i]+" "+ValorDecimal[i]+" "+ValorFuncion[i]+" "+
             decimal.format(AptitudRelativa[i])+" "+decimal.format(AptitudAcumulada[i])+" "+(Rangos[i]));
     
      //generarIndividuos();
        }
        llenarTabla();
    }
    private static void llenar(){
         String numeroBinario="";
    for(int i=0; i<ValorCromosoma.length; i++){
     
      //generarIndividuos();
      
      for(int j=0;j<numCromosomas;j++){
      numeroBinario+=generarIndividuos();
      
      }
      
     ValorCromosoma[i]=numeroBinario;
     
      //System.out.print(numeroBinario);
      numeroBinario="";
    }
   
       for(int i=0; i<ValorCromosoma.length; i++){
      for(int j=0;j<numCromosomas;j++){
      numeroBinario+=generarIndividuos();
      }     
     ValorCromosoma[i]=numeroBinario;
     ValorDecimal[i]=ConvertirADecimal(ValorCromosoma[i]);
     ValorFuncion[i]= funcion(6, 2, 3, -1, ValorDecimal[i], 3);
     suma+=ValorFuncion[i];
      //System.out.print(numeroBinario);
      numeroBinario="";
    }
       double acumulador=0;
       
     for(int i=0;i<AptitudRelativa.length;i++){
     AptitudRelativa[i]=(ValorFuncion[i]/suma);
     AptitudAcumulada[i]=AptitudRelativa[i]*100;
         try {
                Rangos[i]= "("+decimal.format(acumulador)+" - "+decimal.format(AptitudAcumulada[i]+acumulador)+")";
                acumulador+=AptitudAcumulada[i];
     
     
         } catch (Exception e) {
         }
     
     }  
       
    }
    public static void llenarTabla( ){
        Object data [][] = new Object [numIndividuos][6];
        String columName [] = {"CROMOSOMA","X","F(X)","APTITUD RELATIVA","ACTITUD ACUMULADA","RANGO"};
        for (int i = 0; i <numIndividuos ; i++) {
            for (int j = 0; j < 6; j++) {
                data[j][0] = String.valueOf(ValorCromosoma[j]); 
                data[j][1] = String.valueOf(ValorDecimal[j]); 
                data[j][2] = String.valueOf(ValorFuncion[j]);
                data[j][3] = String.valueOf(decimal.format(AptitudRelativa[j]));
                data[j][4] = String.valueOf(decimal.format(AptitudAcumulada[j]));
                data[j][5] = Rangos[j];
                
            }
        }
        DefaultTableModel modelo = new DefaultTableModel(data, columName);
        NewJFrame frame = new NewJFrame();
  frame.show();
     frame.tablaContenido.setModel(modelo);
     
        
    }
         
    
//    private double Truncar(double numero){
//        
////    NumberFormat number= NumberFormat.getInstance();
////    number.setMaximumFractionDigits(2);
////    number.setRoundingMode(RoundingMode.DOWN);
////    return number.format(numero);
//    
//    }
      /**
	 * Método para generar el conjunto inicial de individuos
	 */
	public static String generarIndividuos(){
        String valor="";
//		for (int i=0; i<numIndividuos; i++){
//			for (int j=0; j<numCromosomas; j++){
				double aleatorio = Math.random();
				if (aleatorio < 0.5)
					//individuos[i][j] = 0;
                                    valor="0";
				else
					//individuos[i][j] = 1;
                                    valor="1";
			//}
		//}
                                return valor;
	}
        
    private static double ConvertirADecimal(String numeroBinario){
    Stack vectorBase2= new Stack();
    
    double numero=0;
    for(int i=0;i<numeroBinario.length();i++){
        vectorBase2.add(Math.pow(2,i));
    }
    
    for(int i=0,j=vectorBase2.size()-1;i<numeroBinario.length();i++,j--){
        if(numeroBinario.charAt(i)=='1'){
            numero+=(double)vectorBase2.get(j);
        }
        
    
    }
    //System.out.println("Numero Decimal de "+numeroBinario);
    //System.out.println(numero);
    return numero;
    
//    for(int i=vectorBase2.size()-1;i>=0;i--){
//       
//    System.out.print(vectorBase2.get(i)+"  ");
//    }
    
    }
    private static void ConvertirABinario(String numeroDecimal){
    Stack pila= new Stack(); 
    int _numeroDecimal= Integer.valueOf(numeroDecimal);
    int Dividendo=_numeroDecimal;
    int divisor=2;
    int cociente=0;
    int residuo;
    
    while(Dividendo>=2){
    cociente = Dividendo/divisor;
    residuo= Dividendo%divisor;
    pila.add(residuo);
    
    Dividendo=cociente;
    }
    pila.add(cociente);
    
    System.out.println("Numero Binario de "+numeroDecimal+" :");
    for(int i=pila.size()-1;i>=0;i--){
        System.out.print(pila.get(i));
    }
    System.out.println();
    }

private static String convertirABinario(int a){
                               int i=0;
                               int bin[]=new int[8];
                               while(a>=1){
                                               if((a%2)==0) bin[i]=0;
                                               else bin[i]=1;
                                               a=a/2;
                                               i++;
                               }
                               int k=bin.length-1;
                               String cad="";
                               while(k>=0){
                                               cad+=bin[k];
                                               k--;
                               }
                               int lon=cad.length();
                               String cad2="";
                               for(int l=1;l<=7-lon;l++){
                                               cad2+="0";
                               }
                               cad2+=cad;
                               return cad2;
                }


private static int GenerarPuntoCruce(){
    Random rnd= new Random();
int randomico = (int)(rnd.nextDouble()*6+0);
//System.out.println("aleatorio "+randomico);
return randomico;

}

/**
	 * Metodo que devuelve el valor de la funcion 
         * funciones hasta de grado 3
         * ax^3 + bx^2 + cx + d
	 */
public static double funcion (double coeficienteX3,double coeficienteX2,double coeficienteX1,double terminoIndependiente,double ValorX,int gradoFuncion){
	double valorFuncion=0;
            switch (gradoFuncion)	{
            case 2:
                valorFuncion= (Math.pow(ValorX, 2))*coeficienteX2+(coeficienteX1*ValorX)+terminoIndependiente;
                    
                break;
            case 3:
                valorFuncion= (Math.pow(ValorX, 3))*coeficienteX3+(Math.pow(ValorX, 2))*coeficienteX2+(coeficienteX1*ValorX)+terminoIndependiente;
                break;
        }
            return valorFuncion;
	}
}

