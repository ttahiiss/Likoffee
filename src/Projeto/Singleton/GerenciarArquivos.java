package Projeto.Singleton;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import Projeto.Factory.Pedido.CafeInterface;
import Projeto.Factory.CafeFactory;

public class GerenciarArquivos {
    private static GerenciarArquivos instancia;
    private Map<String, Integer> estoqueQuantidades; //so armazena quantidades
    private final String caminho = "estoque_cafes.txt";
    private CafeFactory cafeFactory;


    private GerenciarArquivos() {
        estoqueQuantidades = new HashMap<>();
        cafeFactory = new CafeFactory();
        carregarEstoque();
    }

    public static GerenciarArquivos getInstancia() {
        if (instancia == null) {
            instancia = new GerenciarArquivos();
        }
        return instancia;
    }

    //confere se o estoque existe e cria um caso n
    public void carregarEstoque() {
        File arquivo = new File(caminho);
        if (!arquivo.exists()) {
            System.out.println("Arquivo de estoque não encontrado, criando novo...");
            inicializarEstoqueVazio();
        } else {
            try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
                String linha;
                while ((linha = br.readLine()) != null) {
                    String[] partes = linha.split("\\|");
                    if (partes.length == 4) {
                        String tipo = partes[0];
                        int quantidade = Integer.parseInt(partes[3]);
                        estoqueQuantidades.put(tipo, quantidade);
                    }
                }
            } catch (IOException e) {
                System.out.println("Erro ao carregar estoque: " + e.getMessage());
            }
        }
    }
    public void inicializarEstoqueVazio() {
        String[] tipos = {"Expresso", "Coado", "CafeComLeite", "Cappuccino", "Latte", "Mocha", "IcedCoffee", "Frappe", "Cortado", "Americano"};
        for (String tipo : tipos) {
            estoqueQuantidades.put(tipo, 0);
        }
        salvarEstoque();
    }

    public void salvarEstoque() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminho))) {
            for (Map.Entry<String, Integer> entry : estoqueQuantidades.entrySet()) {
                String tipo = entry.getKey();
                int quantidade = entry.getValue();

                CafeInterface cafeInterface = cafeFactory.criarCafe(tipo);
                double preco = cafeInterface.calcularPreco();

                String linha = String.format("%s|%.2f|%d", tipo, preco, quantidade);
                bw.write(linha);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar estoque: " + e.getMessage());
        }
    }

    public void adicionarQuantidade(String tipo, int quantidade) {
        int atual = estoqueQuantidades.getOrDefault(tipo, 0);
        estoqueQuantidades.put(tipo, atual + quantidade);
        salvarEstoque();
    }

    public void listarEstoqueCompleto() {
        System.out.println("=== ESTOQUE COMPLETO DE CAFÉS (INCLUINDO ZERADOS) ===");

        for (String tipo : estoqueQuantidades.keySet()) {
            CafeInterface cafeInterface = cafeFactory.criarCafe(tipo);
            int quantidade = estoqueQuantidades.get(tipo);

            System.out.printf("%-15s - R$%-6.2f - %3d unidades%n",
                    tipo,
                    cafeInterface.calcularPreco(),
                    quantidade);
        }
    }
}