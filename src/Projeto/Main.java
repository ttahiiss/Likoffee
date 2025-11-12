
import Projeto.Factory.CafeFactory;
import Projeto.Factory.Pedido.Cafe;
import Projeto.Singleton.GerenciarArquivos;
import java.util.*;
import java.util.HashMap;
import java.util.Map;

static Scanner entrada = new Scanner(System.in);
static List<Cafe> carrinho = new ArrayList<>();
static CafeFactory factory = new CafeFactory();


void main() {

    System.out.println("--- Liko's Coffee ---");
    System.out.println(" ");
    System.out.println("Escolha o modo: ");
    System.out.println("1 - Modo Cliente");
    System.out.println("2 - Modo Gerente");


    int modo = entrada.nextInt();

    if (modo == 1) {
        executarModoCliente();
    } else if (modo == 2) {
        System.out.print("Digite a senha do gerente: ");
        String senha = entrada.next();

        if (validarSenha(senha)) {
            executarModoGerente();
        } else {
            System.out.println("Senha incorreta! Voltando ao menu...");
        }
    }
}

public static void executarModoCliente(){
    boolean finalizarPedido = false;

    while (!finalizarPedido) {

        System.out.println(" ");
        System.out.println("--- Liko's Coffe ---");
        System.out.println(" ");
        System.out.println("Seja bem vindo! ><");
        System.out.println("Aqui você vai encontrar os melhores cafés da região.");
        System.out.println(" ");
        System.out.println("O que deseja hoje?: ");
        mostrarCardapio();

        int pedido = entrada.nextInt();
        Cafe cafeEscolhido = processarEscolha(pedido);

        if (cafeEscolhido != null) {
            System.out.println("Café selecionado: " + cafeEscolhido.getDescricao());
            System.out.println("Preço: R$" + cafeEscolhido.calcularPreco());

            System.out.print("Adicionar ao carrinho? (1-Sim / 2-Não): ");
            int resposta = entrada.nextInt();

            if (resposta == 1) {
                carrinho.add(cafeEscolhido);
                System.out.println("Adicionado ao carrinho!");

                System.out.println("\nO que deseja fazer?");
                System.out.println("1- Adicionar mais itens");
                System.out.println("2- Ver carrinho");
                System.out.println("3- Finalizar pedido");
                System.out.print("Escolha uma opção: ");
                int opcao = entrada.nextInt();

                if (opcao == 2) {
                    mostrarCarrinho();
                } else if (opcao == 3) {
                    finalizarPedido = true;
                }
            } else {
                System.out.println("\nO que deseja fazer?");
                System.out.println("1- Adicionar mais itens");
                System.out.println("2- Ver carrinho");
                System.out.println("3- Finalizar pedido");
                System.out.print("Escolha uma opção: ");
                int opcao = entrada.nextInt();

                if (opcao == 2) {
                    mostrarCarrinho();
                } else if (opcao == 3) {
                    finalizarPedido = true;
                }
            }
        }
    }
    System.out.println("Pedido finalizado!");
    finalizarPedido();
}
public static void mostrarCardapio() {
    System.out.println(" ");
    System.out.println("Tradicionais -         Especiais -           Cafés gelados -");
    System.out.println("1- Expresso            4- Cappuccino         7- Iced coffee");
    System.out.println("2- Café coado          5- Latte              8- Frappé");
    System.out.println("3- Café com leite      6- Mocha");
    System.out.println(" ");
    System.out.print("Escolha o número do café: ");
}

public static Cafe processarEscolha ( int opcao){
    switch (opcao) {
        case 1:
            return factory.criarCafe("expresso");
        case 2:
            return factory.criarCafe("coado");
        case 3:
            return factory.criarCafe("com leite");
        case 4:
            return factory.criarCafe("cappuccino");
        case 5:
            return factory.criarCafe("latte");
        case 6:
            return factory.criarCafe("mocha");
        case 7:
            return factory.criarCafe("iced coffee");
        case 8:
            return factory.criarCafe("frappe");
        default:
            System.out.println("Opção inválida!");
            return null;
    }
}

