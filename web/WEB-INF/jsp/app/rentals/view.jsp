<%@ include file="/WEB-INF/jsp/layout/header.jsp" %>
<c:if test="${!empty message}">
    <div class="container-fluid">
        <div class="col-lg-12 col-md-12 col-sm-12 col-sx-12">
            <div class="alert alert-success">
                <i class="fa fa-exclamation-circle"></i><small>${message}</small>
                <button type="button" class="close" data-dismiss="alert">
                    &times;
                </button>
            </div>
        </div>
    </div>
</c:if>
<div class="container-fluid">
    <div class="col-lg-12 col-md-12 col-sm-12 col-sx-12">
        <div class="panel-body">
            <div class="row">
                <div class="pull-right">
                    <a href="${pageContext.request.contextPath}/rentals/" data-toggle="tooltip" title="Back To Grid" class="btn btn-default"><i class="fa fa-refresh"></i> Back</a>
                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-12 col-md-12 col-sm-12 col-sx-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Details Rentals</h3>
            </div>
            <div class="panel-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Customer Name</label>
                        <div class="col-sm-6">
                            <label class="control-label">:</label>
                            <label class="control-label">${data.getRentals().getCustomers().getFullName()}</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Movie title</label>
                        <div class="col-sm-6">
                            <label class="control-label">:</label>
                            <label class="control-label">${data.getRentals().getMovies().getTitle()}</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Status</label>
                        <div class="col-sm-6">
                            <label class="control-label">:</label>
                            <c:choose>
                                <c:when test="${data.getRentals().getStatus()=='1'}">
                                    <small class="label label-danger">Rentals</small>
                                </c:when>
                                <c:otherwise>
                                    <small class="label label-success">Returned</small>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Date Out</label>
                        <div class="col-sm-6">
                            <label class="control-label">:</label>
                            <label class="control-label">${data.getRentals().getDateOut()}</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Date Returned</label>
                        <div class="col-sm-6">
                            <label class="control-label">:</label>
                            <label class="control-label">${data.getRentals().getDateReturned()}</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Amount</label>
                        <div class="col-sm-6">
                            <label class="control-label">:</label>
                            <label class="control-label">${data.getAmount()}</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Account Name</label>
                        <div class="col-sm-6">
                            <label class="control-label">:</label>
                            <label class="control-label">${data.getAccounts().getName()}</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Transaction Type</label>
                        <div class="col-sm-6">
                            <label class="control-label">:</label>
                            <label class="control-label">${data.getTransactionTypes().getDescription()}</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Comments</label>
                        <div class="col-sm-6">
                            <label class="control-label">:</label>
                            <label class="control-label">${data.getComment()}</label>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/layout/footer.jsp" %>