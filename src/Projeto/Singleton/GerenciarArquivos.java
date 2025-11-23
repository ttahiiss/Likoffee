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
        String tipoNormalizado = tipo.trim().toLowerCase();

        try {
            cafeFactory.criarCafe(tipoNormalizado);
        } catch (IllegalArgumentException e) {
            System.out.println("Café '" + tipo + "' não existe no sistema");
            return;
        }

        int atual = estoqueQuantidades.getOrDefault(tipoNormalizado, 0);
        estoqueQuantidades.put(tipoNormalizado, atual + quantidade);
        salvarEstoque();
        System.out.println("Café adicionado no estoque :)");
    }

    public void listarEstoqueCompleto() {
        if (estoqueQuantidades.isEmpty()) {
            System.out.println("\n==O estoque esta vazio, sinto muito==\n");
        }else {
            System.out.println("\n=== ESTOQUE COMPLETO DE CAFÉS ===");
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

    public boolean removerQuantidade(String tipo, int quantidadeARemover) {
        String tipoC = tipo.trim().toLowerCase();

        String chaveEncontrada = null;
        for (String chave : estoqueQuantidades.keySet()) {
            if (chave.toLowerCase().equals(tipoC)) {
                chaveEncontrada = chave;
                break;
            }
        }

        if (chaveEncontrada == null) {
            System.out.println("Café não encontrado: " + tipo);
            System.out.println("Cafés disponíveis: " + estoqueQuantidades.keySet());
            return false;
        }

        int estoqueAtual = estoqueQuantidades.get(chaveEncontrada);

        if (quantidadeARemover <= 0) {
            estoqueQuantidades.remove(chaveEncontrada);
            salvarEstoque();
            System.out.println("\nCafé transferido completamente: " + chaveEncontrada + "\n");
            return true;
        }

        if (estoqueAtual < quantidadeARemover) {
            System.out.println("\nEstoque insuficiente de " + chaveEncontrada);
            System.out.println("Estoque atual: " + estoqueAtual + "\n");
            return false;
        }

        int novoEstoque = estoqueAtual - quantidadeARemover;

        if (novoEstoque == 0) {
            estoqueQuantidades.remove(chaveEncontrada);
            System.out.println("\nTransferidas " + quantidadeARemover + " unidades de " + chaveEncontrada);
            System.out.println("Acabou o cafêzinho :(\n");
        } else {
            estoqueQuantidades.put(chaveEncontrada, novoEstoque);
            System.out.println("\nTransferidas " + quantidadeARemover + " unidades de " + chaveEncontrada);
            System.out.println("Estoque restante: " + novoEstoque + " unidades\n");
        }

        salvarEstoque();
        return true;
    }

    public void registrarCompra(String tipo, double preco) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("historico.txt", true))) {
            String linha = tipo + "|" + preco;
            bw.write(linha);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao registrar compra: " + e.getMessage());
        }
    }

    public void verHistorico() {
        File arquivo = new File("historico.txt");

        if (!arquivo.exists()) {
            System.out.println("Nenhuma compra registrada ainda.");
            return;
        }

        System.out.println("\n=== HISTÓRICO DE COMPRAS ===");
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            double total = 0;
            int count = 0;

            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split("\\|");
                if (partes.length == 2) {
                    count++;
                    double preco = Double.parseDouble(partes[1]);
                    total += preco;
                    System.out.println(count + ". " + partes[0] + " | R$" + partes[1]);
                }
            }

            System.out.println("----------------------------");
            System.out.printf("Total: R$%.2f (%d vendas)%n", total, count);

        } catch (IOException e) {
            System.out.println("Erro ao ler histórico: " + e.getMessage());
        }
    }
}