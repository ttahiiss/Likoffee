package Projeto.Decorator;

import Projeto.Factory.Pedido.CafeInterface;

public class Canela extends CafeAbstract {

    @Override
    public String getDescricaoBase() {
        return cafeDecorado.getDescricaoBase();
    }

    public Canela(CafeInterface cafe) {
        super(cafe);
    }

    @Override
    public String getDescricao() {
        return cafeDecorado.getDescricao() + " + Canela";
    }

    @Override
    public double calcularPreco() {
        return cafeDecorado.calcularPreco() + 0.25;
    }
}
