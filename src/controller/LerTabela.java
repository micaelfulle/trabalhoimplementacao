/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Coluna;
import model.Tabela;
import utils.Constantes;

/**
 *
 * @author micae
 */
public class LerTabela {
    
    private String nomeBanco;
    private String nomeTabela;

    public LerTabela(String nomeBanco, String nomeTabela) {
        this.nomeBanco = nomeBanco;
        this.nomeTabela = nomeTabela;
    }
    
    
    
    public Tabela realizarLeitura(){
        Tabela t= null;
        try{
            int numeroColunas;
            String s="";
            t= new Tabela();
            RandomAccessFile arq =  new RandomAccessFile( nomeBanco+"/"+nomeTabela+".dat", "rw");
            
            arq.seek(0);
            for(int i = 0; i<Constantes.TAMANHO_NOME_TABELA; i++ ){
                s+= arq.readChar();
            }
            s = s.trim();
            t.setNome(s);
            
            numeroColunas = arq.readInt();
            for( int i = 0; i<numeroColunas; i++){
                Coluna col = new Coluna();
                s="";
                for(int j = 0 ; j<Constantes.TAMANHO_TIPO_COLUNA; j++){
                 s+= arq.readChar();
            }
            s = s.trim();
            col.setTipo(s);
            
            col.setTamanho(arq.readInt());
            
            s="";
            for(int j = 0; j<Constantes.TAMANHO_NOME_COLUNA; j++){
                s+= arq.readChar();
            }
            s = s.trim();
            col.setNome(s);
            t.addColuna(col);
            
            }
            t.setPosicaoFinalCabecalho((int)arq.length());
            arq.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return null;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return t;
    }
}
