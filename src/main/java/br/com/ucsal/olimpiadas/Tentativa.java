package br.com.ucsal.olimpiadas;

import java.util.ArrayList;
import java.util.List;

public class Tentativa {
	private long id;
	private long participanteId;
	private long provaId;
	private final List<Resposta> respostas = new ArrayList<>();

	public void setId(long id) {
		this.id = id;
	}

	public void setParticipanteId(long participanteId) {
		this.participanteId = participanteId;
	}

	public void setProvaId(long provaId) {
		this.provaId = provaId;
	}

	public long getId() {
		return id;
	}

	public long getParticipanteId() {
		return participanteId;
	}

	public long getProvaId() {
		return provaId;
	}

	public List<Resposta> getRespostas() {
		return respostas;
	}
}