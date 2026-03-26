package br.com.ucsal.olimpiadas;

import java.util.List;

public class TentativaService {

	private long proximoId = 1;

	public void registrar(Tentativa tentativa) {
		tentativa.setId(proximoId++);
	}

	public int calcularNota(Tentativa tentativa) {
		int acertos = 0;

		for (Resposta r : tentativa.getRespostas()) {
			if (r.isCorreta())
				acertos++;
		}

		return acertos;
	}

	public void listar(List<Tentativa> tentativas) {
		for (Tentativa t : tentativas) {
			System.out.println("Tentativa #" + t.getId() + " | Nota: " + calcularNota(t));
		}
	}
}
