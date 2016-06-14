/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vemo.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vemo.model.dao.AccountsDao;
import vemo.model.dao.CustomersDao;
import vemo.model.dao.MoviesDao;
import vemo.model.dao.RentalsDao;
import vemo.model.dao.TransactionTypeDao;
import vemo.model.dao.UsersDao;
import vemo.model.entity.Accounts;
import vemo.model.entity.Chart;
import vemo.model.entity.Customers;
import vemo.model.entity.CustomersRentals;
import vemo.model.entity.Movies;
import vemo.model.entity.Rentals;
import vemo.model.entity.TransactionTypes;
import vemo.model.entity.Users;
import vemo.util.Helper;

/**
 *
 * @author Sandy
 */
@Controller
public class RentalsController {

    private Helper helper = new Helper();

    @RequestMapping(value = "/rentals", method = RequestMethod.GET)
    public String CustomersRentals(ModelMap map, Integer offset, Integer maxResults,
            @RequestParam(value = "first", required = false) String first,
            @RequestParam(value = "last", required = false) String last) {
        List<CustomersRentals> list = RentalsDao.list(offset, maxResults, first, last);
        int number = 0;
        if (offset == null || offset == 0) {
            number = 1;
        } else {
            number = offset;
        }
        map.put("first", first);
        map.put("last", last);
        map.put("number", number);
        map.put("count", RentalsDao.count(first, last));
        map.put("offset", offset);
        map.put("data", list);
        return "app/rentals/index";
    }

    @RequestMapping(value = "rentals/add", method = RequestMethod.GET)
    public String add(ModelMap map) {
        map.put("now", helper.DateNow());
        map.put("transaction", RentalsDao.GetTransactionTypes());
        return "app/rentals/add";
    }

    @RequestMapping(value = "rentals/save", method = RequestMethod.POST)
    public String save(HttpServletRequest request, ModelMap map, final RedirectAttributes redirectAttributes) {
        String movie_id[] = request.getParameterValues("movie_id");
        String amount[] = request.getParameterValues("amount_id");
        int account_id = Integer.parseInt(request.getParameter("account_id"));
        int customer_id = Integer.parseInt(request.getParameter("customer_id"));
        int transaction_type_id = Integer.parseInt(request.getParameter("transaction_type_id"));
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        String comment = request.getParameter("comment");
        Accounts a = AccountsDao.edit(account_id);
        Customers c = CustomersDao.edit(customer_id);
        TransactionTypes t = TransactionTypeDao.edit(transaction_type_id);
        Users u = UsersDao.edit(user_id);
        for (int i = 0; i < movie_id.length; i++) {
            int m_id = Integer.parseInt(movie_id[i]);
            int am = Integer.parseInt(amount[i]);
            Movies m = MoviesDao.edit(m_id);
            Rentals rs = new Rentals();
            rs.setCustomers(c);
            rs.setMovies(m);
            rs.setStatus(1);
            rs.setDateOut(new Date());
            rs.setAmount(am);
            RentalsDao.SaveRentals(rs);
            RentalsDao.UpdateMoviekRate(m_id);

            CustomersRentals cs = new CustomersRentals();
            cs.setAccounts(a);
            cs.setTransactionTypes(t);
            cs.setRentals(rs);
            cs.setUsers(u);
            cs.setDate(new Date());
            cs.setAmount(am);
            cs.setComment(comment);
            RentalsDao.SaveCustomerRentals(cs);

        }
        redirectAttributes.addFlashAttribute("message", " Data Successfully Saved ");
        return "redirect:/rentals";
    }

    @RequestMapping("/json_account")
    public @ResponseBody
    List<Accounts> getAccount(@RequestParam(value = "q") String q) {
        List<Accounts> list = RentalsDao.GetAccounts(q);
        return list;
    }

    @RequestMapping("/json_account_customer")
    public @ResponseBody
    Accounts GetAccountCustomer(@RequestParam(value = "id") Integer id) {
        Accounts get = AccountsDao.edit(id);
        Accounts a = new Accounts();
        a.setId(get.getCustomers().getId());
        return a;
    }

    @RequestMapping("/json_movie")
    public @ResponseBody
    List<Movies> getMovie(@RequestParam(value = "q") String q) {
        List<Movies> list = RentalsDao.GetMovies(q);
        return list;
    }

    @RequestMapping("/json_movie_get")
    public @ResponseBody
    Movies GetMovieById(@RequestParam(value = "id") Integer id) {
        Movies data = MoviesDao.edit(id);
        Movies m = new Movies();
        m.setReleaseYear(Integer.valueOf(data.getReleaseYear()));
        m.setStock(data.getStock());
        m.setPrice(data.getPrice());
        return m;
    }

    @RequestMapping(value = "rentals/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable(value = "id") Integer id, ModelMap map, final RedirectAttributes redirectAttributes) {
        RentalsDao.DeleteCustomersRentals(id);
        RentalsDao.DeleteRentals(id);
        redirectAttributes.addFlashAttribute("message", " Data Successfully Deleted");
        return "redirect:/rentals";
    }

    @RequestMapping(value = "rentals/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable(value = "id") Integer id, ModelMap map, final RedirectAttributes redirectAttributes) {
        map.put("now", helper.DateNow());
        map.put("data", RentalsDao.edit(id));
        return "app/rentals/view";
    }

    @RequestMapping(value = "rentals/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable(value = "id") Integer id, ModelMap map, final RedirectAttributes redirectAttributes) {
        map.put("now", helper.DateNow());
        map.put("transaction", RentalsDao.GetTransactionTypes());
        map.put("data", RentalsDao.edit(id));
        return "app/rentals/edit";
    }

    @RequestMapping(value = "rentals/update", method = RequestMethod.POST)
    public String update(HttpServletRequest request, ModelMap map, final RedirectAttributes redirectAttributes) {
        int id = Integer.parseInt(request.getParameter("id"));
        int movie_id = Integer.parseInt(request.getParameter("movie_id"));
        int account_id = Integer.parseInt(request.getParameter("account_id"));
        int customer_id = Integer.parseInt(request.getParameter("customer_id"));
        int transaction_type_id = Integer.parseInt(request.getParameter("transaction_type_id"));
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        int status = Integer.parseInt(request.getParameter("status"));
        int amount = Integer.parseInt(request.getParameter("amount"));
        String date_out = request.getParameter("date_out");
        String date_returned = request.getParameter("date_returned");
        String comment = request.getParameter("comment");
        Movies m = MoviesDao.edit(movie_id);
        Accounts a = AccountsDao.edit(account_id);
        Customers c = CustomersDao.edit(customer_id);
        TransactionTypes t = TransactionTypeDao.edit(transaction_type_id);
        Users u = UsersDao.edit(user_id);

        Rentals r = new Rentals();
        r.setId(id);
        r.setCustomers(c);
        r.setMovies(m);
        r.setStatus(status);
        r.setDateOut(helper.convertStringToDate(date_out));
        r.setDateReturned(helper.convertStringToDate(date_returned));
        r.setAmount(amount);
        RentalsDao.UpdateRentals(r);

        CustomersRentals cs = new CustomersRentals();
        cs.setId(id);
        cs.setAccounts(a);
        cs.setTransactionTypes(t);
        cs.setRentals(r);
        cs.setUsers(u);
        cs.setDate(new Date());
        cs.setAmount(amount);
        cs.setComment(comment);
        RentalsDao.UpdateCustomerRentals(cs);

        redirectAttributes.addFlashAttribute("message", " Data Successfully Updated ");
        return "redirect:/rentals/view/" + id;
    }

}
