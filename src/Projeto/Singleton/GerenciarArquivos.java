package Projeto.Singleton;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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
                    if (partes.length == 3) {
                        String tipo = partes[0];
                        int quantidade = Integer.parseInt(partes[2]);
                        estoqueQuantidades.put(tipo, quantidade);
                    }else {
                        System.out.println("Linha inválida ignorada: " + linha);
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
        try {
            cafeFactory.criarCafe(tipo);
        } catch (IllegalArgumentException e) {
            System.out.println("Café '" + tipo + "' não existe no sistema!");
            return;
        }

        int atual = estoqueQuantidades.getOrDefault(tipo, 0);
        estoqueQuantidades.put(tipo, atual + quantidade);
        salvarEstoque();
        System.out.println("Café adicionado no estoque :)");
    }

    public void listarEstoqueCompleto() {
        if (estoqueQuantidades.isEmpty()) {
            System.out.println("\n==O estoque esta vazio, sinto muito==\n");
        }else {
            System.out.println("=== ESTOQUE COMPLETO DE CAFÉS ===");
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

    public boolean removerCafe(String tipo, boolean confirmar) {
        if (!estoqueQuantidades.containsKey(tipo)) {
            System.out.println("\nCafé não encontrado: " + tipo + "\n");
            return false;
        }

        CafeInterface cafe = cafeFactory.criarCafe(tipo);
        int quantidade = estoqueQuantidades.get(tipo);

        if (confirmar) {
            System.out.println("Você estaria removendo: ");
            System.out.printf("   %s - %s - %d unidades%n",
                    tipo, cafe.getDescricao(), quantidade);
            System.out.print("Confirmar remoção? (s/n): ");

            Scanner scanner = new Scanner(System.in);
            String resposta = scanner.nextLine().trim().toLowerCase();

            if (resposta.equals("s") || resposta.equals("sim")) {
                System.out.println("Okay, removendo produto");
            } else {
                System.out.println("Remoção cancelada");
                return false;
            }
        }
        estoqueQuantidades.remove(tipo);
        salvarEstoque();
        System.out.println("Café removido: " + tipo);
        return true;
    }
}