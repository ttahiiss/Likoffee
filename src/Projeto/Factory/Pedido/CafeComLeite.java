package Projeto.Factory.Pedido;

public class CafeComLeite implements CafeInterface {

    @Override
    public String getTipo() {
        return "cafe com leite";
    }

    @Override
    public String getDescricao() {
        return "250ml";
    }

    @Override
    public double calcularPreco() {
        return 5.20;
    }
}
