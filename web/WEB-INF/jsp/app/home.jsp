<%@ include file="/WEB-INF/jsp/layout/header.jsp" %>
<c:if test="${!empty message}">
    <div class="container-fluid">
        <div class="alert alert-success">
            <i class="fa fa-exclamation-circle"></i><small>${message}</small>
            <button type="button" class="close" data-dismiss="alert">
                &times;
            </button>
        </div>
    </div>
</c:if>
<div class="container-fluid">
    <div class="row">
        <div class="col-lg-3 col-md-3 col-sm-6">
            <div class="tile">
                <div class="tile-heading">
                    Total Customers
                </div>
                <div class="tile-body">
                    <i class="fa fa-users"></i>
                    <h2 class="pull-right">${customers}</h2>
                </div>
                <div class="tile-footer">
                    <a target="_blank" href="${pageContext.request.contextPath}/customers">View more..</a>
                </div>
            </div>
        </div>
        <div class="col-lg-3 col-md-3 col-sm-6">
            <div class="tile">
                <div class="tile-heading">
                    Total Rentals
                </div>
                <div class="tile-body">
                    <i class="fa fa-credit-card"></i>
                    <h2 class="pull-right">${rentals}</h2>
                </div>
                <div class="tile-footer">
                    <a target="_blank" href="${pageContext.request.contextPath}/rentals">View more...</a>
                </div>
            </div>
        </div>
        <div class="col-lg-3 col-md-3 col-sm-6">
            <div class="tile">
                <div class="tile-heading">
                    Total Movies
                </div>
                <div class="tile-body">
                    <i class="fa fa-home"></i>
                    <h2 class="pull-right">${movies}</h2>
                </div>
                <div class="tile-footer">
                    <a target="_blank" href="${pageContext.request.contextPath}/movies">View more...</a>
                </div>
            </div>
        </div>
        <div class="col-lg-3 col-md-3 col-sm-6">
            <div class="tile">
                <div class="tile-heading">
                    Total Stores
                </div>
                <div class="tile-body">
                    <i class="fa fa-bell"></i>
                    <h2 class="pull-right">${stores}</h2>
                </div>
                <div class="tile-footer">
                    <a target="_blank" href="${pageContext.request.contextPath}/stores">View more...</a>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12 col-md-12 col-sm-12 col-sx-12">
            <script language="javascript" type="text/javascript" src="assets/js/jquery/flot/jquery.flot.js"></script>
            <script language="javascript" type="text/javascript" src="assets/js/jquery/flot/jquery.flot.categories.js"></script>
            <script language="javascript" type="text/javascript" src="assets/js/jquery/flot/jquery.flot.resize.min.js"></script>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="fa fa-bar-chart"></i> Charts Of 2015</h3>
                </div>
                <div class="panel-body">
                    <div id="placeholder" class="demo-placeholder" style="height:300px;"></div>
                </div>

                <script type="text/javascript">
                    $(function() {

                        var data = [
                            ["January", ${chart1}],
                            ["February", ${chart2}],
                            ["March", ${chart3}],
                            ["April", ${chart4}],
                            ["May", ${chart5}],
                            ["June", ${chart6}],
                            ["July", ${chart7}],
                            ["August", ${chart8}],
                            ["September", ${chart9}],
                            ["October", ${chart10}],
                            ["November", ${chart11}],
                            ["December", ${chart12}],
                        ];

                        $.plot("#placeholder", [data], {
                            series: {
                                bars: {
                                    show: true,
                                    barWidth: 0.6,
                                    align: "center"
                                }
                            },
                            xaxis: {
                                mode: "categories",
                                tickLength: 0
                            }
                        });

                    });

                </script>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12 col-md-12 col-sm-12 col-sx-12">
            <div class="panel panel-default">
                <link type="text/css" href="assets/js/jquery/jqvmap/jqvmap.css" rel="stylesheet" media="screen" />
                <script type="text/javascript" src="assets/js/jquery/jqvmap/jquery.vmap.js"></script>
                <script type="text/javascript" src="assets/js/jquery/jqvmap/maps/jquery.vmap.world.js"></script>
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="fa fa-road"></i> Locations</h3>
                </div>
                <div class="panel-body">
                    <div id="vmap" style="width: 100%; height: 300px;"></div>
                </div>
                <script type="text/javascript">
                    $(document).ready(function() {
                        jQuery('#vmap').vectorMap({
                            map: 'world_en',
                            backgroundColor: '#a5bfdd',
                            borderColor: '#818181',
                            borderOpacity: 0.25,
                            borderWidth: 1,
                            color: '#f4f3f0',
                            enableZoom: true,
                            hoverColor: '#c9dfaf',
                            hoverOpacity: null,
                            normalizeFunction: 'linear',
                            scaleColors: ['#b6d6ff', '#005ace'],
                            selectedColor: '#c9dfaf',
                            selectedRegion: null,
                            showTooltip: true,
                            onRegionClick: function(element, code, region) {
                                var message = 'You clicked "' + region + '" which has the code: ' + code.toUpperCase();

                                alert(message);
                            }
                        });
                    });
                </script>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12 col-md-12 col-sm-12 col-sx-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="fa fa-external-link"></i> Latest Rentals</h3>
                </div>
                <div class="table-responsive">
                    <table class="table table-bordered table-hover">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Date</th>
                                <th>Account Name</th>
                                <th>Transaction Type</th>
                                <th>Movie Title</th>
                                <th>Amount</th>
                                <th>Comments</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="data" items="${data}" varStatus="itr">
                                <tr>
                                    <td>${(itr.index+1)}</td>
                                    <td>${data.getDate()}</td>
                                    <td>${data.getAccounts().getName()}</td>
                                    <td>${data.getTransactionTypes().getDescription()}</td>
                                    <td>${data.getRentals().getMovies().getTitle()}</td>
                                    <td>${data.getAmount()}</td>
                                    <td>${data.getComment()}</td>
                                    <td>
                                        <a target="_blank" href="${pageContext.request.contextPath}/rentals/view/${data.getId()}"><i class="fa fa-pencil"></i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="pagination">
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/layout/footer.jsp" %>