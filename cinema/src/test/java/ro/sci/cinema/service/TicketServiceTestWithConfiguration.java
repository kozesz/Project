package ro.sci.cinema.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ro.sci.cinema.ApplicationConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfiguration.class})
public class TicketServiceTestWithConfiguration extends BaseTicketServiceTest {
/*
    @Autowired
    private TicketService service;

    @Override
    protected TicketService getTicketService() {
        return service;
    }

*/
}
