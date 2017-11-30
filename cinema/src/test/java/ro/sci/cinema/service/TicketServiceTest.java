package ro.sci.cinema.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ro.sci.cinema.service.BaseTicketServiceTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:beans.xml" })
public class TicketServiceTest extends BaseTicketServiceTest {
/*
	@Autowired
	private TicketService ticketService;
	
	@Override
	protected TicketService getTicketService() {
		return ticketService;
	}
*/
}
