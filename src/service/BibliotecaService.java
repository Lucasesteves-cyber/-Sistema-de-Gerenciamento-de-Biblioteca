package service;

import java.util.ArrayList;
import java.util.List;

import model.Livro;

// Serviço da biblioteca: cadastro, listagem, busca, empréstimo e devolução
public class BibliotecaService {

    private final List<Livro> livros = new ArrayList<>();

    public void cadastrar(Livro livro) {
        for (Livro l : livros) {
            if (l.getCodigo() == livro.getCodigo()) {
                throw new IllegalArgumentException("Já existe um livro com o código " + livro.getCodigo() + ".");
            }
        }
        livros.add(livro);
    }

    public List<Livro> listar() {
        return new ArrayList<>(livros);
    }

    public Livro buscarPorCodigo(int codigo) throws LivroNaoEncontradoException {
        for (Livro l : livros) {
            if (l.getCodigo() == codigo) {
                return l;
            }
        }
        throw new LivroNaoEncontradoException(codigo);
    }

    public void emprestar(int codigo) throws LivroNaoEncontradoException {
        Livro livro = buscarPorCodigo(codigo);
        livro.emprestar();
    }

    public void devolver(int codigo) throws LivroNaoEncontradoException {
        Livro livro = buscarPorCodigo(codigo);
        livro.devolver();
    }
}
