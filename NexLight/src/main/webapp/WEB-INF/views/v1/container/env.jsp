<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="col-md-12">
	<div class="m-portlet m-portlet--head-sm m-portlet--full-height ">
		<div class="m-portlet__head">
			<div class="m-portlet__head-caption">
				<div class="m-portlet__head-title">
					<h3 class="m-portlet__head-text">Environment</h3>
				</div>
			</div>
		</div>
		<div class="m-portlet__body m-portlet__body--no-padding">
			<!-- <table class="table m-table"> -->
			<table class="table m-table">
				<colgroup>
					<col />
					<col />
				</colgroup>
				<thead>
					<tr>
						<th class="text-left">Attribute Name</th>
						<th class="text-left">Values</th>
					</tr>
				</thead>
				<tbody id="tbody">

				</tbody>
			</table>
		</div>
	</div>
</div>


<script>
	jQuery(document).ready(
		function() {		
			var env = ${env};
			var html = "";
			
			for(var i=0; i<env.Config.Env.length;i++) {
				var result = env.Config.Env[i].split("=");
				
				html += "<tr>";
				html += "  <td class='text-left'>" + result[0] + "</td>";
				html += "  <td class='text-left'>" + result[1] + "</td>";
				html += "</tr>";			
			}
			$("#tbody").html(html);

		});
</script>