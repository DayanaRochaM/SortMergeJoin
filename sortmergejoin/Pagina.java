package sortmergejoin;

public class Pagina {
    
    private Tupla[] tuplas = new Tupla[12];
    private int qtsTuplasOcup;

    public Tupla[] getTuplas() {
        return tuplas;
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
