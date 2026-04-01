package br.com.ucsal.olimpiadas;

import java.util.List;

public class ParticipanteService {

    private List<Participante> participantes;

    public ParticipanteService(List<Participante> participantes) {
        this.participantes = participantes;
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

        participantes.add(p);

        System.out.println("Participante cadastrado: " + p.getId());
    }

    public List<Participante> listar() {
        return participantes;
    }
}