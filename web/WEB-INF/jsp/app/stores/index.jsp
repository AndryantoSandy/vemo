<%@ include file="/WEB-INF/jsp/layout/header.jsp" %>

<div class="container-fluid">
    <div class="col-lg-12 col-md-12 col-sm-12 col-sx-12">
        <div class="panel-body">
            <div class="row">
                <div class="pull-right">
                    <a href="${pageContext.request.contextPath}/stores/add" data-toggle="tooltip" title="Add New" class="btn btn-default"><i class="fa fa-plus"></i> Add New</a>
                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-12 col-md-12 col-sm-12 col-sx-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Stores</h3>
            </div>
            <div class="panel-body">
                <div class="well">
                    <div class="row">
                        <div class="col-sm-12">
                            <form class="form-horizontal" action="${pageContext.request.contextPath}/stores/">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Filter By</label>
                                    <div class="col-sm-3">
                                        <select name="filter" class="form-control" required>
                                            <option value=""></option>
                                            <option value="name">Store Name</option>	
                                            <option value="email">Email</option>		
                                            <option value="phone">Phone</option>	
                                            <option value="address">Address</option>	
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
                                        <a href="${pageContext.request.contextPath}/stores/" class="btn btn-success">
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
                                <th>Store Name</th>
                                <th>Phone</th>
                                <th>Email</th>
                                <th>Address</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="data" items="${data}" varStatus="itr">
                                <tr>
                                    <td>${((number*10)-9) + itr.index }</td>
                                    <td>${data.getName()}</td>
                                    <td>${data.getPhone()}</td>
                                    <td>${data.getEmail()}</td>
                                    <td>${data.getAddress()}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/stores/view/${data.getId()}"><i class="fa fa-pencil"></i></a>
                                        <a href="${pageContext.request.contextPath}/stores/edit/${data.getId()}"><i class="fa fa-wrench"></i></a>
                                        <a href="${pageContext.request.contextPath}/stores/delete/${data.getId()}" onclick="return confirm('Are you sure ?? ');"><i class="fa fa-trash-o"></i></a>
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
                          uri="${pageContext.request.contextPath}/stores" next="&raquo;" previous="&laquo;" />
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/layout/footer.jsp" %>



