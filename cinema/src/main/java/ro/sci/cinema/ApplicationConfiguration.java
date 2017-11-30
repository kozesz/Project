package ro.sci.cinema;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.sci.cinema.dao.TicketDAO;
import ro.sci.cinema.dao.inmemory.IMTicketDAO;
import ro.sci.cinema.service.TicketService;


@Configuration
public class ApplicationConfiguration {

	@Bean
	public TicketService employeeService() {
		TicketService ts = new TicketService();

		ts.setDao(ticketDAO());
		return ts;
	}
	
	@Bean
	public TicketDAO ticketDAO() {
		return new IMTicketDAO();
	}
}
