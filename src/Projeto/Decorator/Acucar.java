package Projeto.Decorator;

import Projeto.Factory.Pedido.CafeInterface;

public class Acucar extends CafeAbstract{
    protected CafeInterface cafeDecorado;

    public Acucar(CafeInterface cafe) {
        super(cafe);
    }

    @Override
    public String getDescricao() {
        return cafeDecorado.getDescricao() + "+ Açucar";
    }

    @Override
    public double calcularPreco() {
        return cafeDecorado.calcularPreco() + 0.0;
    }
}