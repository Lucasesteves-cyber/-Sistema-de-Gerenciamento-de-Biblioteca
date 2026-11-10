package service;

// Exceção personalizada: livro não encontrado
public class LivroNaoEncontradoException extends Exception {

    public LivroNaoEncontradoException(int codigo) {
        super("Livro com código " + codigo + " não encontrado.");
    }
}
