
import sortmergejoin.Operador;
import sortmergejoin.Tabela;


// VOCÊ DEVE INCLUIR OS SEUS PACKAGES AQUI!!!

public class Main {

	public static void main(String[] args) throws CloneNotSupportedException {
		String[] vinho_cols = {"vinho_id", "rotulo", "ano_colheita", "pais_producao_id", "uva_id"};
		String[] uva_cols   = {"uva_id", "nome", "tipo", "pais_origem_id"};
		String[] pais_cols  = {"pais_id", "nome"};

	    Tabela vinho = new Tabela(vinho_cols);
	    Tabela uva   = new Tabela (uva_cols);
	    Tabela pais  = new Tabela (pais_cols);
	    
	    pais.inserirTupla(new String[] {"0", "Brasil"});
	    pais.inserirTupla(new String[] {"1", "Franca"});
	    pais.inserirTupla(new String[] {"2", "Italia"});

	    uva.inserirTupla(new String[] {"0", "uva0", "tinto",  "0"});
	    uva.inserirTupla(new String[] {"1", "uva1", "branco", "1"});
	    uva.inserirTupla(new String[] {"2", "uva2", "tinto",  "2"});           

	    vinho.inserirTupla(new String[] {"0", "vinho0", "1990", "0", "0"});
	    vinho.inserirTupla(new String[] {"1", "vinho1", "1991", "1", "0"});
	    vinho.inserirTupla(new String[] {"2", "vinho2", "1992", "2", "2"});
		
	    

	    // DESCOMENTE O TIPO DO OPERADOR QUE VOCE IRA IMPLEMENTAR!!!

//	    // SELECAO:
//	    Operador op = new Operador(vinho, "rotulo", "uva0"); // representa SELECT * FROM Vinho WHERE rotulo=uva0

	    
//	    // PROJECAO:
//	    String[] projecao_cols = {"vinho_id", "uva_id"};
//	    Operador op = new Operador(vinho, projecao_cols); // representa SELECT vinho_id, uva_id FROM Vinho

	    
//	    // JUNCAO:
            Operador op = new Operador(vinho, uva, "vinho_id", "uva_id"); // representa SELECT * FROM Vinho, Uva WHERE vinho_id=uva_id
            
	    op.executar(); // Realiza a operacao desejada.

	    //int num_tuplas  = op.numTuplasGeradas(); // Retorna a quantidade de tuplas geradas pela operacao.
	    //int num_pags    = op.numPagsGeradas();  // Retorna a quantidade de paginas geradas pela operacao.

	    //String[] tuplas = op.tuplasGeradas(); // Retorna as tuplas geradas pela operacao.
	    
	}

}
