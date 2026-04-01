package br.com.ucsal.olimpiadas;

public abstract class QuestaoBase {

    private long id;
    private long provaId;
    private String enunciado;
    private String fenInicial;

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

    public String getFenInicial() {
        return fenInicial;
    }

    public void setFenInicial(String fenInicial) {
        this.fenInicial = fenInicial;
    }
    
    public static char normalizar(char c) {
        char up = Character.toUpperCase(c);
        if (up < 'A' || up > 'E') {
            throw new IllegalArgumentException();
        }
        return up;
    }

    public abstract boolean isRespostaCorreta(String resposta);

    public abstract void exibir();
}