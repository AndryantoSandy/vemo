<%@ include file="/WEB-INF/jsp/layout/header.jsp" %>

<div class="container-fluid">
    <div class="col-lg-12 col-md-12 col-sm-12 col-sx-12">
        <div class="panel-body">
            <div class="row">
                <div class="pull-right">
                    <a href="${pageContext.request.contextPath}/movies/add" data-toggle="tooltip" title="Add New" class="btn btn-default"><i class="fa fa-plus"></i> Add New</a>
                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-12 col-md-12 col-sm-12 col-sx-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Movies</h3>
            </div>
            <div class="panel-body">
                <div class="well">
                    <div class="row">
                        <div class="col-sm-12">
                            <form class="form-horizontal" action="${pageContext.request.contextPath}/movies/">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Filter By</label>
                                    <div class="col-sm-3">
                                        <select name="filter" class="form-control" required>
                                            <option value=""></option>
                                            <option value="m.title">Title</option>	
                                            <option value="m.genres.details">Genre</option>	
                                            <option value="m.stock">Stock</option>	
                                            <option value="m.price">Price</option>	
                                            <option value="m.formats.details">Format</option>	
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
                                        <a href="${pageContext.request.contextPath}/movies/" class="btn btn-success">
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
                                <th>Title</th>
                                <th>Genre</th>
                                <th>Stock</th>
                                <th>Price</th>
                                <th>Format</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="data" items="${data}" varStatus="itr">
                                <tr>
                                    <td>${((number*10)-9) + itr.index }</td>
                                    <td>${data[0].getTitle()}</td>
                                    <td>${data[2].getDetails()}</td>
                                    <td>${data[0].getStock()}</td>
                                    <td>${data[0].getPrice()}</td>
                                    <td>${data[1].getDetails()}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/movies/view/${data[0].getId()}"><i class="fa fa-pencil"></i></a>
                                        <a href="${pageContext.request.contextPath}/movies/edit/${data[0].getId()}"><i class="fa fa-wrench"></i></a>
                                        <a href="${pageContext.request.contextPath}/movies/delete/${data[0].getId()}" onclick="return confirm('Are you sure ?? ');"><i class="fa fa-trash-o"></i></a>
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
                          uri="${pageContext.request.contextPath}/movies" next="&raquo;" previous="&laquo;" />
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/layout/footer.jsp" %>



