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
                <h3 class="panel-title">Rental of Movies</h3>
            </div>
            <div class="panel-body">
                <form class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/rentals/save">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Date Rentals</label>
                        <div class="col-sm-2">
                            <input type="text" class="form-control" name="date" value="${now}" readonly="true"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Transaction Type</label>
                        <div class="col-sm-2">
                            <select name="transaction_type_id" class="form-control" required>
                                <option></option>
                                <c:forEach var="transaction" items="${transaction}">
                                    <option value="${transaction.getId()}">${transaction.getDescription()}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Account Name</label>
                        <div class="col-sm-3">
                            <input type="hidden" class="form-control" id="account_id"  name="account_id" />
                            <input type="hidden" class="form-control" id="customer_id"  name="customer_id" />
                            <input type="hidden" class="form-control" id="user_id" name="user_id" value="<%=session.getAttribute("user_id")%>"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Comment</label>
                        <div class="col-sm-5">
                            <textarea class="form-control" name="comment" rows="5"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label"></label>
                        <div class="col-sm-3">
                            <a href="javascript:void(0)" onclick="add()" title="Add New Movie" class="btn btn-success"><i class="fa fa-plus"></i> Add Movie</a>
                            <a href="${pageContext.request.contextPath}/rentals/add" class="btn btn-warning">
                                <i class="fa fa-refresh"></i> Reset
                            </a>
                        </div>
                    </div>
                    <div class="table-responsive">
                        <table class="table table-bordered table-hover" id="TData">
                            <thead>
                                <tr>
                                    <th>Movie Title</th>
                                    <th>Published</th>
                                    <th>Stock</th>
                                    <th>Price</th>
                                    <th>Qty</th>
                                    <th>Amount</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>
                    <div class="pull-right">
                        <button type="reset" data-toggle="tooltip" title="Reset Form" class="btn btn-warning"><i class="fa fa-refresh"></i> Reset</button>
                        <button type="submit" data-toggle="tooltip" title="Save" class="btn btn-primary"><i class="fa fa-save"></i> Save</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">

    function deleteRow(btn) {
        var row = btn.parentNode.parentNode;
        row.parentNode.removeChild(row);
    }

    function add() {
        var row = $('#TData tbody tr').length;
        if (row > 0) {
            var index = $('.index').last().attr('id');
            index = parseInt(index);
            row = parseInt(row);
            row = index + 1;
        }
        var html = '<tr id="' + row + '" class="index">';
        html += '<td style="width:25%"><input type="hidden" id="movie_id' + row + '" name="movie_id" class="form-control" onChange="javascript:GetDetail(this,' + row + ')" required/></td>';
        html += '<td><input type="text" id="published' + row + '" readonly="true" class="form-control"/></td>';
        html += '<td><input type="text" id="stock' + row + '" readonly="true" class="form-control"/></td>';
        html += '<td><input type="text" id="price' + row + '" readonly="true" class="form-control"/></td>';
        html += '<td><input min="1" type="number" id="qty' + row + '" onChange="javascript:GetAmount(this,' + row + ')"  class="form-control"/></td>';
        html += '<td><input type="text" id="amount' + row + '" name="amount_id" readonly="true"  class="form-control"/></td>';
        html += '<td><a href="javascript:void(0)" onclick="javascript:deleteRow(this)" class="btn btn-danger"><i class="fa fa-times"></i></a></td>';
        html += '</tr>';
        $('#TData tbody').append(html);
        $('#movie_id' + row).select2({
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

    }


    function GetDetail(x, row) {
        var id = $(x).attr('id');
        var value = $('#' + id).val();
        $.ajax({
            url: '${pageContext.request.contextPath}/json_movie_get?id=' + value,
            dataType: 'json',
            async: false,
            success: function(data) {
                var list = eval(data);
                $('#published' + row).val(list.releaseYear);
                $('#stock' + row).val(list.stock);
                $('#price' + row).val(list.stock);
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                alert('Request Error !!, Please try again !!');
            },
        })
    }

    function GetAmount(x, row) {
        var id = $(x).attr('id');
        var value = $('#' + id).val();
        var price = $('#price' + row).val();
        price = parseInt(price);
        value = parseInt(value);
        var amount = price * value;
        $('#amount' + row).val(amount);
    }


    $(document).ready(function() {

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

        $('#account_id').change(function() {
            var id = $(this).val();
            $.ajax({
                url: '${pageContext.request.contextPath}/json_account_customer?id=' + id,
                dataType: 'json',
                async: false,
                success: function(data) {
                    var list = eval(data);
                    $('#customer_id').val(list.id);
                },
                error: function(XMLHttpRequest, textStatus, errorThrown) {
                    alert('Request Error !!, Please try again !!');
                },
            })
        });


    });


</script>
<%@ include file="/WEB-INF/jsp/layout/footer.jsp" %>