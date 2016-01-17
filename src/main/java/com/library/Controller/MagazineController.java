package com.library.Controller;

import com.library.Model.Magazine;
import com.library.Service.MagazineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by D on 16.1.2016 г..
 */

@RestController
public class MagazineController {
    @Autowired
    private MagazineService magazineService;

    @RequestMapping(value = "/magazine", method = RequestMethod.GET)
    public Iterable<Magazine> getAllBooks() {
        return magazineService.getAll();
    }

    @RequestMapping(value = "/magazine/{id}", method = RequestMethod.GET)
    public Magazine getBook(@RequestParam long id) {
        return magazineService.getMagazineById(id);
    }

    @RequestMapping(value = "/magazine", method = RequestMethod.POST)
    public Magazine getBook(@RequestBody Magazine magazine) {
        return magazineService.save(magazine);
    }


    @RequestMapping(value = "/magazine", method = RequestMethod.PUT)
    public Magazine updateBook(@RequestBody Magazine magazine) {
        return magazineService.updateMagazine(magazine);
    }

    @RequestMapping(value = "/magazine/{id}", method = RequestMethod.DELETE)
    public void deleteBook(@RequestParam long id) {
        magazineService.deleteMagazine(id);
    }

}
