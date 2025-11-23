package Projeto.Decorator;

import Projeto.Factory.Pedido.CafeInterface;

public class Canela extends CafeAbstract {

    @Override
    public String getTipo() {
        return cafeDecorado.getTipo();
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
