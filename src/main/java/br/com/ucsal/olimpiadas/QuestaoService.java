package br.com.ucsal.olimpiadas;

import java.util.List;

public class QuestaoService {

    private List<Questao> questoes;

    public QuestaoService(List<Questao> questoes) {
        this.questoes = questoes;
    }

    public void cadastrar(Long provaId, String enunciado, String[] alternativas, char correta) {

        try {
            correta = Questao.normalizar(correta);
        } catch (Exception e) {
            System.out.println("alternativa inválida");
            return;
        }

        Questao q = new Questao();
        q.setId(IdGenerator.proximaQuestaoId++);
        q.setProvaId(provaId);
        q.setEnunciado(enunciado);
        q.setAlternativas(alternativas);
        q.setAlternativaCorreta(correta);

        questoes.add(q);

        System.out.println("Questão cadastrada: " + q.getId() + " (na prova " + provaId + ")");
    }
}