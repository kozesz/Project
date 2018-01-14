package ro.sci.cinema.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.sci.cinema.dao.ProgramDAO;
import ro.sci.cinema.domain.ProgramCSVReader;
import ro.sci.cinema.domain.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class ProgramService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProgramService.class);

    private ProgramDAO pdao;

    public Collection<Program> listAll() {
        return pdao.getAll();
    }

    public Collection<Program> search( String query) {
        LOGGER.debug("Searching for " + query);
        return pdao.searchByName(query);
    }

    public boolean delete(Long id) {
        LOGGER.debug("Deleting program for id: " + id);
        Program program  = pdao.findById(id);
        if (program != null) {
            pdao.delete(program);
            return true;
        }
        return false;
    }

    public Program get(Long id) {
        LOGGER.debug("Getting progam for id: " + id);
        return pdao.findById(id);
    }

    public void save(Program program) throws ValidationException {
        LOGGER.debug("Saving: " + program);
        validate(program);

        pdao.update(program);
    }

    private void validate(Program program) throws ValidationException {}


    public ProgramDAO getPdao() {
        return pdao;
    }

    public void setPdao(ProgramDAO pdao) {
        this.pdao = pdao;
    }
}
