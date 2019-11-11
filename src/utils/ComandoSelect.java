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
import java.util.RandomAccess;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Coluna;
import model.Comando;
import model.Tabela;

/**
 *
 * @author micae
 */
public class ComandoSelect {
    private Comando com;
    private LerTabela leitor;
    private Tabela tabelaLida;

    public ComandoSelect(Comando com) {
        this.com = com;
        leitor = new LerTabela(com.getNomeBanco(), com.getNomeTabela());
    }
    
    public String processar(){
         tabelaLida = leitor.realizarLeitura();
         String retorno="";
         String s ="";
        try {
            RandomAccessFile arq = new RandomAccessFile(com.getNomeBanco() + "/" + com.getNomeTabela() + ".dat", "rw");
            arq.seek(tabelaLida.getPosicaoFinalCabecalho());
            for(Coluna c: tabelaLida.getListaColuna()){
                s = c.getNome();
                s = String.format("|%20s", s );
                retorno+= s;
            }
            retorno+= "|\n";
            
            while(arq.getFilePointer()!= tabelaLida.getPosicaofinal()){
                for(Coluna c: tabelaLida.getListaColuna()){
                    if(c.getTipo().equals(Coluna.INT)){
                        int i = arq.readInt();
                        if( i == Integer.MIN_VALUE ){
                            s = String.format("|%20s", "");
                            retorno += s;
                        }else{
                            s = String.format("|%20d", i );
                            retorno += s;
                        }
                        
                    }

                    if(c.getTipo().equals(Coluna.CHAR)){
                        s = "|";
                        for(int i=0; i<c.getTamanho(); i++)
                            s += arq.readChar()+"";
                        retorno += s;
                    }
                    if(c.getTipo().equals(Coluna.FLOAT)){
                        float f = arq.readFloat();
                        if( f == Float.MIN_VALUE ){
                            s = String.format("|%20s", "");
                            retorno += s;
                        }else{
                            s = String.format("|%18.2f", f);
                            retorno += s;
                        }
                    }

                }
                retorno += "|\n";
            }
            arq.close();
            
            return retorno;
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    
}
