package Projeto.Factory.Pedido;

public class Americano implements CafeInterface {
    @Override
    public String getDescricao() {
        return "Um espresso diluído em água quente, resultando em uma xícara grande, lisa e aromática, sem a intensidade do espresso puro.";
    }

    @Override
    public double calcularPreco() {
        return 5.00;
    }
}
