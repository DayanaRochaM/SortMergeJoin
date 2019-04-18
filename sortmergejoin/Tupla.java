package sortmergejoin;

public class Tupla {
    
    private String[] cols;
    
    public Tupla(String[] cols){
        this.cols = cols;
    }

    public String[] getCols() {
        return cols;
    }

    public void setCols(String[] cols) {
        this.cols = cols;
    }
    
}
