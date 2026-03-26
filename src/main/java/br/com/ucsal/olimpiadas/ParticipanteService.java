package br.com.ucsal.olimpiadas;

import java.util.List;

public class ParticipanteService {

	private List<Participante> participantes;
	private long proximoId = 1;

	public ParticipanteService(List<Participante> participantes) {
		this.participantes = participantes;
	}

	public void cadastrar(String nome, String email) {
		if (nome == null || nome.isBlank()) {
			throw new IllegalArgumentException("Nome inválido");
		}

		Participante p = new Participante();
		p.setId(proximoId++);
		p.setNome(nome);
		p.setEmail(email);

		participantes.add(p);
	}

	public List<Participante> listar() {
		return participantes;
	}
}
