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
import vemo.model.dao.StoresDao;
import vemo.model.entity.Stores;
import vemo.util.Helper;

/**
 *
 * @author Sandy
 */
@Controller
public class StoresController {

    private Helper helper = new Helper();

    @RequestMapping(value = "/stores", method = RequestMethod.GET)
    public String stores(ModelMap map, Integer offset, Integer maxResults,
            @RequestParam(value = "filter", required = false) String filter,
            @RequestParam(value = "keyword", required = false) String keyword) {
        List<Stores> list = StoresDao.list(offset, maxResults, filter, keyword);
        int number = 0;
        if (offset == null || offset == 0) {
            number = 1;
        } else {
            number = offset;
        }
        map.put("filter", filter);
        map.put("keyword", keyword);
        map.put("number", number);
        map.put("count", StoresDao.count(filter, keyword));
        map.put("offset", offset);
        map.put("data", list);
        return "app/stores/index";
    }

    @RequestMapping(value = "stores/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable(value = "id") Integer id, ModelMap map, final RedirectAttributes redirectAttributes) {
        StoresDao.Delete(id);
        redirectAttributes.addFlashAttribute("message", " Data Successfully Deleted");
        return "redirect:/stores";
    }

    @RequestMapping(value = "stores/add", method = RequestMethod.GET)
    public String add(ModelMap map) {
        map.put("now", helper.DateNow());
        map.put("stores", new Stores());
        return "app/stores/add";
    }

    @RequestMapping(value = "stores/save", method = RequestMethod.POST)
    public String save(@ModelAttribute Stores c, ModelMap map, final RedirectAttributes redirectAttributes) {
        StoresDao.Save(c);
        redirectAttributes.addFlashAttribute("message", " Data Successfully Saved");
        int id = StoresDao.Last_id().getId();
        return "redirect:/stores/view/" + id;
    }

    @RequestMapping(value = "stores/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable(value = "id") Integer id, ModelMap map) {
        map.put("now", helper.DateNow());
        map.put("data", StoresDao.edit(id));
        map.put("stores", new Stores());
        return "app/stores/edit";
    }

    @RequestMapping(value = "stores/update", method = RequestMethod.POST)
    public String update(@ModelAttribute Stores c, ModelMap map, final RedirectAttributes redirectAttributes) {
        StoresDao.Update(c);
        redirectAttributes.addFlashAttribute("message", " Data Successfully Updated");
        return "redirect:/stores/view/" + c.getId();
    }

    @RequestMapping(value = "stores/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable(value = "id") Integer id, ModelMap map) {
        map.put("data", StoresDao.edit(id));
        map.put("stores", new Stores());
        return "app/stores/view";
    }

}
