/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vemo.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vemo.model.dao.AccountsDao;
import vemo.model.dao.PaymentsDao;
import vemo.model.entity.Accounts;
import vemo.model.entity.Customers;
import vemo.model.entity.Payments;
import vemo.util.Helper;

/**
 *
 * @author Sandy
 */
@Controller
public class AccountsController {

    private Helper helper = new Helper();

    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
    public String accounts(ModelMap map, Integer offset, Integer maxResults,
            @RequestParam(value = "filter", required = false) String filter,
            @RequestParam(value = "keyword", required = false) String keyword) {
        List<Accounts> list = AccountsDao.list(offset, maxResults, filter, keyword);
        int number = 0;
        if (offset == null || offset == 0) {
            number = 1;
        } else {
            number = offset;
        }
        map.put("filter", filter);
        map.put("keyword", keyword);
        map.put("number", number);
        map.put("count", AccountsDao.count(filter, keyword));
        map.put("offset", offset);
        map.put("data", list);
        return "app/accounts/index";
    }

    @RequestMapping(value = "accounts/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable(value = "id") Integer id, ModelMap map, final RedirectAttributes redirectAttributes) {
        AccountsDao.Delete(id);
        redirectAttributes.addFlashAttribute("message", " Data Successfully Deleted");
        return "redirect:/accounts";
    }

    @RequestMapping(value = "accounts/add", method = RequestMethod.GET)
    public String add(ModelMap map) {
        List<Payments> payments = AccountsDao.GetPayments();
        map.put("payments", payments);
        map.put("now", helper.DateNow());
        map.put("accounts", new Accounts());
        return "app/accounts/add";
    }

    @RequestMapping(value = "accounts/save", method = RequestMethod.POST)
    public String save(@ModelAttribute Accounts a, HttpServletRequest r, ModelMap map, final RedirectAttributes redirectAttributes) {
        int customer_id = Integer.parseInt(r.getParameter("customer_id"));
        int payment_id = Integer.parseInt(r.getParameter("payment_id"));
        AccountsDao.Save(a, customer_id, payment_id);
        redirectAttributes.addFlashAttribute("message", " Data Successfully Saved ");
        int id = AccountsDao.Last_id().getId();
        return "redirect:/accounts/view/" + id;
    }

    @RequestMapping(value = "accounts/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable(value = "id") Integer id, ModelMap map) {
        List<Payments> payments = AccountsDao.GetPayments();
        map.put("payments", payments);
        map.put("now", helper.DateNow());
        map.put("data", AccountsDao.edit(id));
        map.put("accounts", new Accounts());
        return "app/accounts/edit";
    }

    @RequestMapping(value = "accounts/update", method = RequestMethod.POST)
    public String update(@ModelAttribute Accounts a, HttpServletRequest r, ModelMap map, final RedirectAttributes redirectAttributes) {
        int customer_id = Integer.parseInt(r.getParameter("customer_id"));
        int payment_id = Integer.parseInt(r.getParameter("payment_id"));
        AccountsDao.Update(a, customer_id, payment_id);
        redirectAttributes.addFlashAttribute("message", " Data Successfully Saved ");
        int id = AccountsDao.Last_id().getId();
        return "redirect:/accounts/view/" + a.getId();
    }

    @RequestMapping(value = "accounts/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable(value = "id") Integer id, ModelMap map) {
        map.put("data", AccountsDao.edit(id));
        map.put("accounts", new Accounts());
        return "app/accounts/view";
    }

    @RequestMapping("/json_cutomers")
    public @ResponseBody
    List<Customers> getCustomers(@RequestParam(value = "q") String q) {
        List<Customers> list = AccountsDao.GetCustomers(q);
        return list;
    }
}
