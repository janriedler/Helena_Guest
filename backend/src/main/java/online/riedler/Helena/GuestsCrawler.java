package online.riedler.Helena;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestsCrawler {
    @Autowired
    private JdbcTemplate jtm;

    public List<Guest> findAll() {
        String sql = "SELECT * FROM guests ORDER BY id DESC;";
        return jtm.query(sql, new BeanPropertyRowMapper<>(Guest.class));
    }

    public List<Guest> findAllName() {
        String sql = "SELECT * FROM guests ORDER BY name;";
        return jtm.query(sql, new BeanPropertyRowMapper<>(Guest.class));
    }

    public List<Guest> findAllDate() {
        String sql = "SELECT * FROM guests ORDER BY checkIn DESC ;";
        return jtm.query(sql, new BeanPropertyRowMapper<>(Guest.class));
    }

    public Guest findById(Long id) {
        String sql = "SELECT * FROM guests WHERE id = ?";
        return jtm.queryForObject(sql, new Object[]{id},
                new BeanPropertyRowMapper<>(Guest.class));
    }

    public int deleteAll() {
        return jtm.update( "delete from guests");
    }


    public int someUpdate(String name, String email, String checkIn, String checkOut, String sonstiges, String raum,
                          String preis, String gezahlt) {
        return jtm.update("INSERT INTO guests(name, email, checkIn, checkOut, sonstiges, raum, preis, gezahlt) " +
                "VALUES(?, ?, ?, ? , ?, ?, ?, ?);", name, email, checkIn, checkOut, sonstiges, raum, preis, gezahlt);
    }

    public int edit(String id, String name, String email, String checkIn, String checkOut, String sonstiges, String raum,
                          String preis, String gezahlt) {
        return  jtm.update(
                "update guests set name = ?, email = ?, checkIn = ?, checkOut = ?, sonstiges = ?, raum = ?, preis = ?, gezahlt = ?  where id = ?",
                name, email,checkIn,checkOut, sonstiges, raum, preis, gezahlt, id);
    }
}
