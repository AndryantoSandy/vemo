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
                    <a href="${pageContext.request.contextPath}/movies/" data-toggle="tooltip" title="Back To Grid" class="btn btn-default"><i class="fa fa-refresh"></i> Back</a>
                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-12 col-md-12 col-sm-12 col-sx-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Details Movies</h3>
            </div>
            <div class="panel-body">
                <form class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/movies/save" commandName="movies">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Genre</label>
                        <div class="col-sm-4">
                            <label class="control-label">:</label>
                            <label class="control-label">${data.getGenres().getDetails()}</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Store</label>
                        <div class="col-sm-4">
                            <label class="control-label">:</label>
                            <label class="control-label">${data.getStores().getName()}</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Format</label>
                        <div class="col-sm-4">
                            <label class="control-label">:</label>
                            <label class="control-label">${data.getFormats().getDetails()}</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Release Year</label>
                        <div class="col-sm-2">
                            <label class="control-label">:</label>
                            <label class="control-label">${data.getReleaseYear()}</label>   
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Title</label>
                        <div class="col-sm-5">
                            <label class="control-label">:</label>
                            <label class="control-label">${data.getTitle()}</label> 
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Description</label>
                        <div class="col-sm-4">
                            <label class="control-label">:</label>
                            <label class="control-label">${data.getDescription()}</label> 
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Stock</label>
                        <div class="col-sm-2">
                            <label class="control-label">:</label>
                            <label class="control-label">${data.getStock()}</label> 
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Rental Or Sale</label>
                        <div class="col-sm-2">
                            <label class="control-label">:</label>
                            <label class="control-label">${data.getRentalOrSale()}</label> 
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Price</label>
                        <div class="col-sm-2">
                            <label class="control-label">:</label>
                            <label class="control-label">${data.getPrice()}</label> 
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/layout/footer.jsp" %>
