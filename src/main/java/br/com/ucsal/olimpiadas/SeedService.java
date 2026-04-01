package br.com.ucsal.olimpiadas;

import java.util.List;

public class SeedService {

    public void carregar(List<Prova> provas, List<Questao> questoes) {

        Prova prova = new Prova();
        prova.setId(IdGenerator.proximaProvaId++);
        prova.setTitulo("Olimpíada 2026 • Nível 1 • Prova A");
        provas.add(prova);

        Questao q1 = new Questao();
        q1.setId(IdGenerator.proximaQuestaoId++);
        q1.setProvaId(prova.getId());

        q1.setEnunciado("""
                Questão 1 — Mate em 1.
                É a vez das brancas.
                Encontre o lance que dá mate imediatamente.
                """);

        q1.setFenInicial("6k1/5ppp/8/8/8/7Q/6PP/6K1 w - - 0 1");

        q1.setAlternativas(new String[] {
                "A) Qh7#",
                "B) Qf5#",
                "C) Qc8#",
                "D) Qh8#",
                "E) Qe6#"
        });

        q1.setAlternativaCorreta('C');

        questoes.add(q1);
    }
}