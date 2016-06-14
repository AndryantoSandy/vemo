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
import vemo.model.dao.UsersDao;
import vemo.model.entity.Users;
import vemo.util.Helper;

/**
 *
 * @author Sandy
 */
@Controller
public class UsersController {

    private Helper helper = new Helper();

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String users(ModelMap map, Integer offset, Integer maxResults,
            @RequestParam(value = "filter", required = false) String filter,
            @RequestParam(value = "keyword", required = false) String keyword) {
        List<Users> list = UsersDao.list(offset, maxResults, filter, keyword);
        int number = 0;
        if (offset == null || offset == 0) {
            number = 1;
        } else {
            number = offset;
        }
        map.put("filter", filter);
        map.put("keyword", keyword);
        map.put("number", number);
        map.put("count", UsersDao.count(filter, keyword));
        map.put("offset", offset);
        map.put("data", list);
        return "app/users/index";
    }

    @RequestMapping(value = "users/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable(value = "id") Integer id, ModelMap map, final RedirectAttributes redirectAttributes) {
        UsersDao.Delete(id);
        redirectAttributes.addFlashAttribute("message", " Data Successfully Deleted");
        return "redirect:/users";
    }

    @RequestMapping(value = "users/add", method = RequestMethod.GET)
    public String add(ModelMap map) {
        map.put("now", helper.DateNow());
        map.put("users", new Users());
        return "app/users/add";
    }

    @RequestMapping(value = "users/save", method = RequestMethod.POST)
    public String save(@ModelAttribute Users c, ModelMap map, final RedirectAttributes redirectAttributes) {
        UsersDao.Save(c);
        redirectAttributes.addFlashAttribute("message", " Data Successfully Saved");
        int id = UsersDao.Last_id().getId();
        return "redirect:/users/view/" + id;
    }

    @RequestMapping(value = "users/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable(value = "id") Integer id, ModelMap map) {
        map.put("now", helper.DateNow());
        map.put("data", UsersDao.edit(id));
        map.put("users", new Users());
        return "app/users/edit";
    }

    @RequestMapping(value = "users/update", method = RequestMethod.POST)
    public String update(@ModelAttribute Users c, ModelMap map, final RedirectAttributes redirectAttributes) {
        UsersDao.Update(c);
        redirectAttributes.addFlashAttribute("message", " Data Successfully Updated");
        return "redirect:/users/view/" + c.getId();
    }

    @RequestMapping(value = "users/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable(value = "id") Integer id, ModelMap map) {
        map.put("data", UsersDao.edit(id));
        map.put("users", new Users());
        return "app/users/view";
    }

}
