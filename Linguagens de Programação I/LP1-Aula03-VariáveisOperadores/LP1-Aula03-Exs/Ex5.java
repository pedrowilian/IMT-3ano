import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Ex5 {
    public static void lerTextoInvertido(String nomeArquivo){
        String[] palavras = new String[10];
        int count = 0;
        try{
            File arquivo = new File(nomeArquivo);
            Scanner sc = new Scanner(arquivo);
            while(sc.hasNext()){
                palavras[count] = sc.next();
                count++;
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = count -1; i>=0;i--){
            System.out.print(palavras[i]+" ");
        }
    }
    
        public static void main(String[] args){
            lerTextoInvertido("teste.txt");

    }
}
