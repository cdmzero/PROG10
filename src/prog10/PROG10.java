/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prog10;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.InputMismatchException;



/**
 *
 * @author zero
 */
public class PROG10 {

    /**
     * @param args the command line arguments
     */
    
    static BufferedReader dato = new BufferedReader(new InputStreamReader(System.in));
    
    
    
     private static void mostrarMenu() {

        
        boolean enabledmostrarMenu = true;
        
        String busqueda = null;
       
        while (enabledmostrarMenu == true) { 
        System.out.println("Menu Actores:");
        System.out.println("\n");
        System.out.println("Selecciona una opcion.");
        System.out.println("          Opcion 1 Añadir actor :");
        System.out.println("          Opcion 2 Listar actores:");
        System.out.println("          Opcion 3 Borrar actor  por codigo:");
        System.out.println("          Opcion 4 Editar actor por codigo:");
        System.out.println("          Opcion 5 Salir.");
            try {
                    System.out.print("Introduzca su opcion[1-5][5 Salir]: ");

                    int opt = Integer.parseInt(dato.readLine());
                    

                    switch (opt) {

                        case 1:
                            addActor();
                            break;

                        case 2:
                            listActors();
                            break;

                        case 3:
                            deleteActor();
                            break;

                        case 4:
                            editActor();                      
                            break;

                        case 5:
                            System.out.println("Adios");
                            enabledmostrarMenu = false;
                            
                            break;

                        default:
                            System.err.println("Opcion invalida");

                    }
               

            } catch (InputMismatchException err) {
                System.err.println("Dato invalido");
                
            } catch (Exception err) {
                System.err.println(err.getMessage());
            }
       }

    }
    
     
     static void editActor() throws IOException, ClassNotFoundException, SQLException
     {
                // Registramos el driver
                Class.forName("oracle.jdbc.OracleDriver");

                            // Parametros de conexion
                            String dbURL = "jdbc:oracle:thin:@localhost:49161:xe";
                            String user  = "system";
                            String pass  = "oracle";

                            // Objeto de conexion
                            Connection conn = null;
                            
                            // Socket de Conexion para BD
                            conn = DriverManager.getConnection(dbURL, user, pass);

                            // Costruimos el objeto con las preguntas como valores a los atributos y lo añadimos a la lista
                            Statement stmt = conn.createStatement();


         System.out.println("Introduzca el codigo del actor que desea editar");
            
            
            int ni= Integer.parseInt(dato.readLine());
            
                ResultSet rset = stmt.executeQuery("SELECT * FROM APEX_040000.ACTORES WHERE CODIGO_ACTOR = "+ni+"");
                            
              System.out.println(" --- Editar Actor  --- "+ ni);
             
                while (rset.next()){

                    System.out.print("Añade el nombre --> ");

                    String nombre = dato.readLine();    
                            
                    if(nombre.isEmpty()){
                        
                        nombre = rset.getString(1);
                    }
                    
                    System.out.println("");

                    System.out.print("Añade el Apellido --> ");

                    String apellido = dato.readLine();
                    
                    if(apellido.isEmpty()){
                        
                    apellido = rset.getString(2);
                    
                    }
                    
                    System.out.println("");
                    
                    System.out.print("Añade la fecha de nacimiento --> ");

                    String fechaNacimiento = dato.readLine();
                    
                    if(fechaNacimiento.isEmpty()){
                        fechaNacimiento = rset.getString(3);
                    }
                    
                    System.out.println("");

                    
                    System.out.print("Añade los premios --> ");

                    String premio = dato.readLine();
                    
                    if(premio.isEmpty()){
                        premio = rset.getString(4);
                    }
                    
                    System.out.println("");
                    
                    System.out.print("Añade el salario --> ");
                    
                    String salario = dato.readLine();
                    
                    if (salario.equals(null)){
                        salario = rset.getString(5);
                    }
                    
                    
                    
                    System.out.println("");
                      
                 
                      
                 

                            // Resulset guarda el resultado de la consulta a la tabla de BD
                            String query =  "UPDATE APEX_040000.ACTORES set nombre='"+nombre +"', apellido='"+apellido +"', f_nacimiento='"+fechaNacimiento +"', premios='"+premio +"'"
                                    + ", salario='"+salario +"' where codigo_actor='"+ rset.getString(6) +"'";

                        rset = stmt.executeQuery(query);
                        break;
 }
                        
                    
  
                  stmt.close();  
            
         System.out.println("Editado con exito");
     
     }
     
