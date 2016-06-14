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
import vemo.model.dao.CustomersDao;
import vemo.model.entity.Customers;
import vemo.util.Helper;

/**
 *
 * @author Sandy
 */
@Controller
public class CustomersController {

    private Helper helper = new Helper();

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public String customers(ModelMap map, Integer offset, Integer maxResults,
            @RequestParam(value = "filter", required = false) String filter,
            @RequestParam(value = "keyword", required = false) String keyword) {
        List<Customers> list = CustomersDao.list(offset, maxResults, filter, keyword);
        int number = 0;
        if (offset == null || offset == 0) {
            number = 1;
        } else {
            number = offset;
        }
        map.put("filter", filter);
        map.put("keyword", keyword);
        map.put("number", number);
        map.put("count", CustomersDao.count(filter, keyword));
        map.put("offset", offset);
        map.put("data", list);
        return "app/customers/index";
    }

    @RequestMapping(value = "customers/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable(value = "id") Integer id, ModelMap map, final RedirectAttributes redirectAttributes) {
        CustomersDao.Delete(id);
        redirectAttributes.addFlashAttribute("message", " Data Successfully Deleted");
        return "redirect:/customers";
    }

    @RequestMapping(value = "customers/add", method = RequestMethod.GET)
    public String add(ModelMap map) {
        map.put("now", helper.DateNow());
        map.put("customers", new Customers());
        return "app/customers/add";
    }

    @RequestMapping(value = "customers/save", method = RequestMethod.POST)
    public String save(@ModelAttribute Customers c, ModelMap map, final RedirectAttributes redirectAttributes) {
        CustomersDao.Save(c);
        redirectAttributes.addFlashAttribute("message", " Data Successfully Saved");
        int id = CustomersDao.Last_id().getId();
        return "redirect:/customers/view/" + id;
    }

    @RequestMapping(value = "customers/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable(value = "id") Integer id, ModelMap map) {
        map.put("now", helper.DateNow());
        map.put("data", CustomersDao.edit(id));
        map.put("customers", new Customers());
        return "app/customers/edit";
    }

    @RequestMapping(value = "customers/update", method = RequestMethod.POST)
    public String update(@ModelAttribute Customers c, ModelMap map, final RedirectAttributes redirectAttributes) {
        CustomersDao.Update(c);
        redirectAttributes.addFlashAttribute("message", " Data Successfully Updated");
        return "redirect:/customers/view/" + c.getId();
    }

    @RequestMapping(value = "customers/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable(value = "id") Integer id, ModelMap map) {
        map.put("data", CustomersDao.edit(id));
        map.put("customers", new Customers());
        return "app/customers/view";
    }

}
