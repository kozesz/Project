package ro.sci.cinema.dao.inmemory;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.util.StringUtils;
import ro.sci.cinema.dao.TicketDAO;
import ro.sci.cinema.domain.Ticket;


public class IMTicketDAO extends IMBaseDAO<Ticket> implements TicketDAO {


	public Collection<Ticket> searchByName(String query) {
		if (StringUtils.isEmpty(query)) {
			return getAll();
		}
		
		Collection<Ticket> all = new LinkedList<Ticket>(getAll());
		for (Iterator<Ticket> it = all.iterator(); it.hasNext();) {
			Ticket ticket = it.next();
			String ss = ticket.getClient() + " " + ticket.getMovie();
			if (!ss.toLowerCase().contains(query.toLowerCase())) {
				it.remove();
			}
		}
		return all;
	}

	

}
