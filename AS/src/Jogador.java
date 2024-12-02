import java.util.*;

class Jogador {
    private String nome;
    private double saldo;
    private int posicao;
    private List<Imovel> propriedades;
    private double salario;

    public Jogador(String nome, double saldoInicial, double salario) {
        this.nome = nome;
        this.saldo = saldoInicial;
        this.posicao = 0;
        this.propriedades = new ArrayList<>();
        this.salario = salario;
    }

    public String getNome() {
        return nome;
    }

    public double getSaldo() {
        return saldo;
    }

    public double getSalario() {
        return salario;
    }

    public void adicionarSaldo(double valor) {
        this.saldo += valor;
    }

    public void subtrairSaldo(double valor) {
        this.saldo -= valor;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public void comprarImovel(Imovel imovel) {
        if (this.saldo >= imovel.getPreco()) {
            this.saldo -= imovel.getPreco();
            imovel.comprar(this); // Passa o jogador como proprietário
            this.propriedades.add(imovel);
            System.out.println("Parabéns pela compra do imóvel " + imovel.getNome());
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }

    public List<Imovel> getPropriedades() {
        return propriedades;
    }
}