package Projeto.Decorator;

import Projeto.Factory.Pedido.CafeInterface;

public class Bolachinha extends CafeAbstract {

    public Bolachinha(CafeInterface cafe) {
        super(cafe);
    }

    @Override
    public String getDescricao() {
        return cafeDecorado.getDescricao() + " + Bolachinha";
    }

    @Override
    public double calcularPreco() {
        return cafeDecorado.calcularPreco() + 1.50;
    }
}
