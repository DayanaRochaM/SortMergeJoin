package sortmergejoin;

public class Tupla implements Comparable<Tupla>, Cloneable{
    
    private String[] cols;
    private int ordenacao;

    @Override
    public Tupla clone() throws CloneNotSupportedException {
        return (Tupla) super.clone();
    }
    
    public int getOrdenacao() {
        return ordenacao;
    }

    public void setOrdenacao(int ordenacao) {
        this.ordenacao = ordenacao;
    }
    
    public Tupla(String[] cols){
        this.cols = cols;
    }
    
    public String getCampo(int indice){
        return this.cols[indice];
    }
    
    public String[] getCols() {
        return cols;
    }

    public void setCols(String[] cols) {
        this.cols = cols;
    }

    @Override
    public int compareTo(Tupla o) {
        return getCampo(ordenacao).compareTo(o.getCampo(o.getOrdenacao()));
    }
}
