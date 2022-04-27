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
        System.out.println("Menu de Cuerpo Celeste:");
        System.out.println("\n");
        System.out.println("Selecciona una opcion.");
        System.out.println("          Opcion 1 Añadir actor :");
        System.out.println("          Opcion 2 Listar actores:");
        System.out.println("          Opcion 3 Buscar actor  por código:");
        System.out.println("          Opcion 4 Buscar actor por nombre:");
        System.out.println("          Opcion 5 Borrar actor:");
        System.out.println("          Opcion 6 Borrar fichero.:\n");
        System.out.println("          Opcion 7 Salir.");
            try {
                    System.out.print("Introduzca su opcion[1-6][7 Salir]: ");

                    int opt = Integer.parseInt(dato.readLine());
                    

                    switch (opt) {

                        case 1:
                           // addCuerpo();
                            break;

                        case 2:
                          //  listarCuerpos();
                            break;

                        case 3:
                            busqueda = "Codigo";
                          //  buscarCuerpo(busqueda);
                            break;

                        case 4:
                             busqueda = "otro";
                           // buscarCuerpo(busqueda);                      
                            break;

                        case 5:
                          //  eliminarCuerpo();
                            break;

                        case 6:
                          //  eliminarFichero();
                            break;

                        case 7:
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
    
      static void decision() throws IOException, Exception
      {
       


      
       String decision = null;
       boolean menu = true;
          
          while (menu)
          { 
           System.out.println(" --- Menu Actores  --- ");
                    try 
                    {
                    System.out.print("Añade el codigo --> ");

                    int codigoCuerpo = Integer.parseInt(dato.readLine());
                    
                    System.out.println("");

                    System.out.print("Añade el nombre --> ");

                    String nombre = dato.readLine();
                    
                    System.out.println("");

                    System.out.print("Añade el tipo --> ");

                    String tipoCuerpo = dato.readLine();
                    
                    System.out.println("");

                    System.out.print("Añade el diametro --> ");
                    int diametro = Integer.parseInt(dato.readLine());
                    System.out.println("");
                   
                    
                    if((nombre.isEmpty()) || (codigoCuerpo <= 0 )|| (tipoCuerpo.isEmpty()) || (diametro <= 0)) //controlamos que no haya datos en nulos
                    {
                     throw new NumberFormatException("Ningun dato puede ser nulo o negativo, vuelve a intentarlo");
                    }
                    else
                    {
                        //Costruimos el objeto con las preguntas como valores a los atributos y lo añadimos a la lista
                       //  listaCuerpos.add(new CuerpoCeleste(codigoCuerpo,nombre, tipoCuerpo, diametro));
                        
                           
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
                                 //Listamos todos los cuerpos desde el primer hasta el ultimo index
                                   for (int i = 0 ; i < 10 ; i++){
                                            
                                            System.out.println("Cuerpo celeste---> +listaCuerpos.get(i)");
                                          
                                    }
                                    
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
               
              decision();
               
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
