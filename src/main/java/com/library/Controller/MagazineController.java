package com.library.Controller;

import com.library.Dto.PublicationWorkSimpleResponseModel;
import com.library.Model.Magazine;
import com.library.Service.MagazineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MagazineController {
    @Autowired
    private MagazineService magazineService;

    @RequestMapping(value = "/magazine/last", method = RequestMethod.GET)
    public Iterable<PublicationWorkSimpleResponseModel> getLast(@RequestParam int count) {
        Pageable pageable = new PageRequest(0, count, new Sort(Sort.Direction.DESC, "id"));
        Iterable<Magazine> magazines = magazineService.getPage(pageable);

        List<PublicationWorkSimpleResponseModel> result = new ArrayList<>();
        for (Magazine magazine: magazines) {
            result.add(PublicationWorkSimpleResponseModel.fromMagazine(magazine));
        }

        return result;
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

    @RequestMapping(value = "/magazine/{id}", method = RequestMethod.DELETE)
    public void deleteMagazine(@RequestParam long id) {
        magazineService.deleteMagazine(id);
    }
}
