package sortmergejoin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Operador {
    
    private Tabela tabela_result;
    private Tabela tab1;
    private Tabela tab2;
    private String chave_tab1;
    private String chave_tab2;
    private String [] t1_cols;
    private String [] t2_cols;
    
    public Operador(Tabela tab1, Tabela tab2, String chave_tab1, String chave_tab2){
        this.tab1 = tab1;
        this.tab2 = tab2;
        this.chave_tab1 = chave_tab1;
        this.chave_tab2 = chave_tab2;
        
        Set<String> cols1 = tab1.getEsquema().getNome_para_indice().keySet();
        this.t1_cols = cols1.toArray(new String[cols1.size()]);
        
        Set<String> cols2 = tab2.getEsquema().getNome_para_indice().keySet();
        this.t2_cols = cols2.toArray(new String[cols2.size()]);
        
        String tr_cols[];
        tr_cols = new String[cols1.size() + cols2.size()];
        
        System.arraycopy(t1_cols, 0, tr_cols, 0, cols1.size());
        
        int index = 0;
        for(int i = cols1.size(); i < cols1.size()+cols2.size(); i++){
            tr_cols[i] = t2_cols[index];
            index++;
        }
        
//        System.out.println("Print no operador.");
//        System.out.println(Arrays.toString(t1_cols));
//        System.out.println(Arrays.toString(t2_cols));
//        System.out.println(Arrays.toString(tr_cols));
        this.tabela_result = new Tabela(tr_cols);
    }
    
    public void executar() throws CloneNotSupportedException{
        // Todo o código ficará aqui
        this.ordenarTabs();
    }
    
    public void ordenarTabs() throws CloneNotSupportedException{
        
        // Pre ordenacao
        int indice_tab1 = this.tab1.getIndice(this.chave_tab1);
        int indice_tab2 = this.tab2.getIndice(this.chave_tab2);
        
        // Criando Tabela 1 a ser ordenada
        Map<String, String> cols_tab1_map = this.tab1.getEsquema().getNome_para_indice();
        Tabela tab1_ord = new Tabela(this.t1_cols);
        
        // Criando Tabela 2 a ser ordenada
        Map<String, String> cols_tab2_map = this.tab2.getEsquema().getNome_para_indice();
        Tabela tab2_ord = new Tabela(this.t2_cols);

        Pagina pag_ord = new Pagina();
        
        // Ordenando páginas individuais da tabela 1
        for(Pagina pag: tab1.getPags()){
            
            pag_ord = pag.clone();
            
            for(Tupla tupla: pag_ord.getTuplas()){
                tupla.setOrdenacao(indice_tab1);
            }
            
            Tupla [] tuplas = pag_ord.clone().getTuplas();
            Arrays.sort(tuplas);
            pag_ord.setTuplas(tuplas);
            
            tab1_ord.inserirPagina(pag_ord.clone());
            pag_ord = new Pagina();
        }
        
        // Ordenando páginas individuais da tabela 2
        for(Pagina pag: tab2.getPags()){
            
            pag_ord = pag.clone();
            
            for(Tupla tupla: pag_ord.getTuplas()){
                tupla.setOrdenacao(indice_tab2);
            }
            
            Tupla [] tuplas = pag_ord.clone().getTuplas();
            Arrays.sort(tuplas);
            pag_ord.setTuplas(tuplas);
            
            tab2_ord.inserirPagina(pag_ord.clone());
            pag_ord = new Pagina();
        }
       
        // Exibir resultados
        for(Pagina pag: tab1_ord.getPags()){
            for(Tupla tupla: pag.getTuplas()){
                System.out.println(Arrays.toString(tupla.getCols()));
            }
        }
        
        for(Pagina pag: tab2_ord.getPags()){
            for(Tupla tupla: pag.getTuplas()){
                System.out.println(Arrays.toString(tupla.getCols()));
            }
        }
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
