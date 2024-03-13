/**
 * 
 */
package com.augusto.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 * @author com.augusto
 *
 */
@Named
@ViewScoped
public class IndexController implements Serializable {

	private static final long serialVersionUID = -784519597996507487L;

	public String redirectPessoa() {
		return "/pessoa/list.xhtml";
	}
	

}
