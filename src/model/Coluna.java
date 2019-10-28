/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author micae
 */
public class Coluna {
    
    public final static String INT ="int";
    public final static String FLOAT ="float";
    public final static String CHAR ="char";
    
    private String tipo;
    private int tamanho;
    private String nome;
    private Object literal;

    public Coluna(String tipo, int tamanho, String nome, Object literal) {
        this.tipo = tipo;
        this.tamanho = tamanho;
        this.nome = nome;
        this.literal = literal;
    }

    public Coluna(){}
    

    public Object getLiteral() {
        return literal;
    }

    public void setLiteral(Object literal) {
        this.literal = literal;
    }
    
    

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Coluna{" + "tipo=" + tipo + ", tamanho=" + tamanho + ", nome=" + nome + ", literal=" + literal + '}';
    }
    
    
}
