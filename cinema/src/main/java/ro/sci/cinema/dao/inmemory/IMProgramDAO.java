package ro.sci.cinema.dao.inmemory;

import org.springframework.util.StringUtils;
import ro.sci.cinema.dao.ProgramDAO;
import ro.sci.cinema.domain.Program;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class IMProgramDAO extends IMBaseDAO<Program> implements ProgramDAO {

    public Collection<Program> searchByName(String query) {
        if (StringUtils.isEmpty(query)) {
            return getAll();
        }

        Collection<Program> all = new LinkedList<Program>(getAll());
        for (Iterator<Program> it = all.iterator(); it.hasNext();) {
            Program program = it.next();
            String ss = String.valueOf(program.getDate() + " " + program.getHour() + " " + program.getMovie() + " " + program.getHall());
            if (!ss.toLowerCase().contains(query.toLowerCase())) {
                it.remove();
            }
        }
        return all;
    }
}
