
class TestePessoaFisica {

    public static void main(String[] args) {
        PessoaFisica privateIndividual = new PessoaFisica("Arthur", "Gama", "123");

        System.out.println(privateIndividual.dados());

        PessoaFisica privateIndividualGeneric = new PessoaFisica();

        System.out.println(privateIndividualGeneric.dados());

    }

}
