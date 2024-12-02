import java.util.*;

class BancoImobiliario {
    private List<Jogador> jogadores;
    private List<Imovel> imoveis;
    private List<CasaEspecial> casasEspeciais;
    private Random random;
    private int rodadasMaximas;

    public BancoImobiliario() {
        this.jogadores = new ArrayList<>();
        this.imoveis = new ArrayList<>();
        this.casasEspeciais = new ArrayList<>();
        this.random = new Random();
    }

    public void adicionarJogador(Jogador jogador) {
        if (jogadores.size() < 6) {
            jogadores.add(jogador);
            System.out.println("Jogador " + jogador.getNome() + " cadastrado com sucesso.");
        } else {
            System.out.println("Número máximo de jogadores alcançado.");
        }
    }

    public void removerJogador(String nome) {
        jogadores.removeIf(jogador -> jogador.getNome().equalsIgnoreCase(nome));
        System.out.println("Jogador " + nome + " removido com sucesso.");
    }

    public void listarJogadores() {
        if (jogadores.isEmpty()) {
            System.out.println("Nenhum jogador cadastrado.");
        } else {
            System.out.println("Jogadores cadastrados:");
            for (Jogador jogador : jogadores) {
                System.out.println("- " + jogador.getNome() + " (Saldo : R$" + jogador.getSaldo() + ")");
            }
        }
    }

    public void atualizarJogador(String nome, double novoSaldo) {
        for (Jogador jogador : jogadores) {
            if (jogador.getNome().equalsIgnoreCase(nome)) {
                jogador.adicionarSaldo(novoSaldo - jogador.getSaldo());
                System.out.println("Saldo do jogador " + nome + " atualizado para R$" + novoSaldo);
                return;
            }
        }
        System.out.println("Jogador não encontrado.");
    }

    public void adicionarImovel(Imovel imovel) {
        if (imoveis.size() < 40) {
            imoveis.add(imovel);
            System.out.println("Imóvel " + imovel.getNome() + " cadastrado com sucesso.");
        } else {
            System.out.println("Número máximo de imóveis alcançado.");
        }
    }

    public void removerImovel(String nome) {
        imoveis.removeIf(imovel -> imovel.getNome().equalsIgnoreCase(nome));
        System.out.println("Imóvel " + nome + " removido com sucesso.");
    }

    public void listarImoveis() {
        if (imoveis.isEmpty()) {
            System.out.println("Nenhum imóvel cadastrado.");
        } else {
            System.out.println("Imóveis cadastrados:");
            for (Imovel imovel : imoveis) {
                System.out.println("- " + imovel.getNome() + " (Preço: R$" + imovel.getPreco() + ", Aluguel: R$" + imovel.getAluguel() + ")");
            }
        }
    }

    public void atualizarImovel(String nome, double novoPreco, double novoAluguel) {
        for (Imovel imovel : imoveis) {
            if (imovel.getNome().equalsIgnoreCase(nome)) {
                imovel.setPreco(novoPreco);
                imovel.setAluguel(novoAluguel);
                System.out.println("Imóvel " + nome + " atualizado com sucesso.");
                return;
            }
        }
        System.out.println("Imóvel não encontrado.");
    }

    public void adicionarCasaEspecial(CasaEspecial casa) {
        casasEspeciais.add(casa);
        System.out.println("Casa especial " + casa.getNome() + " cadastrada com sucesso.");
    }

    public void listarCasasEspeciais() {
        if (casasEspeciais.isEmpty()) {
            System.out.println("Nenhuma casa especial cadastrada.");
        } else {
            System.out.println("Casas especiais cadastradas:");
            for (CasaEspecial casa : casasEspeciais) {
                System.out.println("- " + casa.getNome());
            }
        }
    }

    public void setRodadasMaximas(int rodadas) {
        this.rodadasMaximas = rodadas;
    }

    public void iniciarJogo() {
        if (jogadores.size() < 2) {
            System.out.println("É necessário pelo menos 2 jogadores para iniciar o jogo.");
            return;
        }
        if (imoveis.size() < 10) {
            System.out.println("É necessário cadastrar pelo menos 10 imóveis para iniciar o jogo.");
            return;
        }

        for (int rodada = 0; rodada < rodadasMaximas; rodada++) {
            for (Jogador jogador : jogadores) {
                jogarTurno(jogador);
            }
        }

        declararVencedor();
    }

    private void jogarTurno(Jogador jogador) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(jogador.getNome() + ", é sua vez de jogar!");
        System.out.println("Saldo atual: R$" + jogador.getSaldo());

        // Jogar o dado
        System.out.println("Pressione Enter para jogar o dado...");
        scanner.nextLine();
        int dado = random.nextInt(6) + 1; // Lançar o dado
        jogador.setPosicao((jogador.getPosicao() + dado) % (imoveis.size() + casasEspeciais.size())); // Avançar no tabuleiro

        if (jogador.getPosicao() < imoveis.size()) {
            Imovel imovelAtual = imoveis.get(jogador.getPosicao());
            System.out.println(jogador.getNome() + " caiu em " + imovelAtual.getNome());

            if (!imovelAtual.isComprado()) {
                System.out.println("Imóvel disponível para compra. Preço: R$" + imovelAtual.getPreco());
                System.out.println("Você deseja comprar este imóvel? (s/n)");
                String resposta = scanner.nextLine();
                if (resposta.equalsIgnoreCase("s")) {
                    jogador.comprarImovel(imovelAtual);
                }
            } else {
                Jogador proprietario = imovelAtual.getProprietario();
                System.out.println(jogador.getNome() + " pagou R$" + imovelAtual.getAluguel() + " de aluguel ao jogador " + proprietario.getNome() + ".");
                jogador.subtrairSaldo(imovelAtual.getAluguel());
                proprietario.adicionarSaldo(imovelAtual.getAluguel()); // Adiciona o aluguel ao saldo do proprietário
            }
        } else {
            int casaIndex = jogador.getPosicao() - imoveis.size();
            CasaEspecial casaAtual = casasEspeciais.get(casaIndex);
            System.out.println(jogador.getNome() + " caiu na " + casaAtual.getNome());

            switch (casaAtual.getNome()) {
                case "Casa de Imposto":
                    double imposto = jogador.getSaldo() * 0.05;
                    jogador.subtrairSaldo(imposto);
                    System.out.println(jogador.getNome() + " pagou R$" + imposto + " de imposto.");
                    break;
                case "Casa de Restituição":
                    double restitucao = jogador.getSalario() * 0.10;
                    jogador.adicionarSaldo(restitucao);
                    System.out.println(jogador.getNome() + " recebeu R$" + restitucao + " de restituição.");
                    break;
                case "Início":
                    jogador.adicionarSaldo(jogador.getSalario());
                    System.out.println(jogador.getNome() + " recebeu R$" + jogador.getSalario() + " por passar pela casa Início.");
                    break;
            }
        }
    }

    private void declararVencedor() {
        Jogador vencedor = jogadores.get(0);
        for (Jogador jogador : jogadores) {
            if (jogador.getSaldo() > vencedor.getSaldo()) {
                vencedor = jogador;
            }
        }
        System.out.println("O vencedor é " + vencedor.getNome() + " com um saldo de R$" + vencedor.getSaldo());
    }
}