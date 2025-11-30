package peppa.hamburgueria;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PedidoService {

    private final CardapioService cardapio;
    // Usamos BigDecimal para garantir precisão monetária absoluta
    private BigDecimal total = BigDecimal.ZERO;

    public PedidoService(CardapioService cardapio) {
        this.cardapio = cardapio;
    }

    public void adicionarItem(String item, int quantidade) {
        if (!cardapio.existe(item)) {
            throw new IllegalArgumentException("Item indisponível no cardápio");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade inválida");
        }

        // Converte o preço (double) para BigDecimal de forma segura
        BigDecimal preco = BigDecimal.valueOf(cardapio.precoDe(item));
        BigDecimal qtd = new BigDecimal(quantidade);

        // total = total + (preco * quantidade)
        this.total = this.total.add(preco.multiply(qtd));
    }

    public void aplicarDesconto(double percentualDesconto) {
        // fator = 1 - (desconto / 100)
        BigDecimal desconto = BigDecimal.valueOf(percentualDesconto).divide(BigDecimal.valueOf(100));
        BigDecimal fator = BigDecimal.ONE.subtract(desconto);

        // total = total * fator
        // Arredondamento HALF_UP é o padrão para moeda (ex: 1.555 -> 1.56)
        this.total = this.total.multiply(fator).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getTotal() {
        // Garante que o retorno tenha sempre 2 casas decimais
        return this.total.setScale(2, RoundingMode.HALF_UP);
    }

    public int calcularTempoEstimado(int quantidadeTotal) {
        return 8 + 2 * quantidadeTotal;
    }

    public void resetar() {
        this.total = BigDecimal.ZERO;
    }
}