    static void listActors() throws SQLException, ClassNotFoundException
    {
    
       

                            // Registramos el driver
                            Class.forName("oracle.jdbc.OracleDriver");

                            // Parametros de conexion
                            String dbURL = "jdbc:oracle:thin:@localhost:49161:xe";
                            String user  = "system";
                            String pass  = "oracle";

                            // Objeto de conexion
                            Connection conn = null;

                            // Socket de Conexion para BD
                            conn = DriverManager.getConnection(dbURL, user, pass);

                            // Costruimos el objeto con las preguntas como valores a los atributos y lo añadimos a la lista
                            Statement stmt = conn.createStatement();

                            // Resulset guarda el resultado de la consulta a la tabla de BD
                      

                            ResultSet rset = stmt.executeQuery("SELECT NOMBRE, APELLIDO, F_NACIMIENTO, PREMIOS, SALARIO, CODIGO_ACTOR FROM APEX_040000.ACTORES");

                            while (rset.next())
                            {
                            String nombre = rset.getString(1);
                            String apellido = rset.getString(2);
                            String fNacimiento = rset.getString(3);
                            String premios = rset.getString(4);
                            float  salario = rset.getFloat(5);
                            int    codigoActor = rset.getInt(6);
                            
                            
                                System.out.println("Nombre: "+ nombre + " Apellido: "+ apellido +" Fecha Nacimiento: "+ fNacimiento + " Premios: "+ premios + " Salario: "+ salario + " Codigo Actor:" + codigoActor);
                            
                            }

                            stmt.close();      
    
    }
    
    static void deleteActor() throws SQLException, ClassNotFoundException, IOException
    {
        
        
                            // Registramos el driver
                            Class.forName("oracle.jdbc.OracleDriver");

                            // Parametros de conexion
                            String dbURL = "jdbc:oracle:thin:@localhost:49161:xe";
                            String user  = "system";
                            String pass  = "oracle";

                            // Objeto de conexion
                            Connection conn = null;
        
      
            System.out.println("Introduzca el codigo de actor que desea eliminar");
            
            
            int ni= Integer.parseInt(dato.readLine());
            
            int i=0;
 
            
          
                    System.out.println("Está seguro que desea eliminarlo (S/N)");
                    String res;
                    do{
                        res = dato.readLine();
                        
                        if(!res.equals("N")&&!res.equals("S")) {
                            System.err.println("Sólo 'S' para borrar y 'N' para mantenerlo");
                        }
                        if(res.equals("S")){
                            //borramos el registro
                     

                            // Socket de Conexion para BD
                            conn = DriverManager.getConnection(dbURL, user, pass);

                            // Costruimos el objeto con las preguntas como valores a los atributos y lo añadimos a la lista
                            Statement stmt = conn.createStatement();

                            // Resulset ejecuta la query

                            ResultSet rset = stmt.executeQuery("DELETE FROM APEX_040000.ACTORES\n" +
                            "WHERE CODIGO_ACTOR='"+ ni + "'");
                      
                            stmt.close();      
    
                            
                        }
                    }while(!res.equals("S") && !res.equals("N"));
    }

    
    
