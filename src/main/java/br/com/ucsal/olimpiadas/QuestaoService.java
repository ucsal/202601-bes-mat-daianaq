package br.com.ucsal.olimpiadas;

import java.util.List;

public class QuestaoService {

	private List<Questao> questoes;
	private long proximoId = 1;

	public QuestaoService(List<Questao> questoes) {
		this.questoes = questoes;
	}

	public void cadastrar(Long provaId, String enunciado, String[] alternativas, char correta) {

		Questao q = new Questao();
		q.setId(proximoId++);
		q.setProvaId(provaId);
		q.setEnunciado(enunciado);
		q.setAlternativas(alternativas);
		q.setAlternativaCorreta(correta);

		questoes.add(q);
	}
}