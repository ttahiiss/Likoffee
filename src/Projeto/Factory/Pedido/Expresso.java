package Projeto.Factory.Pedido;

public class Expresso implements CafeInterface {

    @Override
    public String getDescricaoBase() {
        return "Expresso";
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
