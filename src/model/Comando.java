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
public class Comando {
    
    public  final static String CREATE= "CREATE";
    public final static String INSERT= "INSERT";
    public final static String SELECT= "SELECT";
    
    private String nomeBanco;
    private String tipo;
    private String nomeTabela;
    private List<Coluna> lista = new ArrayList<Coluna>();

    
    public Comando(){}

    public String getNomeBanco() {
        return nomeBanco;
    }

    public void setNomeBanco(String nomeBanco) {
        this.nomeBanco = nomeBanco;
    }
    
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNomeTabela() {
        return nomeTabela;
    }

    public void setNomeTabela(String nomeTabela) {
        this.nomeTabela = nomeTabela;
    }

    public List<Coluna> getLista() {
        return lista;
    }

    public void setLista(List<Coluna> lista) {
        this.lista = lista;
    }
    
    public void addColuna(Coluna c){
        lista.add(c);
    }

    @Override
    public String toString() {
        return "Comando{" + "nomeBanco=" + nomeBanco + ", tipo=" + tipo + ", nomeTabela=" + nomeTabela + ", lista=" + lista + '}';
    }

    
    
}
