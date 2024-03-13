/**
 * 
 */
package com.augusto.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.augusto.dao.IPessoaDAO;
import com.augusto.domain.Pessoa;
import com.augusto.exceptions.DAOException;
import com.augusto.exceptions.MaisDeUmRegistroException;
import com.augusto.exceptions.TableException;
import com.augusto.services.generic.GenericService;

/**
 * @author com.augusto
 *
 */
@Stateless
public class PessoaService extends GenericService<Pessoa, Long> implements IPessoaService {
	
	private IPessoaDAO clienteDAO;
	
	@Inject
	public PessoaService(IPessoaDAO clienteDAO) {
		super(clienteDAO);
		this.clienteDAO = clienteDAO;
	}

	@Override
	public Pessoa buscarPorCPF(Long cpf) throws DAOException {
		try {
			return this.dao.consultar(cpf);
		} catch (MaisDeUmRegistroException | TableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Pessoa> filtrarClientes(String query) {
		return clienteDAO.filtrarClientes(query);
	}

}
