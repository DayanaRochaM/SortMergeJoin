package sortmergejoin;

import java.util.HashMap;
import java.util.Map;

public class Esquema {
    
    private int qtd_cols;
    private Map<String, String> nome_para_indice = new HashMap<String, String>();

    public int getIndice(String chave){
        return Integer.parseInt(this.nome_para_indice.get(chave));
    }
    
    public int getQtd_cols() {
        return qtd_cols;
    }

    public void setQtd_cols(int qtd_cols) {
        this.qtd_cols = qtd_cols;
    }

    public Map<String, String> getNome_para_indice() {
        return nome_para_indice;
    }

    public void setNome_para_indice(Map<String, String> nome_para_indice) {
        this.nome_para_indice = nome_para_indice;
    }
   
}
