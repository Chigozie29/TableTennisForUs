package tabletennis;

public class Competition {
    private int CpID;
    private String CpTitle;
    private String location;
    private int CpPrize;
    private String CpDate;
    
    //constructor
    public Competition(int CID, String t, String l, int p, String d){
        CpID = CID;
        CpTitle = t;
        location = l;
        CpPrize = p;
        CpDate = d;
        
    }
    
    //accessor
    public int getCpID(){
        return CpID;
    }
    public String getCpTitle(){
        return CpTitle;
    }
    public String getCpLocation(){
        return location;
    }
    public String getCpPrize(){
        return "R" + CpPrize;
    }
    public String getCpDate(){
        return CpDate;
    }
    
    //toString
    public String toString(){
        return CpID + "\n" + CpTitle + "\n" + location
                + "\n" + "R" + CpPrize + "\n" + CpDate;
    }
    
}
