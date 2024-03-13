/**
 * 
 */
package com.augusto.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import com.augusto.dao.generic.GenericDAO;
import com.augusto.domain.Pessoa;

/**
 * @author com.augusto
 *
 */
public class PessoaDAO extends GenericDAO<Pessoa, Long> implements IPessoaDAO {

	public PessoaDAO() {
		super(Pessoa.class);
	}

	@Override
	public List<Pessoa> filtrarClientes(String query) {
		TypedQuery<Pessoa> tpQuery = 
				this.entityManager.createNamedQuery("Cliente.findByNome", this.persistenteClass);
		tpQuery.setParameter("nome", "%" + query + "%");
        return tpQuery.getResultList();
		
	}

}
