package sortmergejoin;

import java.util.Arrays;
import java.util.Set;


public class Operador {
    
    private Tabela tabela_result;
    private Tabela tab1;
    private Tabela tab2;
    private String chave_tab1;
    private String chave_tab2;
    //private String [] tr_cols; 
    
    public Operador(Tabela tab1, Tabela tab2, String chave_tab1, String chave_tab2){
        this.tab1 = tab1;
        this.tab2 = tab2;
        this.chave_tab1 = chave_tab1;
        this.chave_tab2 = chave_tab2;
        
        Set<String> cols1 = tab1.getEsquema().getNome_para_indice().keySet();
        String [] t1_cols;
        t1_cols = cols1.toArray(new String[cols1.size()]);
        
        Set<String> cols2 = tab1.getEsquema().getNome_para_indice().keySet();
        String [] t2_cols;
        t2_cols = cols2.toArray(new String[cols2.size()]);
        
        String tr_cols[];
        tr_cols = new String[cols1.size() + cols2.size()];
        
        System.arraycopy(t1_cols, 0, tr_cols, 0, cols1.size());
        
        int index = 0;
        for(int i = cols1.size(); i < cols1.size()+cols2.size(); i++){
            tr_cols[i] = t2_cols[index];
            index++;
        }
        
        System.out.println("Print no operador.");
        System.out.println(Arrays.toString(t1_cols));
        System.out.println(Arrays.toString(t2_cols));
        System.out.println(Arrays.toString(tr_cols));
        this.tabela_result = new Tabela(tr_cols);
    }
    
    public void executar(){
        // Todo o código ficará aqui
    }
    
    public Tabela getTabela_result() {
        return tabela_result;
    }

    public void setTabela_result(Tabela tabela_result) {
        this.tabela_result = tabela_result;
    }

    public Tabela getTab1() {
        return tab1;
    }

    public void setTab1(Tabela tab1) {
        this.tab1 = tab1;
    }

    public Tabela getTab2() {
        return tab2;
    }

    public void setTab2(Tabela tab2) {
        this.tab2 = tab2;
    }

    public String getChave_tab1() {
        return chave_tab1;
    }

    public void setChave_tab1(String chave_tab1) {
        this.chave_tab1 = chave_tab1;
    }

    public String getChave_tab2() {
        return chave_tab2;
    }

    public void setChave_tab2(String chave_tab2) {
        this.chave_tab2 = chave_tab2;
    }
    
    
}
