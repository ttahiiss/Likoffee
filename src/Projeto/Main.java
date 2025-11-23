
import Projeto.Decorator.Acucar;
import Projeto.Decorator.Bolachinha;
import Projeto.Decorator.Canela;
import Projeto.Decorator.Chantilly;
import Projeto.Factory.CafeFactory;
import Projeto.Factory.Pedido.CafeInterface;
import Projeto.Singleton.GerenciarArquivos;
import java.util.InputMismatchException;

static Scanner entrada = new Scanner(System.in);
static CafeFactory factory = new CafeFactory();

void main() {

    boolean inicio = true;

    while (inicio) {
            System.out.println("\n--- Liko's Coffee ---");
            System.out.println("\nEscolha o modo: ");
            System.out.println("\n1 - Modo Cliente");
            System.out.println("2 - Modo Gerente");
            System.out.println("3 - Sair");

                try {
                    int modo = entrada.nextInt();

                    switch (modo) {
                        case 1 : executarModoCliente();
                        break;
                        case 2 : {
                            System.out.print("\nSenha: ");
                            String senha = entrada.next();
                            if (validarSenha(senha)) {
                                executarModoGerente();
                            } else {
                                System.out.println("\nSenha incorreta!");
                            }
                            break;
                        }
                        case 3 : {
                            System.out.println("\nTchau tchau!");
                            entrada.close();
                            inicio = false;
                            break;
                        }
                        default : System.out.println("\nOpção Inválida!");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("\nOpção Inválida!");
                    entrada.next();
                }

            }
}

public static void executarModoCliente(){
    GerenciarArquivos estoque = GerenciarArquivos.getInstancia(); // vai usar pra salvar no singleton né?

    boolean finalizarPedido = false;

    while (!finalizarPedido) {

        System.out.println("\n--- Liko's Coffe ---");
        System.out.println("\nSeja bem vindo! ><");
        System.out.println("Aqui você vai encontrar os melhores cafés da região.");
        System.out.println("\nO que deseja hoje?: ");
        mostrarCardapio();

        try {
            int pedido = entrada.nextInt();
            CafeInterface cafeEscolhido = processarEscolha(pedido);

            if (cafeEscolhido != null) {

                System.out.println(cafeEscolhido);
                System.out.println("\nDeseja adicionar algo?");

                boolean adicionando = true;
                while (adicionando) {
                    System.out.println("\n1 - Açúcar + por conta da casa ;) ");
                    System.out.println("2 - Bolachinhas + R$:1,50");
                    System.out.println("3 - Canela + R$: 0,25");
                    System.out.println("4 - Chantilly + R$: 1,50");
                    System.out.println("0 - Finalizar adicionais");
                    System.out.print("\nEscolha: ");

                    int adicional = entrada.nextInt();

                    switch (adicional) {
                        case 1:
                            cafeEscolhido = new Acucar(cafeEscolhido);
                            System.out.println("\n✓ Açúcar adicionado!");
                            System.out.println("\nTotal parcial: R$ " + cafeEscolhido.calcularPreco());
                            break;
                        case 2:
                            cafeEscolhido = new Bolachinha(cafeEscolhido);
                            System.out.println("\n✓ Bolachinhas adicionadas!");
                            System.out.println("\nTotal parcial: R$ " + cafeEscolhido.calcularPreco());
                            break;
                        case 3:
                            cafeEscolhido = new Canela(cafeEscolhido);
                            System.out.println("\n✓ Canela adicionada!");
                            System.out.println("\nTotal parcial: R$ " + cafeEscolhido.calcularPreco());
                            break;
                        case 4:
                            cafeEscolhido = new Chantilly(cafeEscolhido);
                            System.out.println("\n✓ Chantilly adicionado!");
                            System.out.println("\nTotal parcial: R$ " + cafeEscolhido.calcularPreco());
                            break;
                        case 0:
                            adicionando = false;
                            System.out.println("\nFinalizando adicionais...");
                            break;
                        default:
                            System.out.println("\nOpção inválida!");
                    }
                }

                System.out.println("\nTotal: R$ " + cafeEscolhido.calcularPreco());

                System.out.println("\n1- Finalizar pedido");
                System.out.println("2 - sair");

                int resposta = entrada.nextInt();

                if (resposta == 1) {
                        double precoFinal = cafeEscolhido.calcularPreco();
                        boolean removido = estoque.removerQuantidade(cafeEscolhido.getDescricaoBase(), 1);
                        if (removido) {
                            estoque.registrarCompra(cafeEscolhido.getDescricaoBase(), precoFinal);
                            System.out.println("\nPedido finalizado");
                        } else {
                            System.out.println("\nErro ao finalizar pedido");
                            }

                }else if (resposta == 2){
                    System.out.println("\nVoltando pro menu...");
                    return;
                }
            }
        } catch (java.util.InputMismatchException e) {
            System.out.println("\nOpção invalida");
            entrada.nextLine();
        }
    }
}
public static void mostrarCardapio() {
    CafeFactory factory = new CafeFactory();

    System.out.println("\nTradicionais -                       Especiais -                         Cafés gelados -");
    System.out.println(
            "\n1- Expresso " + formatarCafe(factory.criarCafe("expresso"))   + "            " +
                    "4- Cappuccino " + formatarCafe(factory.criarCafe("cappuccino")) + "         " +
                    "7- Iced Coffee " + formatarCafe(factory.criarCafe("iced coffee"))
    );

    System.out.println(
            "2- Café coado " + formatarCafe(factory.criarCafe("coado"))      + "          " +
                    "5- Latte " + formatarCafe(factory.criarCafe("latte"))      + "              " +
                    "8- Frappé " + formatarCafe(factory.criarCafe("frappe"))
    );

    System.out.println(
            "3- Café com Leite " + formatarCafe(factory.criarCafe("com leite"))  + "      " +
                    "6- Mocha " + formatarCafe(factory.criarCafe("mocha"))      + "              " +
                    "9- Sair "
    );

    System.out.print("\nEscolha o número do café: ");
}

private static String formatarCafe(CafeInterface cafe) {
    return String.format("%-2s R$ %.2f", cafe.getDescricao(), cafe.calcularPreco());
}

public static CafeInterface processarEscolha (int opcao){
    GerenciarArquivos estoque = GerenciarArquivos.getInstancia();

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
        case 9:
            System.out.print("\nVolte sempre! :D");
            System.out.println("\nSaindo...");
            estoque.salvarEstoque();
            entrada.close();
            System.exit(0);
        default:
            System.out.println("\nOpção inválida!");
            return null;
    }
}

