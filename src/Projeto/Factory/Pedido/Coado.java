package Projeto.Factory.Pedido;

public class Coado implements Cafe {
    @Override
    public String getDescricao() {
        return "Café puro e intenso, preparado sob pressão com grãos moídos na hora. Sabor encorpado e cremoso";
    }

    @Override
    public double calcularPreco() {
        return 3.80;
    }
}