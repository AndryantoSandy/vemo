/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vemo.model.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import vemo.model.entity.Actors;
import vemo.util.HibernateUtil;

/**
 *
 * @author Sandy
 */
public class ActorsDao {

    public static Long count(String filter, String keyword) {
        SessionFactory session = HibernateUtil.getSessionFactory();
        try {
            if (filter == null && keyword == null) {
                return (Long) session.openSession()
                        .createCriteria(Actors.class)
                        .setProjection(Projections.rowCount())
                        .uniqueResult();
            } else {
                return (Long) session.openSession()
                        .createQuery("select count(*) from Actors WHERE " + filter + " LIKE '%" + keyword + "%'")
                        .uniqueResult();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Actors> list(Integer offset, Integer maxResults, String filter, String keyword) {
        List<Actors> list = null;
        try {
            SessionFactory session = HibernateUtil.getSessionFactory();
            maxResults = 10;
            int page = offset == null ? 1 : offset;
            int startpoint = (page * maxResults) - maxResults;
            if (filter == null && keyword == null) {
                list = session.openSession()
                        .createQuery("from Actors ORDER by id DESC")
                        .setFirstResult(startpoint)
                        .setMaxResults(maxResults)
                        .list();
            } else {
                list = session.openSession()
                        .createQuery("from Actors WHERE " + filter + " LIKE '%" + keyword + "%'  ORDER by " + filter + " ASC")
                        .setFirstResult(startpoint)
                        .setMaxResults(maxResults)
                        .list();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void Save(Actors c) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.save(c);
            Transaction tx = session.beginTransaction();
            if (!tx.wasCommitted()) {
                tx.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Actors edit(int id) {
        Actors c = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            c = (Actors) session.get(Actors.class, id);
            Transaction tx = session.beginTransaction();
            if (!tx.wasCommitted()) {
                tx.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    public static void Update(Actors c) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.update(c);
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
            Actors c = (Actors) session.get(Actors.class, id);
            session.delete(c);
            Transaction tx = session.beginTransaction();
            if (!tx.wasCommitted()) {
                tx.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Actors Last_id() {
        SessionFactory session = HibernateUtil.getSessionFactory();
        Criteria c = session.openSession().createCriteria(Actors.class);
        c.addOrder(Order.desc("id"));
        c.setMaxResults(1);
        return (Actors) c.uniqueResult();
    }

}
