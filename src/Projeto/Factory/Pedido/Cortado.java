package Projeto.Factory.Pedido;

public class Cortado implements Cafe {
    @Override
    public String getDescricao() {
        return " Bebida espanhola preparada misturando café expresso misturado com uma quantidade aproximadamente igual de leite morno para reduzir a acidez";
    }

    @Override
    public double calcularPreco() {
        return 7.20;
    }
}
