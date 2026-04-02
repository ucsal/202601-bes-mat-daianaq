package br.com.ucsal.olimpiadas;

public class Resposta {
    private long questaoId;
    private String resposta;
    private boolean correta;

    public long getQuestaoId() {
        return questaoId;
    }

    public void setQuestaoId(long questaoId) {
        this.questaoId = questaoId;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public boolean isCorreta() {
        return correta;
    }

    public void setCorreta(boolean correta) {
        this.correta = correta;
    }
}