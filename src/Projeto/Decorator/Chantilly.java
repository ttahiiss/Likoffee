package Projeto.Decorator;

import Projeto.Factory.Pedido.CafeInterface;

public class Chantilly extends CafeAbstract {

    public Chantilly(CafeInterface cafe) {
        super(cafe);
    }

    @Override
    public String getDescricao() {
        return cafeDecorado.getDescricao() + " + Chantilly";
    }

    @Override
    public double calcularPreco() {
        return cafeDecorado.calcularPreco() + 1.50;
    }
}

