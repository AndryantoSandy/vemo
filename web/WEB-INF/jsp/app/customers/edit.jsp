<%@ include file="/WEB-INF/jsp/layout/header.jsp" %>
<div class="container-fluid">
    <div class="col-lg-12 col-md-12 col-sm-12 col-sx-12">
        <div class="panel-body">
            <div class="row">
                <div class="pull-right">
                    <a href="${pageContext.request.contextPath}/customers/" data-toggle="tooltip" title="Back To Grid" class="btn btn-default"><i class="fa fa-refresh"></i> Back</a>
                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-12 col-md-12 col-sm-12 col-sx-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Customers</h3>
            </div>
            <div class="panel-body">
                <form class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/customers/update" commandName="customers">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Member Number</label>
                        <div class="col-sm-4">
                            <input type="text" value="${data.getMemberNumber()}" name="memberNumber" class="form-control" />
                            <input type="hidden" name="id" value="${data.getId()}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Customers Name</label>
                        <div class="col-sm-6">
                            <input type="text" name="fullName" value="${data.getFullName()}" class="form-control" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Gender</label>
                        <div class="col-sm-10">
                            <c:choose>
                                <c:when test="${data.getGender()=='Male'}">
                                    <label class="radio-inline">
                                        <input type="radio" name="gender" value="Male" checked="true" /> Male
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="gender" value="Female" /> Female
                                    </label>
                                </c:when>
                                <c:otherwise>
                                    <label class="radio-inline">
                                        <input type="radio" name="gender" value="Male" /> Male
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="gender" value="Female" checked="true" /> Female
                                    </label>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Phone</label>
                        <div class="col-sm-6">
                            <input type="text" name="phone" value="${data.getPhone()}" class="form-control" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Email</label>
                        <div class="col-sm-6">
                            <input type="email" name="email" value="${data.getEmail()}" class="form-control" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Address</label>
                        <div class="col-sm-6">
                            <textarea class="form-control" name="address" rows="5">${data.getAddress()}</textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Details</label>
                        <div class="col-sm-6">
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
