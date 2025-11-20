package Projeto.Factory.Pedido;

public class CafeComLeite implements CafeInterface {
    @Override
    public String getDescricao() {
        return "Blend perfeito entre café coado e leite vaporizado. Equilíbrio entre sabor e cremosidade.";
    }

    @Override
    public double calcularPreco() {
        return 5.20;
    }
}
