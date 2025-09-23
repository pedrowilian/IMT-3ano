class TesteTarefeiro {
    public static void main(String[] args) {
        Tarefeiro tarefeiro = new Tarefeiro("Arthur", "Gama", "1234", 1000, 10);
        System.out.println(tarefeiro.dados());


        Tarefeiro TarefeiroPadrao = new Tarefeiro();

        System.out.println(TarefeiroPadrao.dados());
    }
}
