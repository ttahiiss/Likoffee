package Projeto.Factory.Pedido;


public class Frappe implements CafeInterface {
    @Override
    public String getDescricao() {
        return "Café gelado batido com gelo, leite e xarope. Textura cremosa e refrescante.";
    }

    @Override
    public double calcularPreco() {
        return 12.90;
    }
}

