<%@ include file="/WEB-INF/jsp/layout/header.jsp" %>

<div class="container-fluid">
    <div class="col-lg-12 col-md-12 col-sm-12 col-sx-12">
        <div class="panel-body">
            <div class="row">
                <div class="pull-right">
                    <a href="${pageContext.request.contextPath}/actors/add" data-toggle="tooltip" title="Add New" class="btn btn-default"><i class="fa fa-plus"></i> Add New</a>
                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-12 col-md-12 col-sm-12 col-sx-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Actors</h3>
            </div>
            <div class="panel-body">
                <div class="well">
                    <div class="row">
                        <div class="col-sm-12">
                            <form class="form-horizontal" action="${pageContext.request.contextPath}/actors/">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Filter By</label>
                                    <div class="col-sm-3">
                                        <select name="filter" class="form-control" required>
                                            <option value=""></option>
                                            <option value="first_name">First Name</option>	
                                            <option value="last_name">Last Name</option>	
                                            <option value="gender">Gender</option>	
                                            <option value="details">Phone</option>	
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Keyword</label>
                                    <div class="col-sm-6">
                                        <input type="text" placeholder="Please enter searching keyword"  name="keyword" class="form-control" required/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label"></label>
                                    <div class="col-sm-3">
                                        <button type="submit" id="button-filter" class="btn btn-primary">
                                            <i class="fa fa-search"></i> Filter
                                        </button>
                                        <a href="${pageContext.request.contextPath}/actors/" class="btn btn-success">
                                            <i class="fa fa-refresh"></i> Reset
                                        </a>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

                <c:if test="${!empty message}">
                    <div class="alert alert-success">
                        <i class="fa fa-exclamation-circle"></i><small>${message}</small>
                        <button type="button" class="close" data-dismiss="alert">
                            &times;
                        </button>
                    </div>
                </c:if>


                <div class="table-responsive">
                    <table class="table table-bordered table-hover">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Gender</th>
                                <th>Details</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="data" items="${data}" varStatus="itr">
                                <tr>
                                    <td>${((number*10)-9) + itr.index }</td>
                                    <td>${data.getFirstName()}</td>
                                    <td>${data.getLastName()}</td>
                                    <td>${data.getGender()}</td>
                                    <td>${data.getDetails()}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/actors/view/${data.getId()}"><i class="fa fa-pencil"></i></a>
                                        <a href="${pageContext.request.contextPath}/actors/edit/${data.getId()}"><i class="fa fa-wrench"></i></a>
                                        <a href="${pageContext.request.contextPath}/actors/delete/${data.getId()}" onclick="return confirm('Are you sure ?? ');"><i class="fa fa-trash-o"></i></a>
                                    </td>
                                </tr>
                            </c:forEach>    
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="pagination">
            <tag:paginate max="100" filter="${filter}" keyword="${keyword}" offset="${offset}" count="${count}"
                          uri="${pageContext.request.contextPath}/actors" next="&raquo;" previous="&laquo;" />
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/layout/footer.jsp" %>



