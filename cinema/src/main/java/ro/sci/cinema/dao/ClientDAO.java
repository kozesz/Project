package ro.sci.cinema.dao;

import ro.sci.cinema.domain.Client;

import java.util.Collection;

public interface ClientDAO extends BaseDAO<Client> {

    Collection<Client> searchByName(String query);
}
