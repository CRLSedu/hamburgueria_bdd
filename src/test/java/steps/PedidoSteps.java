package steps;

import io.cucumber.java.pt.*;
import io.cucumber.datatable.DataTable;
import peppa.hamburgueria.CardapioService;
import peppa.hamburgueria.PedidoService;

import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;

public class PedidoSteps {

    private CardapioService cardapioService;
    private PedidoService pedidoService;
    private Exception erroCapturado;
    private int quantidadeTotal = 0;

    public PedidoSteps() {
        cardapioService = new CardapioService();
        pedidoService = new PedidoService(cardapioService);
        erroCapturado = null;
    }

    @Dado("que o cardápio contém os itens:")
    public void que_o_cardapio_contem_os_itens(DataTable dataTable) {
        dataTable.asMaps().forEach(linha ->
                cardapioService.cadastrarItem(linha.get("item"), Double.parseDouble(linha.get("preco")))
        );
        pedidoService.resetar();
        erroCapturado = null;
        quantidadeTotal = 0;
    }

    private void adicionarItem(String item, Integer quantidade) {
        try {
            pedidoService.adicionarItem(item, quantidade);
            quantidadeTotal += quantidade;
        } catch (IllegalArgumentException e) {
            erroCapturado = e;
            pedidoService.resetar();
            quantidadeTotal = 0;
        } catch (Exception e) {
            erroCapturado = e;
        }
    }

    // Steps de Pedido
    @Quando("eu peço {string} com {int} unidades")
    public void eu_peco_com_unidades(String item, Integer quantidade) {
        adicionarItem(item, quantidade);
    }

    @Quando("eu peço {string} com {int} unidade")
    public void eu_peco_com_unidade(String item, Integer quantidade) {
        adicionarItem(item, quantidade);
    }

    @E("eu adiciono {string} com {int} unidades")
    public void eu_adiciono_com_unidades(String item, Integer quantidade) {
        adicionarItem(item, quantidade);
    }

    @E("eu adiciono {string} com {int} unidade")
    public void eu_adiciono_com_unidade(String item, Integer quantidade) {
        adicionarItem(item, quantidade);
    }

    @E("eu tenho um cupom de {int} por cento de desconto")
    public void eu_tenho_um_cupom_de_por_cento_de_desconto(Integer desconto) {
        pedidoService.aplicarDesconto(desconto);
    }

    // Asserções
    @Então("o valor total deve ser {double}")
    public void o_valor_total_deve_ser(Double esperado) {
        // Converte o double recebido do Gherkin (50.00) para BigDecimal
        BigDecimal esperadoBD = BigDecimal.valueOf(esperado);

        // Obtém o total calculado pelo serviço
        BigDecimal atualBD = pedidoService.getTotal();

        // Compara usando compareTo: retorna 0 se forem iguais matematicamente
        // Isso evita erros como 50.00 vs 50.000001
        assertEquals(0, esperadoBD.compareTo(atualBD),
                "O valor esperado (" + esperadoBD + ") difere do atual (" + atualBD + ")");
    }

    @Então("deve me retornar a mensagem {string}")
    public void deve_me_retornar_a_mensagem(String msgEsperada) {
        assertNotNull(erroCapturado, "Esperava um erro, mas nenhum foi capturado.");
        assertEquals(msgEsperada, erroCapturado.getMessage());
    }

    @Então("o tempo estimado deve ser {int} minutos")
    public void o_tempo_estimado_deve_ser_minutos(Integer esperado) {
        assertEquals(esperado, pedidoService.calcularTempoEstimado(quantidadeTotal));
    }
}