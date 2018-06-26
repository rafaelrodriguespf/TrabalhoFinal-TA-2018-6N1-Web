package br.edu.ifsul.converter;



import br.edu.ifsul.modelo.Modelo;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



@FacesConverter(value = "converterModelo")
public class ConverterModelo implements Serializable, Converter {
    
    @PersistenceContext(unitName = "TrabalhoFinal-TA-2018-6N1-WebPU")
    private EntityManager em;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.equals("Selecione um registro")){
            return null;
        }
        return em.find(Modelo.class, Integer.parseInt(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null){
            return null;
        }
        Modelo obj = (Modelo) o;
        return obj.getId().toString();
    }

}
