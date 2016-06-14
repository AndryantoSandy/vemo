/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vemo.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.ModelAndView;
import vemo.model.dao.ReportDao;
import vemo.model.entity.Book;
import vemo.model.entity.CustomersRentals;
import vemo.util.Helper;

/**
 *
 * @author Sandy
 */
@Controller
public class ReportController {

    private Helper helper = new Helper();

    @RequestMapping(value = "report/rentals", method = RequestMethod.GET)
    public String rentals(ModelMap map, Integer offset, Integer maxResults,
            @RequestParam(value = "first", required = false) String first,
            @RequestParam(value = "last", required = false) String last,
            HttpServletRequest request) {
        List<CustomersRentals> list = ReportDao.list(offset, maxResults, first, last);
        int number = 0;
        if (offset == null || offset == 0) {
            number = 1;
        } else {
            number = offset;
        }
        map.put("first", first);
        map.put("last", last);
        map.put("number", number);
        map.put("count", list.size());
        map.put("offset", offset);
        map.put("data", list);
        return "app/report/rentals";
    }

    @RequestMapping(value = "report/RentalsPDF", produces = "application/pdf")
    @ResponseBody
    public String RentalsPDF(ModelMap model, HttpServletResponse response, HttpServletRequest request,
            Integer maxResults,
            @RequestParam(value = "first", required = false) String first,
            @RequestParam(value = "last", required = false) String last) throws DocumentException, IOException {
        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        document.addTitle("Report Of Rentals");
        document.addSubject("Report");
        document.addKeywords("Report Of Rentals");
        document.addAuthor("Sandy Andryanto");
        document.addCreator("Sandy Andryanto");

        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" REPORT OF RENTALS "));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(6);
        table.addCell("No");
        table.addCell("Date");
        table.addCell("Account Count");
        table.addCell("Movie Count");
        table.addCell("Total Loan");
        table.addCell("Total Returned");

        List<CustomersRentals> list = ReportDao.list(0, maxResults, first, last);
        for (int i = 0; i < list.size(); i++) {
            table.addCell("" + (i + 1));
            table.addCell("" + list.get(i).getDate());
            table.addCell("" + ReportDao.GetReportRentals("" + list.get(i).getDate(), "account_id"));
            table.addCell("" + ReportDao.GetReportRentals("" + list.get(i).getDate(), "movie_id"));
            table.addCell("" + ReportDao.GetReportRentals2("" + list.get(i).getDate(), "loan"));
            table.addCell("" + ReportDao.GetReportRentals2("" + list.get(i).getDate(), "return"));
        }

        document.add(table);
        document.close();

        return "RentalsPDF";
    }

    @RequestMapping(value = "report/financials", method = RequestMethod.GET)
    public String financials(ModelMap map, Integer offset, Integer maxResults,
            @RequestParam(value = "first", required = false) String first,
            @RequestParam(value = "last", required = false) String last,
            HttpServletRequest request) {
        List<CustomersRentals> list = ReportDao.list(offset, maxResults, first, last);
        int number = 0;
        if (offset == null || offset == 0) {
            number = 1;
        } else {
            number = offset;
        }
        map.put("first", first);
        map.put("last", last);
        map.put("number", number);
        map.put("count", list.size());
        map.put("offset", offset);
        map.put("data", list);
        return "app/report/financials";
    }

    @RequestMapping(value = "report/FinancialsPDF", produces = "application/pdf")
    @ResponseBody
    public String FinancialsPDF(ModelMap model, HttpServletResponse response, HttpServletRequest request,
            Integer maxResults,
            @RequestParam(value = "first", required = false) String first,
            @RequestParam(value = "last", required = false) String last) throws DocumentException, IOException {
        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        document.addTitle("Report Of Financials");
        document.addSubject("Report");
        document.addKeywords("Report Of Financials");
        document.addAuthor("Sandy Andryanto");
        document.addCreator("Sandy Andryanto");

        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" REPORT OF FINANCIALS "));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(5);
        table.addCell("No");
        table.addCell("Date");
        table.addCell("Customer Name");
        table.addCell("Movie Title");
        table.addCell("Amount");

        int SUM = 0;
        List<CustomersRentals> list = ReportDao.list(0, maxResults, first, last);
        for (int i = 0; i < list.size(); i++) {
            table.addCell("" + (i + 1));
            table.addCell("" + list.get(i).getDate());
            table.addCell("" + list.get(i).getRentals().getCustomers().getFullName());
            table.addCell("" + list.get(i).getRentals().getMovies().getTitle());
            table.addCell("" + list.get(i).getAmount());
            SUM += list.get(i).getAmount();
        }

        table.addCell("");
        table.addCell("");
        table.addCell("TOTAL");
        table.addCell("");
        table.addCell("$ " + SUM);

        document.add(table);
        document.close();

        return "RentalsPDF";
    }

}
