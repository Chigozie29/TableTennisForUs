package tabletennis;

import java.sql.*;

public class CompetitionManager {
    //Declare variables
    public static DB db = new DB();
    public ResultSet rs ;
    public Competition competition[] = new Competition[1000];
    private int size = 1;
   
    //constructor
    public CompetitionManager() throws SQLException{
        String t, l;
        int cID , p;
        String d;
        
        //Extract all competition in database
        ResultSet rs = CompetitionManager.db.queryDB("SELECT * FROM tblCompetition;");
        
        while(rs.next()){
            cID = Integer.parseInt(rs.getString("CpID"));
            t = rs.getString("CpTitle");
            l = rs.getString("CpLocation");
            p = Integer.parseInt(rs.getString("CpPrize"));
            d = (rs.getString("CpDate")).substring(0,10);
            
           
            competition[size] = new Competition(cID, t, l, p, d);
            size++;
        }
            
    }
    
}

