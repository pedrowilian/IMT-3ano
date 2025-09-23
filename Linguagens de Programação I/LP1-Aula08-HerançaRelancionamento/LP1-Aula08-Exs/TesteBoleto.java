class TesteBoleto {
    public static void main(String[] args) {
        Boleto boleto= new Boleto("Cleber", "37739658855", 12, "123", 2, 2, 2022);
        System.out.println(boleto.getNome());
        System.out.println(boleto.getCpf());
        System.out.println(boleto.getValorAserPago());
        System.out.println(boleto.getNumeroBoleto());
        System.out.println(boleto.getDia());
        System.out.println(boleto.getMes());
        System.out.println(boleto.getAno());
    }
}
