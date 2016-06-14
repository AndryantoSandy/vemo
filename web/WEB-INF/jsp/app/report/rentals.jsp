<%@ include file="/WEB-INF/jsp/layout/header.jsp" %>
<div class="container-fluid">
    <div class="col-lg-12 col-md-12 col-sm-12 col-sx-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Report Of Rentals</h3>
            </div>
            <div class="panel-body">
                <div class="well">
                    <div class="row">
                        <div class="col-sm-12">
                            <form class="form-horizontal" action="${pageContext.request.contextPath}/report/rentals">
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
                                        <a href="${pageContext.request.contextPath}/report/rentals" class="btn btn-success">
                                            <i class="fa fa-refresh"></i> Reset
                                        </a>
                                        <c:choose>
                                            <c:when test="${first}">
                                                <a target="_blank" href="${pageContext.request.contextPath}/report/RentalsPDF?first=${first}&last=${last}" class="btn btn-warning">
                                                    <i class="fa fa-print"></i> Print
                                                </a>
                                            </c:when>
                                            <c:otherwise>
                                                <a target="_blank" href="${pageContext.request.contextPath}/report/RentalsPDF" class="btn btn-warning">
                                                    <i class="fa fa-print"></i> Print
                                                </a>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

                <div class="table-responsive">
                    <table class="table table-bordered table-hover">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Date</th>
                                <th>Account Count</th>
                                <th>Movie Count</th>
                                <th>Total Loan</th>
                                <th>Total Returned</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="data" items="${data}" varStatus="itr">
                                <tr>
                                    <td>${((number*10)-9) + itr.index }</td>
                                    <td>${data.getDate()}</td>
                                    <report:report date="${data.getDate()}" criteria="rentals" />
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>                    
        <div class="pagination">
            <tagDate:paginateDate max="100" first="${first}" last="${last}" offset="${offset}" count="${count}"
                                  uri="${pageContext.request.contextPath}/report/rentals" next="&raquo;" previous="&laquo;" />
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



