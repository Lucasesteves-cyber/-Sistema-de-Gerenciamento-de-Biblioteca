package app;

import java.util.List;
import java.util.Scanner;

import model.Livro;
import service.BibliotecaService;
import service.LivroNaoEncontradoException;

// Classe principal: menu interativo do sistema
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final BibliotecaService biblioteca = new BibliotecaService();

    public static void main(String[] args) {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n===== SISTEMA DE BIBLIOTECA =====");
            System.out.println("1 - Cadastrar livro");
            System.out.println("2 - Listar livros");
            System.out.println("3 - Buscar livro por código");
            System.out.println("4 - Emprestar livro");
            System.out.println("5 - Devolver livro");
            System.out.println("6 - Executar testes obrigatórios");
            System.out.println("0 - Sair");

            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> cadastrarLivro();
                case 2 -> listarLivros();
                case 3 -> buscarLivro();
                case 4 -> emprestarLivro();
                case 5 -> devolverLivro();
                case 6 -> executarTestes();
                case 0 -> System.out.println("Encerrando o sistema. Até logo!");
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        }
        scanner.close();
    }

    private static void cadastrarLivro() {
        int codigo = lerInteiro("Código: ");
        String titulo = lerTexto("Título: ");
        String autor = lerTexto("Autor: ");
        int ano = lerInteiro("Ano: ");

        try {
            biblioteca.cadastrar(new Livro(codigo, titulo, autor, ano));
            System.out.println("Livro cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void listarLivros() {
        List<Livro> livros = biblioteca.listar();
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
            return;
        }
        System.out.println("--- Livros cadastrados ---");
        for (Livro l : livros) {
            System.out.println(l);
        }
    }

    private static void buscarLivro() {
        int codigo = lerInteiro("Código do livro: ");
        try {
            Livro livro = biblioteca.buscarPorCodigo(codigo);
            System.out.println("Encontrado: " + livro);
        } catch (LivroNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void emprestarLivro() {
        int codigo = lerInteiro("Código do livro a emprestar: ");
        try {
            biblioteca.emprestar(codigo);
            System.out.println("Livro emprestado com sucesso!");
        } catch (LivroNaoEncontradoException | IllegalStateException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void devolverLivro() {
        int codigo = lerInteiro("Código do livro a devolver: ");
        try {
            biblioteca.devolver(codigo);
            System.out.println("Livro devolvido com sucesso!");
        } catch (LivroNaoEncontradoException | IllegalStateException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void executarTestes() {
        System.out.println("\n===== EXECUTANDO TESTES OBRIGATÓRIOS =====");

        System.out.println("\n> Teste 1: Cadastrar três livros");
        try {
            biblioteca.cadastrar(new Livro(101, "Dom Casmurro", "Machado de Assis", 1899));
            biblioteca.cadastrar(new Livro(102, "O Cortiço", "Aluísio Azevedo", 1890));
            biblioteca.cadastrar(new Livro(103, "Vidas Secas", "Graciliano Ramos", 1938));
            System.out.println("Três livros cadastrados.");
        } catch (IllegalArgumentException e) {
            System.out.println("Aviso: " + e.getMessage() + " (testes já executados antes?)");
        }

        System.out.println("\n> Teste 2: Listar livros");
        listarLivros();

        System.out.println("\n> Teste 3: Buscar livro de código 102");
        try {
            System.out.println("Encontrado: " + biblioteca.buscarPorCodigo(102));
        } catch (LivroNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        System.out.println("\n> Teste 4: Emprestar livro 101");
        try {
            biblioteca.emprestar(101);
            System.out.println("Livro emprestado com sucesso!");
        } catch (LivroNaoEncontradoException | IllegalStateException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        System.out.println("\n> Teste 5: Tentar emprestar novamente o livro 101");
        try {
            biblioteca.emprestar(101);
            System.out.println("Livro emprestado com sucesso!");
        } catch (LivroNaoEncontradoException | IllegalStateException e) {
            System.out.println("Erro tratado: " + e.getMessage());
        }

        System.out.println("\n> Teste 6: Devolver livro 101");
        try {
            biblioteca.devolver(101);
            System.out.println("Livro devolvido com sucesso!");
        } catch (LivroNaoEncontradoException | IllegalStateException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        System.out.println("\n> Teste 7: Buscar livro inexistente (código 999)");
        try {
            biblioteca.buscarPorCodigo(999);
        } catch (LivroNaoEncontradoException e) {
            System.out.println("Exceção tratada: " + e.getMessage());
        }

        System.out.println("\n===== FIM DOS TESTES =====");
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido! Digite um número inteiro.");
            }
        }
    }

    private static String lerTexto(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim();
            if (!entrada.isEmpty()) {
                return entrada;
            }
            System.out.println("O campo não pode ficar vazio.");
        }
    }
}
