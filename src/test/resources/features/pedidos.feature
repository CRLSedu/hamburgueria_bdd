# language: pt
@hamburgueria
Funcionalidade: Pedidos na hamburgueria Peppa Lanches
  Para realizar pedidos corretos
  Como cliente
  Eu quero saber se o item pode ser pedido, o valor total e o tempo estimado

  Contexto:
    Dado que o cardápio contém os itens:
      | item         | preco |
      | x-bacon      | 25.00 |
      | x-salada     | 22.00 |
      | batata frita | 12.00 |

  @feliz
  Cenário: Pedido simples de item existente
    Quando eu peço "x-bacon" com 2 unidades
    Então o valor total deve ser 50.00
    E o tempo estimado deve ser 12 minutos

  @inexistente
  Cenário: Pedido de item inexistente
    Quando eu peço "milk-shake" com 1 unidade
    Então deve me retornar a mensagem "Item indisponível no cardápio"
    E o valor total deve ser 0.00

  @quantidade
  Cenário: Pedido com quantidade inválida
    Quando eu peço "x-salada" com 0 unidades
    Então deve me retornar a mensagem "Quantidade inválida"
    E o valor total deve ser 0.00

  @desconto
  Cenário: Pedido com desconto de 10 por cento
    # Pedido: 2 x-bacon (50.00) + 1 batata frita (12.00) = 62.00
    # Desconto de 10%: 62.00 - 6.20 = 55.80
    Quando eu peço "x-bacon" com 2 unidades
    E eu adiciono "batata frita" com 1 unidade
    E eu tenho um cupom de 10 por cento de desconto
    Então o valor total deve ser 55.80

  @sla
  Cenário: Calcular tempo estimado de preparo
    # Quantidade total = 1 x-salada + 3 batata frita = 4
    # Tempo estimado = 8 + 2 * 4 = 16 minutos
    Quando eu peço "x-salada" com 1 unidade
    E eu adiciono "batata frita" com 3 unidades
    Então o tempo estimado deve ser 16 minutos