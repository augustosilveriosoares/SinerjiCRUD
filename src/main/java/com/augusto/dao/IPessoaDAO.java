/**
 * 
 */
package com.augusto.dao;

import java.util.List;

import com.augusto.dao.generic.IGenericDAO;
import com.augusto.domain.Pessoa;

/**
 * @author com.augusto
 *
 */
public interface IPessoaDAO extends IGenericDAO<Pessoa, Long>{

	List<Pessoa> filtrarClientes(String query);

}
