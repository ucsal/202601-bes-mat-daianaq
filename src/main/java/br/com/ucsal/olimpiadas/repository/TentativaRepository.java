package br.com.ucsal.olimpiadas.repository;

import br.com.ucsal.olimpiadas.Tentativa;
import java.util.List;

public interface TentativaRepository {
    void save(Tentativa tentativa);
    List<Tentativa> findAll();
}