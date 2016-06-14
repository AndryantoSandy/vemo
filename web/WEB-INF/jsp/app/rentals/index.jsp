<%@ include file="/WEB-INF/jsp/layout/header.jsp" %>
<div class="container-fluid">
    <div class="col-lg-12 col-md-12 col-sm-12 col-sx-12">
        <div class="panel-body">
            <div class="row">
                <div class="pull-right">
                    <a href="${pageContext.request.contextPath}/rentals/add" data-toggle="tooltip" title="Add New" class="btn btn-default"><i class="fa fa-plus"></i> Add New</a>
                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-12 col-md-12 col-sm-12 col-sx-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Rentals</h3>
            </div>
            <div class="panel-body">
                <div class="well">
                    <div class="row">
                        <div class="col-sm-12">
                            <form class="form-horizontal" action="${pageContext.request.contextPath}/rentals/">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">First Date</label>
                                    <div class="col-sm-3">
                                        <div class="input-group date">
                                            <input type="text" name="first"  placeholder="First Date" data-date-format="YYYY-MM-DD" id="input-date-start" class="form-control" />
                                            <span class="input-group-btn">
                                                <button type="button" class="btn btn-default"><i class="fa fa-calendar"></i></button>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Last Date</label>
                                    <div class="col-sm-3">
                                        <div class="input-group date">
                                            <input type="text" name="last"  placeholder="Last Date" data-date-format="YYYY-MM-DD" id="input-date-start" class="form-control" />
                                            <span class="input-group-btn">
                                                <button type="button" class="btn btn-default"><i class="fa fa-calendar"></i></button>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label"></label>
                                    <div class="col-sm-3">
                                        <button type="submit" id="button-filter" class="btn btn-primary">
                                            <i class="fa fa-search"></i> Filter
                                        </button>
                                        <a href="${pageContext.request.contextPath}/rentals/" class="btn btn-success">
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
                                <th>Date</th>
                                <th>Account Name</th>
                                <th>Transaction Type</th>
                                <th>Movie Title</th>
                                <th>Amount</th>
                                <th>Comments</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="data" items="${data}" varStatus="itr">
                                <tr>
                                    <td>${((number*10)-9) + itr.index }</td>
                                    <td>${data.getDate()}</td>
                                    <td>${data.getAccounts().getName()}</td>
                                    <td>${data.getTransactionTypes().getDescription()}</td>
                                    <td>${data.getRentals().getMovies().getTitle()}</td>
                                    <td>${data.getAmount()}</td>
                                    <td>${data.getComment()}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/rentals/view/${data.getId()}"><i class="fa fa-pencil"></i></a>
                                        <a href="${pageContext.request.contextPath}/rentals/edit/${data.getId()}"><i class="fa fa-wrench"></i></a>
                                        <a href="${pageContext.request.contextPath}/rentals/delete/${data.getId()}" onclick="return confirm('Are you sure ?? ');"><i class="fa fa-trash-o"></i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>                    
        <div class="pagination">
            <tagDate:paginateDate max="100" first="${first}" last="${last}" offset="${offset}" count="${count}"
                                  uri="${pageContext.request.contextPath}/rentals" next="&raquo;" previous="&laquo;" />
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function() {
        $('.date').datetimepicker({
            pickTime: false
        });
    });
</script>
<%@ include file="/WEB-INF/jsp/layout/footer.jsp" %>



