package Projeto.Factory.Pedido;

public class Cappuccino implements CafeInterface {


    @Override
    public String getDescricao() {
        return "Clássico italiano com 1/3 de expresso, 1/3 de leite vaporizado e 1/3 de espuma. Finalizado com cacau em pó.";
    }

    @Override
    public double calcularPreco() {
        return 6.80;
    }
}
