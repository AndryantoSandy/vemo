<%@ include file="/WEB-INF/jsp/layout/header.jsp" %>
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
                <h3 class="panel-title">Movies</h3>
            </div>
            <div class="panel-body">
                <form class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/movies/save" commandName="movies">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Genre</label>
                        <div class="col-sm-4">
                            <select name="genre_id" class="form-control">
                                <option></option>
                                <c:forEach var="genres"  items="${genres}">
                                    <option value="${genres.getId()}">${genres.getDetails()}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Store</label>
                        <div class="col-sm-4">
                            <select name="store_id" class="form-control">
                                <option></option>
                                <c:forEach var="stores" items="${stores}">
                                    <option value="${stores.getId()}">${stores.getDetails()}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Format</label>
                        <div class="col-sm-4">
                            <select name="format_id" class="form-control">
                                <option></option>
                                <c:forEach var="formats" items="${formats}">
                                    <option value="${formats.getId()}">${formats.getDetails()}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Release Year</label>
                        <div class="col-sm-2">
                            <input type="number" class="form-control" min="1900"  name="releaseYear"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Title</label>
                        <div class="col-sm-5">
                            <input type="text" name="title" class="form-control" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Description</label>
                        <div class="col-sm-4">
                            <textarea class="form-control" name="description" rows="5"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Stock</label>
                        <div class="col-sm-2">
                            <input type="number" class="form-control" min="0"  name="stock"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Rental Or Sale</label>
                        <div class="col-sm-4">
                            <div class="col-sm-10">
                                <label class="radio-inline">
                                    <input type="radio" name="rentalOrSale" value="Rental" /> Rental
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="rentalOrSale" value="Sale" /> Sale
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Price</label>
                        <div class="col-sm-2">
                            <input type="number" class="form-control" min="0"  name="price"/>
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
