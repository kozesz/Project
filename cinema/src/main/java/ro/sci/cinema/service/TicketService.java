package ro.sci.cinema.service;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import ro.sci.cinema.dao.TicketDAO;
import ro.sci.cinema.domain.Ticket;


public class TicketService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TicketService.class);

    private TicketDAO dao;

    public Collection<Ticket> listAll() {
        return dao.getAll();
    }

    public Collection<Ticket> search(String query) {
        LOGGER.debug("Searching for " + query);
        return dao.searchByName(query);
    }

    public boolean delete(Long id) {
        LOGGER.debug("Deleting ticket for id: " + id);
        Ticket ticket = dao.findById(id);
        if (ticket != null) {
            dao.delete(ticket);
            return true;
        }
        return false;
    }

    public Ticket get(Long id) {
        LOGGER.debug("Getting ticket for id: " + id);
        return dao.findById(id);

    }

    public void save(Ticket ticket) throws ValidationException {
        LOGGER.debug("Saving: " + ticket);
        validate(ticket);

        dao.update(ticket);
    }

    private void validate(Ticket ticket) throws ValidationException {
        Date currentDate = new Date();
        List<String> errors = new LinkedList<String>();

        if (ticket.getClient() == null) {
            errors.add("Client is Empty");
        }

        if (ticket.getType() == null) {
            errors.add("TicketType is Empty");
        }

        if (ticket.getMovie() == null) {
            errors.add("Movie is Empty");
        }


        /*if (ticket.getDate() == null) {
            errors.add("Date is Empty");
        } else {
            if (currentDate.after(ticket.getDate())) {
                errors.add("Date is in the past");
            }
        }*/

        if (!errors.isEmpty()) {
            throw new ValidationException(errors.toArray(new String[]{}));
        }
    }

    public TicketDAO getDao() {
        return dao;
    }

    public void setDao(TicketDAO dao) {
        this.dao = dao;
    }


}
