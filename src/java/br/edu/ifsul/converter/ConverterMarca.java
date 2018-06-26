package br.edu.ifsul.converter;


import br.edu.ifsul.modelo.Marca;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



@FacesConverter(value = "converterMarca")
public class ConverterMarca implements Serializable, Converter {
    
    @PersistenceContext(unitName = "TrabalhoFinal-TA-2018-6N1-WebPU")
    private EntityManager em;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.equals("Selecione um registro")){
            return null;
        }
        return em.find(Marca.class, Integer.parseInt(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null){
            return null;
        }
        Marca obj = (Marca) o;
        return obj.getId().toString();
    }

}
