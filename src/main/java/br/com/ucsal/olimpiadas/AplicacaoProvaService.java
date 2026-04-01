package br.com.ucsal.olimpiadas;

import java.util.List;
import java.util.Scanner;

public class AplicacaoProvaService {

    private List<Questao> questoes;
    private TabuleiroService tabuleiroService = new TabuleiroService();

    public AplicacaoProvaService(List<Questao> questoes) {
        this.questoes = questoes;
    }

    public Tentativa aplicar(Long participanteId, Long provaId, Scanner in) {

        var questoesDaProva = questoes.stream()
                .filter(q -> q.getProvaId() == provaId)
                .toList();

        if (questoesDaProva.isEmpty()) {
            System.out.println("esta prova não possui questões cadastradas");
            return null;
        }

        Tentativa tentativa = new Tentativa();
        tentativa.setParticipanteId(participanteId);
        tentativa.setProvaId(provaId);

        System.out.println("\n--- Início da Prova ---");

        for (Questao q : questoesDaProva) {

            System.out.println("\nQuestão #" + q.getId());
            System.out.println(q.getEnunciado());

            System.out.println("Posição inicial:");
            tabuleiroService.imprimirTabuleiroFen(q.getFenInicial());

            for (String alt : q.getAlternativas()) {
                System.out.println(alt);
            }

            System.out.print("Sua resposta (A–E): ");
            char marcada;

            try {
                marcada = Questao.normalizar(in.nextLine().trim().charAt(0));
            } catch (Exception e) {
                System.out.println("resposta inválida (marcando como errada)");
                marcada = 'X';
            }

            Resposta r = new Resposta();
            r.setQuestaoId(q.getId());
            r.setAlternativaMarcada(marcada);
            r.setCorreta(q.isRespostaCorreta(marcada));

            tentativa.getRespostas().add(r);
        }

        System.out.println("\n--- Fim da Prova ---");

        return tentativa;
    }
}