package controller;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import br.udesc.udescdb.SQLiteBaseListener;
import br.udesc.udescdb.SQLiteLexer;
import br.udesc.udescdb.SQLiteParser;
import controller.LerTabela;
import java.util.List;
import model.Comando;
import model.Tabela;
import utils.ComandoCriar;
import utils.ComandoInsert;
import utils.ComandoSelect;

public class Controlador {

    public void processarVariosComandos(List<String> lista){
        for(int i =0; i<lista.size(); i++){
            processarComandos(lista.get(i));
        }
    }
    public String processarComandos(String comando) {
        /*String create = "create table Banco.xpto (col1 int, col2 char(20), col3 float)";
		String insert = "insert into Banco.xpto (col1, col2) values (1, 'abc')";
//		String select = "select * from Banco.xpto";
         */
        CodePointCharStream inputStream = CharStreams.fromString(comando);
        SQLiteLexer lexer = new SQLiteLexer(inputStream);
        CommonTokenStream cts = new CommonTokenStream(lexer);
        SQLiteParser parser = new SQLiteParser(cts);
        parser.setBuildParseTree(true);
        ParseTree tree = parser.parse();

        SQLiteBaseListener sqlbl = new SQLiteBaseListener();
        ParseTreeWalker p = new ParseTreeWalker();
        p.walk(sqlbl, tree);

        Comando com = sqlbl.getComando();
        System.out.println(com.toString());

        if (com.getTipo().equals(com.CREATE)) {
            ComandoCriar criar = new ComandoCriar(com);
            criar.processar();
            return "Ok";
        } else if (com.getTipo().equals(com.INSERT)) {

           // LerTabela ler = new LerTabela(com.getNomeBanco(),com.getNomeTabela());
            ComandoInsert insert =  new ComandoInsert(com);
            return ""+ insert.processar();
            
//            System.out.println(t);
        }else{
            ComandoSelect select = new ComandoSelect(com);
           return select.processar();
        }

        // agora vamos pegar as informacoes que o listener capturou e processar o comando 
    }
}
