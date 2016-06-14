/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vemo.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vemo.model.dao.GenresDao;
import vemo.model.entity.Genres;
import vemo.util.Helper;

/**
 *
 * @author Sandy
 */
@Controller
public class GenresController {

    private Helper helper = new Helper();

    @RequestMapping(value = "/genres", method = RequestMethod.GET)
    public String genres(ModelMap map, Integer offset, Integer maxResults,
            @RequestParam(value = "filter", required = false) String filter,
            @RequestParam(value = "keyword", required = false) String keyword) {
        List<Genres> list = GenresDao.list(offset, maxResults, filter, keyword);
        int number = 0;
        if (offset == null || offset == 0) {
            number = 1;
        } else {
            number = offset;
        }
        map.put("filter", filter);
        map.put("keyword", keyword);
        map.put("number", number);
        map.put("count", GenresDao.count(filter, keyword));
        map.put("offset", offset);
        map.put("data", list);
        return "app/genres/index";
    }

    @RequestMapping(value = "genres/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable(value = "id") Integer id, ModelMap map, final RedirectAttributes redirectAttributes) {
        GenresDao.Delete(id);
        redirectAttributes.addFlashAttribute("message", " Data Successfully Deleted");
        return "redirect:/genres";
    }

    @RequestMapping(value = "genres/add", method = RequestMethod.GET)
    public String add(ModelMap map) {
        map.put("now", helper.DateNow());
        map.put("genres", new Genres());
        return "app/genres/add";
    }

    @RequestMapping(value = "genres/save", method = RequestMethod.POST)
    public String save(@ModelAttribute Genres c, ModelMap map, final RedirectAttributes redirectAttributes) {
        GenresDao.Save(c);
        redirectAttributes.addFlashAttribute("message", " Data Successfully Saved");
        int id = GenresDao.Last_id().getId();
        return "redirect:/genres/view/" + id;
    }

    @RequestMapping(value = "genres/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable(value = "id") Integer id, ModelMap map) {
        map.put("now", helper.DateNow());
        map.put("data", GenresDao.edit(id));
        map.put("genres", new Genres());
        return "app/genres/edit";
    }

    @RequestMapping(value = "genres/update", method = RequestMethod.POST)
    public String update(@ModelAttribute Genres c, ModelMap map, final RedirectAttributes redirectAttributes) {
        GenresDao.Update(c);
        redirectAttributes.addFlashAttribute("message", " Data Successfully Updated");
        return "redirect:/genres/view/" + c.getId();
    }

    @RequestMapping(value = "genres/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable(value = "id") Integer id, ModelMap map) {
        map.put("data", GenresDao.edit(id));
        map.put("genres", new Genres());
        return "app/genres/view";
    }

}
