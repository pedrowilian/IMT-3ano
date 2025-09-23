public class Vendedor extends Empregado {
    private double vendas;
    private double comissaoPorVenda;
    private final double salario;

    public Vendedor(String nome, String sobrenome, String cpf, double vendas, double comissaoPorVenda) {
        super(nome, sobrenome, cpf);
        this.vendas = vendas;
        this.comissaoPorVenda = comissaoPorVenda;
        this.salario = vendas*comissaoPorVenda;
    }

    public Vendedor() {
        super();
        this.vendas = 0.0;
        this.comissaoPorVenda = 0.0;
        this.salario = vendas*comissaoPorVenda;
    }
    @Override
    public double salario() {
        return salario;
    }

    public double getVendas() {
        return vendas;
    }

    public void setVendas(double vendas) {
        this.vendas = vendas;
    }

    public double geComissaoPorVendas() {
        return comissaoPorVenda;
    }

    public void setComissaoPorVendas(double comissaoPorVendas) {
        this.comissaoPorVenda = comissaoPorVendas;
    }
}
