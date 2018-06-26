package br.edu.ifsul.dao;


import br.edu.ifsul.modelo.Privilegio;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@Stateful
public class PrivilegioDAO<TIPO> extends DAOGenerico<Privilegio> implements Serializable {

    public PrivilegioDAO(){
        super();
        classePersistente = Privilegio.class;
        ordem = "tipo"; // define a ordem padrão do DAO
        maximoObjetos = 3;
    }
}
