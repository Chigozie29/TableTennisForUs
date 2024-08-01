package tabletennis;

public class Administrator {
     private int AdID;
    private String AdName;
    private String AdSurname;
    private String AdUsername;
    private String AdPassword;
    
    //constructor
    public Administrator(int AI, String AN, String AS, String AU, String AP){
        AdID = AI;
        AdName = AN;
        AdSurname = AS;
        AdUsername = AU;
        AdPassword = AP;
    }
    
    //accessor
    public int getAdID(){
        return AdID;
    }

    public String getAdName() {
        return AdName;
    }

    public String getAdSurname() {
        return AdSurname;
    }

    public String getAdUsername() {
        return AdUsername;
    }

    public String getAdPassword() {
        return AdPassword;
    }
    
    //toString
    public String toString(){
        return AdID + "\n" + AdName + "\n" + AdSurname + "\n" + AdUsername + "\n" + AdPassword;
    }
}
