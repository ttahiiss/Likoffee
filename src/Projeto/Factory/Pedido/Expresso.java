package Projeto.Factory.Pedido;

public class Expresso implements CafeInterface {

    @Override
    public String getTipo() {
        return "expresso";
    }

    @Override
    public String getDescricao() {
        return "300ml";
    }

    @Override
    public double calcularPreco() {
        return 4.50;
    }
}
