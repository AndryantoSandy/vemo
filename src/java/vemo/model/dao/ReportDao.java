/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vemo.model.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import vemo.model.entity.CustomersRentals;
import vemo.util.HibernateUtil;

/**
 *
 * @author Sandy
 */
public class ReportDao {

    public static Long GetReportRentals(String date, String criteria) {
        try {
            SessionFactory session = HibernateUtil.getSessionFactory();
            return (Long) session.openSession()
                    .createQuery("select count(" + criteria + ") from CustomersRentals cs INNER JOIN cs.rentals WHERE date = '" + date + "'  ")
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public static Long GetReportRentals2(String date, String criteria) {
        try {
            SessionFactory session = HibernateUtil.getSessionFactory();
            if (criteria.equalsIgnoreCase("loan")) {
                return (Long) session.openSession()
                        .createQuery("select count(*) from CustomersRentals cs INNER JOIN cs.rentals WHERE date = '" + date + "' AND date_returned = NULL ")
                        .uniqueResult();
            } else {
                return (Long) session.openSession()
                        .createQuery("select count(*) from CustomersRentals cs INNER JOIN cs.rentals WHERE date = '" + date + "' AND date_returned != NULL ")
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
                        .createQuery("from CustomersRentals GROUP BY date ORDER BY date DESC ")
                        .setFirstResult(startpoint)
                        .setMaxResults(maxResults)
                        .list();
            } else {
                list = session.openSession()
                        .createQuery("from CustomersRentals WHERE date BETWEEN '" + first + "' AND '" + last + "' GROUP BY date ORDER BY date ASC ")
                        .setFirstResult(startpoint)
                        .setMaxResults(maxResults)
                        .list();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
