<%@ include file="/WEB-INF/jsp/layout/header.jsp" %>
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
                <form class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/rentals/update">
                    <input type="hidden" name="id" value="${data.getId()}"/>
                    <input type="hidden" class="form-control" id="user_id" name="user_id" value="<%=session.getAttribute("user_id")%>"/>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Customer Name</label>
                        <div class="col-sm-4">
                            <input type="hidden" name="customer_id" id="customer_id" value="${data.getRentals().getCustomers().getId()}" class="form-control">
                            <input type="text" readonly="true" name="customer_name" id="customer_name" value="${data.getRentals().getCustomers().getFullName()}" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Movie title</label>
                        <div class="col-sm-4">
                            <input type="hidden" name="movie_id" id="movie_id" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Status</label>
                        <div class="col-sm-4">
                            <c:choose>
                                <c:when test="${data.getRentals().getStatus()=='1'}">
                                    <label class="radio-inline">
                                        <input type="radio" name="status" value="1" checked="true" /> Rentals
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="status" value="2" /> Returned
                                    </label>
                                </c:when>
                                <c:otherwise>
                                    <label class="radio-inline">
                                        <input type="radio" name="status" value="Male" /> Rentals
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="status" value="Female" checked="true" /> Returned
                                    </label>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Date Out</label>
                        <div class="col-sm-4">                           
                            <div class="input-group date">
                                <input type="text" name="date_out" value="${data.getRentals().getDateOut()}"  data-date-format="YYYY-MM-DD" id="input-date-start" class="form-control" />
                                <span class="input-group-btn">
                                    <button type="button" class="btn btn-default"><i class="fa fa-calendar"></i></button>
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Date Returned</label>
                        <div class="col-sm-4">
                            <div class="input-group date">
                                <input type="text" name="date_returned" value="${data.getRentals().getDateReturned()}"  data-date-format="YYYY-MM-DD" id="input-date-start" class="form-control" />
                                <span class="input-group-btn">
                                    <button type="button" class="btn btn-default"><i class="fa fa-calendar"></i></button>
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Amount</label>
                        <div class="col-sm-4">
                            <input type="number" min="1"  name="amount" class="form-control" value="${data.getAmount()}" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Account Name</label>
                        <div class="col-sm-4">
                            <input type="hidden" name="account_id" id="account_id" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Transaction Type</label>
                        <div class="col-sm-4">
                            <select name="transaction_type_id" class="form-control" required>
                                <option></option>
                                <c:forEach var="transaction" items="${transaction}">
                                    <c:choose>
                                        <c:when test="${transaction.getId()==data.getTransactionTypes().getId()}">
                                            <option value="${transaction.getId()}" selected>${transaction.getDescription()}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${transaction.getId()}">${transaction.getDescription()}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Comments</label>
                        <div class="col-sm-4">
                            <textarea class="form-control" name="comment" rows="5">${data.getComment()}</textarea>
                        </div>
                    </div>
                    <div class="pull-right">
                        <button type="reset" data-toggle="tooltip" title="Reset Form" class="btn btn-warning"><i class="fa fa-refresh"></i> Reset</button>
                        <button type="submit" data-toggle="tooltip" title="Update" class="btn btn-primary"><i class="fa fa-save"></i> Update</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function() {


        $('#movie_id').select2({
            ajax: {
                placeholder: 'Type Movie Title',
                url: '${pageContext.request.contextPath}/json_movie',
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

        $('#account_id').select2({
            ajax: {
                placeholder: 'Type Account',
                url: '${pageContext.request.contextPath}/json_account',
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

        $('.date').datetimepicker({
            pickTime: false
        });

        $("#movie_id").select2("data", {id:${data.getRentals().getMovies().getId()}, text: "${data.getRentals().getMovies().getTitle()}"});
        $("#account_id").select2("data", {id:${data.getAccounts().getId()}, text: "${data.getAccounts().getName()}"});
    });
</script>
<%@ include file="/WEB-INF/jsp/layout/footer.jsp" %>