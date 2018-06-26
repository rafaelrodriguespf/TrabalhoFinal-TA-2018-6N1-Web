package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Funcionario;
import java.io.Serializable;
import javax.ejb.Stateful;
import javax.persistence.Query;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@Stateful
public class FuncionarioDAO<TIPO> extends DAOGenerico<Funcionario> implements Serializable {

    public FuncionarioDAO(){
        super();
        classePersistente = Funcionario.class;
        ordem = "nome"; // define a ordem padrão do DAO
        maximoObjetos = 3;
    }
    
    public Funcionario getObjectById(Object id) throws Exception {
        Funcionario obj = em.find(Funcionario.class, id);
        /**
         * A linha obj.getPermissoes().size(); é necessária para inicializar a coleção
         * para quando ela for exibida na tela não gerar um erro de 
         * lazyInicializationException
         */
        obj.getPrivilegios().size(); 
        return obj;
    }   
    
    public Funcionario localizaPorEmail(String email){
        Query query = em.createQuery("from Funcionario where upper(email) = :email");
        query.setParameter("email",email.toUpperCase());
        Funcionario obj = (Funcionario) query.getSingleResult();
        obj.getPrivilegios().size();
        return obj;
    }
}
