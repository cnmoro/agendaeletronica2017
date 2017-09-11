package agenda.servicos;

import agenda.entidades.Contato;
import agenda.entidades.EManager;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class ContatoService implements Serializable {

    private List<Contato> contatos;
    private List<Contato> contatosFiltrados;
    private Contato novoContato = new Contato();
    private Contato contatoSelecionado = new Contato();

    @PostConstruct
    public void init() {
        refreshContatos();
    }

    public void refreshContatos() {
        this.contatos = EManager.getInstance().createNamedQuery("Contato.findAll").getResultList();
    }

    public void selecionaContato(Contato contato) {
        this.contatoSelecionado = contato;
    }

    public void insereContato() {
        if (this.novoContato.getNome().equalsIgnoreCase("") || this.novoContato.getEndereco().equalsIgnoreCase("") || this.novoContato.getEmail().equalsIgnoreCase("")) {
            popupMessage_Erro();
        } else {
            EManager.getInstance().getTransaction().begin();
            EManager.getInstance().persist(this.novoContato);
            EManager.getInstance().getTransaction().commit();
            popupMessage_NovoSucesso(this.novoContato.getNome());
            refreshContatos();
        }
    }

    public void modificaContato() {
        if (this.contatoSelecionado.getNome().equalsIgnoreCase("") || this.contatoSelecionado.getEndereco().equalsIgnoreCase("") || this.contatoSelecionado.getEmail().equalsIgnoreCase("")) {
            popupMessage_Erro();
        } else {
            EManager.getInstance().getTransaction().begin();
            EManager.getInstance().merge(this.contatoSelecionado);
            EManager.getInstance().getTransaction().commit();
            popupMessage_ModificaSucesso(this.contatoSelecionado.getNome());
            refreshContatos();
        }
    }

    public void deleteContato() {
        EManager.getInstance().getTransaction().begin();
        EManager.getInstance().remove(this.contatoSelecionado);
        EManager.getInstance().getTransaction().commit();
        popupMessage_DeletaSucesso(this.contatoSelecionado.getNome());
        refreshContatos();
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void popupMessage_NovoSucesso(String nome) {
        addMessage("Sucesso", nome + " Cadastrado(a)");
    }

    public void popupMessage_ModificaSucesso(String nome) {
        addMessage("Sucesso", nome + " Modificado(a)");
    }

    public void popupMessage_DeletaSucesso(String nome) {
        addMessage("Sucesso", nome + " Excluído(a)");
    }

    public void popupMessage_Erro() {
        addMessage("Erro", "Dados Incorretos ou Faltantes");
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }

    public Contato getNovoContato() {
        return novoContato;
    }

    public void setNovoContato(Contato novoContato) {
        this.novoContato = novoContato;
    }

    public Contato getContatoSelecionado() {
        return contatoSelecionado;
    }

    public void setContatoSelecionado(Contato contatoSelecionado) {
        this.contatoSelecionado = contatoSelecionado;
    }

    public List<Contato> getContatosFiltrados() {
        return contatosFiltrados;
    }

    public void setContatosFiltrados(List<Contato> contatosFiltrados) {
        this.contatosFiltrados = contatosFiltrados;
    }

}
