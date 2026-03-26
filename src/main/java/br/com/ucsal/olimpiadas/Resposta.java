package br.com.ucsal.olimpiadas;

public class Resposta {

	private long questaoId;
	private char alternativaMarcada;
	private boolean correta;

	public void setQuestaoId(long questaoId) {
		this.questaoId = questaoId;
	}

	public void setAlternativaMarcada(char alternativaMarcada) {
		this.alternativaMarcada = alternativaMarcada;
	}

	public void setCorreta(boolean correta) {
		this.correta = correta;
	}

	public boolean isCorreta() {
		return correta;
	}
}