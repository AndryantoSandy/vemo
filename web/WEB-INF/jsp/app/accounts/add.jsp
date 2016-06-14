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
                <form class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/accounts/save" commandName="accounts">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Payments</label>
                        <div class="col-sm-4">
                            <select name="payment_id" class="form-control">
                                <option></option>
                                <c:forEach var="payments"  items="${payments}">
                                    <option value="${payments.getId()}">${payments.getDescriptions()}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Customer Name</label>
                        <div class="col-sm-4">
                            <input type="hidden" class="form-control" id="customer_id"  name="customer_id" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Account Name</label>
                        <div class="col-sm-4">
                            <input type="text" name="name" class="form-control" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Details</label>
                        <div class="col-sm-4">
                            <textarea class="form-control" name="details" rows="5"></textarea>
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
<script type="text/javascript">
    $(document).ready(function() {

        $('#customer_id').select2({
            ajax: {
                placeholder: 'Type Customer',
                url: '${pageContext.request.contextPath}/json_cutomers',
                dataType: 'json',
                quietMillis: 100,
                data: function(term) {
                    return {
                        q: term, // search term
                    };
                },
                results: function(data) {
                    var myResults = [];
                    $.each(data, function(index, item) {
                        myResults.push({
                            'id': item[0],
                            'text': item[1]
                        });
                    });
                    return {
                        results: myResults
                    };
                },
                minimumInputLength: 3
            }
        });


    });
</script>
<%@ include file="/WEB-INF/jsp/layout/footer.jsp" %>
