package br.edu.ifsul.controle;


import br.edu.ifsul.dao.FuncionarioDAO;
import br.edu.ifsul.dao.PrivilegioDAO;

import br.edu.ifsul.modelo.Funcionario;
import br.edu.ifsul.modelo.Privilegio;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@Named(value = "controleFuncionario")
@ViewScoped
public class ControleFuncionario implements Serializable {
    
    @EJB
    private FuncionarioDAO<Funcionario> dao;
    private Funcionario objeto;
    private Boolean editando;
   
    @EJB
    private PrivilegioDAO<Privilegio> daoPrivilegio;
    private Privilegio privilegio;
    private Boolean editandoPrivilegio;
    
    public ControleFuncionario(){
        editando = false;
    }
    
    public String listar(){
        editando = false;
        return "/privado/funcionario/listar?faces-redirect=true";
    }
    
    public void novo(){
        editando = true;
        editandoPrivilegio = false;
        objeto = new Funcionario();
    }
    
    public void alterar(Object id){
        try {
            objeto = dao.getObjectById(id);
            editando = true;
            editandoPrivilegio = false;
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

    public void novoPrivilegio(){
        editandoPrivilegio = true;
    }
    
    public void salvarPrivilegio(){
        if(objeto.getPrivilegios().contains(privilegio)){
            Util.mensagemErro("Funcionário já possui este Privilegio!");
        } else {
            objeto.getPrivilegios().add(privilegio);
            Util.mensagemInformacao("Privilegio adicionada com sucesso!");
        }
        editandoPrivilegio = false;
    }
    
    public void removerPrivilegio(Privilegio obj){
        objeto.getPrivilegios().remove(obj);
        Util.mensagemInformacao("Privilegio removido com sucesso!");
    }
    
    public FuncionarioDAO<Funcionario> getDao() {
        return dao;
    }

    public void setDao(FuncionarioDAO<Funcionario> dao) {
        this.dao = dao;
    }

    public Funcionario getObjeto() {
        return objeto;
    }

    public void setObjeto(Funcionario objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }


    public PrivilegioDAO<Privilegio> getDaoPrivilegio() {
        return daoPrivilegio;
    }

    public void setDaoPrivilegio(PrivilegioDAO<Privilegio> daoPrivilegio) {
        this.daoPrivilegio = daoPrivilegio;
    }

    public Privilegio getPrivilegio() {
        return privilegio;
    }

    public void setPrivilegio(Privilegio privilegio) {
        this.privilegio = privilegio;
    }

    public Boolean getEditandoPrivilegio() {
        return editandoPrivilegio;
    }

    public void setEditandoPrivilegio(Boolean editandoPrivilegio) {
        this.editandoPrivilegio = editandoPrivilegio;
    }

}
