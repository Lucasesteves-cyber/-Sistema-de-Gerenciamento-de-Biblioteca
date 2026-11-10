package model;

import interfaces.Emprestavel;

// Classe Livro: representa um livro da biblioteca
public class Livro implements Emprestavel {

    private int codigo;
    private String titulo;
    private String autor;
    private int ano;
    private boolean emprestado;

    public Livro(int codigo, String titulo, String autor, int ano) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.emprestado = false;
    }

    @Override
    public void emprestar() {
        if (emprestado) {
            throw new IllegalStateException("O livro \"" + titulo + "\" já está emprestado.");
        }
        emprestado = true;
    }

    @Override
    public void devolver() {
        if (!emprestado) {
            throw new IllegalStateException("O livro \"" + titulo + "\" não está emprestado.");
        }
        emprestado = false;
    }

    public int getCodigo() { return codigo; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public int getAno() { return ano; }
    public boolean isEmprestado() { return emprestado; }

    @Override
    public String toString() {
        return String.format("[%d] %s - %s (%d) | %s",
                codigo, titulo, autor, ano, emprestado ? "EMPRESTADO" : "Disponível");
    }
}
