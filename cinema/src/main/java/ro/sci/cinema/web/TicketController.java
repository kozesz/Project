package ro.sci.cinema.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ro.sci.cinema.domain.*;
import ro.sci.cinema.service.*;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping({"/ticket"})

public class TicketController {
    @Autowired
    private TicketService ticketService;

    @Autowired
    private CinemaHallService cinemaHallService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private ProgramService programService;

    @Autowired
    private SeatService seatService;

    private Ticket ticketInProgress = new Ticket();

    public TicketController() throws IOException, ParseException {
    }

    @RequestMapping({""})
    public ModelAndView list() throws ValidationException {
        ModelAndView result = new ModelAndView("ticket/start");
        Collection<Ticket> ticketList = ticketService.listAll();
        result.addObject("tickets", ticketList);
        return result;
    }

    @RequestMapping({"/get_movies"})
    public ModelAndView getAllMovies() throws IOException, ParseException {
        ModelAndView result = new ModelAndView("ticket/list_movies");
        Collection<Movie> movieList = ticketService.readMyMovies();
        result.addObject("movies", movieList);
        return result;
    }

    @RequestMapping({"/get_tickets"})
    public ModelAndView getTickets() throws IOException, ParseException {
        ModelAndView result = new ModelAndView("ticket/list_tickets");
        Collection<Ticket> ticketList = ticketService.listAll();
        result.addObject("tickets", ticketList);
        return result;
    }

    @RequestMapping({"select_date"})
    public ModelAndView selectDate() throws FileNotFoundException, ParseException {
        ModelAndView result = new ModelAndView("ticket/select_date");
        Collection<Program> program = ticketService.programOfTheWeek();
        List<Date> dates = new ArrayList<>();
        for (Program p : program) {
            if (dates.contains(p.getDate())) {
            } else dates.add(p.getDate());
        }
        result.addObject("dates", dates);
        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save_date")
    public String saveDate(String date) throws ParseException {
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        Date d = dt.parse(date);
        ticketInProgress.setDate(d);

        return "redirect:/ticket/select_movie";
    }

    @RequestMapping({"/select_movie"})
    public ModelAndView todaysMovies() throws IOException, ParseException {
        ModelAndView result = new ModelAndView("ticket/select_movie");
        Collection<Program> programForToday = ticketService.getProgramForToday(ticketInProgress.getDate());
        result.addObject("prog", programForToday);
        return result;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/save_movie")
    public String saveMovie(String hour) throws ParseException, FileNotFoundException {
        SimpleDateFormat dt = new SimpleDateFormat("HH:mm");
        Date d = dt.parse(hour);

        Collection<Program> programForToday = ticketService.getProgramForToday(ticketInProgress.getDate());
        for (Program p : programForToday) {
            if (p.getHour().equals(d)) {
                ticketInProgress.setHour(p.getHour());
                ticketInProgress.setCinemaHall(p.getHall());
                ticketInProgress.setMovie(p.getMovie());
            }
        }

        return "redirect:/ticket/select_ticketquantity";
    }

    @RequestMapping({"/select_ticketquantity"})
    public String displayQuantityPage() {
        return "/ticket/select_ticketquantity";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save_ticketquantity")
    public String saveTicketQuantity(int quantity) throws IOException {
        ticketInProgress.setQuantity(quantity);
        cinemaHallService.readAllSeats(ticketInProgress.getCinemaHall());
        return "redirect:/ticket/list_seat";
    }

    @RequestMapping({"/list_seat"})
    public ModelAndView getAllSeats() throws IOException, ParseException {
        ModelAndView result = new ModelAndView("ticket/list_seat");

        Collection<Seat> seatList = cinemaHallService.getAllSeats();
        result.addObject("seats", seatList);

        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save_seat")
    public String selectSeats(int row, int number) throws FileNotFoundException {
        String result;
        Seat s = new Seat(row, number, true);
        for (Seat seats : cinemaHallService.getAllSeats()) {
            if (seats.getNumber() == s.getNumber() & seats.getRow() == s.getRow() & seats.isAvailable()) {
                cinemaHallService.reserveSeat(s);
                ticketInProgress.getSeats().add(s);
            }
        }

        if (ticketInProgress.getSeats().size() == ticketInProgress.getQuantity())
            result = "redirect:/ticket/select_type";
        else result = "redirect:/ticket/list_seat";

        return result;
    }

    @RequestMapping({"/select_type"})
    public ModelAndView selectTicketType() {
        ModelAndView result = new ModelAndView("ticket/select_type");
        Collection<TicketType> types = ticketService.getTicketTypes();
        result.addObject("types", types);
        return result;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/save_type")
    public String saveTypes(String type) {
        String result;
        for (TicketType t : TicketType.values()) {
            if (TicketType.valueOf(type).equals(t)) {
                ticketInProgress.getTypes().add(t);
            }
        }
        if (ticketInProgress.getTypes().size() == ticketInProgress.getQuantity())
            result = "redirect:/ticket/add_client";
        else result = "redirect:/ticket/select_type";

        return result;
    }

    @RequestMapping({"/add_client"})
    public String displayClientPage() {
        return "/ticket/add_client";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save_client")
    public String saveClient(Client client) {
        ticketInProgress.setClient(client);
        return "redirect:/ticket/display_ticket_inprogress";
    }

    @RequestMapping({"/display_ticket_inprogress"})
    public ModelAndView displayTicket() {
        ModelAndView result = new ModelAndView("ticket/display_ticket");

        result.addObject("inprogress", ticketInProgress);
        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save_ticket")
    public String saveTicket(Ticket thisTicket) throws ValidationException {
        ticketService.save(ticketInProgress);

        return "redirect:/ticket";
    }

    public void clearTicket(Ticket ticket) {
        ticket.setDate(null);
        ticket.setHour(null);
        ticket.setMovie(null);
        ticket.setCinemaHall(null);
        ticket.setQuantity(0);
        ticket.setTypes(null);
        ticket.setSeats(null);
        ticket.setClient(null);

    }
}
