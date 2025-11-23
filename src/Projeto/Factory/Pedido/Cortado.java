package Projeto.Factory.Pedido;

public class Cortado implements CafeInterface {

    @Override
    public String getDescricaoBase() {
        return "Cortado";
    }

    @Override
    public String getDescricao() {
        return "200ml";
    }

    @Override
    public double calcularPreco() {
        return 7.20;
    }
}
