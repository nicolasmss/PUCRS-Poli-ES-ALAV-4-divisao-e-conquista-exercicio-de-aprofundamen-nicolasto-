import java.util.LinkedList;
import java.util.List;

public class Div_Conq {
    public static void main(String[] args) {
        int[] lista = new int[7];
        lista[0] = 3;
        lista[1] = 5;
        lista[2] = 4;
        lista[3] = 1;
        lista[4] = 6;
        lista[5] = 5;
        lista[6] = 3;
        Div_Conq div_conq = new Div_Conq();
        lista = div_conq.mergeSort(lista);
        for (int i = 0;i<lista.length;i++){
            System.out.print(lista[i]);
        }
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

}
