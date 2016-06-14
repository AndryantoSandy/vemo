/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vemo.controller;

import java.util.Calendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vemo.model.dao.CustomersDao;
import vemo.model.dao.MoviesDao;
import vemo.model.dao.RentalsDao;
import vemo.model.dao.StoresDao;
import vemo.model.dao.UsersDao;
import vemo.model.entity.Chart;
import vemo.model.entity.Users;

/**
 *
 * @author Sandy
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public String index(ModelMap map) {
        map.put("users", new Users());
        return "index";
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String home(ModelMap map) {
        map.put("customers", CustomersDao.count(null, null));
        map.put("rentals", RentalsDao.count(null, null));
        map.put("movies", MoviesDao.count(null, null));
        map.put("stores", StoresDao.count(null, null));
        map.put("data", RentalsDao.list(0, 25, null, null));
        int year = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 1; i <= 12; i++) {
            if (i > 9) {
                map.put("chart" + i, RentalsDao.count(year + "-0" + i + "-01", year + "-0" + i + "-31"));
            } else {
                map.put("chart" + i, RentalsDao.count(year + "-" + i + "-01", year + "-" + i + "-31"));
            }
        }
        return "app/home";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute Users s, ModelMap map, final RedirectAttributes redirectAttributes, HttpServletRequest request) {
        List<Users> list = UsersDao.AuthUser(s.getUsername(), s.getPassword());
        if (list.size() > 0) {
            int year = Calendar.getInstance().get(Calendar.YEAR);
            request.getSession().setAttribute("username", list.get(0).getUsername());
            request.getSession().setAttribute("user_id", list.get(0).getId());
            request.getSession().setAttribute("year", year);
            redirectAttributes.addFlashAttribute("message", " Login Successfully");
            return "redirect:dashboard";
        } else {
            redirectAttributes.addFlashAttribute("message", " Failed Login , please check username or password !");
            return "redirect:/";
        }

    }

    @RequestMapping("/logout")
    public String logout(ModelMap map, HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}
