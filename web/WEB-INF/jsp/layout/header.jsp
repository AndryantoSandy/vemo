<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="tag" uri="/WEB-INF/taglibs/customTaglib.tld"%>
<%@taglib prefix="tagDate" uri="/WEB-INF/taglibs/customTaglibDate.tld"%>
<%@taglib prefix="report" uri="/WEB-INF/taglibs/reportTaglib.tld"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html dir="ltr" lang="en">
    <head>
        <meta charset="UTF-8" />
        <title>Vemo</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0" />
        <script type="text/javascript"> var base_url = "${pageContext.request.contextPath}/";</script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-2.1.1.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/bootstrap/js/bootstrap.min.js"></script>
        <link href="${pageContext.request.contextPath}/assets/js/bootstrap/less/bootstrap.less" rel="stylesheet/less" />
        <script src="${pageContext.request.contextPath}/assets/js/bootstrap/less-1.7.4.min.js"></script>
        <link href="${pageContext.request.contextPath}/assets/js/font-awesome/css/font-awesome.min.css" type="text/css" rel="stylesheet" />
        <link href="${pageContext.request.contextPath}/assets/js/summernote/summernote.css" rel="stylesheet" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/summernote/summernote.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/jquery/datetimepicker/moment.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/js/jquery/datetimepicker/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
        <link href="${pageContext.request.contextPath}/assets/js/jquery/datetimepicker/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" media="screen" />
        <link type="text/css" href="${pageContext.request.contextPath}/assets/css/stylesheet.css" rel="stylesheet" media="screen" />
        <link type="text/css" href="${pageContext.request.contextPath}/assets/css/select2.css" rel="stylesheet" media="screen" />
        <link type="text/css" href="${pageContext.request.contextPath}/assets/css/select2-bootstrap.css" rel="stylesheet" media="screen" />
        <script src="${pageContext.request.contextPath}/assets/js/jquery/select2.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/js/jquery/date.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/js/common.js" type="text/javascript"></script>
    </head>
    <body>
        <div id="container">
            <header id="header" class="navbar navbar-static-top">
                <div class="navbar-header">
                    <a type="button" id="button-menu" class="pull-left"><i class="fa fa-indent fa-lg"></i></a>
                </div>
                <ul class="nav pull-right">
                    <li>
                        <a href="${pageContext.request.contextPath}/logout"><span class="hidden-xs hidden-sm hidden-md"> Logout </span> <i class="fa fa-sign-out fa-lg"></i></a>
                    </li>
                </ul>
            </header>
            <nav id="column-left">
                <div id="profile">
                    <div>
                        <a class="dropdown-toggle" data-toggle="dropdown"></a>
                    </div>
                    <div>
                        <h4>
                            <%

                                String url = request.getRequestURL().toString();
                                String baseURL = url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath() + "/";
                                if (session.getAttribute("username") == null || session.getAttribute("username").equals("")) {
                                    response.sendRedirect(baseURL + "/logout");
                                }

                                String username = String.valueOf(session.getAttribute("username"));
                                out.print(username.toUpperCase());
                            %>
                        </h4>
                        <small>Administrator</small>
                    </div>
                </div>
                <ul id="menu">
                    <li id="dashboard">
                        <a href="${pageContext.request.contextPath}/dashboard"><i class="fa fa-dashboard fa-fw"></i> <span>Dashboard</span></a>
                    </li>
                    <li>
                        <a class="parent"><i class="fa fa fa-folder-o fa-fw"></i> <span>Catalog</span></a>
                        <ul>
                            <li>
                                <a href="${pageContext.request.contextPath}/customers">Customers</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/stores">Stores</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/movies">Movies</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/genres">Movie Genres</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/formats">Movie Format</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/actors">Actors</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/users">Users</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a class="parent"><i class="fa fa-tasks fa-fw"></i> <span>Financial</span></a>
                        <ul>
                            <li>
                                <a href="${pageContext.request.contextPath}/accounts">Account</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/transaction_type">Transaction Type</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/payments/type">Payment Type</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/rentals">Rentals</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a class="parent"><i class="fa fa-line-chart fa-fw"></i> <span>Reports</span></a>
                        <ul>
                            <li>
                                <a href="${pageContext.request.contextPath}/report/rentals">Rentals</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/report/financials">Financial</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </nav>
            <div id="content">
                <div class="page-header">
                    <div class="container-fluid">
                        <h1>Vemo</h1>
                        <ul class="breadcrumb">
                            <li>
                                <a href="#">Video Rental Application</a>
                            </li>
                            <li>
                                <a href="#">&copy; <%= session.getAttribute("year")%></a>
                            </li>
                        </ul>
                    </div>   