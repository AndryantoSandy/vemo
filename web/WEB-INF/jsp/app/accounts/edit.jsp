<%@ include file="/WEB-INF/jsp/layout/header.jsp" %>
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
                <h3 class="panel-title">Accounts</h3>
            </div>
            <div class="panel-body">
                <form class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/accounts/update" commandName="accounts">
                    <input type="hidden" class="form-control" name="id" value="${data.getId()}" />
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Payments</label>
                        <div class="col-sm-4">
                            <select name="payment_id" class="form-control">
                                <option></option>
                                <c:forEach var="payments"  items="${payments}">
                                    <c:choose>
                                        <c:when test="${payments.getId()==data.getPayments().getId()}">
                                            <option value="${payments.getId()}" selected="">${payments.getDescriptions()}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${payments.getId()}" selected="">${payments.getDescriptions()}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Customer Name</label>
                        <div class="col-sm-4">
                            <input type="hidden" class="form-control" id="customer_id"  name="customer_id" value="${data.getCustomers().getId()}" />
                            <input type="text" class="form-control"  value="${data.getCustomers().getFullName()}" readonly="true"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Account Name</label>
                        <div class="col-sm-4">
                            <input type="text" name="name" value="${data.getName()}" class="form-control" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Details</label>
                        <div class="col-sm-4">
                            <textarea class="form-control" name="details" rows="5">${data.getDetails()}</textarea>
                        </div>
                    </div>
                    <div class="pull-right">
                        <button type="reset" data-toggle="tooltip" title="Reset Form" class="btn btn-warning">
                            <i class="fa fa-refresh"></i> Reset
                        </button>
                        <button type="submit" data-toggle="tooltip" title="Save" class="btn btn-primary">
                            <i class="fa fa-save"></i> Update
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/layout/footer.jsp" %>
