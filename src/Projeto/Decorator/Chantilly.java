package Projeto.Decorator;
import Projeto.Factory.Pedido.CafeInterface;

public class Chantilly extends CafeAbstract{
    protected CafeInterface cafeDecorado;
    public Chantilly(CafeInterface cafe) {
        super(cafe);
    }

    @Override
    public String getDescricao() {
        return cafeDecorado.getDescricao() + " + Chantilly";
    }

    @Override
    public double calcularPreco() {
        return cafeDecorado.calcularPreco() + 2.00;
    }
}
