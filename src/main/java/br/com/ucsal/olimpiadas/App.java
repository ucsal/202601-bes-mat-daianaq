package br.com.ucsal.olimpiadas;

import br.com.ucsal.olimpiadas.repository.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        List<Participante> participantes = new ArrayList<>();
        List<Prova> provas = new ArrayList<>();
        List<QuestaoBase> questoes = new ArrayList<>();
        List<Tentativa> tentativas = new ArrayList<>();

        ParticipanteRepository participanteRepository = new ParticipanteRepository() {
            @Override
            public void save(Participante p) { participantes.add(p); }
            @Override
            public List<Participante> findAll() { return participantes; }
            @Override
            public boolean existsById(Long id) { 
                return participantes.stream().anyMatch(p -> p.getId() == id); 
            }
        };

        ProvaRepository provaRepository = new ProvaRepository() {
            @Override
            public void save(Prova p) { provas.add(p); }
            @Override
            public List<Prova> findAll() { return provas; }
            @Override
            public boolean existsById(Long id) { 
                return provas.stream().anyMatch(p -> p.getId() == id); 
            }
        };

        QuestaoRepository questaoRepository = new QuestaoRepository() {
            @Override
            public void save(QuestaoBase q) { questoes.add(q); }
            @Override
            public List<QuestaoBase> findAll() { return questoes; }
            @Override
            public List<QuestaoBase> findByProvaId(Long provaId) {
                return questoes.stream().filter(q -> q.getProvaId() == provaId).toList();
            }
        };

        TentativaRepository tentativaRepository = new TentativaRepository() {
            @Override
            public void save(Tentativa t) { tentativas.add(t); }
            @Override
            public List<Tentativa> findAll() { return tentativas; }
        };

        var participanteService = new ParticipanteService(participanteRepository);
        var provaService = new ProvaService(provaRepository);
        var questaoService = new QuestaoService(questaoRepository);
        var tentativaService = new TentativaService(tentativaRepository);
        var aplicacaoProvaService = new AplicacaoProvaService(questaoRepository);

        Registravel registrador = tentativaService;
        Calculavel calculador = tentativaService;
        Listavel listador = tentativaService;

        new SeedService().carregar(provaRepository, questaoRepository);

        while (true) {

            System.out.println("\n=== OLIMPÍADA DE QUESTÕES (V1) ===");
            System.out.println("1) Cadastrar participante");
            System.out.println("2) Cadastrar prova");
            System.out.println("3) Cadastrar questão (A–E) em uma prova");
            System.out.println("4) Aplicar prova (selecionar participante + prova)");
            System.out.println("5) Listar tentativas (resumo)");
            System.out.println("0) Sair");
            System.out.print("> ");

            switch (in.nextLine()) {

            case "1" -> {
                System.out.print("Nome: ");
                String nome = in.nextLine();

                System.out.print("Email (opcional): ");
                String email = in.nextLine();

                participanteService.cadastrar(nome, email);
            }

            case "2" -> {
                System.out.print("Título da prova: ");
                String titulo = in.nextLine();

                provaService.cadastrarProva(titulo);
            }

            case "3" -> {
                if (provaService.listar().isEmpty()) {
                    System.out.println("não há provas cadastradas");
                    break;
                }

                Long provaId = escolherProva(provaService);
                if (provaId == null)
                    break;

                System.out.println("Enunciado:");
                String enunciado = in.nextLine();

                System.out.println("Tipo da questão:");
                System.out.println("1) Múltipla escolha");
                System.out.println("2) Verdadeiro ou Falso");
                System.out.print("> ");

                String tipo = in.nextLine();

                if (tipo.equals("1")) {

                    String[] alternativas = new String[5];

                    for (int i = 0; i < 5; i++) {
                        char letra = (char) ('A' + i);
                        System.out.print("Alternativa " + letra + ": ");
                        alternativas[i] = letra + ") " + in.nextLine();
                    }

                    System.out.print("Alternativa correta (A–E): ");

                    char correta;
                    try {
                        correta = QuestaoBase.normalizar(in.nextLine().trim().charAt(0));
                    } catch (Exception e) {
                        System.out.println("alternativa inválida");
                        break;
                    }

                    questaoService.cadastrarMultiplaEscolha(provaId, enunciado, alternativas, correta);
                }

                else if (tipo.equals("2")) {

                    System.out.print("Resposta correta (V/F): ");
                    String resp = in.nextLine();

                    boolean correta;

                    if (resp.equalsIgnoreCase("V")) {
                        correta = true;
                    } else if (resp.equalsIgnoreCase("F")) {
                        correta = false;
                    } else {
                        System.out.println("resposta inválida");
                        break;
                    }

                    questaoService.cadastrarVerdadeiroFalso(provaId, enunciado, correta);
                }

                else {
                    System.out.println("tipo inválido");
                }
            }

            case "4" -> {

                if (participanteService.listar().isEmpty()) {
                    System.out.println("cadastre participantes primeiro");
                    break;
                }

                if (provaService.listar().isEmpty()) {
                    System.out.println("cadastre provas primeiro");
                    break;
                }

                Long participanteId = escolherParticipante(participanteService);
                if (participanteId == null)
                    break;

                Long provaId = escolherProva(provaService);
                if (provaId == null)
                    break;

                var tentativa = aplicacaoProvaService.aplicar(participanteId, provaId, in);

                if (tentativa != null) {
                    registrador.registrar(tentativa);
                    int nota = calculador.calcularNota(tentativa);

                    System.out.println("\n--- Fim da Prova ---");
                    System.out.println("Nota (acertos): " + nota + " / " + tentativa.getRespostas().size());
                }
            }

            case "5" -> listador.listar(tentativaRepository.findAll());

            case "0" -> {
                System.out.println("tchau");
                return;
            }

            default -> System.out.println("opção inválida");
            }
        }
    }

    static Long escolherParticipante(ParticipanteService service) {
        System.out.println("\nParticipantes:");
        for (var p : service.listar()) {
            System.out.printf("  %d) %s%n", p.getId(), p.getNome());
        }

        System.out.print("Escolha o id do participante: ");

        try {
            long id = Long.parseLong(in.nextLine());

            if (!service.existePorId(id)) {
                System.out.println("id inválido");
                return null;
            }

            return id;
        } catch (Exception e) {
            System.out.println("entrada inválida");
            return null;
        }
    }

    static Long escolherProva(ProvaService service) {
        System.out.println("\nProvas:");
        for (var p : service.listar()) {
            System.out.printf("  %d) %s%n", p.getId(), p.getTitulo());
        }

        System.out.print("Escolha o id da prova: ");

        try {
            long id = Long.parseLong(in.nextLine());

            if (!service.existePorId(id)) {
                System.out.println("id inválido");
                return null;
            }

            return id;
        } catch (Exception e) {
            System.out.println("entrada inválida");
            return null;
        }
    }
}