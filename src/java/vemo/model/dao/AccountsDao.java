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
import vemo.model.entity.Accounts;
import vemo.model.entity.Customers;
import vemo.model.entity.Payments;
import vemo.util.HibernateUtil;

/**
 *
 * @author Sandy
 */
public class AccountsDao {

    public static Long count(String filter, String keyword) {
        SessionFactory session = HibernateUtil.getSessionFactory();
        try {
            if (filter == null && keyword == null) {
                return (Long) session.openSession()
                        .createQuery("select count(*) from Accounts a INNER JOIN a.customers INNER JOIN a.payments  ")
                        .uniqueResult();
            } else {
                return (Long) session.openSession()
                        .createQuery("select count(*) from Accounts a INNER JOIN a.customers INNER JOIN a.payments WHERE " + filter + " LIKE '%" + keyword + "%'")
                        .uniqueResult();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Accounts> list(Integer offset, Integer maxResults, String filter, String keyword) {
        List<Accounts> list = null;
        try {
            SessionFactory session = HibernateUtil.getSessionFactory();
            maxResults = 10;
            int page = offset == null ? 1 : offset;
            int startpoint = (page * maxResults) - maxResults;
            if (filter == null && keyword == null) {
                list = session.openSession()
                        .createQuery("from Accounts a INNER JOIN a.customers INNER JOIN a.payments ORDER by a.id DESC")
                        .setFirstResult(startpoint)
                        .setMaxResults(maxResults)
                        .list();
            } else {
                list = session.openSession()
                        .createQuery("from Accounts a INNER JOIN a.customers INNER JOIN a.payments WHERE " + filter + " LIKE '%" + keyword + "%'  ORDER by " + filter + " ASC")
                        .setFirstResult(startpoint)
                        .setMaxResults(maxResults)
                        .list();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void Save(Accounts a, Integer customer_id, Integer payment_id) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Customers c = (Customers) session.get(Customers.class, customer_id);
            Payments p = (Payments) session.get(Payments.class, payment_id);
            a.setPayments(p);
            a.setCustomers(c);
            session.save(a);
            Transaction tx = session.beginTransaction();
            if (!tx.wasCommitted()) {
                tx.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Accounts edit(int id) {
        Accounts c = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            c = (Accounts) session.get(Accounts.class, id);
            Transaction tx = session.beginTransaction();
            if (!tx.wasCommitted()) {
                tx.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    public static void Update(Accounts a, Integer customer_id, Integer payment_id) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Customers c = (Customers) session.get(Customers.class, customer_id);
            Payments p = (Payments) session.get(Payments.class, payment_id);
            a.setPayments(p);
            a.setCustomers(c);
            session.update(a);
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
            Accounts c = (Accounts) session.get(Accounts.class, id);
            session.delete(c);
            Transaction tx = session.beginTransaction();
            if (!tx.wasCommitted()) {
                tx.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Accounts Last_id() {
        SessionFactory session = HibernateUtil.getSessionFactory();
        Criteria c = session.openSession().createCriteria(Accounts.class);
        c.addOrder(Order.desc("id"));
        c.setMaxResults(1);
        return (Accounts) c.uniqueResult();
    }

    public static List<Payments> GetPayments() {
        List<Payments> list = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            list = session.createQuery("from Payments").list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<Customers> GetCustomers(String full_name) {
        List<Customers> list = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            list = session.createQuery("SELECT c.id as id, c.fullName as text from Customers as c WHERE c.fullName LIKE '%" + full_name + "%' ORDER BY c.fullName ASC ").setMaxResults(10).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
