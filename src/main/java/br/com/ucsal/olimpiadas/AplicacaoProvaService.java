package br.com.ucsal.olimpiadas;

import java.util.List;
import java.util.Scanner;

public class AplicacaoProvaService {

    private List<QuestaoBase> questoes;
    private TabuleiroService tabuleiroService = new TabuleiroService();

    public AplicacaoProvaService(List<QuestaoBase> questoes) {
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

        for (QuestaoBase q : questoesDaProva) {

            System.out.println("\nQuestão #" + q.getId());
            System.out.println(q.getEnunciado());

            System.out.println("Posição inicial:");
            tabuleiroService.imprimirTabuleiroFen(q.getFenInicial());

            q.exibir();

            System.out.print("Sua resposta: ");
            String resposta = in.nextLine();

            Resposta r = new Resposta();
            r.setQuestaoId(q.getId());
            r.setAlternativaMarcada(resposta.charAt(0));
            r.setCorreta(q.isRespostaCorreta(resposta));

            tentativa.getRespostas().add(r);
        }

        System.out.println("\n--- Fim da Prova ---");

        return tentativa;
    }
}