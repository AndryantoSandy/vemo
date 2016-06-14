/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vemo.model.dao;

import java.util.List;
import java.util.Set;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import vemo.model.entity.Formats;
import vemo.model.entity.Genres;
import vemo.model.entity.Movies;
import vemo.model.entity.Stores;
import vemo.util.HibernateUtil;

/**
 *
 * @author Sandy
 */
public class MoviesDao {
    
    public static Long count(String filter, String keyword) {
        SessionFactory session = HibernateUtil.getSessionFactory();
        try {
            if (filter == null && keyword == null) {
                return (Long) session.openSession()
                        .createQuery("select count(*) from Movies m INNER JOIN m.stores INNER JOIN m.formats INNER JOIN m.genres ")
                        .uniqueResult();
            } else {
                return (Long) session.openSession()
                        .createQuery("select count(*) from Movies m INNER JOIN m.stores INNER JOIN m.formats INNER JOIN m.genres WHERE " + filter + " LIKE '%" + keyword + "%'")
                        .uniqueResult();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static List<Movies> list(Integer offset, Integer maxResults, String filter, String keyword) {
        List<Movies> list = null;
        try {
            SessionFactory session = HibernateUtil.getSessionFactory();
            maxResults = 10;
            int page = offset == null ? 1 : offset;
            int startpoint = (page * maxResults) - maxResults;
            if (filter == null && keyword == null) {
                list = session.openSession()
                        .createQuery("from Movies m INNER JOIN m.stores INNER JOIN m.formats INNER JOIN m.genres ORDER by m.id DESC")
                        .setFirstResult(startpoint)
                        .setMaxResults(maxResults)
                        .list();
            } else {
                list = session.openSession()
                        .createQuery("from Movies m INNER JOIN m.stores INNER JOIN m.formats INNER JOIN m.genres WHERE " + filter + " LIKE '%" + keyword + "%'  ORDER by " + filter + " ASC")
                        .setFirstResult(startpoint)
                        .setMaxResults(maxResults)
                        .list();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public static void Save(Movies m, Integer genre_id, Integer format_id, Integer store_id) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Genres g = (Genres) session.get(Genres.class, genre_id);
            Formats f = (Formats) session.get(Formats.class, format_id);
            Stores s = (Stores) session.get(Stores.class, store_id);
            m.setFormats(f);
            m.setGenres(g);
            m.setStores(s);
            session.save(m);
            Transaction tx = session.beginTransaction();
            if (!tx.wasCommitted()) {
                tx.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static Movies edit(int id) {
        Movies c = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            c = (Movies) session.get(Movies.class, id);
            Transaction tx = session.beginTransaction();
            if (!tx.wasCommitted()) {
                tx.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }
    
    public static void Update(Movies m, Integer genre_id, Integer format_id, Integer store_id) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Genres g = (Genres) session.get(Genres.class, genre_id);
            Formats f = (Formats) session.get(Formats.class, format_id);
            Stores s = (Stores) session.get(Stores.class, store_id);
            m.setFormats(f);
            m.setGenres(g);
            m.setStores(s);
            session.update(m);
            Transaction tx = session.beginTransaction();
            if (!tx.wasCommitted()) {
                tx.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void Delete(int id) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Movies c = (Movies) session.get(Movies.class, id);
            session.delete(c);
            Transaction tx = session.beginTransaction();
            if (!tx.wasCommitted()) {
                tx.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static Movies Last_id() {
        SessionFactory session = HibernateUtil.getSessionFactory();
        Criteria c = session.openSession().createCriteria(Movies.class);
        c.addOrder(Order.desc("id"));
        c.setMaxResults(1);
        return (Movies) c.uniqueResult();
    }
    
    public static List<Formats> GetFormats() {
        List<Formats> list = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            list = session.createQuery("from Formats").list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public static List<Genres> GetGenres() {
        List<Genres> list = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            list = session.createQuery("from Genres").list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public static List<Stores> GetStores() {
        List<Stores> list = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            list = session.createQuery("from Stores").list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
}
