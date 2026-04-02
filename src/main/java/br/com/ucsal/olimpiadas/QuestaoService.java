package br.com.ucsal.olimpiadas;

import br.com.ucsal.olimpiadas.repository.QuestaoRepository;
import java.util.List;

public class QuestaoService {
    private final QuestaoRepository repository;

    public QuestaoService(QuestaoRepository repository) {
        this.repository = repository;
    }

    public void cadastrarMultiplaEscolha(Long provaId, String enunciado, String[] alternativas, char correta) {
        QuestaoMultiplaEscolha q = new QuestaoMultiplaEscolha();
        q.setId(IdGenerator.proximaQuestaoId++);
        q.setProvaId(provaId);
        q.setEnunciado(enunciado);
        q.setAlternativas(alternativas);
        q.setAlternativaCorreta(correta);
        
        repository.save(q);
        System.out.println("Questão cadastrada: " + q.getId() + " (na prova " + provaId + ")");
    }

    public void cadastrarVerdadeiroFalso(Long provaId, String enunciado, boolean correta) {
        QuestaoVerdadeiroFalso q = new QuestaoVerdadeiroFalso();
        q.setId(IdGenerator.proximaQuestaoId++);
        q.setProvaId(provaId);
        q.setEnunciado(enunciado);
        q.setCorreta(correta);
        
        repository.save(q);
        System.out.println("Questão cadastrada: " + q.getId() + " (na prova " + provaId + ")");
    }
}