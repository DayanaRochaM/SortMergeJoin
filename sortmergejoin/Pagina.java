package sortmergejoin;

import java.util.Arrays;

public class Pagina implements Cloneable{
    
    private Tupla[] tuplas = new Tupla[12];
    private int qtsTuplasOcup;

    @Override
    public Pagina clone() throws CloneNotSupportedException {
        return (Pagina) super.clone();
    }
    
    public Tupla getTupla(int indice){
        
//        for(Tupla tupla: Arrays.copyOfRange(tuplas, 0, this.qtsTuplasOcup)){
//            if(tupla.getCampo(indice).equals(valor)){
//                return tupla;
//            }
//        }
        return this.tuplas[indice];
    }
    
    public boolean temEspaco(){
        if(this.qtsTuplasOcup == 12){
            return false;
        }
        return true;
    }
    
    public void adicionarTupla(Tupla tupla){
        this.tuplas[this.qtsTuplasOcup] = tupla;
        this.qtsTuplasOcup++;
    }
    
    public Tupla[] getTuplas() {
        return Arrays.copyOfRange(tuplas, 0, this.qtsTuplasOcup);
    }
    
    public void setTuplas(Tupla[] tuplas) {
        this.tuplas = tuplas;
    }

    public int getQtsTuplasOcup() {
        return qtsTuplasOcup;
    }

    public void setQtsTuplasOcup(int qtsTuplasOcup) {
        this.qtsTuplasOcup = qtsTuplasOcup;
    }
    
}
