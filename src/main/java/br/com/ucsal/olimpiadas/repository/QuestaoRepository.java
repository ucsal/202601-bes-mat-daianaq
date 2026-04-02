package br.com.ucsal.olimpiadas.repository;

import br.com.ucsal.olimpiadas.QuestaoBase;
import java.util.List;

public interface QuestaoRepository {
    void save(QuestaoBase questao);
    List<QuestaoBase> findAll();
    List<QuestaoBase> findByProvaId(Long provaId);
}