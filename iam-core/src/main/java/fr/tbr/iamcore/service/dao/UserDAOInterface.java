package fr.tbr.iamcore.service.dao;

import java.util.Collection;

import fr.tbr.iamcore.datamodel.User;
import fr.tbr.iamcore.exception.DAOSaveException;
import fr.tbr.iamcore.exception.DAOSearchException;

public interface UserDAOInterface {
	public Collection<User> Search(User criteria)  throws DAOSearchException ;
	public void Save(User user) throws DAOSaveException;
	public boolean Authenticate(String username, String password) throws DAOSearchException, DAOSaveException;
	}