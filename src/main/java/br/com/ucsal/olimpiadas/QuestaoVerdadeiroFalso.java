package br.com.ucsal.olimpiadas;

public class QuestaoVerdadeiroFalso extends QuestaoBase {

    private boolean correta;

    public void setCorreta(boolean correta) {
        this.correta = correta;
    }

    @Override
    public boolean isRespostaCorreta(String resposta) {
        if (resposta.equalsIgnoreCase("V")) return correta;
        if (resposta.equalsIgnoreCase("F")) return !correta;
        return false;
    }

    @Override
    public void exibir() {
        System.out.println("A) Verdadeiro");
        System.out.println("B) Falso");
    }
}