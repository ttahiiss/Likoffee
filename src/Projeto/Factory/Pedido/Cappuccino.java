package Projeto.Factory.Pedido;

public class Cappuccino implements CafeInterface {

    @Override
    public String getTipo() {
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
