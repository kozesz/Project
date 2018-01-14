package ro.sci.cinema.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.sci.cinema.dao.ClientDAO;
import ro.sci.cinema.domain.*;

import java.util.Collection;

public class ClientService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientService.class);

    private ClientDAO clientdao;

    public Collection<Client> listAll() {
        return clientdao.getAll();
    }

    public Collection<Client> search( String query) {
        LOGGER.debug("Searching for " + query);
        return clientdao.searchByName(query);
    }

    public boolean delete(Long id) {
        LOGGER.debug("Deleting client for id: " + id);
        Client client = clientdao.findById(id);
        if (client != null) {
            clientdao.delete(client);
            return true;
        }
        return false;
    }

    public Client get(Long id) {
        LOGGER.debug("Getting client for id: " + id);
        return clientdao.findById(id);

    }

    public void save(Client client) throws ValidationException {
        LOGGER.debug("Saving: " + client);
        validate(client);

        clientdao.update(client);
    }

    private void validate(Client client) throws ValidationException {}


    public ClientDAO getClientdao() {
        return clientdao;
    }

    public void setClientdao(ClientDAO clientdao) {
        this.clientdao = clientdao;
    }
}
