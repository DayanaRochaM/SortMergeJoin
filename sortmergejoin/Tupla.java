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
        List<String> list = new ArrayList<>();
        list.add(getCampo(ordenacao));
        list.add(o.getCampo(ordenacao));
        Collections.sort(list);
        
        if(getCampo(ordenacao).equals(o.getCampo(ordenacao))){
            return 0;
        }
        else if (getCampo(ordenacao).equals(list.get(0))) {
            return -1;
        }
        else{
            return 1;
        }
    }
}