public static void finalizarPedido() {
    if (carrinho.isEmpty()) {
        System.out.println("O carrinho está vazio!");
        return;
    }

    System.out.println("\n=== SEU PEDIDO ==="); // Adicionei as barrinhas

    Map<String, Integer> quantidadeItens = new HashMap<>();
    Map<String, Double> precoItens = new HashMap<>();

    for (Cafe cafe : carrinho) {
        String descricao = cafe.getDescricao();
        quantidadeItens.put(descricao, quantidadeItens.getOrDefault(descricao, 0) + 1);
        precoItens.put(descricao, cafe.calcularPreco());
    }

    double total = 0;
    for (Map.Entry<String, Integer> entry : quantidadeItens.entrySet()) {
        String descricao = entry.getKey();
        int quantidade = entry.getValue();
        double precoUnitario = precoItens.get(descricao);
        double subtotal = quantidade * precoUnitario;
        total += subtotal;

        System.out.printf("%d x %s\n", quantidade, descricao);
        System.out.printf("  R$ %.2f cada → R$ %.2f\n", precoUnitario, subtotal);
    }

    System.out.println("==================");
    System.out.printf("TOTAL: R$ %.2f\n", total);
    System.out.println("==================");
    System.out.println("Obrigado pela preferência!");
}

public static void mostrarCarrinho() {
    if (carrinho.isEmpty()) {
        System.out.println("\nSeu carrinho está vazio!");
        return;
    }

    System.out.println("\n=== SEU CARRINHO ===");

    Map<String, Integer> quantidadeItens = new HashMap<>();
    Map<String, Double> precoItens = new HashMap<>();

    for (Cafe cafe : carrinho) {
        String descricao = cafe.getDescricao();
        quantidadeItens.put(descricao, quantidadeItens.getOrDefault(descricao, 0) + 1);
        precoItens.put(descricao, cafe.calcularPreco());
    }

    double total = 0;
    for (Map.Entry<String, Integer> entry : quantidadeItens.entrySet()) {
        String descricao = entry.getKey();
        int quantidade = entry.getValue();
        double precoUnitario = precoItens.get(descricao);
        double subtotal = quantidade * precoUnitario;
        total += subtotal;

        System.out.printf("%d x %s → R$ %.2f\n", quantidade, descricao, subtotal);
    }

    System.out.printf("Total parcial: R$ %.2f\n", total);
    System.out.println("====================");

}

public static void executarModoGerente() {
    GerenciarArquivos estoque = GerenciarArquivos.getInstancia();
    Scanner scanner = new Scanner(System.in);

    System.out.println("--- Liko's Coffe --- Modo Gerente ---");
    System.out.println(" ");

    while (true) {
        System.out.println("1. Listar estoque completo");
        System.out.println("2. Adicionar quantidade");
        System.out.println("3. Remover quantidade");
        System.out.println("4. Sair");
        System.out.print("Escolha uma opção: ");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                estoque.listarEstoqueCompleto();
                break;

            case 2:
                System.out.print("Digite o tipo do café: ");
                String tipoAdd = scanner.nextLine();
                System.out.print("Digite a quantidade a adicionar: ");
                int qtdAdd = scanner.nextInt();
                estoque.adicionarQuantidade(tipoAdd, qtdAdd);
                break;

            // case 3:
            //System.out.print("Digite o tipo do café: ");
            //String tipoRemove = scanner.nextLine();
            //System.out.print("Digite a quantidade a remover: ");
            // int qtdRemove = scanner.nextInt();
            //boolean sucesso = estoque.removerQuantidade(tipoRemove, qtdRemove);
            //System.out.println(sucesso ? "Removido com sucesso!" : "Falha ao remover!");
            // break;


            case 4:
                System.out.println("Saindo do sistema...");
                scanner.close();
                return;

            default:
                System.out.println("Opção inválida!");
        }
    }
}

public static boolean validarSenha(String senha) {
    return senha.equals("admin123");
}


