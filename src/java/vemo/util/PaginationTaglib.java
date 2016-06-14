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

public class PaginationTaglib extends SimpleTagSupport {

    private String uri, filter, keyword;
    private int offset;
    private int count;
    private int max = 10;
    private int steps = 10;
    private String previous = "Previous";
    private String next = "Next";

    private Writer getWriter() {
        JspWriter out = getJspContext().getOut();
        return out;
    }

    @Override
    public void doTag() throws JspException {
        Writer out = getWriter();

        try {
            out.write("<nav>");
            out.write("<ul class=\"pagination\">");

            int per_page = 10;
            int total = count;
            int adjacents = 2;

            int page = (offset == 0 ? 1 : offset);
            int start = (page - 1) * per_page;

            int prev = page - 1;
            int next = page + 1;
            int lastpage = (int) Math.ceil((double) total / (double) per_page);
            int lpm1 = lastpage - 1;
            int counter = 0;

            if (lastpage > 1) {

                if (lastpage < 7 + (adjacents * 2)) {

                    for (counter = 1; counter <= lastpage; counter++) {
                        if (counter == page) {
                            out.write(constructLink(counter, String.valueOf(counter), "active", true, filter, keyword));
                        } else {
                            out.write(constructLink(counter, String.valueOf(counter), null, false, filter, keyword));
                        }
                    }

                } else if (lastpage > 5 + (adjacents * 2)) {
                    if (page < 1 + (adjacents * 2)) {
                        for (counter = 1; counter < 4 + (adjacents * 2); counter++) {
                            if (counter == page) {
                                out.write(constructLink(counter, String.valueOf(counter), "active", true, filter, keyword));
                            } else {
                                out.write(constructLink(counter, String.valueOf(counter), null, false, filter, keyword));
                            }
                        }
                        out.write(constructLink(0, String.valueOf("..."), "disabled", true, filter, keyword));
                        out.write(constructLink(lpm1, String.valueOf(lpm1), null, false, filter, keyword));
                        out.write(constructLink(lastpage, String.valueOf(lastpage), null, false, filter, keyword));
                    } else if (lastpage - (adjacents * 2) > page && page > (adjacents * 2)) {
                        out.write(constructLink(1, String.valueOf(1), null, false, filter, keyword));
                        out.write(constructLink(2, String.valueOf(2), null, false, filter, keyword));
                        out.write(constructLink(0, String.valueOf("..."), "disabled", true, filter, keyword));
                        for (counter = page - adjacents; counter <= page + adjacents; counter++) {
                            if (counter == page) {
                                out.write(constructLink(counter, String.valueOf(counter), "active", true, filter, keyword));
                            } else {
                                out.write(constructLink(counter, String.valueOf(counter), null, false, filter, keyword));
                            }
                        }
                        out.write(constructLink(0, String.valueOf("..."), "disabled", true, filter, keyword));
                        out.write(constructLink(lpm1, String.valueOf(lpm1), null, false, filter, keyword));
                        out.write(constructLink(lastpage, String.valueOf(lastpage), null, false, filter, keyword));
                    } else {
                        out.write(constructLink(1, String.valueOf(1), null, false, filter, keyword));
                        out.write(constructLink(2, String.valueOf(2), null, false, filter, keyword));
                        out.write(constructLink(0, String.valueOf("..."), "disabled", true, filter, keyword));
                        for (counter = lastpage - (2 + (adjacents * 2)); counter <= lastpage; counter++) {
                            if (counter == page) {
                                out.write(constructLink(counter, String.valueOf(counter), "active", true, filter, keyword));
                            } else {
                                out.write(constructLink(counter, String.valueOf(counter), null, false, filter, keyword));
                            }
                        }
                    }
                }

                if (page < counter - 1) {
                    out.write(constructLink(next, String.valueOf("Next"), null, false, filter, keyword));
                    out.write(constructLink(lastpage, String.valueOf("Last"), null, false, filter, keyword));
                } else {
                    out.write(constructLink(next, String.valueOf("Next"), "active", true, filter, keyword));
                    out.write(constructLink(lastpage, String.valueOf("Last"), "active", true, filter, keyword));
                }

            }

            out.write("</ul>");
            out.write("</nav>");
        } catch (java.io.IOException ex) {
            throw new JspException("Error in Paginator tag", ex);
        }
    }

    private String constructLink(int page, String text, String className, boolean disabled, String filter, String keyword) {
        StringBuilder link = new StringBuilder("<li");
        if (className != null) {
            link.append(" class=\"");
            link.append(className);
            link.append("\"");
        }
        if (disabled) {
            link.append(">").append("<a href=\"#\">" + text + "</a></li>");
        } else {
            if (filter.equalsIgnoreCase("") && keyword.equalsIgnoreCase("")) {
                link.append(">").append("<a href=\"" + uri + "?offset=" + page + "\">" + text + "</a></li>");
            } else {
                link.append(">").append("<a href=\"" + uri + "?offset=" + page + "&filter=" + filter + "&keyword=" + keyword + "\">" + text + "</a></li>");
            }
        }
        return link.toString();
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

}
