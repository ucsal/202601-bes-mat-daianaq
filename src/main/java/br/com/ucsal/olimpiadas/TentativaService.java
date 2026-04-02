package br.com.ucsal.olimpiadas;

import br.com.ucsal.olimpiadas.repository.TentativaRepository;
import java.util.List;

public class TentativaService implements Registravel, Calculavel, Listavel {
    private final TentativaRepository repository;

    public TentativaService(TentativaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void registrar(Tentativa tentativa) {
        tentativa.setId(IdGenerator.proximaTentativaId++);
        repository.save(tentativa);
    }

    @Override
    public int calcularNota(Tentativa tentativa) {
        int acertos = 0;
        for (Resposta r : tentativa.getRespostas()) {
            if (r.isCorreta())
                acertos++;
        }
        return acertos;
    }

    @Override
    public void listar(List<Tentativa> tentativas) {
        System.out.println("\n--- Tentativas ---");
        for (Tentativa t : tentativas) {
            System.out.println("Tentativa #" + t.getId() + " | Nota: " + calcularNota(t));
        }
    }
}