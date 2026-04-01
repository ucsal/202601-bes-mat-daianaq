# Olimpíada

## 📌 Principais mudanças

-O sistema original possuía muitas responsabilidades concentradas na classe App. 
Para melhorar a organização, essas responsabilidades foram separadas em classes específicas.

-O sistema foi modificado para permitir a adição de novos tipos de questões sem alterar o código existente.

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
