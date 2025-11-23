package Projeto.Factory.Pedido;

public class Cappuccino implements CafeInterface {

    @Override
    public String getDescricaoBase() {
        return "Cappuccino";
    }

    @Override
    public String getDescricao() {
        return "300ml";
    }

    @Override
    public double calcularPreco() {
        return 6.80;
    }
}
