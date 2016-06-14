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
                    <a href="${pageContext.request.contextPath}/payments/type/" data-toggle="tooltip" title="Back To Grid" class="btn btn-default"><i class="fa fa-refresh"></i> Back</a>
                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-12 col-md-12 col-sm-12 col-sx-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Genre Of Movies</h3>
            </div>
            <div class="panel-body">
                <form class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/payments/type/update" commandName="genres">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Descriptions</label>
                        <div class="col-sm-6">
                            <label class="control-label">:</label>
                            <label class="control-label">${data.getDescriptions()}</label>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/layout/footer.jsp" %>
