package Projeto.Factory;

import Projeto.Factory.Pedido.CafeInterface;
import Projeto.Factory.Pedido.Expresso;
import Projeto.Factory.Pedido.Coado;
import Projeto.Factory.Pedido.CafeComLeite;
import Projeto.Factory.Pedido.Cappuccino;
import Projeto.Factory.Pedido.Latte;
import Projeto.Factory.Pedido.Mocha;
import Projeto.Factory.Pedido.IcedCoffee;
import Projeto.Factory.Pedido.Frappe;

public class CafeFactory {

    // ⭐ CORRIJA O RETORNO: CafeFactory → Cafe
    public CafeInterface criarCafe(String tipo) {
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
            default:
                throw new IllegalArgumentException("Tipo de café não disponível: " + tipo);
        }
    }
}