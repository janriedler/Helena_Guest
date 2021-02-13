package online.riedler.Helena;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;


@RestController()
public class controller {

    @Autowired
    private GuestsCrawler guestsCrawler;


    @GetMapping(value = {"/"})
    @ResponseBody
    public String homePage() {
        StringBuilder erg = new StringBuilder();
        try {
            File file = new File("backend/src/main/resources/public/index.html");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {
                erg.append(st);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return erg.toString();
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Guest> findCities(@RequestParam String sort) {
        if (sort.equals("namen")) {
            return guestsCrawler.findAllName();
        }
        if (sort.equals("id")) return guestsCrawler.findAll();
        if (sort.equals("date")) return guestsCrawler.findAllDate();
        else {
            return null;
        }

    }

    @RequestMapping("/del")
    public int del() {
        return guestsCrawler.deleteAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @ResponseBody
    public int add(@RequestParam String name, String email, String checkIn, String checkOut,
                                           String sonstiges, String raum, String preis, String gezahlt){
        return guestsCrawler.someUpdate(name, email, checkIn, checkOut, sonstiges, raum, preis, gezahlt);

    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    @ResponseBody
    public int add(@RequestParam String id, String name, String email, String checkIn, String checkOut,
                   String sonstiges, String raum, String preis, String gezahlt){
        return guestsCrawler.edit(id, name, email, checkIn, checkOut, sonstiges, raum, preis, gezahlt);

    }

    @RequestMapping(value = "/id", method = RequestMethod.GET)
    @ResponseBody
    public Guest add(@RequestParam String id){
        return guestsCrawler.findById(Long.parseLong(id));

    }
}
