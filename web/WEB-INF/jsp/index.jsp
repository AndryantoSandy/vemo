<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html dir="ltr" lang="en">
    <head>
        <meta charset="UTF-8" />
        <title>Vemo</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0" />
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
        <link type="text/css" href="${pageContext.request.contextPath}/assets/css/login.css" rel="stylesheet" media="screen" />
        <script src="${pageContext.request.contextPath}/assets/js/common.js" type="text/javascript"></script>
    </head>
    <body>
        <div id="container">
            <header id="header" class="navbar navbar-static-top">
                <div class="navbar-header">
                    <a href="#" class="navbar-brand"></a>
                </div>
            </header>
            <div id="content">
                <div class="container-fluid">
                    <br />
                    <br />
                    <div class="row">
                        <div class="col-sm-offset-4 col-sm-4">
                            <div class="panel">
                                <div class="panel-heading">
                                    <h1 class="panel-title"><i class="fa fa-lock"></i> Please enter your login details.</h1>
                                </div>
                                <div class="panel-body">
                                    <c:if test="${!empty message}">
                                        <div class="alert alert-danger">
                                            <i class="fa fa-exclamation-circle"></i><small>${message}</small>
                                            <button type="button" class="close" data-dismiss="alert">&times;</button>
                                        </div>
                                    </c:if>
                                    <form:form  action="${pageContext.request.contextPath}/login" method="post" commandName="users">
                                        <div class="form-group">
                                            <label for="input-username">Username</label>
                                            <div class="input-group">
                                                <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                                <input type="text" name="username" placeholder="Username" id="input-username" class="form-control" required/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="input-password">Password</label>
                                            <div class="input-group">
                                                <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                                                <input type="password" name="password" placeholder="Password" id="input-password" class="form-control" required/>
                                            </div>
                                        </div>
                                        <div class="text-right">
                                            <button type="submit" name="submit" value="login" class="btn btn-default">
                                                <i class="fa fa-key"></i> Login
                                            </button>
                                        </div>
                                    </form:form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <footer id="footer">
                <a href="#">Vemo</a> &copy; 2015 All Rights Reserved.
                <br />
            </footer>
        </div>
    </body>
</html>
