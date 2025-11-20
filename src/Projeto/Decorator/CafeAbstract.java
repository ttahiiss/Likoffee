package Projeto.Decorator;

import Projeto.Factory.Pedido.CafeInterface;

public abstract class CafeAbstract implements CafeInterface {
    protected CafeInterface cafeDecorado;

    public CafeAbstract(CafeInterface cafe) {
        this.cafeDecorado = cafe;
    }

    @Override
    public String getDescricao() {
        return cafeDecorado.getDescricao();
    }

    @Override
    public double calcularPreco() {
        return cafeDecorado.calcularPreco();
    }
}
