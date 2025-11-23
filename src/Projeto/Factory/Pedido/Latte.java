package Projeto.Factory.Pedido;

public class Latte implements CafeInterface {

    @Override
    public String getDescricao() {
        return "350ml";
    }

    @Override
    public double calcularPreco() {
        return 7.20;
    }
}
