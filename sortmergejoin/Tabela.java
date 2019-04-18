package sortmergejoin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tabela {
    
    private List<Pagina> pags;
    private int qtd_pags;
    private Esquema esquema;
    
    Tabela(String[] cols){
        esquema = new Esquema();
        esquema.setQtd_cols(cols.length);
        Map<String, String> h = new HashMap<String, String>();
        for(int i=0; i<cols.length; i++){
            h.put(cols[i], Integer.toString(i));
        }
    }
    
    public void inserirTupla(String [] cols){
        
        if(this.pags == null){
            this.pags = new ArrayList();
            Pagina pag = new Pagina();
            this.pags.add(pag);
        }
        
        Tupla t = new Tupla(cols);
        Pagina p_ult = this.pags.get(qtd_pags);
        // Sempre inserir na última página disponível
        if (p_ult.temEspaco()){
            p_ult.adicionarTupla(t);
        }else{
            Pagina p_new = new Pagina();
            p_new.adicionarTupla(t);
        }
    }
    
    public List<Pagina> getPags() {
        return pags;
    }

    public void setPags(List<Pagina> pags) {
        this.pags = pags;
    }

    public int getQtd_pags() {
        return qtd_pags;
    }

    public void setQtd_pags(int qtd_pags) {
        this.qtd_pags = qtd_pags;
    }

    public Esquema getEsquema() {
        return esquema;
    }

    public void setEsquema(Esquema esquema) {
        this.esquema = esquema;
    }
   
}
