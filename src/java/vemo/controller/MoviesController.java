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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vemo.model.dao.MoviesDao;
import vemo.model.entity.Formats;
import vemo.model.entity.Genres;
import vemo.model.entity.Movies;
import vemo.model.entity.Stores;
import vemo.util.Helper;

/**
 *
 * @author Sandy
 */
@Controller
public class MoviesController {

    private Helper helper = new Helper();

    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    public String movies(ModelMap map, Integer offset, Integer maxResults,
            @RequestParam(value = "filter", required = false) String filter,
            @RequestParam(value = "keyword", required = false) String keyword) {
        List<Movies> list = MoviesDao.list(offset, maxResults, filter, keyword);
        int number = 0;
        if (offset == null || offset == 0) {
            number = 1;
        } else {
            number = offset;
        }
        map.put("filter", filter);
        map.put("keyword", keyword);
        map.put("number", number);
        map.put("count", MoviesDao.count(filter, keyword));
        map.put("offset", offset);
        map.put("data", list);
        return "app/movies/index";
    }

    @RequestMapping(value = "movies/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable(value = "id") Integer id, ModelMap map, final RedirectAttributes redirectAttributes) {
        MoviesDao.Delete(id);
        redirectAttributes.addFlashAttribute("message", " Data Successfully Deleted");
        return "redirect:/movies";
    }

    @RequestMapping(value = "movies/add", method = RequestMethod.GET)
    public String add(ModelMap map) {
        List<Formats> formats = MoviesDao.GetFormats();
        List<Genres> genres = MoviesDao.GetGenres();
        List<Stores> stores = MoviesDao.GetStores();
        map.put("formats", formats);
        map.put("genres", genres);
        map.put("stores", stores);
        map.put("now", helper.DateNow());
        map.put("movies", new Movies());
        return "app/movies/add";
    }

    @RequestMapping(value = "movies/save", method = RequestMethod.POST)
    public String save(@ModelAttribute Movies c, HttpServletRequest r, ModelMap map, final RedirectAttributes redirectAttributes) {
        int format_id = Integer.parseInt(r.getParameter("format_id"));
        int store_id = Integer.parseInt(r.getParameter("store_id"));
        int genre_id = Integer.parseInt(r.getParameter("genre_id"));
        MoviesDao.Save(c, genre_id, format_id, store_id);
        redirectAttributes.addFlashAttribute("message", " Data Successfully Saved ");
        int id = MoviesDao.Last_id().getId();
        return "redirect:/movies/view/" + id;
    }

    @RequestMapping(value = "movies/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable(value = "id") Integer id, ModelMap map) {
        List<Formats> formats = MoviesDao.GetFormats();
        List<Genres> genres = MoviesDao.GetGenres();
        List<Stores> stores = MoviesDao.GetStores();
        map.put("formats", formats);
        map.put("genres", genres);
        map.put("stores", stores);
        map.put("now", helper.DateNow());
        map.put("data", MoviesDao.edit(id));
        map.put("movies", new Movies());
        return "app/movies/edit";
    }

    @RequestMapping(value = "movies/update", method = RequestMethod.POST)
    public String update(@ModelAttribute Movies c, HttpServletRequest r, ModelMap map, final RedirectAttributes redirectAttributes) {
        int format_id = Integer.parseInt(r.getParameter("format_id"));
        int store_id = Integer.parseInt(r.getParameter("store_id"));
        int genre_id = Integer.parseInt(r.getParameter("genre_id"));
        MoviesDao.Update(c, genre_id, format_id, store_id);
        redirectAttributes.addFlashAttribute("message", " Data Successfully Updated");
        return "redirect:/movies/view/" + c.getId();
    }

    @RequestMapping(value = "movies/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable(value = "id") Integer id, ModelMap map) {
        map.put("data", MoviesDao.edit(id));
        map.put("movies", new Movies());
        return "app/movies/view";
    }

}
