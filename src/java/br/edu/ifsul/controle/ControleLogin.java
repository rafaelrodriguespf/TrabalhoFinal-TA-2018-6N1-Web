package br.edu.ifsul.controle;

import br.edu.ifsul.dao.FuncionarioDAO;
import br.edu.ifsul.modelo.Funcionario;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named(value = "controleLogin")
@SessionScoped
public class ControleLogin implements Serializable {

    private Funcionario funcionarioAutenticado;
    @EJB
    private FuncionarioDAO<Funcionario> dao;
    private String email;
    private String password;

    public ControleLogin() {
    }

    public String paginaLogin() {
        return "/login?faces-redirect=true";
    }

    public String efetuarLogin() {
        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.login(this.getEmail(), this.getPassword());
            if (request.getUserPrincipal() != null) {
                setFuncionarioAutenticado(getDao().localizaPorEmail(request.getUserPrincipal().getName()));
                Util.mensagemInformacao("Login efetuado com sucesso!");
                setEmail("");
                setPassword("");
            }
            return "/index";
        } catch (Exception e) {
            e.printStackTrace();
            Util.mensagemErro("E-mail ou senha inválidos! "
                    + Util.getMensagemErro(e));
            return "/login";
        }
    }

    public String efetuarLogout() {
        try {
            setFuncionarioAutenticado(null);
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.logout();
        } catch (Exception e) {
            Util.mensagemErro("Erro: " + Util.getMensagemErro(e));
        }
        return "/index";
    }

    /**
     * @return the funcionarioAutenticado
     */
    public Funcionario getFuncionarioAutenticado() {
        return funcionarioAutenticado;
    }

    /**
     * @param funcionarioAutenticado the funcionarioAutenticado to set
     */
    public void setFuncionarioAutenticado(Funcionario funcionarioAutenticado) {
        this.funcionarioAutenticado = funcionarioAutenticado;
    }

    /**
     * @return the dao
     */
    public FuncionarioDAO<Funcionario> getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(FuncionarioDAO<Funcionario> dao) {
        this.dao = dao;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
