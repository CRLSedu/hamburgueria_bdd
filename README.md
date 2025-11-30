Este projeto pertence a uma atividade da UC de Gestão de Software do centro universitário UNA, onde aplico os conhecimentos de testes unitários e testes BDD em um sistema de hamburgueria.

Observações do projeto:
Durante o desenvolvimento tive problemas para implementar todos os testes do sistema, onde o projeto implementa com sucesso 3 de 5 cenários, 
validando lógica de negócio (itens, quantidades) e cálculos exatos (tempo estimado).

Os 2 cenários de preço falham devido a um erro de escala não resolvido na minha IDE (IntelliJ/JUnit).
O log de erro mostra que o valor esperado é escalado por um fator de $10.000$ (ex: $500.000,00$) enquanto o código retorna corretamente a escala de centavos ($5.000,00$).
Este problema de Locale/Tipo de Dados persiste apesar da implementação correta com BigDecimal e da tentativa de correção das configurações da JVM (VM options).

Depois de realizar diversa pesquisas em sites e com o suporte da IA Gemini, não consegui resolver estes problemas, por isso, a versão desta atividade na data de hoje (30/11/2025), 
estara disponivel neste repositório com 3 testes de 5 que passaram com sucesso, será feita uma avaliação com a professora na data final de entrega da atividade (03/12/2025 / Quarta-Feira), durante a aula
para auxilio.
