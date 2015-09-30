package br.com.fabricadeprogramador.service;

import br.com.fabricadeprogramador.dao.DAOException;

public class ServiceException extends Exception {

	public ServiceException(String msg) {
		super(msg);
	}

	public ServiceException(DAOException e) {
		super(e);
	}

}
