import java.util.LinkedList;
import java.util.List;

import static java.lang.Integer.max;

public class Div_Conq {
    public static void main(String[] args) {
        int n = 32;
        int[] vetor = new int[n];
        for (int i =0;i<n;i++){
            vetor[i]= (int) (Math.random()*100);
        }
        Div_Conq div_conq = new Div_Conq();
        vetor = div_conq.mergeSort(vetor);
        for (int i = 0;i<vetor.length;i++){
            System.out.print("["+vetor[i]+"]");
        }
        int max2 = div_conq.maxVal2(vetor,0,5);
        System.out.println("\n"+max2);

        System.out.println(div_conq.multiply(13,12,4));
    }

    public int[] mergeSort(int[] vetor){
        if (vetor.length==1)
            return vetor;
        int tamanho = vetor.length;
        if (tamanho%2==1){
            tamanho = vetor.length+1;
        }
        int[] lista = new int[tamanho/2];
        int[] lista2 = new int[vetor.length-(tamanho/2)];
        System.arraycopy(vetor,0,lista,0,tamanho/2);
        System.arraycopy(vetor,tamanho-(tamanho/2),lista2,0,vetor.length-(tamanho/2));

        int[] a = mergeSort(lista);
        int[] b = mergeSort(lista2);
        vetor = merge(a,b);
        return vetor;
    }

    private int[] merge(int[] a, int[] b) {
        int[] lista = new int[a.length+b.length];
        int j = 0;
        int i=0;
        int aux = 0;
        while (i<a.length&&j<b.length){
            if (a[i]<b[j]){
                lista[aux] = a[i];
                aux++;
                i++;
            }else if(a[i]>b[j]){
                lista[aux] = b[j];
                aux++;
                j++;
            }else{
                lista[aux] = a[i];
                aux++;
                i++;
                lista[aux] = b[j];
                aux++;
                j++;
            }
        }
        if (b.length>j)
            System.arraycopy(b,j,lista,i+j,b.length-j);

        if (a.length>i)
            System.arraycopy(a,i,lista,i+j,a.length-i);
        //System.arraycopy(b,j,lista,i+j,b.length-1);

        return lista;
    }

    public int maxVal1(int[] a, int n){
        int max = a[0];
        for (int i=1;i<n;i++){
            if (a[i]>max)
                max=a[i];
        }
        return max;
    }

    public int maxVal2(int[]a,int init,int end){
        if (end-init<=1)
            return max(a[init],a[end]);
        else{
            int m = (init+end)/2;
            int v1 = maxVal2(a,init,m);
            int v2 = maxVal2(a,m+1,end);
            return max(v1,v2);
        }
    }

    public long multiply(long x,long y,long n){
        if (n==1)
            return x*y;
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
            return (long) ((Math.pow(2,2*m))*e + (Math.pow(2,m))*(g+h)+f);
        }
    }

}
