/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vemo.util;

import java.security.MessageDigest;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Sandy
 */
public class Helper {

    public String md5(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte byteData[] = md.digest();
            //convert the byte to hex format method 1
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public String DateNow() {
        Date tgl_sekarang = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String tgl = format.format(tgl_sekarang);
        return tgl;
    }

    public String Time() {
        Date tgl_sekarang = new Date();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        String tgl = format.format(tgl_sekarang);
        return tgl;
    }

    public String Currency(int number) {
        NumberFormat n = NumberFormat.getCurrencyInstance(new Locale("in", "ID"));
        String s = n.format(number);
        return s;
    }

    public Date convertStringToDate(String strDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date tanggal = null;
        try {
            tanggal = sdf.parse(strDate);
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
        return tanggal;
    }

}
