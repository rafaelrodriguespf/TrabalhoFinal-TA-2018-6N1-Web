package br.edu.ifsul.dao;


import br.edu.ifsul.modelo.Foto;
import java.io.Serializable;
import javax.ejb.Stateful;


@Stateful
public class FotoDAO<TIPO> extends DAOGenerico<Foto> implements Serializable {

    public FotoDAO(){
        super();
        classePersistente = Foto.class;
        ordem = "id"; // define a ordem padrão do DAO
        maximoObjetos = 3;
    }
}
