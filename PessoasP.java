import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

public class PessoasP{
   ConexaoBD ct =null;
   Connection conn=null;
   ResultSet resp = null;
   PreparedStatement ps=null;
   ArrayList<Pessoas> pessoasPe= new ArrayList<Pessoas>();
   
   String stmt= "SELECT * FROM pessoas";
   
   public ArrayList completaTabela(){

      try{

         ct =new ConexaoBD();
         int idade;
         String nome,sexo,endereco,esporte;
         boolean vegetariano;
         conn=ct.conectar();
         ps=conn.prepareStatement(stmt);
         resp= ps.executeQuery();
         int i=0;
         while(resp.next()){
            Pessoas objeto=new Pessoas();
            nome= resp.getString("nomePessoa");
            idade= resp.getInt("idade");
            sexo= resp.getString("sexo");   
            endereco= resp.getString("endereço");
            vegetariano= resp.getBoolean("vegetariano");
            esporte= resp.getString("fk_nomeEsporte");  
            
            objeto.setNome(nome);
            objeto.setIdade(idade);
            objeto.setSexo(sexo);
            objeto.setEndereco(endereco);
            objeto.setVegetariano(vegetariano);
            objeto.setEsporte(esporte);
            
            pessoasPe.add(objeto);             
         }
         ps.close();
         resp.close();
         return pessoasPe;

      }catch(SQLException e){
         e.printStackTrace();
         return pessoasPe;
      }finally{
         try{
            
            if (conn != null){
               conn.close(); 
            }
            
         }catch(SQLException t){
            t.printStackTrace();
         }
      }
      
      
      
      
   }
   
   

}