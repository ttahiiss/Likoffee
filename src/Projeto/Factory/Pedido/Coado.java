package Projeto.Factory.Pedido;

public class Coado implements CafeInterface {

    @Override
    public String getDescricao() {
        return "200ml";
    }

    @Override
    public double calcularPreco() {
        return 3.80;
    }
}