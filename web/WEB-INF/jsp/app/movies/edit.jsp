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
                <form class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/movies/update" commandName="movies">
                    <input type="hidden" name="id" value="${data.getId()}"/>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Genre</label>
                        <div class="col-sm-4">
                            <select name="genre_id" class="form-control">
                                <option></option>
                                <c:forEach var="genres"  items="${genres}">
                                    <c:choose>
                                        <c:when test="${genres.getId()==data.getGenres().getId()}">
                                            <option value="${genres.getId()}" selected>${genres.getDetails()}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${genres.getId()}" >${genres.getDetails()}</option>
                                        </c:otherwise>
                                    </c:choose>
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
                                    <c:choose>
                                        <c:when test="${stores.getId()==data.getStores().getId()}">
                                            <option value="${stores.getId()}" selected>${stores.getName()}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${stores.getId()}">${stores.getName()}</option>
                                        </c:otherwise>
                                    </c:choose>
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
                                    <c:choose>
                                        <c:when test="${formats.getId()==data.getFormats().getId()}">
                                            <option value="${formats.getId()}" selected>${formats.getDetails()}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${formats.getId()}" >${formats.getDetails()}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Release Year</label>
                        <div class="col-sm-2">
                            <input type="number" class="form-control" min="1900" value="${data.getReleaseYear()}"  name="releaseYear"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Title</label>
                        <div class="col-sm-5">
                            <input type="text" name="title" class="form-control" value="${data.getTitle()}" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Description</label>
                        <div class="col-sm-4">
                            <textarea class="form-control" name="description" rows="5">${data.getDescription()}</textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Stock</label>
                        <div class="col-sm-2">
                            <input type="number" class="form-control" min="0" value="${data.getStock()}"  name="stock"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Rental Or Sale</label>
                        <div class="col-sm-4">
                            <div class="col-sm-10">
                                <c:choose>
                                    <c:when test="${data.getRentalOrSale()=='Sale'}">
                                        <label class="radio-inline">
                                            <input type="radio" name="rentalOrSale" value="Rental" /> Rental
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="rentalOrSale" checked="true" value="Sale" /> Sale
                                        </label>
                                    </c:when>
                                    <c:otherwise>
                                        <label class="radio-inline">
                                            <input type="radio" name="rentalOrSale" checked="true" value="Rental" /> Rental
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="rentalOrSale" value="Sale" /> Sale
                                        </label>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Price</label>
                        <div class="col-sm-2">
                            <input type="number" class="form-control" min="0" value="${data.getPrice()}"  name="price"/>
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
