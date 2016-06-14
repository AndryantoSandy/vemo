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
import vemo.model.dao.PaymentsDao;
import vemo.model.entity.Payments;
import vemo.util.Helper;

/**
 *
 * @author Sandy
 */
@Controller
public class PaymentsController {

    private Helper helper = new Helper();

    @RequestMapping(value = "/payments/type", method = RequestMethod.GET)
    public String payments(ModelMap map, Integer offset, Integer maxResults,
            @RequestParam(value = "filter", required = false) String filter,
            @RequestParam(value = "keyword", required = false) String keyword) {
        List<Payments> list = PaymentsDao.list(offset, maxResults, filter, keyword);
        int number = 0;
        if (offset == null || offset == 0) {
            number = 1;
        } else {
            number = offset;
        }
        map.put("filter", filter);
        map.put("keyword", keyword);
        map.put("number", number);
        map.put("count", PaymentsDao.count(filter, keyword));
        map.put("offset", offset);
        map.put("data", list);
        return "app/payments/index";
    }

    @RequestMapping(value = "payments/type/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable(value = "id") Integer id, ModelMap map, final RedirectAttributes redirectAttributes) {
        PaymentsDao.Delete(id);
        redirectAttributes.addFlashAttribute("message", " Data Successfully Deleted");
        return "redirect:/payments/type";
    }

    @RequestMapping(value = "payments/type/add", method = RequestMethod.GET)
    public String add(ModelMap map) {
        map.put("now", helper.DateNow());
        map.put("payments", new Payments());
        return "app/payments/add";
    }

    @RequestMapping(value = "payments/type/save", method = RequestMethod.POST)
    public String save(@ModelAttribute Payments c, ModelMap map, final RedirectAttributes redirectAttributes) {
        PaymentsDao.Save(c);
        redirectAttributes.addFlashAttribute("message", " Data Successfully Saved");
        int id = PaymentsDao.Last_id().getId();
        return "redirect:/payments/type/view/" + id;
    }

    @RequestMapping(value = "payments/type/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable(value = "id") Integer id, ModelMap map) {
        map.put("now", helper.DateNow());
        map.put("data", PaymentsDao.edit(id));
        map.put("payments", new Payments());
        return "app/payments/edit";
    }

    @RequestMapping(value = "payments/type/update", method = RequestMethod.POST)
    public String update(@ModelAttribute Payments c, ModelMap map, final RedirectAttributes redirectAttributes) {
        PaymentsDao.Update(c);
        redirectAttributes.addFlashAttribute("message", " Data Successfully Updated");
        return "redirect:/payments/type/view/" + c.getId();
    }

    @RequestMapping(value = "payments/type/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable(value = "id") Integer id, ModelMap map) {
        map.put("data", PaymentsDao.edit(id));
        map.put("payments", new Payments());
        return "app/payments/view";
    }

}
