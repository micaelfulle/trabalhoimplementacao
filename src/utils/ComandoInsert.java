/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import controller.LerTabela;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Coluna;
import model.Comando;
import model.Tabela;

/**
 *
 * @author micae
 */
public class ComandoInsert {
    
    private Comando com;
    private LerTabela leitor;
    private Tabela tabela;
    
    public ComandoInsert(Comando com){
        this.com= com;
        leitor  =  new LerTabela(com.getNomeBanco(), com.getNomeTabela());
    }
    
    public boolean processar(){
        try {
        if(validar()){
          RandomAccessFile arq = new RandomAccessFile(com.getNomeBanco()+"/"+com.getNomeTabela() + ".dat", "rw");
       
        arq.close();
        }} catch (FileNotFoundException ex) {
               ex.printStackTrace();
                    } catch (IOException ex) {
           ex.printStackTrace();
        }
        return false;
    }
    
    private boolean encontrarColuna(Coluna c, List<Coluna> listaColunas){
        boolean encontrou = false;
        for(Coluna col: listaColunas){
            if(c.getNome().equals(col.getNome())){
                encontrou = true;
                break;
            }
        }
        return encontrou;
    }
    private boolean validar(){
        tabela = leitor.realizarLeitura();
        for(Coluna c : com.getLista()){
                if(!encontrarColuna(c,tabela.getListaColuna())){
                    System.out.print("Coluna não encontrada"+c);
                    return false;
                }
            }
        return true;
    }
}
