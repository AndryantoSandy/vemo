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
import vemo.model.dao.ActorsDao;
import vemo.model.entity.Actors;
import vemo.util.Helper;

/**
 *
 * @author Sandy
 */
@Controller
public class ActorsController {

    private Helper helper = new Helper();

    @RequestMapping(value = "/actors", method = RequestMethod.GET)
    public String actors(ModelMap map, Integer offset, Integer maxResults,
            @RequestParam(value = "filter", required = false) String filter,
            @RequestParam(value = "keyword", required = false) String keyword) {
        List<Actors> list = ActorsDao.list(offset, maxResults, filter, keyword);
        int number = 0;
        if (offset == null || offset == 0) {
            number = 1;
        } else {
            number = offset;
        }
        map.put("filter", filter);
        map.put("keyword", keyword);
        map.put("number", number);
        map.put("count", ActorsDao.count(filter, keyword));
        map.put("offset", offset);
        map.put("data", list);
        return "app/actors/index";
    }

    @RequestMapping(value = "actors/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable(value = "id") Integer id, ModelMap map, final RedirectAttributes redirectAttributes) {
        ActorsDao.Delete(id);
        redirectAttributes.addFlashAttribute("message", " Data Successfully Deleted");
        return "redirect:/actors";
    }

    @RequestMapping(value = "actors/add", method = RequestMethod.GET)
    public String add(ModelMap map) {
        map.put("now", helper.DateNow());
        map.put("actors", new Actors());
        return "app/actors/add";
    }

    @RequestMapping(value = "actors/save", method = RequestMethod.POST)
    public String save(@ModelAttribute Actors c, ModelMap map, final RedirectAttributes redirectAttributes) {
        ActorsDao.Save(c);
        redirectAttributes.addFlashAttribute("message", " Data Successfully Saved");
        int id = ActorsDao.Last_id().getId();
        return "redirect:/actors/view/" + id;
    }

    @RequestMapping(value = "actors/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable(value = "id") Integer id, ModelMap map) {
        map.put("now", helper.DateNow());
        map.put("data", ActorsDao.edit(id));
        map.put("actors", new Actors());
        return "app/actors/edit";
    }

    @RequestMapping(value = "actors/update", method = RequestMethod.POST)
    public String update(@ModelAttribute Actors c, ModelMap map, final RedirectAttributes redirectAttributes) {
        ActorsDao.Update(c);
        redirectAttributes.addFlashAttribute("message", " Data Successfully Updated");
        return "redirect:/actors/view/" + c.getId();
    }

    @RequestMapping(value = "actors/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable(value = "id") Integer id, ModelMap map) {
        map.put("data", ActorsDao.edit(id));
        map.put("actors", new Actors());
        return "app/actors/view";
    }

}
