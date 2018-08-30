<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

             <div class="m-content">                
                <h2 class="m--font-success m--margin-bottom-10">
                	<span id="container_name"></span>
                	(<span id="container_id"></span>)
                	 - <span id="container_status"></span>
                </h2>
                
                <div class="btn-group m--margin-top-20" id="containerButtons">
					<a href="#" class="btn btn-primary" id="stat">Stats</a>
					<a href="#" class="btn btn-default" id="proc">Process</a>
					<a href="#" class="btn btn-default" id="net">Networks</a>
					<a href="#" class="btn btn-default" id="env">Environment</a>
					<a href="#" class="btn btn-default" id="vol">Volumes</a>
					<a href="#" class="btn btn-default" id="log">Logs</a>
				</div>
				<div id="body">
					<jsp:include page="/WEB-INF/views/v1/container/stat.jsp"/>
				</div>
            </div>					
<script>

$(document).ready(function(){ 		
	var con = ${con}; 
	$("#container_name").html(con.Name.substr(1));
	$("#container_id").html(con.Id.substr(0, 20));
	$("#container_status").html(con.State.Status);
	$('#containerButtons a').on('click', function(e){
		e.preventDefault();
		$(this).attr('class', 'btn btn-primary').siblings().attr('class', 'btn btn-default');
		var select_val = $(this).attr('id');
		getContainerById(select_val);
    });
});
 	
function getContainerById (type) {
	$.ajax({
		url: "${prefix}/containers/${container_id}/" + type,
		type: "GET",
		dataType: "html",
		success: function(data){
			$('#body').html(data);
		},
		error: function(request, status, error){
	        alert("code:"+request.status+"\n\n"+"message:"+request.responseText+"\n\n"+"error:"+error);
	    }
	});
}
</script>