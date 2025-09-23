
class TesteEmpregado {

    public static void main(String[] args) {
        Empregado empregado = new Empregado();
        System.out.println(empregado.dados());
        System.out.println(empregado.salario());

        Empregado empregado1 = new Empregado("Arthur", "Gama", "53588500820");
        System.out.println(empregado1.dados());
        System.out.println(empregado1.salario());
    }

}
