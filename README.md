# Olimpíada

## 📌 Principais mudanças

-O sistema original possuía muitas responsabilidades concentradas na classe App. 
Para melhorar a organização, essas responsabilidades foram separadas em classes específicas.

-O sistema foi modificado para permitir a adição de novos tipos de questões sem alterar o código existente.

-A captura e validação das respostas foram generalizadas para aceitar diferentes formatos.

-Foram criadas interfaces segregadas (Registravel, Calculavel e Listavel) para separar as responsabilidades do serviço de tentativas.

-Os serviços passaram a depender de abstrações (repositórios) em vez de implementações concretas, facilitando testes e troca da camada de persistência.

## 📌 Princípio aplicado: SRP (Single Responsibility Principle)

Cada classe passou a ter uma única responsabilidade:

•ParticipanteService → cadastro e listagem de participantes;

•ProvaService → cadastro e listagem de provas;

•QuestaoService → cadastro de questões;

•AplicacaoProvaService → aplicação da prova (execução das questões);

•TentativaService → registro e cálculo de notas;

•TabuleiroService → exibe no console o tabuleiro da questão;

•SeedService → carga inicial de dados.

## 📌 Princípio aplicado: OCP (Open/Closed Principle)

O sistema foi preparado para permitir extensão sem modificação do código existente:

•A classe `Questao` foi transformada em `QuestaoBase` (abstrata);

•Criação das classes `QuestaoMultiplaEscolha` e `QuestaoVerdadeiroFalso`;

•Uso de polimorfismo com os métodos `exibir()` e `isRespostaCorreta()`;

•Adição da escolha do tipo de questão no momento do cadastro.

## 📌 Princípio aplicado: LSP (Liskov Substitution Principle)

O sistema foi ajustado para garantir que qualquer tipo de questão possa ser usado no lugar da classe base sem alterar o comportamento:

•A classe Resposta foi modificada para armazenar a resposta como String (antes era char);

•O método isRespostaCorreta() passou a receber String, permitindo que cada tipo de questão faça sua própria validação;

•A aplicação da prova agora trata todas as questões de forma polimórfica, sem verificar o tipo específico.

## 📌 Princípio aplicado: ISP (Interface Segregation Principle)

Foram criadas interfaces específicas para segregar as responsabilidades do serviço de tentativas:

•Registravel → responsável apenas por registrar uma tentativa;

•Calculavel → responsável apenas por calcular a nota;

•Listavel → responsável apenas por listar as tentativas;

•A classe TentativaService passou a implementar apenas as interfaces necessárias;

•Separação clara entre registro, cálculo e visualização;

## 📌 Princípio aplicado: DIP (Dependency Inversion Principle)

Os serviços foram modificados para depender de abstrações em vez de implementações concretas:

•Foram criadas interfaces de repositório: ParticipanteRepository, ProvaRepository, QuestaoRepository, TentativaRepository;

•Os services passaram a depender dessas interfaces, e não mais de implementações concretas.

•Isso permite maior flexibilidade e facilita a evolução do sistema sem impactar outras partes.
