<%@ include file="/WEB-INF/jsp/layout/header.jsp" %>
<div class="container-fluid">
    <div class="col-lg-12 col-md-12 col-sm-12 col-sx-12">
        <div class="panel-body">
            <div class="row">
                <div class="pull-right">
                    <a href="${pageContext.request.contextPath}/transaction_type/" data-toggle="tooltip" title="Back To Grid" class="btn btn-default"><i class="fa fa-refresh"></i> Back</a>
                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-12 col-md-12 col-sm-12 col-sx-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Transaction Type</h3>
            </div>
            <div class="panel-body">
                <form class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/transaction_type/save" commandName="genres">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Descriptions</label>
                        <div class="col-sm-6">
                            <textarea class="form-control" name="description" rows="5"></textarea>
                        </div>
                    </div>
                    <div class="pull-right">
                        <button type="reset" data-toggle="tooltip" title="Reset Form" class="btn btn-warning">
                            <i class="fa fa-refresh"></i> Reset
                        </button>
                        <button type="submit" data-toggle="tooltip" title="Save" class="btn btn-primary">
                            <i class="fa fa-save"></i> Save
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/layout/footer.jsp" %>
