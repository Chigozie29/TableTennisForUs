package tabletennis;

import javax.swing.JOptionPane;
import java.sql.*;

public class DB 
{
    //Declare properties
    private final String driver = "net.ucanaccess.jdbc.UcanaccessDriver";
    //DB must be in project folder
    private final String url = "jdbc:ucanaccess://C://Users//chigi//OneDrive//Documents//NetBeansProjects//Table Tennis//Table Tennis.accdb";
    
    //Declare the new variables
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    
    //Constructor method
    public DB()
    {
    
        //Load driver
        try
        { 
          Class.forName(driver);
          System.out.println("Driver found");
        }
        catch (ClassNotFoundException e)
        //Trap the error if the driver is not found
        {
            //System.out.println(e.toString());
            JOptionPane.showMessageDialog(null, 
                    "Error: Database driver not found");
        }
        
        //Create connection
        try
        {
            connection = DriverManager.getConnection(url);
        }
        catch(SQLException e)
        //Trap the error if database connection is not successful
        {
            //System.out.println(e.toString());
            JOptionPane.showMessageDialog(null, "unable to connect");
        }
    }
    
    public ResultSet queryDB(String inStatement) throws SQLException
    //genetic query to execute a select statement
    {
        //query the database
        statement = connection.createStatement();
        System.out.println(inStatement);
        
        resultSet = statement.executeQuery(inStatement);
        //return data as a resultset
        return resultSet;
    }
    public void changeDB(String inStatement) throws SQLException
    //This is the genetic query to execute an INSERT/ UPDATE/ DELETE statement
    {
        try{
            System.out.println(inStatement);
            statement = connection.createStatement();
            //Excecute the SQL statement
            statement.executeUpdate(inStatement);
            statement.close();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Could not alter database");
        }
    }
    
}
