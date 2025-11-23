package Projeto.Factory.Pedido;

public class IcedCoffee implements CafeInterface {

    @Override
    public String getTipo() {
        return "IcedCoffee";
    }

    @Override
    public String getDescricao() {
        return "400ml";
    }

    @Override
    public double calcularPreco() {
        return 9.80;
    }
}

