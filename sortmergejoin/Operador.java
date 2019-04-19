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
        
        this.tabela_result = new Tabela(tr_cols);
    }
    
    public void executar() throws CloneNotSupportedException{
        // Todo o código ficará aqui
        Tabela tab1_ordenada = this.ordenarTab(this.tab1, this.chave_tab1, this.t1_cols);
        Tabela tab2_ordenada = this.ordenarTab(this.tab2, this.chave_tab2, this.t2_cols);
    }
    
    public Tabela ordenarTab(Tabela tab, String chave_tab, String[] tab_cols) throws CloneNotSupportedException{
        
        // Pre ordenacao
        int indice_tab = tab.getIndice(chave_tab);
        
        // Criando Tabela ordenada
        Map<String, String> cols_tab_map = tab.getEsquema().getNome_para_indice();
        Tabela tab_ord = new Tabela(tab_cols);
        
        Pagina pag_ord = new Pagina();
        
        // Ordenando páginas individuais da tabela
        for(Pagina pag: tab.getPags()){
            
            pag_ord = pag.clone();
            
            for(Tupla tupla: pag_ord.getTuplas()){
                tupla.setOrdenacao(indice_tab);
            }
            
            Tupla [] tuplas = pag_ord.clone().getTuplas();
            Arrays.sort(tuplas);
            pag_ord.setTuplas(tuplas);
            
            tab_ord.inserirPagina(pag_ord.clone());
            pag_ord = new Pagina();
        }
        
        // Ordenacao mais interna
        int k, l;
        Tabela tab_run = null;
        
        int quant_pags = tab_ord.getQtd_pags(); 
        while(quant_pags > 1){
            
            for(int i=0; i < (int)quant_pags/2; i++){

                Pagina p_i = tab_ord.getPagina(i);
                Pagina p_i1 = tab_ord.getPagina(i+1);
                k=0;
                l=0;

                tab_run = new Tabela(tab_cols);
                String chave;
                while(k < p_i.getQtsTuplasOcup() & l < p_i1.getQtsTuplasOcup()){
                   
                    // tupla de p_i antecede a de p_i1
                    while(p_i.getTupla(k).compareTo(p_i1.getTupla(l))== -1){
                        tab_run.inserirTupla(p_i.getTupla(k).getCols());
                        k++;
                    }
                    
                    // tupla de p_i1 antecede a de p_i
                    while(p_i.getTupla(k).compareTo(p_i1.getTupla(l))== 1){
                        tab_run.inserirTupla(p_i1.getTupla(l).getCols());
                        l++;
                    }
                   
                    // tuplas de p_i e p_i1 são iguais
                    if(p_i.getTupla(k).compareTo(p_i1.getTupla(l))== 0){
                        // Colocar todas as tuplas com essa chave
                        chave = p_i.getTupla(k).getCampo(indice_tab);
                        
                        
                        while(k < p_i.getQtsTuplasOcup() && p_i.getTupla(k).getCampo(indice_tab).equals(chave)){
                            tab_run.inserirTupla(p_i.getTupla(k).getCols());
                            k++;
                        }
                        
                        while(l < p_i1.getQtsTuplasOcup() && p_i1.getTupla(l).getCampo(indice_tab).equals(chave)){
                            tab_run.inserirTupla(p_i1.getTupla(l).getCols());
                            l++;
                        }
                    }
                    
                }   
////                  System.out.println("k: " + k);
//                    System.out.println("l: " + l);
//                    System.out.println("p_i " + p_i.getQtsTuplasOcup());
//                    System.out.println("p_i1 " + p_i1.getQtsTuplasOcup());
//                    System.out.println("ficou no fro");
//                    
                if(k < p_i.getQtsTuplasOcup()){
                    for(int j=k; j < p_i.getQtsTuplasOcup(); j++){
                        tab_run.inserirTupla(p_i.getTupla(k).getCols());
                    }
                }

                if(l < p_i1.getQtsTuplasOcup()){
                    for(int j=l; j < p_i1.getQtsTuplasOcup(); j++){
                        tab_run.inserirTupla(p_i1.getTupla(l).getCols());
                    }
                }
            }
         
            quant_pags = (int)quant_pags/2;
        }
        
        // Pegar resultado
        Tabela tab_result = tab_ord.clone();
        if(tab_run != null){
            Pagina pag = tab_run.getPagina(tab_run.getQtd_pags()-1);
            tab_result = new Tabela(tab_cols);
            tab_result.inserirPagina(pag);
        }
        
        // Exibir resultados
        for(Pagina pag: tab_result.getPags()){
            for(Tupla tupla: pag.getTuplas()){
                System.out.println(Arrays.toString(tupla.getCols()));
            }

        }
        
        return tab_result;
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
