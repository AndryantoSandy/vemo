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
                    <a href="${pageContext.request.contextPath}/stores/" data-toggle="tooltip" title="Back To Grid" class="btn btn-default"><i class="fa fa-refresh"></i> Back</a>
                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-12 col-md-12 col-sm-12 col-sx-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Store</h3>
            </div>
            <div class="panel-body">
                <form class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/stores/update" commandName="stores">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Store Name</label>
                        <div class="col-sm-6">
                            <label class="control-label">:</label>
                            <label class="control-label">${data.getName()}</label>
                        </div>
                    </div>  
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Phone</label>
                        <div class="col-sm-6">
                            <label class="control-label">:</label>
                            <label class="control-label">${data.getPhone()}</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Email</label>
                        <div class="col-sm-6">
                            <label class="control-label">:</label>
                            <label class="control-label">${data.getEmail()}</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Address</label>
                        <div class="col-sm-6">
                            <label class="control-label">:</label>
                            <label class="control-label">${data.getAddress()}</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Details</label>
                        <div class="col-sm-6">
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
