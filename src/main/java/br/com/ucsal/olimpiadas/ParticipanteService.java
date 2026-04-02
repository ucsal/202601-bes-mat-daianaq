package br.com.ucsal.olimpiadas;

import br.com.ucsal.olimpiadas.repository.ParticipanteRepository;
import java.util.List;

public class ParticipanteService {
    private final ParticipanteRepository repository;

    public ParticipanteService(ParticipanteRepository repository) {
        this.repository = repository;
    }

    public void cadastrar(String nome, String email) {
        if (nome == null || nome.isBlank()) {
            System.out.println("nome inválido");
            return;
        }

        Participante p = new Participante();
        p.setId(IdGenerator.proximoParticipanteId++);
        p.setNome(nome);
        p.setEmail(email);

        repository.save(p);
        System.out.println("Participante cadastrado: " + p.getId());
    }

    public List<Participante> listar() {
        return repository.findAll();
    }

    public boolean existePorId(Long id) {
        return repository.existsById(id);
    }
}