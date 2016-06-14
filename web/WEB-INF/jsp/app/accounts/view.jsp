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
                    <a href="${pageContext.request.contextPath}/accounts/" data-toggle="tooltip" title="Back To Grid" class="btn btn-default"><i class="fa fa-refresh"></i> Back</a>
                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-12 col-md-12 col-sm-12 col-sx-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Details Accounts</h3>
            </div>
            <div class="panel-body">
                <form class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/accounts/save" commandName="movies">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Account Name</label>
                        <div class="col-sm-4">
                            <label class="control-label">:</label>
                            <label class="control-label">${data.getName()}</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Customer Name</label>
                        <div class="col-sm-4">
                            <label class="control-label">:</label>
                            <label class="control-label">${data.getCustomers().getFullName()}</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Payment Type</label>
                        <div class="col-sm-4">
                            <label class="control-label">:</label>
                            <label class="control-label">${data.getPayments().getDescriptions()}</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Details</label>
                        <div class="col-sm-4">
                            <label class="control-label">:</label>
                            <label class="control-label">${data.getDetails()}</label>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/layout/footer.jsp" %>
