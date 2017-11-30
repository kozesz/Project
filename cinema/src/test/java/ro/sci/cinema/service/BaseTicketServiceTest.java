package ro.sci.cinema.service;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import org.junit.After;
import org.junit.Assert;

import org.junit.Test;
import ro.sci.cinema.domain.*;


public abstract class BaseTicketServiceTest {

	/*protected abstract TicketService getTicketService();

	@After
	public void tearDown() {
		Collection<Ticket> tickets = new LinkedList<Ticket>(getTicketService().listAll());
		for (Ticket ticket : tickets) {
			getTicketService().delete(ticket.getId());
		}
	}

	@Test
	public void test_empty_get_all() {
		Collection<Ticket> tickets = getTicketService().listAll();
		Assert.assertTrue(tickets.isEmpty());
	}

	@Test(expected = ValidationException.class)
	public void test_add_no_client() throws ValidationException {
		Ticket ticket = new Ticket();
		ticket.setCinema(Cinema.FLORIN_PIERSIC);
		ticket.setDate(new Date());
		ticket.setMovie(new Movie("Star Wars"));
		ticket.setSeat("A1");
		ticket.setType(TicketType.ADULT);
		getTicketService().save(ticket);
	}

	@Test(expected = ValidationException.class)
	public void test_add_no_cinema() throws ValidationException {
        Ticket ticket = new Ticket();
        ticket.setClient(new Client("Ana", "Pop"));
        ticket.setDate(new Date());
        ticket.setMovie(new Movie("Star Wars"));
        ticket.setSeat("A1");
        ticket.setType(TicketType.ADULT);
        getTicketService().save(ticket);
	}

    @Test(expected = ValidationException.class)
    public void test_add_no_date() throws ValidationException {
        Ticket ticket = new Ticket();
        ticket.setClient(new Client("Ana", "Pop"));
        ticket.setCinema(Cinema.FLORIN_PIERSIC);
        ticket.setMovie(new Movie("Star Wars"));
        ticket.setSeat("A1");
        ticket.setType(TicketType.ADULT);
        getTicketService().save(ticket);
    }

    @Test(expected = ValidationException.class)
    public void test_add_no_movie() throws ValidationException {
        Ticket ticket = new Ticket();
        ticket.setClient(new Client("Ana", "Pop"));
        ticket.setCinema(Cinema.FLORIN_PIERSIC);
        ticket.setDate(new Date());
        ticket.setSeat("A1");
        ticket.setType(TicketType.ADULT);
        getTicketService().save(ticket);
    }

    @Test(expected = ValidationException.class)
    public void test_add_no_seat() throws ValidationException {
        Ticket ticket = new Ticket();
        ticket.setClient(new Client("Ana", "Pop"));
        ticket.setCinema(Cinema.FLORIN_PIERSIC);
        ticket.setDate(new Date());
        ticket.setMovie(new Movie("Star Wars"));
        ticket.setType(TicketType.ADULT);
        getTicketService().save(ticket);
    }

    @Test(expected = ValidationException.class)
    public void test_add_no_ticket_type() throws ValidationException {
        Ticket ticket = new Ticket();
        ticket.setClient(new Client("Ana", "Pop"));
        ticket.setCinema(Cinema.FLORIN_PIERSIC);
        ticket.setDate(new Date());
        ticket.setMovie(new Movie("Star Wars"));
        ticket.setSeat("A1");
        getTicketService().save(ticket);
    }



	@Test(expected = ValidationException.class)
	public void test_add_empty() throws ValidationException {
		Ticket ticket = new Ticket();
        getTicketService().save(ticket);
		}

	@Test
	public void test_add_valid_ticket() throws ValidationException {
        Ticket ticket = new Ticket();
        ticket.setClient(new Client("Ana", "Pop"));
        ticket.setCinema(Cinema.FLORIN_PIERSIC);
        ticket.setDate(new Date(17, 12, 12));
        ticket.setMovie(new Movie("Star Wars"));
        ticket.setSeat("A1");
        ticket.setType(TicketType.ADULT);
        getTicketService().save(ticket);

		Assert.assertEquals(1, getTicketService().listAll().size());
		Ticket fromDB = getTicketService().listAll().iterator().next();
		Assert.assertNotNull(fromDB);
		Assert.assertTrue(fromDB.getId() > 0);
		ticket.setId(fromDB.getId());
		Assert.assertEquals(ticket, fromDB);
	}

	@Test
	public void test_delete_inexistent() throws ValidationException {

		Assert.assertFalse(getTicketService().delete(-10l));

	}

	@Test
	public void test_add_delete() throws Exception {
        Ticket ticket = new Ticket();
        ticket.setClient(new Client("Ana", "Pop"));
        ticket.setCinema(Cinema.FLORIN_PIERSIC);
        ticket.setDate(new Date(16, 12, 12));
        ticket.setMovie(new Movie("Star Wars"));
        ticket.setSeat("A1");
        ticket.setType(TicketType.ADULT);
        getTicketService().save(ticket);

		Assert.assertEquals(1, getTicketService().listAll().size());
		Ticket fromDB = getTicketService().listAll().iterator().next();

		Assert.assertTrue(getTicketService().delete(fromDB.getId()));
		Assert.assertFalse(getTicketService().delete(fromDB.getId()));
		Assert.assertEquals(0, getTicketService().listAll().size());

	}

	@Test
	public void test_search_by_name_no_result() throws ValidationException {
        Ticket ticket = new Ticket();
        ticket.setClient(new Client("Ana", "Pop"));
        ticket.setCinema(Cinema.FLORIN_PIERSIC);
        ticket.setDate(new Date(16, 12, 12));
        ticket.setMovie(new Movie("Star Wars"));
        ticket.setSeat("A1");
        ticket.setType(TicketType.ADULT);
        getTicketService().save(ticket);

		Assert.assertEquals(0, getTicketService().search("cucu").size());

	}

	@Test
	public void test_search_by_name_multiple_results() throws ValidationException {
        Ticket ticket = new Ticket();
        ticket.setClient(new Client("Ana", "Pop"));
        ticket.setCinema(Cinema.FLORIN_PIERSIC);
        ticket.setDate(new Date(16, 12, 12));
        ticket.setMovie(new Movie("Star Wars"));
        ticket.setSeat("A1");
        ticket.setType(TicketType.ADULT);
        getTicketService().save(ticket);

        Ticket ticket2 = new Ticket();
        ticket2.setClient(new Client("Mircea", "Ionescu"));
        ticket2.setCinema(Cinema.IULIUS_MALL_CLUJ);
        ticket2.setDate(new Date(16, 12, 21));
        ticket2.setMovie(new Movie("Star Wars"));
        ticket2.setSeat("C5");
        ticket2.setType(TicketType.ADULT);
        getTicketService().save(ticket2);

		Assert.assertEquals(2, getTicketService().search("Star Wars").size());

	}

	@Test
	public void test_search_by_name_multiple_results_partial_search() throws ValidationException {
        Ticket ticket = new Ticket();
        ticket.setClient(new Client("Ana", "Pop"));
        ticket.setCinema(Cinema.FLORIN_PIERSIC);
        ticket.setDate(new Date(16, 12, 12));
        ticket.setMovie(new Movie("Star Wars"));
        ticket.setSeat("A1");
        ticket.setType(TicketType.ADULT);
        getTicketService().save(ticket);

        Ticket ticket2 = new Ticket();
        ticket2.setClient(new Client("Mircea", "Ionescu"));
        ticket2.setCinema(Cinema.IULIUS_MALL_CLUJ);
        ticket2.setDate(new Date(16, 12, 21));
        ticket2.setMovie(new Movie("Star Wars"));
        ticket2.setSeat("C5");
        ticket2.setType(TicketType.ADULT);
        getTicketService().save(ticket2);

        Assert.assertEquals(2, getTicketService().search("Star").size());

	}

	@Test
	public void test_search_by_name_multiple_results_partial_case_insensitive() throws ValidationException {
        Ticket ticket = new Ticket();
        ticket.setClient(new Client("Ana", "Pop"));
        ticket.setCinema(Cinema.FLORIN_PIERSIC);
        ticket.setDate(new Date(16, 12, 12));
        ticket.setMovie(new Movie("Star Wars"));
        ticket.setSeat("A1");
        ticket.setType(TicketType.ADULT);
        getTicketService().save(ticket);

        Ticket ticket2 = new Ticket();
        ticket2.setClient(new Client("Mircea", "Ionescu"));
        ticket2.setCinema(Cinema.IULIUS_MALL_CLUJ);
        ticket2.setDate(new Date(16, 12, 21));
        ticket2.setMovie(new Movie("Star Wars"));
        ticket2.setSeat("C5");
        ticket2.setType(TicketType.ADULT);
        getTicketService().save(ticket2);

        Ticket ticket3 = new Ticket();
        ticket3.setClient(new Client("Mircea", "Ionescu"));
        ticket3.setCinema(Cinema.IULIUS_MALL_CLUJ);
        ticket3.setDate(new Date(16, 12, 21));
        ticket3.setMovie(new Movie("Star Wars"));
        ticket3.setSeat("C5");
        ticket3.setType(TicketType.ADULT);
        getTicketService().save(ticket3);

		Assert.assertEquals(2, getTicketService().search("sTAr").size());

	}*/

}