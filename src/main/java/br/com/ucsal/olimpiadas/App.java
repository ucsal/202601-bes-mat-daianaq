package br.com.ucsal.olimpiadas;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

	static final List<Participante> participantes = new ArrayList<>();
	static final List<Prova> provas = new ArrayList<>();
	static final List<Questao> questoes = new ArrayList<>();
	static final List<Tentativa> tentativas = new ArrayList<>();

	static final Scanner in = new Scanner(System.in);

	public static void main(String[] args) {

		var participanteService = new ParticipanteService(participantes);
		var provaService = new ProvaService(provas, questoes);
		var questaoService = new QuestaoService(questoes);
		var tentativaService = new TentativaService();

		while (true) {

			System.out.println("\n=== OLIMPÍADA DE QUESTÕES (V1) ===");
			System.out.println("1) Cadastrar participante");
			System.out.println("2) Cadastrar prova");
			System.out.println("3) Cadastrar questão (A–E) em uma prova");
			System.out.println("4) Aplicar prova (selecionar participante + prova)");
			System.out.println("5) Listar tentativas (resumo)");
			System.out.println("0) Sair");
			System.out.print("> ");
			
			switch (in.nextLine()) {

			case "1" -> {
				System.out.print("Nome: ");
				String nome = in.nextLine();

				System.out.print("Email: ");
				String email = in.nextLine();

				participanteService.cadastrar(nome, email);
			}

			case "2" -> {
				System.out.print("Título: ");
				provaService.cadastrarProva(in.nextLine());
			}

			case "3" -> {
				Long provaId = escolherProva(provaService);
				if (provaId == null)
					break;

				System.out.print("Enunciado: ");
				String enunciado = in.nextLine();

				String[] alternativas = new String[5];
				for (int i = 0; i < 5; i++) {
					System.out.print((char) ('A' + i) + ": ");
					alternativas[i] = in.nextLine();
				}

				System.out.print("Correta: ");
				char correta = in.nextLine().toUpperCase().charAt(0);

				questaoService.cadastrar(provaId, enunciado, alternativas, correta);
			}

			case "4" -> {
				Long participanteId = escolherParticipante(participanteService);
				Long provaId = escolherProva(provaService);

				if (participanteId == null || provaId == null)
					break;

				var tentativa = provaService.aplicarProva(participanteId, provaId, in);

				tentativaService.registrar(tentativa);
				tentativas.add(tentativa);

				System.out.println("Nota: " + tentativaService.calcularNota(tentativa));
			}

			case "5" -> tentativaService.listar(tentativas);

			case "0" -> {
				System.out.println("tchau");
				return;
			}
			}
		}
	}

	static Long escolherParticipante(ParticipanteService service) {
		System.out.println("\nParticipantes:");
		for (var p : service.listar()) {
			System.out.println(p.getId() + " - " + p.getNome());
		}

		try {
			long id = Long.parseLong(in.nextLine());
			return id;
		} catch (Exception e) {
			return null;
		}
	}

	static Long escolherProva(ProvaService service) {
		System.out.println("\nProvas:");
		for (var p : service.listar()) {
			System.out.println(p.getId() + " - " + p.getTitulo());
		}

		try {
			long id = Long.parseLong(in.nextLine());
			return id;
		} catch (Exception e) {
			return null;
		}
	}
}