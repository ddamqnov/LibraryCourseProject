package com.library.Controller;

import com.library.Model.Magazine;
import com.library.Service.MagazineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MagazineController {
    @Autowired
    private MagazineService magazineService;

    @RequestMapping(value = "/magazine", method = RequestMethod.GET)
    public Iterable<Magazine> getAllMagazines() {
        return magazineService.getAll();
    }

    @RequestMapping(value = "/magazine/{id}", method = RequestMethod.GET)
    public Magazine getMagazine(@RequestParam long id) {
        return magazineService.getMagazineById(id);
    }

    @RequestMapping(value = "/magazine", method = RequestMethod.POST)
    public Magazine createMagazine(@RequestBody Magazine magazine) {
        return magazineService.save(magazine);
    }

    @RequestMapping(value = "/magazine", method = RequestMethod.PUT)
    public Magazine updateMagazine(@RequestBody Magazine magazine) {
        return magazineService.updateMagazine(magazine);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteMagazine(@RequestParam long id) {
        magazineService.deleteMagazine(id);
    }
}
