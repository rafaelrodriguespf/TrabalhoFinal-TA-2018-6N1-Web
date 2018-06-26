package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AutomovelDAO;
import br.edu.ifsul.dao.CombustivelDAO;
import br.edu.ifsul.dao.FotoDAO;
import br.edu.ifsul.dao.FuncionarioDAO;
import br.edu.ifsul.dao.ModeloDAO;
import br.edu.ifsul.modelo.Automovel;
import br.edu.ifsul.modelo.Combustivel;
import br.edu.ifsul.modelo.Foto;
import br.edu.ifsul.modelo.Funcionario;
import br.edu.ifsul.modelo.Modelo;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;


import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;

import org.apache.commons.io.IOUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@Named(value = "controleAutomovel")
@SessionScoped
public class ControleAutomovel implements Serializable {

    @EJB
    private AutomovelDAO<Automovel> dao;
    private Automovel objeto;
    private Boolean editando;
    private byte[] ft;
    @EJB
    private FuncionarioDAO<Funcionario> daoFuncionario;
    @EJB
    private ModeloDAO<Modelo> daoModelo;
    @EJB
    private CombustivelDAO<Combustivel> daoCombustivel;

    @EJB
    private FotoDAO<Foto> daoFoto;
    private Foto foto;
    private Boolean editandoFoto;

    public ControleAutomovel() {
        editando = false;

    }

    public String listar() {
        editando = false;
        return "/privado/automovel/listar?faces-redirect=true";
    }

    public void novo() {
        editando = true;
        editandoFoto = false;
        objeto = new Automovel();
    }

    public void alterar(Object id) {
        try {
            objeto = dao.getObjectById(id);
            editando = true;
            editandoFoto = false;
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: "
                    + Util.getMensagemErro(e));
        }
    }

    public void excluir(Object id) {
        try {
            objeto = dao.getObjectById(id);
            dao.remover(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e) {
            Util.mensagemErro("Erro ao remover objeto: "
                    + Util.getMensagemErro(e));
        }
    }

    public void salvar() {
        try {
            if (objeto.getId() == null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
            editando = false;
        } catch (Exception e) {
            Util.mensagemErro("Erro ao persistir objeto: "
                    + Util.getMensagemErro(e));
        }
    }

    public void enviarFoto(FileUploadEvent event) {
        try {

            this.ft = IOUtils.toByteArray(event.getFile().getInputstream());
            
        } catch (Exception e) {
            System.out.println("" + e);
        }
    }

    public void novaFoto() {
        if(objeto.getFotos().size() ==1){
            Util.mensagemErro("Permitido somente uma Foto!");
        }else{
            editandoFoto = true;
        }
        
    }

    public void salvarFoto() {
        if (this.ft != null) {
            Foto f = new Foto();
            f.setAutomovel(objeto);
            f.setArquivo(this.ft);
            objeto.getFotos().add(f);
            Util.mensagemInformacao("Foto adicionada com sucesso!");
        }
        editandoFoto = false;
    }

    public StreamedContent getImagemDinamica() {
        String strid = FacesContext.getCurrentInstance().getExternalContext()
                .getRequestParameterMap().get("id_imagem");
        if (strid != null) {
            Integer id = Integer.parseInt(strid);
            Automovel obj = dao.localizaPorId(id);
            List<Foto> f = new ArrayList<>();
            f = obj.getFotos();
            for (int i = 0; i < f.size(); i++) {
               return new DefaultStreamedContent(new ByteArrayInputStream(f.get(i).getArquivo()));
		 
		}
        
        }
        return new DefaultStreamedContent();
    }

    public void removerFoto(Foto obj) {
        objeto.getFotos().remove(obj);
        Util.mensagemInformacao("Foto removido com sucesso!");
    }

    public AutomovelDAO<Automovel> getDao() {
        return dao;
    }

    public void setDao(AutomovelDAO<Automovel> dao) {
        this.dao = dao;
    }

    public Automovel getObjeto() {
        return objeto;
    }

    public void setObjeto(Automovel objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

    /**
     * @return the daoFuncionario
     */
    public FuncionarioDAO<Funcionario> getDaoFuncionario() {
        return daoFuncionario;
    }

    /**
     * @param daoFuncionario the daoFuncionario to set
     */
    public void setDaoFuncionario(FuncionarioDAO<Funcionario> daoFuncionario) {
        this.daoFuncionario = daoFuncionario;
    }

    /**
     * @return the daoModelo
     */
    public ModeloDAO<Modelo> getDaoModelo() {
        return daoModelo;
    }

    /**
     * @param daoModelo the daoModelo to set
     */
    public void setDaoModelo(ModeloDAO<Modelo> daoModelo) {
        this.daoModelo = daoModelo;
    }

    /**
     * @return the daoCombustivel
     */
    public CombustivelDAO<Combustivel> getDaoCombustivel() {
        return daoCombustivel;
    }

    /**
     * @param daoCombustivel the daoCombustivel to set
     */
    public void setDaoCombustivel(CombustivelDAO<Combustivel> daoCombustivel) {
        this.daoCombustivel = daoCombustivel;
    }

    /**
     * @return the daoFoto
     */
    public FotoDAO<Foto> getDaoFoto() {
        return daoFoto;
    }

    /**
     * @param daoFoto the daoFoto to set
     */
    public void setDaoFoto(FotoDAO<Foto> daoFoto) {
        this.daoFoto = daoFoto;
    }

    /**
     * @return the foto
     */
    public Foto getFoto() {
        return foto;
    }

    /**
     * @param foto the foto to set
     */
    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    /**
     * @return the editandoFoto
     */
    public Boolean getEditandoFoto() {
        return editandoFoto;
    }

    /**
     * @param editandoFoto the editandoFoto to set
     */
    public void setEditandoFoto(Boolean editandoFoto) {
        this.editandoFoto = editandoFoto;
    }

    /**
     * @return the ft
     */
    public byte[] getFt() {
        return ft;
    }

    /**
     * @param ft the ft to set
     */
    public void setFt(byte[] ft) {
        this.ft = ft;
    }

}
