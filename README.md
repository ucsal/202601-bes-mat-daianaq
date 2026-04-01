# Olimpíada

## 📌 Principais mudanças

-O sistema original possuía muitas responsabilidades concentradas na classe App. 
Para melhorar a organização, essas responsabilidades foram separadas em classes específicas.

## 📌 Princípio aplicado: SRP (Single Responsibility Principle)

Cada classe passou a ter uma única responsabilidade:

•ParticipanteService → cadastro e listagem de participantes;

•ProvaService → cadastro e listagem de provas;

•QuestaoService → cadastro de questões;

•AplicacaoProvaService → aplicação da prova (execução das questões);

•TentativaService → registro e cálculo de notas;

•TabuleiroService → exibe no console o tabuleiro da questão;

•SeedService → carga inicial de dados.
