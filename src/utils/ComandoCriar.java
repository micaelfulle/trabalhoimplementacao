/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Coluna;
import model.Comando;

/**
 *
 * @author micae
 */
public class ComandoCriar {
    private Comando com;
    
    public ComandoCriar(Comando com){
        this.com = com;
    }
    
    
    public void processar(){
        try {
            new File(com.getNomeBanco()).mkdir();
            RandomAccessFile arq = new RandomAccessFile(com.getNomeBanco()+"/"+com.getNomeTabela()+".dat","rw");
            
            
            arq.seek(0);
            arq.writeChars(String.format("%1$"+ Constantes.TAMANHO_NOME_TABELA + "s", com.getNomeTabela()));
            List< Coluna> colunas = com.getLista();
            arq.writeInt(colunas.size());
            
            for( int i = 0; i<colunas.size(); i++){
                arq.writeChars(String.format("%1$"+ Constantes.TAMANHO_TIPO_COLUNA + "s", colunas.get(i).getTipo()));
                arq.writeInt(colunas.get(i).getTamanho());
                 arq.writeChars(String.format("%1$"+ Constantes.TAMANHO_NOME_COLUNA + "s", colunas.get(i).getNome()));
            }
            
            arq.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
