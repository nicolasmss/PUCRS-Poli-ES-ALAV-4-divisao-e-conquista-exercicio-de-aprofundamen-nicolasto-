import java.util.LinkedList;
import java.util.List;

import static java.lang.Integer.max;

public class Div_Conq {
    public static long cont = 0;

    public static void main(String[] args) {
        ContadorTempo tempo = new ContadorTempo();
        int n = 1048576;
        int[] vetor = new int[n];
        for (int i =0;i<n;i++){
            vetor[i]= (int) (Math.random()*1000);
        }
        Div_Conq div_conq = new Div_Conq();

        tempo.comecar();//contador de tempo
        System.out.println(div_conq.maxVal1(vetor,n));//EX2 AQUI
        tempo.parar();

        System.out.println("Iterações do ex2: "+cont);

        cont=0;
        tempo.comecar();//contador de tempo
        vetor = div_conq.mergeSort(vetor);//EX1 AQUI
        tempo.parar();

        //for (int i = 0;i<vetor.length;i++){
       //     System.out.print("["+vetor[i]+"]");
       // }
        System.out.println("\nIterações do ex1: "+cont);

        cont=0;
        tempo.comecar();//contador de tempo
        int max2 = div_conq.maxVal2(vetor,0,n-1);//EX3 AQUI
        tempo.parar();
        System.out.println(max2);
        System.out.println("Iterações do ex3: "+cont);

        cont=0;
        tempo.comecar();//contador de tempo
        System.out.println(div_conq.multiply(132546,123456,64));//EX4 AQUI
        tempo.parar();
        System.out.println("Iterações do ex4: "+cont);
    }

    //EX 1
    public int[] mergeSort(int[] vetor){
        if (vetor.length==1) {
            cont+=3;
            return vetor;
        }
        int tamanho = vetor.length;
        if (tamanho%2==1){
            tamanho = vetor.length+1;
            cont+=5;
        }
        int[] lista = new int[tamanho/2];
        int[] lista2 = new int[vetor.length-(tamanho/2)];
        System.arraycopy(vetor,0,lista,0,tamanho/2);
        System.arraycopy(vetor,tamanho-(tamanho/2),lista2,0,vetor.length-(tamanho/2));

        int[] a = mergeSort(lista);
        int[] b = mergeSort(lista2);
        vetor = merge(a,b);
        cont+=22;
        return vetor;
    }

    private int[] merge(int[] a, int[] b) {
        int[] lista = new int[a.length+b.length];
        int j = 0;
        int i=0;
        int aux = 0;
        cont+=11;
        while (i<a.length&&j<b.length){
            cont+=8;
            if (a[i]<b[j]){
                lista[aux] = a[i];
                aux++;
                i++;
                cont+=7;
            }else if(a[i]>b[j]){
                lista[aux] = b[j];
                aux++;
                j++;
                cont+=10;
            }else{
                lista[aux] = a[i];
                aux++;
                i++;
                lista[aux] = b[j];
                aux++;
                j++;
                cont+=17;
            }
        }
        if (b.length>j){
            System.arraycopy(b,j,lista,i+j,b.length-j);
            cont+=4;
        }
        if (a.length>i){
            System.arraycopy(a,i,lista,i+j,a.length-i);
            cont+=4;
        }

        cont+=5;
        return lista;
    }

    //EX 2
    public int maxVal1(int[] a, int n){
        cont+=4;
        int max = a[0];
        for (int i=1;i<n;i++){
            cont+=6;
            if (a[i]>max){
                max=a[i];
                cont+=2;
            }
        }
        return max;
    }

    //EX 3
    public int maxVal2(int[]a,int init,int end){
        cont+=2;
        if (end-init<=1){
            cont+=3;
            return max(a[init],a[end]);
        }
        else{
            int m = (init+end)/2;
            int v1 = maxVal2(a,init,m);
            int v2 = maxVal2(a,m+1,end);
            cont+=10;
            return max(v1,v2);
        }
    }

    //EX 4
    public long multiply(long x,long y,long n){
        cont+=1;
        if (n==1){
            cont+=2;
            return x*y;
        }
        else{
            long m = n/2;
            long a = (long) (x/(Math.pow(2,m)));
            long b = (long) (x%(Math.pow(2,m)));
            long c = (long) (y/(Math.pow(2,m)));
            long d = (long) (y%(Math.pow(2,m)));
            long e = multiply(a,c,m);
            long f = multiply(b,d,m);
            long g = multiply(b,c,m);
            long h = multiply(a,d,m);
            cont+=40;
            return (long) ((Math.pow(2,2*m))*e + (Math.pow(2,m))*(g+h)+f);
        }
    }

}

class ContadorTempo {

    public static long tempInicial;

    public static long tempFinal;

    public static void comecar(){
        tempInicial = System.currentTimeMillis();
    }

    public static void parar(){
        tempFinal = System.currentTimeMillis();

        long dif = (tempFinal - tempInicial);
        System.out.println(String.format("Tempo de execução: %02d segundos  e %02d milisegundos", dif/1000, dif%1000));
    }
}

