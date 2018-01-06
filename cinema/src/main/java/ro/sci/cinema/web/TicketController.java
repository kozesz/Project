package ro.sci.cinema.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ro.sci.cinema.domain.*;
import ro.sci.cinema.service.TicketService;
import ro.sci.cinema.service.ValidationException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

@Controller
@RequestMapping({"/ticket"})


public class TicketController {
    @Autowired
    private TicketService ticketService;
    private Cinema cinema = new Cinema();
    private int index = 0;
    private Ticket ticket = new Ticket();

    public TicketController() {
    }

    @RequestMapping({""})
    public ModelAndView list() throws ValidationException {
        ModelAndView result = new ModelAndView("ticket/start");
        Collection<Ticket> ticketList = this.ticketService.listAll();
        result.addObject("tickets", ticketList);
        return result;
    }

    @RequestMapping({"/get_movies"})
    public ModelAndView getAllMovies() throws IOException, ParseException {
        ModelAndView result = new ModelAndView("ticket/list_movies");
        Collection<Movie> movieList = this.cinema.readMyMovies();
        result.addObject("tick", movieList);
        return result;
    }

    @RequestMapping({"/select_date"})
    public String displayDatePage() {
        return "/ticket/select_date";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save_date")
    public String saveDate(Date date) {
        ticket.setDate(date);
        return "redirect:/ticket/select_movie";
    }

    @RequestMapping({"/select_movie"})
    public ModelAndView todaysMovies() throws IOException, ParseException {
        ModelAndView result = new ModelAndView("ticket/select_movie");
        Collection<MoviesFromProgram> program = this.cinema.programOfTheWeek();
        result.addObject("prog", program);
        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save_movie")
    public String saveMovie(long id) {

        ticket.setMovie(this.ticketService.getMovie(id));
        return "redirect:/ticket/select_ticketquantity";
    }

    @RequestMapping({"/select_ticketquantity"})
    public String displayQuantityPage() {
        return "/ticket/select_ticketquantity";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save_ticketquantity")
    public String saveTicketQuantity(int quantity) {
        ticket.setQuantity(quantity);
        return "redirect:/ticket/list_seat";
    }

    @RequestMapping({"/list_seat"})
    public ModelAndView getAllSeats() throws IOException, ParseException {
        ModelAndView result = new ModelAndView("ticket/list_seat");
        Collection<Seat> seatList = this.cinema.getAllSeats();
        result.addObject("seats", seatList);
        return result;
    }

    @RequestMapping({"/select_seat"})
    public String selectSeats(Seat seat) {
        ticket.addSeats(seat);
        return "";
    }

    @RequestMapping({"save_seat"})
    public String saveSeats() {
        return "redirect:/ticket/add_client";
    }


    @RequestMapping({"/add_client"})
    public String displayClientPage() {
        return "/ticket/add_client";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save_client")
    public String saveClient(Client client) {
        ticket.setClient(client);
        return "redirect:/ticket/get_ticket";
    }

    @RequestMapping({"/get_ticket"})
    public ModelAndView getTicket() {
        ModelAndView result = new ModelAndView("ticket/get_ticket");
        result.addObject("tick", ticket);
        return result;
    }

    @RequestMapping(method = {RequestMethod.POST}, value = {"/save_ticket"})
    public String saveTicket(Ticket ticket) throws ValidationException {
        this.ticketService.saveTicket(ticket);
        return "redirect:/ticket";
    }


}