public static void executarModoGerente() {
    GerenciarArquivos estoque = GerenciarArquivos.getInstancia();
    Scanner scanner = new Scanner(System.in); //n precisa desse scanner, já tem um no inicio do código, "entrada"

    System.out.println("\n--- Liko's Coffe --- Modo Gerente ---");

    while (true) {
        System.out.println("\n1. Listar estoque completo");
        System.out.println("2. Adicionar quantidade");
        System.out.println("3. Remover item do estoque");
        System.out.println("4. Mostrar histórico de vendas");
        System.out.println("5. Sair");
        System.out.print("Escolha uma opção: ");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                estoque.listarEstoqueCompleto();
                break;

            case 2:
                System.out.print("\n==Opções==");
                System.out.println("\nCafe com Leite | Cappuccino | \nCoado | Cortado | Expresso | \nFrappe | IcedCoffee | Latte | \nou Mocha");
                System.out.print("\nDigite o tipo do café: ");
                String tipoAdd = scanner.nextLine();
                System.out.print("\nDigite a quantidade a adicionar: ");
                int qtdAdd = scanner.nextInt();
                estoque.adicionarQuantidade(tipoAdd, qtdAdd);
                break;

            case 3:
                try {
                    System.out.print("\nDigite a quantidade a remover (0 para remover café): ");
                    int qtdRemover = entrada.nextInt();
                    entrada.nextLine();

                    System.out.print("\nDigite o tipo do café: ");
                    String tipoRemover = entrada.nextLine();

                    boolean sucesso = estoque.removerQuantidade(tipoRemover, qtdRemover);
                    if (sucesso) {
                        System.out.println("\nOperação realizada com sucesso!");
                    }
                    break;
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Erro:0137 não é um número válido!");
                    executarModoGerente();
                }

            case 4:
                estoque.verHistorico();
                executarModoGerente();

            case 5:
                estoque.salvarEstoque();
                System.out.println("\nSaindo do sistema");
                return;

            default:
                System.out.println("\nOpção inválida!");
        }
    }
}

public static boolean validarSenha(String senha) {
    return senha.equals("adm123");
}