package Projeto.Decorator;

import Projeto.Factory.Pedido.CafeInterface;

public class Canela extends CafeAbstract{
    protected CafeInterface cafeDecorado;

    public Canela(CafeInterface cafe) {
        super(cafe);
    }

    @Override
    public String getDescricao() {
        return cafeDecorado.getDescricao() + " + Canela";
    }

    @Override
    public double calcularPreco() {
        return super.calcularPreco() + 0.10;
    }
}