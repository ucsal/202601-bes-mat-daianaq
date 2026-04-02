package br.com.ucsal.olimpiadas;

import br.com.ucsal.olimpiadas.repository.ProvaRepository;
import java.util.List;

public class ProvaService {
    private final ProvaRepository repository;

    public ProvaService(ProvaRepository repository) {
        this.repository = repository;
    }

    public void cadastrarProva(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            System.out.println("título inválido");
            return;
        }

        Prova p = new Prova();
        p.setId(IdGenerator.proximaProvaId++);
        p.setTitulo(titulo);

        repository.save(p);
        System.out.println("Prova criada: " + p.getId());
    }

    public List<Prova> listar() {
        return repository.findAll();
    }

    public boolean existePorId(Long id) {
        return repository.existsById(id);
    }
}