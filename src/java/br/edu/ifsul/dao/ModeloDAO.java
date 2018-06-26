package br.edu.ifsul.dao;



import br.edu.ifsul.modelo.Modelo;
import java.io.Serializable;
import javax.ejb.Stateful;


@Stateful
public class ModeloDAO<TIPO> extends DAOGenerico<Modelo> implements Serializable {

    public ModeloDAO(){
        super();
        classePersistente = Modelo.class;
        ordem = "nome";
    }
}
