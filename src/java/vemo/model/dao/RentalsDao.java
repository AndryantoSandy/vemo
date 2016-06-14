/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vemo.model.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Projections;
import vemo.model.entity.Accounts;
import vemo.model.entity.CustomersRentals;
import vemo.model.entity.Movies;
import vemo.model.entity.Rentals;
import vemo.model.entity.TransactionTypes;
import vemo.util.HibernateUtil;

/**
 *
 * @author Sandy
 */
public class RentalsDao {

    public static Long count(String first, String last) {
        SessionFactory session = HibernateUtil.getSessionFactory();
        try {
            if (first == null && last == null) {
                return (Long) session.openSession()
                        .createCriteria(CustomersRentals.class)
                        .setProjection(Projections.rowCount())
                        .uniqueResult();
            } else {
                return (Long) session.openSession()
                        .createQuery("select count(*) from CustomersRentals WHERE date BETWEEN '" + first + "' AND '" + last + "' ")
                        .uniqueResult();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<CustomersRentals> list(Integer offset, Integer maxResults, String first, String last) {
        List<CustomersRentals> list = null;
        try {
            SessionFactory session = HibernateUtil.getSessionFactory();
            maxResults = 10;
            int page = offset == null ? 1 : offset;
            int startpoint = (page * maxResults) - maxResults;
            if (last == null && first == null) {
                list = session.openSession()
                        .createQuery("from CustomersRentals  id ORDER by id DESC")
                        .setFirstResult(startpoint)
                        .setMaxResults(maxResults)
                        .list();
            } else {
                list = session.openSession()
                        .createQuery("from CustomersRentals WHERE date BETWEEN '" + first + "' AND '" + last + "' ORDER BY date ASC ")
                        .setFirstResult(startpoint)
                        .setMaxResults(maxResults)
                        .list();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<TransactionTypes> GetTransactionTypes() {
        List<TransactionTypes> list = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            list = session.createQuery("from TransactionTypes").list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<Accounts> GetAccounts(String name) {
        List<Accounts> list = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            list = session.createQuery("SELECT c.id as id, c.name as text from Accounts as c WHERE c.name LIKE '%" + name + "%' ORDER BY c.name ASC ").setMaxResults(10).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<Movies> GetMovies(String title) {
        List<Movies> list = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            list = session.createQuery("SELECT c.id as id, c.title as text from Movies as c WHERE c.title LIKE '%" + title + "%' ORDER BY c.title ASC ").setMaxResults(10).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void SaveRentals(Rentals r) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.save(r);
            Transaction tx = session.beginTransaction();
            if (!tx.wasCommitted()) {
                tx.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void UpdateMoviekRate(int id) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Movies m = (Movies) session.get(Movies.class, id);
            m.setRentalDailyRate(+1);
            session.update(m);
            Transaction tx = session.beginTransaction();
            if (!tx.wasCommitted()) {
                tx.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void SaveCustomerRentals(CustomersRentals cs) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.save(cs);
            Transaction tx = session.beginTransaction();
            if (!tx.wasCommitted()) {
                tx.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void DeleteCustomersRentals(int id) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            CustomersRentals c = (CustomersRentals) session.get(CustomersRentals.class, id);
            session.delete(c);
            Transaction tx = session.beginTransaction();
            if (!tx.wasCommitted()) {
                tx.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void DeleteRentals(int id) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Rentals c = (Rentals) session.get(Rentals.class, id);
            session.delete(c);
            Transaction tx = session.beginTransaction();
            if (!tx.wasCommitted()) {
                tx.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static CustomersRentals edit(int id) {
        CustomersRentals c = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            c = (CustomersRentals) session.get(CustomersRentals.class, id);
            Transaction tx = session.beginTransaction();
            if (!tx.wasCommitted()) {
                tx.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    public static Rentals edit_rentals(int id) {
        Rentals c = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            c = (Rentals) session.get(Rentals.class, id);
            Transaction tx = session.beginTransaction();
            if (!tx.wasCommitted()) {
                tx.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    public static void UpdateRentals(Rentals r) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.update(r);
            Transaction tx = session.beginTransaction();
            if (!tx.wasCommitted()) {
                tx.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void UpdateCustomerRentals(CustomersRentals cs) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.update(cs);
            Transaction tx = session.beginTransaction();
            if (!tx.wasCommitted()) {
                tx.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
