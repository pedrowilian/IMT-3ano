package Ex1;

class FuncionarioTest {

    public static void main(String[] args) {
        Funcionario f = new Funcionario("João", 20, "Trabalho", 1000);
        System.out.println(f.toString());

        Funcionario f2 = new Funcionario("Maria", 30, "Trabalho", 1000);
        System.out.println(f2.toString());
    }
}
