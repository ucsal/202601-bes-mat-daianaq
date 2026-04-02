package br.com.ucsal.olimpiadas.repository;

import br.com.ucsal.olimpiadas.Prova;
import java.util.List;

public interface ProvaRepository {
    void save(Prova prova);
    List<Prova> findAll();
    boolean existsById(Long id);
}