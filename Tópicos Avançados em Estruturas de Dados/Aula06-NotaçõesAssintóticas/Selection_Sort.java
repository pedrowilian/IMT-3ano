// Pedro Wilian Palumbo Bevilacqua RA: 23.01307-9
// É possível notar que existem dois estruturas de laço (for) logo a complexidade é O(n²)

public class Selection_Sort {
    public static void selection_sort(int num[]){
        int i, j, min, aux;
        for (i = 0; i < (num.length-1); i++) {
            min = i;
            for (j = (i+1); j < num.length; j++) {
                if(num[j] < num[min]) 
                    min = j;
                }
                if (i != min) {
                    aux = num[i];
                    num[i] = num[min];
                    num[min] = aux;
                }
            }
        }

    public static void main(String[] args) throws Exception {
        int vet[] = {5, 4, 3, 2, 1};
        selection_sort(vet);

        for(int i=0; i<vet.length; i++)
            System.out.print(vet[i]+" ");
    }
}
