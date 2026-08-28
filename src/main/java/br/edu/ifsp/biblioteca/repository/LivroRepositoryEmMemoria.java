package br.edu.ifsp.biblioteca.repository;

import br.edu.ifsp.biblioteca.domain.Livro;

import java.util.*;

public class LivroRepositoryEmMemoria implements ILivroRepository {

    private final Map<Long, Livro> livros = new HashMap<Long, Livro>();
    private Long sequenciaId = 0L;

    @Override
    public Livro salvar(Livro livro) {

        if (livro.getId() == null) {
            this.sequenciaId = this.sequenciaId + 1;
            livro.setId(this.sequenciaId);

            //pre e pos incremento -->(++Oi) (Oi++)
        }

        this.livros.put(livro.getId(), livro);

        return livro;
    }

    @Override
    public List<Livro> listarTodos() {
        return new ArrayList<>(this.livros.values());
    }

    @Override
    public Optional<Livro> buscarPorId(Long Id) {
        return Optional.ofNullable(this.livros.get(Id));


        //Livro l = this.livros.get(Id);
        //
        //  if (l == null) {
        //return Optional.empty();
        //}
        //return Optional.of(l);
        //}
    }
    @Override
    public Optional<Livro> buscarPorIsbn(String isbn) {


        //foreach
        //para cada item livro(for) na lista colecaoLivros(each)
       //  for(Livro livro: colecaoLivros) {
       //     if (livro.getIsbn().equalsIgnoreCase(isbn))
       //         return Optional.of(livro);
       // }


        List<Livro> colecaoLivros = new ArrayList<>(this.livros.values());

        for (int i = 0; i < colecaoLivros.size(); i++){
            Livro livro = colecaoLivros.get(i);
            String livroIsbn = livro.getIsbn();

            if(livroIsbn.equalsIgnoreCase(isbn)){
                return Optional.of(livro);
            }
        }


        return Optional.empty(); //retorna se n achar o fim do loop
    }

    @Override
    public List<Livro> buscarPorTitulo(String titulo) {

        List<Livro> colecaoLivros = new ArrayList<>(this.livros.values());
        List<Livro> colecaoNova = new ArrayList<>();
        for(Livro livro: colecaoLivros) {
             if (livro.getTitulo().equalsIgnoreCase(titulo)){
                 colecaoNova.add(livro);
             }

        return colecaoNova;
    }
}
