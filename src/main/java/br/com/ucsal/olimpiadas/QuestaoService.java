package br.com.ucsal.olimpiadas;

import java.util.List;

public class QuestaoService {

	private List<QuestaoBase> questoes;

	public QuestaoService(List<QuestaoBase> questoes) {
	    this.questoes = questoes;
	}
	
    public void cadastrarMultiplaEscolha(Long provaId, String enunciado, String[] alternativas, char correta) {

        QuestaoMultiplaEscolha q = new QuestaoMultiplaEscolha();

        q.setId(IdGenerator.proximaQuestaoId++);
        q.setProvaId(provaId);
        q.setEnunciado(enunciado);
        q.setAlternativas(alternativas);
        q.setAlternativaCorreta(correta);

        questoes.add(q);

        System.out.println("Questão cadastrada: " + q.getId() + " (na prova " + provaId + ")");
    }

    public void cadastrarVerdadeiroFalso(Long provaId, String enunciado, boolean correta) {

        QuestaoVerdadeiroFalso q = new QuestaoVerdadeiroFalso();

        q.setId(IdGenerator.proximaQuestaoId++);
        q.setProvaId(provaId);
        q.setEnunciado(enunciado);
        q.setCorreta(correta);

        questoes.add(q);

        System.out.println("Questão cadastrada: " + q.getId() + " (na prova " + provaId + ")");
    }
}