package test;

import entities.Animal;
import entities.Laticinio;
import entities.OfertaLeite;
import entities.Ordenha;
import entities.Produtor;
import exceptions.EntidadeNaoEncontradaException;
import exceptions.ValidacaoException;
import repository.Repositorio;
import repository.RepositorioEmMemoria;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.Scanner;

public class Teste {
    private static final Scanner scanner = new Scanner(System.in);

    private static final Repositorio<Produtor> repoProdutor = new RepositorioEmMemoria<>("Produtor");
    private static final Repositorio<Animal> repoAnimal = new RepositorioEmMemoria<>("Animal");
    private static final Repositorio<Ordenha> repoOrdenha = new RepositorioEmMemoria<>("Ordenha");
    private static final Repositorio<Laticinio> repoLaticinio = new RepositorioEmMemoria<>("Laticinio");
    private static final Repositorio<OfertaLeite> repoOferta = new RepositorioEmMemoria<>("OfertaLeite");

    public static void main(String[] args) {
        System.out.println("=======================================");
        System.out.println(" MILTEK - CLASSE DE TESTE (CRUD TERMINAL)");
        System.out.println("=======================================");

        boolean continuar = true;
        while (continuar) {
            exibirMenuPrincipal();
            String opcao = lerTexto("Escolha uma opção");

            switch (opcao) {
                case "1" -> menuProdutor();
                case "2" -> menuAnimal();
                case "3" -> menuOrdenha();
                case "4" -> menuLaticinio();
                case "5" -> menuOferta();
                case "6" -> executar(Teste::relatorioGeral);
                case "0" -> continuar = false;
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }

        System.out.println();
        System.out.println("Encerrando o teste. Até a próxima!");
        scanner.close();
    }

    private static void exibirMenuPrincipal() {
        System.out.println();
        System.out.println("========== MENU PRINCIPAL ==========");
        System.out.println("1 - Produtores");
        System.out.println("2 - Animais");
        System.out.println("3 - Ordenhas");
        System.out.println("4 - Laticínios");
        System.out.println("5 - Ofertas de Leite");
        System.out.println("6 - Relatório geral");
        System.out.println("0 - Sair");
    }

    private static void executar(Runnable acao) {
        try {
            acao.run();
        } catch (ValidacaoException e) {
            System.out.println("[ERRO DE VALIDACAO] " + e.getMessage());
        } catch (EntidadeNaoEncontradaException e) {
            System.out.println("[ERRO] " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("[ERRO] Valor numérico inválido digitado.");
        } catch (DateTimeParseException e) {
            System.out.println("[ERRO] Data inválida. Use o formato AAAA-MM-DD.");
        } catch (RuntimeException e) {
            System.out.println("[ERRO INESPERADO] " + e.getMessage());
        }
    }

    private static void menuProdutor() {
        boolean continuar = true;
        while (continuar) {
            System.out.println();
            printsMenu("--- PRODUTORES ---");
            String opcao = lerTexto("Escolha uma opção");

            switch (opcao) {
                case "1" -> executar(Teste::cadastrarProdutor);
                case "2" -> executar(Teste::listarProdutores);
                case "3" -> executar(Teste::buscarProdutor);
                case "4" -> executar(Teste::atualizarProdutor);
                case "5" -> executar(Teste::deletarProdutor);
                case "0" -> continuar = false;
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void cadastrarProdutor() {
        String nome = lerTexto("Nome");
        String cpf = lerTexto("CPF");
        String telefone = lerTexto("Telefone");
        String endereco = lerTexto("Endereço");
        double latitude = lerDouble("Latitude");
        double longitude = lerDouble("Longitude");
        String status = lerTexto("Status (ATIVO/INATIVO)");

        Produtor produtor = new Produtor(nome, cpf, telefone, endereco, latitude, longitude, status, LocalDate.now());
        produtor.validar();
        repoProdutor.salvar(produtor);
        System.out.println("[OK] Produtor cadastrado: " + produtor);
    }

    private static void listarProdutores() {
        var produtores = repoProdutor.listarTodos();
        if (produtores.isEmpty()) {
            System.out.println("Nenhum produtor cadastrado ainda.");
            return;
        }
        produtores.forEach(p -> System.out.println("  - " + p));
    }

    private static void buscarProdutor() {
        Long id = lerLong("Id do produtor");
        Optional<Produtor> encontrado = repoProdutor.buscarPorId(id);
        if (encontrado.isPresent()) {
            System.out.println("Encontrado: " + encontrado.get());
        } else {
            System.out.println("Nenhum produtor encontrado com id " + id);
        }
    }

    private static void atualizarProdutor() {
        Long id = lerLong("Id do produtor a atualizar");
        Produtor existente = repoProdutor.buscarPorId(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Produtor", id));

        existente.setNome(lerTextoOuManter("Nome", existente.getNome()));
        existente.setTelefone(lerTextoOuManter("Telefone", existente.getTelefone()));
        existente.setEndereco(lerTextoOuManter("Endereço", existente.getEndereco()));
        existente.setStatus(lerTextoOuManter("Status", existente.getStatus()));

        existente.validar();
        repoProdutor.atualizar(id, existente);
        System.out.println("[OK] Produtor atualizado: " + existente);
    }

    private static void deletarProdutor() {
        Long id = lerLong("Id do produtor a deletar");
        repoProdutor.deletar(id);
        System.out.println("[OK] Produtor removido.");
    }

    private static void menuAnimal() {
        boolean continuar = true;
        while (continuar) {
            System.out.println();
            printsMenu("--- ANIMAIS ---");
            String opcao = lerTexto("Escolha uma opção");

            switch (opcao) {
                case "1" -> executar(Teste::cadastrarAnimal);
                case "2" -> executar(Teste::listarAnimais);
                case "3" -> executar(Teste::buscarAnimal);
                case "4" -> executar(Teste::atualizarAnimal);
                case "5" -> executar(Teste::deletarAnimal);
                case "0" -> continuar = false;
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void cadastrarAnimal() {
        String nome = lerTexto("Nome do animal");
        String raca = lerTexto("Raça");
        String sexo = lerTexto("Sexo (MACHO/FEMEA)");
        LocalDate dataNascimento = lerData("Data de nascimento (AAAA-MM-DD)");
        String status = lerTexto("Status (ex.: LACTANTE, SECA, BEZERRA)");
        Long produtorId = lerLong("Id do produtor dono do animal");

        Produtor produtor = repoProdutor.buscarPorId(produtorId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Produtor", produtorId));

        Animal animal = new Animal(nome, raca, sexo, dataNascimento, status, produtor);
        animal.validar();
        repoAnimal.salvar(animal);
        produtor.getAnimais().add(animal);
        System.out.println("[OK] Animal cadastrado: " + animal);
    }

    private static void listarAnimais() {
        var animais = repoAnimal.listarTodos();
        if (animais.isEmpty()) {
            System.out.println("Nenhum animal cadastrado ainda.");
            return;
        }
        animais.forEach(a -> System.out.println("  - " + a));
    }

    private static void buscarAnimal() {
        Long id = lerLong("Id do animal");
        Optional<Animal> encontrado = repoAnimal.buscarPorId(id);
        if (encontrado.isPresent()) {
            System.out.println("Encontrado: " + encontrado.get());
        } else {
            System.out.println("Nenhum animal encontrado com id " + id);
        }
    }

    private static void atualizarAnimal() {
        Long id = lerLong("Id do animal a atualizar");
        Animal existente = repoAnimal.buscarPorId(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Animal", id));

        existente.setNome(lerTextoOuManter("Nome", existente.getNome()));
        existente.setRaca(lerTextoOuManter("Raça", existente.getRaca()));
        existente.setStatus(lerTextoOuManter("Status", existente.getStatus()));

        existente.validar();
        repoAnimal.atualizar(id, existente);
        System.out.println("[OK] Animal atualizado: " + existente);
    }

    private static void deletarAnimal() {
        Long id = lerLong("Id do animal a deletar");
        repoAnimal.deletar(id);
        System.out.println("[OK] Animal removido.");
    }

    private static void menuOrdenha() {
        boolean continuar = true;
        while (continuar) {
            System.out.println();
            printsMenu("--- ORDENHAS ---");
            String opcao = lerTexto("Escolha uma opção");

            switch (opcao) {
                case "1" -> executar(Teste::cadastrarOrdenha);
                case "2" -> executar(Teste::listarOrdenhas);
                case "3" -> executar(Teste::buscarOrdenha);
                case "4" -> executar(Teste::atualizarOrdenha);
                case "5" -> executar(Teste::deletarOrdenha);
                case "0" -> continuar = false;
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void cadastrarOrdenha() {
        LocalDate data = lerData("Data da ordenha (AAAA-MM-DD)");
        String turno = lerTexto("Turno (MANHA/TARDE)");
        double litros = lerDouble("Litros produzidos");
        String observacao = lerTexto("Observação");
        Long animalId = lerLong("Id do animal ordenhado");

        Animal animal = repoAnimal.buscarPorId(animalId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Animal", animalId));

        Ordenha ordenha = new Ordenha(data, turno, litros, observacao, animal);
        ordenha.validar();
        repoOrdenha.salvar(ordenha);
        animal.getOrdenhas().add(ordenha);
        System.out.println("[OK] Ordenha registrada: " + ordenha);
    }

    private static void listarOrdenhas() {
        var ordenhas = repoOrdenha.listarTodos();
        if (ordenhas.isEmpty()) {
            System.out.println("Nenhuma ordenha registrada ainda.");
            return;
        }
        ordenhas.forEach(o -> System.out.println("  - " + o));
    }

    private static void buscarOrdenha() {
        Long id = lerLong("Id da ordenha");
        Optional<Ordenha> encontrada = repoOrdenha.buscarPorId(id);
        if (encontrada.isPresent()) {
            System.out.println("Encontrada: " + encontrada.get());
        } else {
            System.out.println("Nenhuma ordenha encontrada com id " + id);
        }
    }

    private static void atualizarOrdenha() {
        Long id = lerLong("Id da ordenha a atualizar");
        Ordenha existente = repoOrdenha.buscarPorId(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Ordenha", id));

        existente.setLitros(lerDoubleOuManter("Litros", existente.getLitros()));
        existente.setObservacao(lerTextoOuManter("Observação", existente.getObservacao()));

        existente.validar();
        repoOrdenha.atualizar(id, existente);
        System.out.println("[OK] Ordenha atualizada: " + existente);
    }

    private static void deletarOrdenha() {
        Long id = lerLong("Id da ordenha a deletar");
        repoOrdenha.deletar(id);
        System.out.println("[OK] Ordenha removida.");
    }

    private static void menuLaticinio() {
        boolean continuar = true;
        while (continuar) {
            System.out.println();
            printsMenu("--- LATICÍNIOS ---");
            String opcao = lerTexto("Escolha uma opção");

            switch (opcao) {
                case "1" -> executar(Teste::cadastrarLaticinio);
                case "2" -> executar(Teste::listarLaticinios);
                case "3" -> executar(Teste::buscarLaticinio);
                case "4" -> executar(Teste::atualizarLaticinio);
                case "5" -> executar(Teste::deletarLaticinio);
                case "0" -> continuar = false;
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void cadastrarLaticinio() {
        String nome = lerTexto("Nome do laticínio");
        String cnpj = lerTexto("CNPJ");
        String telefone = lerTexto("Telefone");
        String endereco = lerTexto("Endereço");
        double latitude = lerDouble("Latitude");
        double longitude = lerDouble("Longitude");
        double precoLitro = lerDouble("Preço do litro (R$)");
        String status = lerTexto("Status (ATIVO/INATIVO)");

        Laticinio laticinio = new Laticinio(nome, cnpj, telefone, endereco, latitude, longitude, precoLitro, status);
        laticinio.validar();
        repoLaticinio.salvar(laticinio);
        System.out.println("[OK] Laticínio cadastrado: " + laticinio);
    }

    private static void listarLaticinios() {
        var laticinios = repoLaticinio.listarTodos();
        if (laticinios.isEmpty()) {
            System.out.println("Nenhum laticínio cadastrado ainda.");
            return;
        }
        laticinios.forEach(l -> System.out.println("  - " + l));
    }

    private static void buscarLaticinio() {
        Long id = lerLong("Id do laticínio");
        Optional<Laticinio> encontrado = repoLaticinio.buscarPorId(id);
        if (encontrado.isPresent()) {
            System.out.println("Encontrado: " + encontrado.get());
        } else {
            System.out.println("Nenhum laticínio encontrado com id " + id);
        }
    }

    private static void atualizarLaticinio() {
        Long id = lerLong("Id do laticínio a atualizar");
        Laticinio existente = repoLaticinio.buscarPorId(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Laticinio", id));

        existente.setPrecoLitro(lerDoubleOuManter("Preço do litro (R$)", existente.getPrecoLitro()));
        existente.setStatus(lerTextoOuManter("Status", existente.getStatus()));

        existente.validar();
        repoLaticinio.atualizar(id, existente);
        System.out.println("[OK] Laticínio atualizado: " + existente);
    }

    private static void deletarLaticinio() {
        Long id = lerLong("Id do laticínio a deletar");
        repoLaticinio.deletar(id);
        System.out.println("[OK] Laticínio removido.");
    }

    private static void menuOferta() {
        boolean continuar = true;
        while (continuar) {
            System.out.println();
            printsMenu("--- OFERTAS DE LEITE ---");
            String opcao = lerTexto("Escolha uma opção");

            switch (opcao) {
                case "1" -> executar(Teste::cadastrarOferta);
                case "2" -> executar(Teste::listarOfertas);
                case "3" -> executar(Teste::buscarOferta);
                case "4" -> executar(Teste::atualizarOferta);
                case "5" -> executar(Teste::deletarOferta);
                case "0" -> continuar = false;
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void cadastrarOferta() {
        LocalDate dataOferta = lerData("Data da oferta (AAAA-MM-DD)");
        double volumeLitros = lerDouble("Volume ofertado (litros)");
        double precoNegociado = lerDouble("Preço negociado (R$)");
        String status = lerTexto("Status (DISPONIVEL/COLETADA/CANCELADA)");
        Long produtorId = lerLong("Id do produtor");
        Long laticinioId = lerLong("Id do laticínio");

        Produtor produtor = repoProdutor.buscarPorId(produtorId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Produtor", produtorId));
        Laticinio laticinio = repoLaticinio.buscarPorId(laticinioId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Laticinio", laticinioId));

        OfertaLeite oferta = new OfertaLeite(dataOferta, volumeLitros, precoNegociado, status, produtor, laticinio);
        oferta.validar();
        repoOferta.salvar(oferta);
        produtor.getOfertaLeites().add(oferta);
        laticinio.getOfertaLeites().add(oferta);
        System.out.println("[OK] Oferta registrada: " + oferta);
    }

    private static void listarOfertas() {
        var ofertas = repoOferta.listarTodos();
        if (ofertas.isEmpty()) {
            System.out.println("Nenhuma oferta registrada ainda.");
            return;
        }
        ofertas.forEach(o -> System.out.println("  - " + o));
    }

    private static void buscarOferta() {
        Long id = lerLong("Id da oferta");
        Optional<OfertaLeite> encontrada = repoOferta.buscarPorId(id);
        if (encontrada.isPresent()) {
            System.out.println("Encontrada: " + encontrada.get());
        } else {
            System.out.println("Nenhuma oferta encontrada com id " + id);
        }
    }

    private static void atualizarOferta() {
        Long id = lerLong("Id da oferta a atualizar");
        OfertaLeite existente = repoOferta.buscarPorId(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("OfertaLeite", id));

        existente.setPrecoNegociado(lerDoubleOuManter("Preço negociado (R$)", existente.getPrecoNegociado()));
        existente.setStatus(lerTextoOuManter("Status", existente.getStatus()));

        existente.validar();
        repoOferta.atualizar(id, existente);
        System.out.println("[OK] Oferta atualizada: " + existente);
    }

    private static void deletarOferta() {
        Long id = lerLong("Id da oferta a deletar");
        repoOferta.deletar(id);
        System.out.println("[OK] Oferta removida.");
    }

    private static void relatorioGeral() {
        System.out.println();
        System.out.println("========== RELATÓRIO GERAL ==========");
        System.out.println("Produtores: " + repoProdutor.listarTodos().size());
        System.out.println("Animais: " + repoAnimal.listarTodos().size());
        System.out.println("Ordenhas: " + repoOrdenha.listarTodos().size());
        System.out.println("Laticínios: " + repoLaticinio.listarTodos().size());
        System.out.println("Ofertas de leite: " + repoOferta.listarTodos().size());
        System.out.println();
        repoProdutor.listarTodos().forEach(System.out::println);
        repoAnimal.listarTodos().forEach(System.out::println);
        repoOrdenha.listarTodos().forEach(System.out::println);
        repoLaticinio.listarTodos().forEach(System.out::println);
        repoOferta.listarTodos().forEach(System.out::println);
    }

    private static void printsMenu(String linha) {
        System.out.println(linha);
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Listar todas");
        System.out.println("3 - Buscar por id");
        System.out.println("4 - Atualizar");
        System.out.println("5 - Deletar");
        System.out.println("0 - Voltar");
    }

    private static String lerTexto(String rotulo) {
        System.out.print(rotulo + ": ");
        return scanner.nextLine().trim();
    }

    private static String lerTextoOuManter(String rotulo, String valorAtual) {
        System.out.print(rotulo + " (atual: " + valorAtual + " | ENTER para manter): ");
        String linha = scanner.nextLine().trim();
        return linha.isBlank() ? valorAtual : linha;
    }

    private static double lerDouble(String rotulo) {
        System.out.print(rotulo + ": ");
        String linha = scanner.nextLine().trim();
        return Double.parseDouble(linha.replace(",", "."));
    }

    private static double lerDoubleOuManter(String rotulo, double valorAtual) {
        System.out.print(rotulo + " (atual: " + valorAtual + " | ENTER para manter): ");
        String linha = scanner.nextLine().trim();
        return linha.isBlank() ? valorAtual : Double.parseDouble(linha.replace(",", "."));
    }

    private static Long lerLong(String rotulo) {
        System.out.print(rotulo + ": ");
        String linha = scanner.nextLine().trim();
        return Long.parseLong(linha);
    }

    private static LocalDate lerData(String rotulo) {
        System.out.print(rotulo + ": ");
        String linha = scanner.nextLine().trim();
        return LocalDate.parse(linha);
    }
}
