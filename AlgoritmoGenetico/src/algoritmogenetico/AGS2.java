/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogenetico;

 public class AGS2 {
                private static int maxSize=256, maxSizeP=100;//tamaño de los arreglos
                private static float table[][]=new float[maxSize][3];//tabla con la funcion
                private static float poblacion[][]=new float[maxSizeP][4];//poblacion
                private static float tAux[][];
private static String binario[]=new String[maxSize];//cadena de numeros binarios de la tabla
private static String binarioP[]=new String[maxSizeP];//cadena de binarios de la poblacion
private static String tempBin[]=new String[maxSizeP];//aqui almacenamos las poblaciones siguientes
               
                /**
                * En este método convertimos nuestro número decimal a una cadena de binarios
                * y completamos hasta 8 bits*/

public static String convertirABinario(int a){
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
               

                /*** acá evaluaremos nuestra función a utilizar en este caso fue:
                * 36-((6*x-3)^2-3)^2     */
                public static void evalFuncion(){
                               int totalNums=maxSize;
                               for(int i=0;i<totalNums;i++){
                                               table[i][0]=i;
                                               binario[i]=convertirABinario(i);
                                               float aux=(float) (i*0.00390625);
                                               table[i][1]=aux;
                                               float aux11=(float) (i*0.00390625);
                                               float aux1=(6*aux11)-3;
                                               float pot=(float) Math.pow(aux1,2);
                                               float aux2=pot-3;
                                               float pot2=(float) Math.pow(aux2,2);
                                               float aux3=36-pot2;
                                               table[i][2]=aux3;
                               }
                }


                /** * Generamos la población de forma aleatoria y asignamos:
                * index, calificación, probabilidad de selección y suma de calificación    */

                public static void generarPoblacion(){
                               float sumaCalif=0, prob=0;
                               for(int i=0;i<maxSizeP;i++){
                                               int temp=(int) (Math.random()*256);
                                               binarioP[i]=convertirABinario(temp);
                                               poblacion[i][0]=temp;
                                               poblacion[i][1]=table[temp][1];//asignamos calificación
                                               sumaCalif=sumaCalif+poblacion[i][1];
                                               poblacion[i][3]=sumaCalif;
                               }

                               for(int j=0;j<maxSizeP;j++){
                                               float probSel=poblacion[j][1]/sumaCalif;
                                               poblacion[j][2]=probSel;
                                               prob+=probSel;
                               }
                               //System.out.println(prob);
                }
               
                /** * Ahora evaluamos la población      */
                public static void evaluarPoblacion(){
                               tAux=new float[maxSizeP][3];
                               int k=0;
                               while(k<maxSizeP){
                                               double r=Math.random()*2;
                                               double c=poblacion[99][3]*r;
                                               float Ca=0;
                                               int i=0;
                                               boolean ban=true;
                                               while(ban){
                                                               Ca=Ca+poblacion[i][1];
                                                               if(Ca>c){
                                                                              tempBin[k]=binarioP[i];
                                                                              tAux[k][0]=poblacion[i][3];
                                                                              ban=false; }
                                                               else i++;
                                                               if(i==99)i=0;       }
                               k++;
                               }//end while
                              
                               /**

                               * Seleccionamos dos individuos para crear dos nuevos individuos
                               */
                               for(int m=0;m<tempBin.length;m+=2){
                                               double Pc;
                                               do{
                                                               Pc=Math.random();
                                               }while(Pc<0.4);
                                               double d=Math.random();
                                               if(d<Pc){
                                                               //cruza
                                                               int l;
                                                               do{
                                                                              l=(int)(Math.random()*7);
                                                               }while(l<2);
                                                               String subStrA=tempBin[m].substring(0,l);
/*obtenemos la subcadena de 1er individuo*/
                                                               String subStrB=tempBin[m+1].substring(l,8);
/*obtenemos la subcadena del 2do individuo*/                                                            
                                                               String subStrA2=tempBin[m].substring(l,8); /*obtenemos la subcadena de 1er individuo*/
                                                               String subStrB2=tempBin[m+1].substring(0,l);                
                                                               tempBin[m]=subStrA+subStrB;/*concatenamos*/
                                                               tempBin[m+1]=subStrA2+subStrB2;

                                               }//end if
                                               //else System.out.println("es mayor");
                               }//end for           
                }
                /**        * Código de mutación   */
                public static void mutar(){
                               double pmi=0.005;
                               double pmf=0.1;
                                for(int i=0;i<maxSizeP;i++){
                                               String cad="";
                                               for(int j=0;j<8;j++){
                                                               double q=Math.random();
                                                               char mander=tempBin[i].charAt(j);
                                                               if(q>pmi&&q<pmf){
                                                                              if(mander=='0')cad+="1";
                                                                              else cad+="0";  }
                                                               else cad+=mander;        }
                                               tempBin[i]=cad; } 
                }
               
                /** * Obtenemos cual es nuestro punto máximo en nuestra función   */
                public static int obtenerMayor(){
                               float x=0;
                               int pos=0;
                               for(int i=0;i<maxSize;i++){
                                               if(table[i][2]>x){
                                                               x=table[i][2];
                                                               pos=i;
                                               }              }
                               System.out.println("((((((((((((((((((((((((((((((((((((((("+table[pos][0]);
                               return pos;         }
               
/** * Para comprobar cuantos individuos son con los genes de la solución optima         */
                public static int generacionOptima(){
                               String optimo=binario[obtenerMayor()];
                               int encontrados=0;
                               for(int i=0;i<maxSizeP;i++){
                                               if(tempBin[i].equalsIgnoreCase(optimo)){
                                                               encontrados++;
                                               }
                               }
                               return encontrados;      }
               
                public static void displayTable(){
                               for(int i=0;i<maxSize;i++){
                                               for(int j=0;j<3;j++){
                                                               System.out.print(table[i][j]+"<-->");
                                               }
                                               System.out.print(binario[i]);
                                               System.out.println();    }              }
               
                public static void displayPob(){
                               for(int i=0;i<maxSizeP;i++){
                                               for(int j=0;j<4;j++){
                                                               System.out.print(poblacion[i][j]+"<-->");
                                               }
                                               System.out.print(binarioP[i]);
                                               System.out.println();    }              }

                public static void show(){
                               for(int i=0;i<tempBin.length;i++){
                                               System.out.println(tempBin[i]);
                               }              }
               
                public static void main(String ar[]){
                               evalFuncion();
//                               for(int i=0;i<256;i++){
//                                    for(int j=0;j<3;j++){
//                                    System.out.println("valor table["+i+"]"+"["+j+"]"+table[i][j]);
//                               }
//                               }
//                               System.out.println("IMPRESION BINARIOS");
//                               
//                               for(int i=0;i<binario.length;i++){
//                                    
//                                    System.out.println("valor binario["+i+"]"+binario[i]);
//                               
//                               }
                               displayTable();
                               generarPoblacion();
                               displayPob();
                               System.out.println("/////////////////////////////////////////");
                               evaluarPoblacion();
                               displayPob();
                               mutar();
                               System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
                               displayPob();
                               int x=generacionOptima();
                               int cont=0;
                               while(x<=3||cont<200){
                                               evaluarPoblacion();
                                               displayPob();
                                               mutar();
                                               displayPob();
                                               x=generacionOptima();
                                               System.out.println("Se encontraron x=...."+x);
                                               if(x>2){
                                                               System.out.println("Generacion optima :) stop"+cont);
                                                               break;
                                               }
                                               cont++;
                                               System.out.println("Conteo:...................."+cont);
                                               if(cont==200) break;      }             
                }    
 }