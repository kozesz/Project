package ro.sci.cinema.dao;
import ro.sci.cinema.domain.Ticket;
import java.util.Collection;

public interface TicketDAO extends BaseDAO<Ticket>{

	Collection<Ticket> searchByName(String query);
}
