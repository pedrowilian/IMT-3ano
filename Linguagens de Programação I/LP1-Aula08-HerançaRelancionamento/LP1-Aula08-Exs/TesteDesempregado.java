
class TesteDesempregado {
    public static void main(String[] args) {
        Desempregado arthur = new Desempregado("Arthur", "Gama", "1234", 10.1);
        System.out.println(arthur.dados());

        arthur.setCpf("4321");
        System.out.println(arthur.dados());

        arthur.setSeguroDesemprego(20.2);
        System.out.println(arthur.dados());
    }
}
