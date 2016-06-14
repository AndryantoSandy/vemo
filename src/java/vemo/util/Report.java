/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vemo.util;

import java.io.Writer;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import org.hibernate.SessionFactory;

/**
 *
 * @author Sandy
 */
public class Report extends SimpleTagSupport {

    private String criteria, date;

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private Writer getWriter() {
        JspWriter out = getJspContext().getOut();
        return out;
    }

    public Long GetReportRentals(String date, String criteria) {
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

    public Long GetReportRentals2(String date, String criteria) {
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

    @Override
    public void doTag() throws JspException {
        Writer out = getWriter();
        try {
            if (criteria.equalsIgnoreCase("rentals")) {
                out.write("<td>" + GetReportRentals(date, "account_id") + "</td>");
                out.write("<td>" + GetReportRentals(date, "movie_id") + "</td>");
                out.write("<td>" + GetReportRentals2(date, "loan") + "</td>");
                out.write("<td>" + GetReportRentals2(date, "return") + "</td>");
            } else {
                out.write("<td></td>");
                out.write("<td></td>");
                out.write("<td></td>");
                out.write("<td></td>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
