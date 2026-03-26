package br.com.ucsal.olimpiadas;

import java.util.List;
import java.util.Scanner;

public class ProvaService {

	private List<Prova> provas;
	private List<Questao> questoes;
	private long proximoId = 1;

	public ProvaService(List<Prova> provas, List<Questao> questoes) {
		this.provas = provas;
		this.questoes = questoes;
	}

	public void cadastrarProva(String titulo) {
		if (titulo == null || titulo.isBlank()) {
			throw new IllegalArgumentException("Título inválido");
		}

		Prova p = new Prova();
		p.setId(proximoId++);
		p.setTitulo(titulo);

		provas.add(p);
	}

	public List<Prova> listar() {
		return provas;
	}

	public Tentativa aplicarProva(Long participanteId, Long provaId, Scanner in) {

		var questoesDaProva = questoes.stream().filter(q -> q.getProvaId() == provaId).toList();

		Tentativa tentativa = new Tentativa();
		tentativa.setParticipanteId(participanteId);
		tentativa.setProvaId(provaId);

		for (Questao q : questoesDaProva) {

			System.out.println("\n" + q.getEnunciado());

			for (String alt : q.getAlternativas()) {
				System.out.println(alt);
			}

			System.out.print("Resposta: ");
			char marcada;

			try {
				marcada = Questao.normalizar(in.nextLine().trim().charAt(0));
			} catch (Exception e) {
				marcada = 'X';
			}

			Resposta r = new Resposta();
			r.setQuestaoId(q.getId());
			r.setAlternativaMarcada(marcada);
			r.setCorreta(q.isRespostaCorreta(marcada));

			tentativa.getRespostas().add(r);
		}

		return tentativa;
	}
}