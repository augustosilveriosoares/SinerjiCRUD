/**
 * 
 */
package com.augusto.controller;

import java.io.Serializable;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.augusto.domain.Pessoa;
import com.augusto.service.IPessoaService;
import com.augusto.utils.ReplaceUtils;

/**
 * @author com.augusto
 *
 */
@Named
@ViewScoped
public class PessoaController implements Serializable {

	private static final long serialVersionUID = 8030245985235567808L;
	
	private Pessoa pessoa;
	
	private Collection<Pessoa> clientes;
	
	@Inject
	private IPessoaService clienteService;
	
	private Boolean isUpdate;
	
	private String cpfMask;
	
	private String telMask;
	
	@PostConstruct
    public void init() {
		try {
			this.isUpdate = false;
			this.pessoa = new Pessoa();
			this.clientes = clienteService.buscarTodos();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Erro ao tentar listar os clientes"));
		}
	}
	
	public void cancel() {
		try {
			this.isUpdate = false;
			this.pessoa = new Pessoa();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Erro ao tentar cancelar ação"));
		}
		
    } 
	
	public void edit(Pessoa cliente) {
		try {
			this.isUpdate = true;
			this.pessoa = cliente;
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Erro ao tentar excluir o cliente"));
		}
		
    } 
	
	public void delete(Pessoa cliente) {
		try {
			clienteService.excluir(cliente);
			clientes.remove(cliente);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Erro ao tentar excluir o cliente"));
		}
		
    } 
	
	public void add() {
		try {
			removerCaracteresInvalidos();
			limparCampos();
			clienteService.cadastrar(pessoa);
			this.clientes = clienteService.buscarTodos();
			this.pessoa = new Pessoa();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Erro ao tentar criar o cliente"));
		}
		
        
    }

    private void removerCaracteresInvalidos() {
    	Long cpf = Long.valueOf(ReplaceUtils.replace(getCpfMask(), ".", "-"));
    	this.pessoa.setCpf(cpf);
    	
    	Long tel = Long.valueOf(ReplaceUtils.replace(getTelMask(), "(", ")", " ", "-"));
    	this.pessoa.setTel(tel);
	}
    
    private void limparCampos() {
    	setCpfMask(null);
    	setTelMask(null);
    }

	public void update() {
    	try {
    		removerCaracteresInvalidos();
			clienteService.alterar(this.pessoa);
			cancel();
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Cliente Atualiado com sucesso"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Erro ao tentar atualizar o cliente"));
		}
        
    }
	
	public String voltarTelaInicial() {
		return "/index.xhtml"; 
	}

	public Pessoa getCliente() {
		return pessoa;
	}

	public void setCliente(Pessoa cliente) {
		this.pessoa = cliente;
	}

	public Collection<Pessoa> getClientes() {
		return clientes;
	}

	public void setClientes(Collection<Pessoa> clientes) {
		this.clientes = clientes;
	}

	public Boolean getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public String getCpfMask() {
		return cpfMask;
	}

	public void setCpfMask(String cpfMask) {
		this.cpfMask = cpfMask;
	}

	public String getTelMask() {
		return telMask;
	}

	public void setTelMask(String telMask) {
		this.telMask = telMask;
	}
	
	

}
