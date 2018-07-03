<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="col-md-12">
	<!--begin:: Container Info-->
	<div class="m-portlet m-portlet--head-sm m-portlet--full-height ">
		<div class="m-portlet__head">
			<div class="m-portlet__head-caption">
				<div class="m-portlet__head-title">
					<h3 class="m-portlet__head-text">Volumes</h3>
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
	<!--end:: Container Info-->
</div>


<script>
	jQuery(document).ready(
		function() {		
			var vol = ${vol};
			var html = "";
			
			html += "<tr>";
			html += "  <td class='text-left'> - volumesBinds</td>";
			html += "  <td class='text-left'></td>";
			html += "</tr>";
			
			if (vol.HostConfig.Binds != null) {
				for(var i=0; i<vol.HostConfig.Binds.length;i++) {
					html += "<tr>";
					html += "  <td class='text-left'>" + "&emsp;&emsp;" + i + "</td>";
					html += "  <td class='text-left'>" + vol.HostConfig.Binds[i] + "</td>";
					html += "</tr>";			
				}
			}
			html += "<tr>";
			html += "  <td class='text-left'> VolumeDriver</td>";
			html += "  <td class='text-left'>" +vol.HostConfig.VolumeDriver +"</td>";
			html += "</tr>";
			
			$("#tbody").html(html);

		});
</script>