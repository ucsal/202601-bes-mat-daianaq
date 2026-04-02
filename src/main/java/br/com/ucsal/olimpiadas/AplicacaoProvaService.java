package br.com.ucsal.olimpiadas;

import br.com.ucsal.olimpiadas.repository.QuestaoRepository;
import java.util.Scanner;

public class AplicacaoProvaService {
    private final QuestaoRepository questaoRepository;
    private final TabuleiroService tabuleiroService = new TabuleiroService();

    public AplicacaoProvaService(QuestaoRepository questaoRepository) {
        this.questaoRepository = questaoRepository;
    }

    public Tentativa aplicar(Long participanteId, Long provaId, Scanner in) {
        var questoesDaProva = questaoRepository.findByProvaId(provaId);

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
            r.setResposta(resposta);
            r.setCorreta(q.isRespostaCorreta(resposta));

            tentativa.getRespostas().add(r);
        }

        System.out.println("\n--- Fim da Prova ---");
        return tentativa;
    }
}