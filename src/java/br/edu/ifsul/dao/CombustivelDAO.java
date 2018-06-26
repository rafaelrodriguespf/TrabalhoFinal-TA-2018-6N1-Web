package br.edu.ifsul.dao;


import br.edu.ifsul.modelo.Combustivel;
import java.io.Serializable;
import javax.ejb.Stateful;


@Stateful
public class CombustivelDAO<TIPO> extends DAOGenerico<Combustivel> implements Serializable {

    public CombustivelDAO(){
        super();
        classePersistente = Combustivel.class;
        ordem = "nome";
    }
}
