
class TesteHorista{


    public static void main(String[] args) {
        Horista horista = new Horista("Erick", "Nagao", "12345678900", 40, 50);
        System.out.println(horista.dados());

        Horista horistaPadrao = new Horista();
        System.out.println(horistaPadrao.dados());

    }

}
