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
    private Tabela tabelaLida;

    public ComandoInsert(Comando com) {
        this.com = com;
        leitor = new LerTabela(com.getNomeBanco(), com.getNomeTabela());
    }

    public boolean processar() {
        try {
            if (validar()) {
                RandomAccessFile arq = new RandomAccessFile(com.getNomeBanco() + "/" + com.getNomeTabela() + ".dat", "rw");
                arq.seek(tabelaLida.getPosicaofinal());
                for (Coluna colunaNaTabela : tabelaLida.getListaColuna()) {
                    Coluna colunaEncontrada = null;
                    for (Coluna colunaNaLista : com.getLista()) {
                        if (colunaNaLista.getNome().equals(colunaNaTabela.getNome())) {
                            colunaEncontrada = colunaNaLista;
                        }
                    }
                    if (colunaNaTabela.getTipo().equals(Coluna.INT) && colunaEncontrada == null) {
                        arq.skipBytes(4);
                    } else if (colunaNaTabela.getTipo().equals(Coluna.INT)) {
                        arq.writeInt(Integer.parseInt( ( (String) colunaEncontrada.getLiteral() ).replaceAll("\"", "") ) );
                    }

                    if (colunaNaTabela.getTipo().equals(Coluna.CHAR) && colunaEncontrada == null) {
                        arq.writeChars(String.format("%1$" + colunaNaTabela.getTamanho() + "s", ""));
                    } else if (colunaNaTabela.getTipo().equals(Coluna.CHAR)) {
                        arq.writeChars(String.format("%1$" + colunaNaTabela.getTamanho() + "s", colunaEncontrada.getLiteral()));
                    }

                    if (colunaNaTabela.getTipo().equals(Coluna.FLOAT) && colunaEncontrada == null) {
                        arq.skipBytes(4);
                    } else if (colunaNaTabela.getTipo().equals(Coluna.FLOAT)) {
                        arq.writeFloat(Float.parseFloat( ( (String) colunaEncontrada.getLiteral() ).replaceAll("\"", "") ) );
                    }
                }

                arq.close();
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private boolean encontrarColuna(Coluna c, List<Coluna> listaColunas) {
        boolean encontrou = false;
        for (Coluna col : listaColunas) {
            if (c.getNome().equals(col.getNome())) {
                encontrou = true;
                break;
            }
        }
        return encontrou;
    }

    private boolean validar() {
        tabelaLida = leitor.realizarLeitura();
        System.out.println(tabelaLida.getPosicaoFinalCabecalho());
        System.out.println(tabelaLida.getPosicaofinal());
        for (Coluna c : com.getLista()) {
            if (!encontrarColuna(c, tabelaLida.getListaColuna())) {
                System.out.print("Coluna não encontrada" + c);
                return false;
            }
        }
        return true;
    }
}
