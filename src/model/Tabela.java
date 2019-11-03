/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author micae
 */
public class Tabela {
    private String nome;
    private List <Coluna> listaColuna = new ArrayList<Coluna>();
    private int posicaoFinalCabecalho;
    private int posicaofinal;

    public int getPosicaoFinalCabecalho() {
        return posicaoFinalCabecalho;
    }

    public void setPosicaoFinalCabecalho(int posicaoFinalCabecalho) {
        this.posicaoFinalCabecalho = posicaoFinalCabecalho;
    }

    public int getPosicaofinal() {
        return posicaofinal;
    }

    public void setPosicaofinal(int posicaofinal) {
        this.posicaofinal = posicaofinal;
    }

    

    public Tabela(){}

    public Tabela(String nome) {
        this.nome = nome;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Coluna> getListaColuna() {
        return listaColuna;
    }

    public void setLitaColuna(List<Coluna> listaColuna) {
        this.listaColuna = listaColuna;
    }
    
    public void addColuna(Coluna c){
        listaColuna.add(c);
    }

    @Override
    public String toString() {
        return "Tabela{" + "nome=" + nome + ", listaColuna=" + listaColuna + '}';
    }
    
}
