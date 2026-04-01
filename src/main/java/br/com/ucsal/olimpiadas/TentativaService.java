package br.com.ucsal.olimpiadas;

import java.util.List;

public class TentativaService {

    public void registrar(Tentativa tentativa) {
        tentativa.setId(IdGenerator.proximaTentativaId++);
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
    	System.out.println("\n--- Tentativas ---");
        for (Tentativa t : tentativas) {
            System.out.println("Tentativa #" + t.getId() + " | Nota: " + calcularNota(t));
        }
    }
}