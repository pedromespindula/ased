class Imovel {
    private String nome;
    private boolean comprado;
    private double preco;
    private double aluguel;
    private Jogador proprietario;

    public Imovel(String nome, double preco, double aluguel) {
        this.nome = nome;
        this.preco = preco;
        this.aluguel = aluguel;
        this.comprado = false;
        this.proprietario = null; // Inicialmente não tem proprietário
    }

    public String getNome() {
        return nome;
    }

    public boolean isComprado() {
        return comprado;
    }

    public void comprar(Jogador jogador) {
        this.comprado = true;
        this.proprietario = jogador; // Define o proprietário
    }

    public double getPreco() {
        return preco;
    }

    public double getAluguel() {
        return aluguel;
    }

    public Jogador getProprietario() {
        return proprietario; // Retorna o proprietário do imóvel
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setAluguel(double aluguel) {
        this.aluguel = aluguel;
    }
}