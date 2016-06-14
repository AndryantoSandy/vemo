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
import vemo.model.dao.FormatsDao;
import vemo.model.entity.Formats;
import vemo.util.Helper;

/**
 *
 * @author Sandy
 */
@Controller
public class FormatsController {

    private Helper helper = new Helper();

    @RequestMapping(value = "/formats", method = RequestMethod.GET)
    public String formats(ModelMap map, Integer offset, Integer maxResults,
            @RequestParam(value = "filter", required = false) String filter,
            @RequestParam(value = "keyword", required = false) String keyword) {
        List<Formats> list = FormatsDao.list(offset, maxResults, filter, keyword);
        int number = 0;
        if (offset == null || offset == 0) {
            number = 1;
        } else {
            number = offset;
        }
        map.put("filter", filter);
        map.put("keyword", keyword);
        map.put("number", number);
        map.put("count", FormatsDao.count(filter, keyword));
        map.put("offset", offset);
        map.put("data", list);
        return "app/formats/index";
    }

    @RequestMapping(value = "formats/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable(value = "id") Integer id, ModelMap map, final RedirectAttributes redirectAttributes) {
        FormatsDao.Delete(id);
        redirectAttributes.addFlashAttribute("message", " Data Successfully Deleted");
        return "redirect:/formats";
    }

    @RequestMapping(value = "formats/add", method = RequestMethod.GET)
    public String add(ModelMap map) {
        map.put("now", helper.DateNow());
        map.put("formats", new Formats());
        return "app/formats/add";
    }

    @RequestMapping(value = "formats/save", method = RequestMethod.POST)
    public String save(@ModelAttribute Formats c, ModelMap map, final RedirectAttributes redirectAttributes) {
        FormatsDao.Save(c);
        redirectAttributes.addFlashAttribute("message", " Data Successfully Saved");
        int id = FormatsDao.Last_id().getId();
        return "redirect:/formats/view/" + id;
    }

    @RequestMapping(value = "formats/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable(value = "id") Integer id, ModelMap map) {
        map.put("now", helper.DateNow());
        map.put("data", FormatsDao.edit(id));
        map.put("formats", new Formats());
        return "app/formats/edit";
    }

    @RequestMapping(value = "formats/update", method = RequestMethod.POST)
    public String update(@ModelAttribute Formats c, ModelMap map, final RedirectAttributes redirectAttributes) {
        FormatsDao.Update(c);
        redirectAttributes.addFlashAttribute("message", " Data Successfully Updated");
        return "redirect:/formats/view/" + c.getId();
    }

    @RequestMapping(value = "formats/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable(value = "id") Integer id, ModelMap map) {
        map.put("data", FormatsDao.edit(id));
        map.put("formats", new Formats());
        return "app/formats/view";
    }

}
