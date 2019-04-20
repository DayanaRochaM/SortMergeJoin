package sortmergejoin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tupla implements Comparable<Tupla>{
    
    private String[] cols;
    private int ordenacao;

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
        return getCampo(ordenacao).compareTo(o.getCampo(ordenacao));
    }
}