    static void addActor() throws IOException, Exception
    {
      
       String decision = null;
       boolean menu = true;
          
          while (menu)
          { 
           System.out.println(" --- Añadir Actores  --- ");
                    try 
                    {
  

                    System.out.print("Añade el nombre --> ");

                    String nombre = dato.readLine();
        
                    System.out.println("");

                    System.out.print("Añade el Apellido --> ");

                    String apellido = dato.readLine();
                    
                    System.out.println("");
                    
                    System.out.print("Añade la fecha de nacimiento --> ");

                    String fechaNacimiento = dato.readLine();
                    
                    System.out.println("");

                    
                    System.out.print("Añade los premios --> ");

                    String premio = dato.readLine();
                    
                    System.out.println("");
                    
                    System.out.print("Añade el salario --> ");
                    
                    float salario = Integer.parseInt(dato.readLine());
                    
                    System.out.println("");
                    
                    System.out.print("Añade un ID --> ");

                    int codigo_actor = Integer.parseInt(dato.readLine());
                    
                    System.out.println("");
                   
                   

                            if((nombre.isEmpty()) || (apellido.isEmpty()) || (fechaNacimiento.isEmpty()) || (premio.isEmpty()) || (premio.isEmpty()) || (salario <= 0) || (codigo_actor <= 0)) //controlamos que no haya datos en nulos
                            {
                             throw new NumberFormatException("Ningun dato puede ser nulo o negativo, vuelve a intentarlo");
                            }
                            else
                            {

                            // Registramos el driver
                            Class.forName("oracle.jdbc.OracleDriver");

                            // Parametros de conexion
                            String dbURL = "jdbc:oracle:thin:@localhost:49161:xe";
                            String user  = "system";
                            String pass  = "oracle";

                            // Objeto de conexion
                            Connection conn = null;

                            // Socket de Conexion para BD
                            conn = DriverManager.getConnection(dbURL, user, pass);

                            // Costruimos el objeto con las preguntas como valores a los atributos y lo añadimos a la lista
                            Statement stmt = conn.createStatement();

                            // Resulset guarda el resultado de la consulta a la tabla de BD
                            String query =  "INSERT INTO APEX_040000.ACTORES (nombre, apellido, f_nacimiento, premios, salario, codigo_actor )" 
                            + "VALUES ('" +nombre+ "', '" +apellido+ "', '" +fechaNacimiento+ "', ' " +premio+ "', '" +salario+ "', '" +codigo_actor+ "')";

                            ResultSet rset = stmt.executeQuery(query);

                            stmt.close();      
                            }
                                    boolean submenu = true;
                                    while(submenu)
                                    {
                                        System.out.println("¿Deseas añadir mas actores? S,s o N,n ");
                                        decision = dato.readLine();
                                            if("S".equals(decision) || "s".equals(decision) )
                                            {
                                            submenu=false;
                                            break;

                                            }
                                            else if("N".equals(decision) || "n".equals(decision))
                                            {
                                            menu=false;
                                            submenu=false;
                                            break;
                                            }
                                            else
                                            {
                                            System.err.println("Opcion invalida");
                                            }
                                   }
                    }
                    catch(InputMismatchException err)
                    {
                    System.err.println("Dato invalido");
                    }
                    catch(NumberFormatException err)
                    {
                    System.err.println(err.getMessage());
                    }
          }
    }
      

          
    
    
    
    
    
    
    public static void main(String[] args) throws Exception {
        
     
        Connection conn = null;
 
        try {

            // Registramos el driver
            Class.forName("oracle.jdbc.OracleDriver");
            
            // Parametros de conexion
            String dbURL = "jdbc:oracle:thin:@localhost:49161:xe";
            String user = "system";
            String pass = "oracle";
            
            // Socket de Conexion para BD
            conn = DriverManager.getConnection(dbURL, user, pass);
            
            if (conn != null) 
            {
                System.out.println("Connected to DB \n");
               
               // Statement ejecuta sentencias SQL y devuelve el resultado.
               Statement stmt = conn.createStatement();
               
               // Resulset guarda el resultado de la consulta a la tabla de BD
               ResultSet rset = stmt.executeQuery("select BANNER from SYS.V_$VERSION");
               
                 
               
               //Mostramos por pantalla el ResultSet
               
                System.out.println("SQL Server info:\n");
               while (rset.next())
               System.out.println (rset.getString(1));   // Print col 1
                
               stmt.close();
               
                   mostrarMenu();
             
             
             
            }
            
       
      
        
 
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
        
        
    }
    
}
