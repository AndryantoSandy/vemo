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
import vemo.model.dao.TransactionTypeDao;
import vemo.model.entity.TransactionTypes;
import vemo.util.Helper;

/**
 *
 * @author Sandy
 */
@Controller
public class TransactionTypesController {

    private Helper helper = new Helper();

    @RequestMapping(value = "/transaction_type", method = RequestMethod.GET)
    public String transaction_type(ModelMap map, Integer offset, Integer maxResults,
            @RequestParam(value = "filter", required = false) String filter,
            @RequestParam(value = "keyword", required = false) String keyword) {
        List<TransactionTypes> list = TransactionTypeDao.list(offset, maxResults, filter, keyword);
        int number = 0;
        if (offset == null || offset == 0) {
            number = 1;
        } else {
            number = offset;
        }
        map.put("filter", filter);
        map.put("keyword", keyword);
        map.put("number", number);
        map.put("count", TransactionTypeDao.count(filter, keyword));
        map.put("offset", offset);
        map.put("data", list);
        return "app/transaction_type/index";
    }

    @RequestMapping(value = "transaction_type/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable(value = "id") Integer id, ModelMap map, final RedirectAttributes redirectAttributes) {
        TransactionTypeDao.Delete(id);
        redirectAttributes.addFlashAttribute("message", " Data Successfully Deleted");
        return "redirect:/transaction_type";
    }

    @RequestMapping(value = "transaction_type/add", method = RequestMethod.GET)
    public String add(ModelMap map) {
        map.put("now", helper.DateNow());
        map.put("transaction_type", new TransactionTypes());
        return "app/transaction_type/add";
    }

    @RequestMapping(value = "transaction_type/save", method = RequestMethod.POST)
    public String save(@ModelAttribute TransactionTypes c, ModelMap map, final RedirectAttributes redirectAttributes) {
        TransactionTypeDao.Save(c);
        redirectAttributes.addFlashAttribute("message", " Data Successfully Saved");
        int id = TransactionTypeDao.Last_id().getId();
        return "redirect:/transaction_type/view/" + id;
    }

    @RequestMapping(value = "transaction_type/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable(value = "id") Integer id, ModelMap map) {
        map.put("now", helper.DateNow());
        map.put("data", TransactionTypeDao.edit(id));
        map.put("transaction_type", new TransactionTypes());
        return "app/transaction_type/edit";
    }

    @RequestMapping(value = "transaction_type/update", method = RequestMethod.POST)
    public String update(@ModelAttribute TransactionTypes c, ModelMap map, final RedirectAttributes redirectAttributes) {
        TransactionTypeDao.Update(c);
        redirectAttributes.addFlashAttribute("message", " Data Successfully Updated");
        return "redirect:/transaction_type/view/" + c.getId();
    }

    @RequestMapping(value = "transaction_type/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable(value = "id") Integer id, ModelMap map) {
        map.put("data", TransactionTypeDao.edit(id));
        map.put("transaction_type", new TransactionTypes());
        return "app/transaction_type/view";
    }

}
