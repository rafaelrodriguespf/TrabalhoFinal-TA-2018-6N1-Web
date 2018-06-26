package br.edu.ifsul.dao;


import br.edu.ifsul.modelo.Automovel;
import java.io.Serializable;
import javax.ejb.Stateful;
import javax.persistence.Query;


@Stateful
public class AutomovelDAO<TIPO> extends DAOGenerico<Automovel> implements Serializable {

    public AutomovelDAO(){
        super();
        classePersistente = Automovel.class;
        ordem = "id";
    }
    public Automovel localizaPorId(Integer id){
        Query query = em.createQuery("from Automovel where id = :id");
        query.setParameter("id",id);
        Automovel obj = (Automovel) query.getSingleResult();
        obj.getFotos().size();
        return obj;
    }
}
