# Sistema de Gerenciamento de Biblioteca

Sistema em Java para cadastrar, listar, buscar, emprestar e devolver livros.

## Estrutura

```
src/
├── app/Main.java                         -> menu interativo (Scanner)
├── interfaces/Emprestavel.java           -> interface com emprestar() e devolver()
├── model/Livro.java                      -> classe Livro (implementa Emprestavel)
└── service/
    ├── BibliotecaService.java            -> regras de negócio (ArrayList<Livro>)
    └── LivroNaoEncontradoException.java  -> exceção personalizada
```

## Como executar

```
javac -encoding UTF-8 -d bin src/app/*.java src/interfaces/*.java src/model/*.java src/service/*.java
java -cp bin app.Main
```

No menu, a opção **6** executa todos os testes obrigatórios.
