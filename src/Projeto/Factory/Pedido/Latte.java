package Projeto.Factory.Pedido;

public class Latte implements CafeInterface {
    @Override
    public String getDescricao() {
        return "Mais suave que o cappuccino, com mais leite vaporizado e uma fina camada de espuma. Perfeito para desenhos.";
    }

    @Override
    public double calcularPreco() {
        return 7.20;
    }
}
