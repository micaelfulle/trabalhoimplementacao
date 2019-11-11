/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Xml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author micae
 */
public class LeitorXml {

    public static void main(String[] args) {
        LeitorXml l = new LeitorXml("src/Xml/Teste.xml");
        System.out.println( l.ler() );
    }

    private String nomeArquivo;

    public LeitorXml(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public List< String > ler() {
        List< String > comandos = new ArrayList<>();
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setIgnoringComments(true);
            dbf.setIgnoringElementContentWhitespace(true);

            DocumentBuilder db;

            db = dbf.newDocumentBuilder();

            Document doc = db.parse(new File(nomeArquivo));

            NodeList insert = doc.getElementsByTagName("insert");
            
            for (int i = 0; i < insert.getLength(); i++) {
                comandos.add( gerarInsert(insert.item(i)) );
            }
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return comandos;
    }

    private String gerarInsert(Node n) {
        String db = "";
        String table = "";
        List<String> nameColumns = new ArrayList<>();
        List<String> valueColumns = new ArrayList<>();

        NodeList list = n.getChildNodes();

        String s = "";
        for (int i = 0; i < list.getLength(); i++) {
            s = list.item(i).getNodeName();
//            System.out.println(s);
            if ( s.equals("db") ) {
                db = list.item(i).getTextContent();
            }else if( s.equals("table") ){
                table = list.item(i).getTextContent();
            }else if( s.equals("columns") ){
                NodeList columns = list.item(i).getChildNodes();
                for( int j = 0; j < columns.getLength(); j++ ){
                    NodeList atributos = columns.item(j).getChildNodes();
                    for( int k = 0; k < atributos.getLength(); k++ ){
                        s = atributos.item(k).getNodeName();
                        if( s.equals( "name" ) ){
                            nameColumns.add( atributos.item(k).getTextContent() );
                        }else if( s.equals( "value") ){
                            if( atributos.item(k).getTextContent().matches("\\[A-Z\\]\\[a-z\\]\\[0-9\\]" ) ){
                                valueColumns.add( "\"" + atributos.item(k).getTextContent() + "\"");
                            }else{
                                valueColumns.add( atributos.item(k).getTextContent() );
                            }
                            
                        }
                    }
                }
            }
            
            
        }
        
//        System.out.println( db );
//        System.out.println( table);
//        System.out.println( nameColumns );
//        System.out.println( valueColumns );

        s = "insert into " + db + "." + table + " (" + 
            nameColumns.toString().replace("[", "").replace("]", "") + 
            ") values ( " + valueColumns.toString().replace("[", "").replace("]", "")+ ")";
//        System.out.println( s );
        return s;
    }

    private static void printFilhos(String dentes, NodeList filhos) {

        for (int i = 0; i < filhos.getLength(); i++) {
            Node filho = filhos.item(i);
            if (filho.getChildNodes().getLength() > 0) {
                printNodeAndAttribs(dentes, filho);
                printFilhos(dentes + "  ", filho.getChildNodes());
            } else {
                if (filho.getNodeType() == Node.ELEMENT_NODE) {
                    printNodeAndAttribs(dentes, filho);
                } else if (filho.getNodeValue().trim().length() > 0) {
                    System.out.println(dentes + filho.getNodeValue());
                }
            }
        }
    }

    private static void printNodeAndAttribs(String dentes, Node node) {
        System.out.println(dentes + node.getNodeName());
        NamedNodeMap n = node.getAttributes();
        if (n != null) {
            for (int j = 0; j < n.getLength(); j++) {
                System.out.println(dentes + "-" + n.item(j));
            }
        }

    }

}
