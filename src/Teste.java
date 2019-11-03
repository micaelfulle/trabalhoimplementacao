import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import br.udesc.udescdb.SQLiteBaseListener;
import br.udesc.udescdb.SQLiteLexer;
import br.udesc.udescdb.SQLiteParser;
import controller.LerTabela;
import model.Comando;
import model.Tabela;
import utils.ComandoCriar;

public class Teste {

	public static void main(String[] args) {
		String create = "create table Banco.xpto (col1 int, col2 char(20), col3 float)";
		String insert = "insert into Banco.xpto (col1, col2) values (1, 'abc')";
		String select = "select * from Banco.xpto";
		
		CodePointCharStream inputStream = CharStreams.fromString(create);
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
                
//                 ComandoCriar criar = new ComandoCriar(com);
//               criar.processar();
//                
                LerTabela ler = new LerTabela("Banco","xpto");
                Tabela t = ler.realizarLeitura();
              System.out.println(t);
//              

		// agora vamos pegar as informacoes que o listener capturou e processar o comando 
	}

}
