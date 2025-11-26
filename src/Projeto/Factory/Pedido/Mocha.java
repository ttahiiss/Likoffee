package Projeto.Factory.Pedido;

public class Mocha implements CafeInterface {

    @Override
    public String getTipo() {
        return "mocha";
    }

    @Override
    public String getDescricao() {
        return "350ml";
    }

    @Override
    public double calcularPreco() {
        return 8.50;
    }
}
