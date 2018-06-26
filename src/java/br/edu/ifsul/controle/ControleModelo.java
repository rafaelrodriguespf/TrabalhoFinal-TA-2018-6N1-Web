/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.MarcaDAO;
import br.edu.ifsul.dao.ModeloDAO;
import br.edu.ifsul.modelo.Marca;
import br.edu.ifsul.modelo.Modelo;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Rafael
 */
@Named(value = "controleModelo")
@ViewScoped
public class ControleModelo implements Serializable {
    
    @EJB
    private ModeloDAO<Modelo> dao;
    private Modelo objeto;
    private Boolean editando;
    
    @EJB
    private MarcaDAO<Marca> daoMarca;
    
    public ControleModelo(){
        editando = false;
    }
    
    public String listar(){
        editando = false;
        return "/privado/modelo/listar?faces-redirect=true";
    }
    
    public void novo(){
        editando = true;
        objeto = new Modelo();
    }
    
    public void alterar(Object id){
        try {
            objeto = dao.getObjectById(id);
            editando = true;
        } catch (Exception e){
            Util.mensagemErro("Erro ao recuperar objeto: " + 
                    Util.getMensagemErro(e));
        } 
    }
    
    public void excluir(Object id){
        try {
            objeto = dao.getObjectById(id);
            dao.remover(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e){
            Util.mensagemErro("Erro ao remover objeto: " + 
                    Util.getMensagemErro(e));
        }
    }
    
    public void salvar(){
        try {
            if (objeto.getId() == null){
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
            editando = false;
        } catch(Exception e){
            Util.mensagemErro("Erro ao persistir objeto: " + 
                    Util.getMensagemErro(e));
        }
    }

    public ModeloDAO<Modelo> getDao() {
        return dao;
    }

    public void setDao(ModeloDAO<Modelo> dao) {
        this.dao = dao;
    }

    public Modelo getObjeto() {
        return objeto;
    }

    public void setObjeto(Modelo objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

    /**
     * @return the daoMarca
     */
    public MarcaDAO<Marca> getDaoMarca() {
        return daoMarca;
    }

    /**
     * @param daoMarca the daoMarca to set
     */
    public void setDaoMarca(MarcaDAO<Marca> daoMarca) {
        this.daoMarca = daoMarca;
    }

    
}
