
class TesteComissionado{
    public static void main(String[] args) {
        Comissionado comissionado = new Comissionado("Arthur", "Gama", "4235435", 30, 10);

        System.out.println(comissionado.dados());

        Comissionado commissioned1 = new Comissionado("João", "Silva", "123.456.789-00", 1000, 100);

        System.out.println(commissioned1.dados());


    }

}
