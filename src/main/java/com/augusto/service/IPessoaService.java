/**
 * 
 */
package com.augusto.service;

import java.util.List;

import com.augusto.domain.Pessoa;
import com.augusto.exceptions.DAOException;
import com.augusto.services.generic.IGenericService;

/**
 * @author com.augusto
 *
 */
public interface IPessoaService extends IGenericService<Pessoa, Long> {

	Pessoa buscarPorCPF(Long cpf) throws DAOException;

	List<Pessoa> filtrarClientes(String query);

}
