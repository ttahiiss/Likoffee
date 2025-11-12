package Projeto.Factory;
import Projeto.Factory.Pedido.*;

public class CafeFactory {
    public Cafe criarCafe(String tipo) {
        switch (tipo.toLowerCase()) {
            case "expresso":
                return new Expresso();
            case "coado":
                return new Coado();
            case "com leite":
                return new CafeComLeite();
            case "cappuccino":
                return new Cappuccino();
            case "latte":
                return new Latte();
            case "mocha":
                return new Mocha();
            case "iced coffee":
                return new IcedCoffee();
            case "frappe":
                return new Frappe();
            case "cortado":
                return new Cortado();
            case "americano":
                return new Americano();
            default:
                throw new IllegalArgumentException("Tipo de café não disponível: " + tipo);
        }
    }

}
