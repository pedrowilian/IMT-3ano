class TesteCheque {

    public static void main(String[] args) {
        Cheque cheque = new Cheque("Arthur", "12345678910", 10.5, "12");

        System.out.println(cheque.getCheckNumber());
        cheque.setCheckNumber("10");
        System.out.println(cheque.getCheckNumber());

        if (cheque instanceof Pagamento)
        {
            System.out.println("Cheque é uma instância de Pagamento");
        }
        else {
            System.out.println("Cheque NÃO é uma instância de Pagamento");
        }

    }
}
