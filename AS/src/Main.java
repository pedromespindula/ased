import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BancoImobiliario jogo = new BancoImobiliario();
        jogo.adicionarCasaEspecial(new CasaEspecial("Casa de Imposto"));
        jogo.adicionarCasaEspecial(new CasaEspecial("Casa de Restituição"));
        jogo.adicionarCasaEspecial(new CasaEspecial("Início"));

        int opcao;

        do {
            System.out.println("Menu:");
            System.out.println("1. Cadastro de Jogadores");
            System.out.println("2. Cadastro de Imóveis");
            System.out.println("3. Definir Número Máximo de Rodadas");
            System.out.println("4. Iniciar Jogo");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    int subOpcaoJogador;
                    do {
                        System.out.println("Menu de Jogadores:");
                        System.out.println("1. Cadastrar Jogador");
                        System.out.println("2. Listar Jogadores");
                        System.out.println("3. Atualizar Jogador");
                        System.out.println("4. Remover Jogador");
                        System.out.println("0. Voltar");
                        System.out.print("Escolha uma opção: ");
                        subOpcaoJogador = Integer.parseInt(scanner.nextLine());

                        switch (subOpcaoJogador) {
                            case 1:
                                System.out.println("Digite o nome do jogador:");
                                String nomeJogador = scanner.nextLine();
                                System.out.println("Digite o saldo inicial do jogador " + nomeJogador + ":");
                                double saldoInicial = Double.parseDouble(scanner.nextLine());
                                System.out.println("Digite o salário do jogador " + nomeJogador + ":");
                                double salario = Double.parseDouble(scanner.nextLine());
                                jogo.adicionarJogador(new Jogador(nomeJogador, saldoInicial, salario));
                                break;
                            case 2:
                                jogo.listarJogadores();
                                break;
                            case 3:
                                System.out.println("Digite o nome do jogador a ser atualizado:");
                                String nomeAtualizar = scanner.nextLine();
                                System.out.println("Digite o novo saldo do jogador " + nomeAtualizar + ":");
                                double novoSaldo = Double.parseDouble(scanner.nextLine());
                                jogo.atualizarJogador(nomeAtualizar, novoSaldo);
                                break;
                            case 4:
                                System.out.println("Digite o nome do jogador a ser removido:");
                                String nomeRemover = scanner.nextLine();
                                jogo.removerJogador(nomeRemover);
                                break;
                        }
                    } while (subOpcaoJogador != 0);
                    break;

                case 2:
                    int subOpcaoImovel;
                    do {
                        System.out.println("Menu de Imóveis:");
                        System.out.println("1. Cadastrar Imóvel");
                        System.out.println("2. Cadastrar Casa Especial");
                        System.out.println("3. Listar Imóveis");
                        System.out.println("4. Listar Casas Especiais");
                        System.out.println("5. Atualizar Imóvel");
                        System.out.println("6. Remover Imóvel");
                        System.out.println("0. Voltar");
                        System.out.print("Escolha uma opção: ");
                        subOpcaoImovel = Integer.parseInt(scanner.nextLine());

                        switch (subOpcaoImovel) {
                            case 1:
                                System.out.println("Digite o nome do imóvel:");
                                String nomeImovel = scanner.nextLine();
                                System.out.println("Digite o preço do imóvel:");
                                double preco = Double.parseDouble(scanner.nextLine());
                                System.out.println("Digite o valor do aluguel do imóvel:");
                                double aluguel = Double.parseDouble(scanner.nextLine());
                                jogo.adicionarImovel(new Imovel(nomeImovel, preco, aluguel));
                                break;
                            case 2:
                                System.out.println("Escolha o tipo de casa especial:");
                                System.out.println("1. Casa de Imposto");
                                System.out.println("2. Casa de Restituição");
                                int tipoCasa = Integer.parseInt(scanner.nextLine());
                                String nomeCasaEspecial = tipoCasa == 1 ? "Casa de Imposto" : "Casa de Restituição";
                                jogo.adicionarCasaEspecial(new CasaEspecial(nomeCasaEspecial));
                                break;
                            case 3:
                                jogo.listarImoveis();
                                break;
                            case 4:
                                jogo.listarCasasEspeciais();
                                break;
                            case 5:
                                System.out.println("Digite o nome do imóvel a ser atualizado:");
                                String nomeImovelAtualizar = scanner.nextLine();
                                System.out.println("Digite o novo preço do imóvel:");
                                double novoPreco = Double.parseDouble(scanner.nextLine());
                                System.out.println("Digite o novo valor do aluguel do imóvel:");
                                double novoAluguel = Double.parseDouble(scanner.nextLine());
                                jogo.atualizarImovel(nomeImovelAtualizar, novoPreco, novoAluguel);
                                break;
                            case 6:
                                System.out.println("Digite o nome do imóvel a ser removido:");
                                String nomeImovelRemover = scanner.nextLine();
                                jogo.removerImovel(nomeImovelRemover);
                                break;
                        }
                    } while (subOpcaoImovel != 0);
                    break;

                case 3:
                    System.out.println("Digite o número máximo de rodadas:");
                    int rodadas = Integer.parseInt(scanner.nextLine());
                    jogo.setRodadasMaximas(rodadas);
                    System.out.println("Número máximo de rodadas definido para " + rodadas);
                    break;

                case 4:
                    jogo.iniciarJogo(); // Inicia o jogo com o número de rodadas definido
                    break;

                case 0:
                    System.out.println("Saindo do jogo...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    }
}