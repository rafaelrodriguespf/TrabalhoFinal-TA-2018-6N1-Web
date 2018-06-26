package br.edu.ifsul.dao;


import br.edu.ifsul.modelo.Marca;
import java.io.Serializable;
import javax.ejb.Stateful;


@Stateful
public class MarcaDAO<TIPO> extends DAOGenerico<Marca> implements Serializable {

    public MarcaDAO(){
        super();
        classePersistente = Marca.class;
        ordem = "nome";
    }
}
