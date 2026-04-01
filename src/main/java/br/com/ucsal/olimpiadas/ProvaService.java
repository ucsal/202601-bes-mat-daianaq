package br.com.ucsal.olimpiadas;

import java.util.List;

public class ProvaService {

    private List<Prova> provas;

    public ProvaService(List<Prova> provas) {
        this.provas = provas;
    }

    public void cadastrarProva(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            System.out.println("título inválido");
            return;
        }

        Prova p = new Prova();
        p.setId(IdGenerator.proximaProvaId++);
        p.setTitulo(titulo);

        provas.add(p);

        System.out.println("Prova criada: " + p.getId());
    }

    public List<Prova> listar() {
        return provas;
    }
}