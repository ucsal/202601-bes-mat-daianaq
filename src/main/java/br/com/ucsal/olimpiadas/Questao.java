package br.com.ucsal.olimpiadas;

import java.util.Arrays;

public class Questao {

	private long id;
	private long provaId;
	private String enunciado;
	private String[] alternativas = new String[5];
	private char alternativaCorreta;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getProvaId() {
		return provaId;
	}

	public void setProvaId(long provaId) {
		this.provaId = provaId;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public String[] getAlternativas() {
		return alternativas;
	}

	public void setAlternativas(String[] alternativas) {
		if (alternativas == null || alternativas.length != 5) {
			throw new IllegalArgumentException("Precisa de 5 alternativas");
		}
		this.alternativas = Arrays.copyOf(alternativas, 5);
	}

	public void setAlternativaCorreta(char alternativaCorreta) {
		this.alternativaCorreta = normalizar(alternativaCorreta);
	}

	public boolean isRespostaCorreta(char marcada) {
		return normalizar(marcada) == alternativaCorreta;
	}

	public static char normalizar(char c) {
		char up = Character.toUpperCase(c);
		if (up < 'A' || up > 'E')
			throw new IllegalArgumentException();
		return up;
	}
}