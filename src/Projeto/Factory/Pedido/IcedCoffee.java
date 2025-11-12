package Projeto.Factory.Pedido;

public class IcedCoffee implements Cafe {
    @Override
    public String getDescricao() {
        return "Café coado gelado lentamente por 12 horas. Sabor suave e refrescante, servido com gelo.";
    }

    @Override
    public double calcularPreco() {
        return 9.80;
    }
}

