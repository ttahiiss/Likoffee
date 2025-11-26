package Projeto.Factory.Pedido;


public class Frappe implements CafeInterface {

    @Override
    public String getTipo() {
        return "frappe";
    }

    @Override
    public String getDescricao() {
        return "500ml";
    }

    @Override
    public double calcularPreco() {
        return 12.90;
    }
}

