package tabletennis;


public class Result {
    private int RsID;
    private int CpID;
    private int PpID;
    private int TotalPoints;
    
    //constructor
    public Result(int ri, int ci, int pi, int tp){
        RsID = ri;
        CpID = ci;
        PpID = pi;
        TotalPoints = tp;
    }
    
    //accessor
    public int getRsID(){
        return RsID;
    }
    public int getCpID(){
        return CpID;
    }
    public int getPpID(){
        return PpID;
    }
    public int getTotalPoints(){
        return TotalPoints;
    }
    
    //toString
    public String toString(){
        return RsID + "\n" + CpID + "\n" + PpID + "\n" + TotalPoints;
    }
}
