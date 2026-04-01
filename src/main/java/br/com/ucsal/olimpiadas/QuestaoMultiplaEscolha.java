package br.com.ucsal.olimpiadas;

import java.util.Arrays;

public class QuestaoMultiplaEscolha extends QuestaoBase {

    private String[] alternativas = new String[5];
    private char alternativaCorreta;

    public void setAlternativas(String[] alternativas) {
        if (alternativas == null || alternativas.length != 5) {
            throw new IllegalArgumentException("Precisa de 5 alternativas");
        }
        this.alternativas = Arrays.copyOf(alternativas, 5);
    }

    public void setAlternativaCorreta(char alternativaCorreta) {
        this.alternativaCorreta = Character.toUpperCase(alternativaCorreta);
    }

    @Override
    public boolean isRespostaCorreta(String resposta) {
        return resposta.toUpperCase().charAt(0) == alternativaCorreta;
    }

    @Override
    public void exibir() {
        for (String alt : alternativas) {
            System.out.println(alt);
        }
    }
}