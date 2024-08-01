package tabletennis;

public class Participant {
     private int PpID;
    private String PpName;
    private String PpSurname;
    private String PpUsername;
    private String PpPassword;
    
    //constructor
    public Participant(int pi, String pn, String ps, String pu, String pp){
        PpID = pi;
        PpName = pn;
        PpSurname = ps;
        PpUsername = pu;
        PpPassword = pp;
    }
    
    //accessor
    public int getPpID() {
        return PpID;
    }

    public String getPpName() {
        return PpName;
    }

    public String getPpSurname() {
        return PpSurname;
    }

    public String getPpUsername() {
        return PpUsername;
    }

    public String getPpPassword() {
        return PpPassword;
    }
    
    //toString 
    public String toString(){
        return PpID + "\n" + PpName + "\n" + PpSurname + "\n" + PpUsername + 
                "\n" + PpPassword;
        
    }
}
