package Projeto.Factory.Pedido;

public class Mocha implements Cafe {
    @Override
    public String getDescricao() {
        return "Combinação indulgente de expresso, chocolate e leite vaporizado. Coberto com chantilly.";
    }

    @Override
    public double calcularPreco() {
        return 8.50;
    }
}
