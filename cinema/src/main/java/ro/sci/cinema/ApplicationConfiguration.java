package ro.sci.cinema;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.sci.cinema.dao.*;
import ro.sci.cinema.dao.inmemory.*;
import ro.sci.cinema.service.*;
import ro.sci.cinema.domain.*;


@Configuration
public class ApplicationConfiguration {

	@Bean
	public TicketService ticketService() {
		TicketService ts = new TicketService();

		ts.setDao(ticketDAO());
		return ts;
	}
	
	@Bean
	public TicketDAO ticketDAO() {
		return new IMTicketDAO();
	}

	@Bean
	public CinemaHallService cinemaHallService() {
		CinemaHallService chs = new CinemaHallService();

		chs.setChdao(cinemaHallDAO());
		return chs;
	}

	@Bean
	public CinemaHallDAO cinemaHallDAO() {
		return new IMCinemaHallDAO();
	}

	@Bean
	public ClientService clientService() {
		ClientService clients = new ClientService();

		clients.setClientdao(clientDAO());
		return clients;
	}

	@Bean
	public ClientDAO clientDAO() {
		return new IMClientDAO();
	}

	@Bean
	public MovieService movieService() {
		MovieService ms = new MovieService();

		ms.setMdao(movieDAO());
		return ms;
	}

	@Bean
	public MovieDAO movieDAO() {
		return new IMMovieDAO();
	}

	@Bean
	public ProgramService programService() {
		ProgramService ps = new ProgramService();

		ps.setPdao(programDAO());
		return ps;
	}

	@Bean
	public ProgramDAO programDAO() {
		return new IMProgramDAO();
	}

	@Bean
	public SeatService seatService() {
		SeatService ss = new SeatService();

		ss.setSdao(seatDAO());
		return ss;
	}

	@Bean
	public SeatDAO seatDAO() {
		return new IMSeatDAO();
	}

}
