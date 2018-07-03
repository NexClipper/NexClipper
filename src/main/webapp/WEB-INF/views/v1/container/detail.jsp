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
			
	        
			if		(select_val == "stat")	fnStat();
			else if	(select_val == "proc")	fnProcess();
			else if	(select_val == "net") 	fnNet();
			else if	(select_val == "env") 	fnEnv();
			else if	(select_val == "vol") 	fnVolume();
			else if	(select_val == "log") 	fnLogs();
	    });
	});
 	
 	function fnStat()
 	{
 		$.ajax({
 			url: "${prefix}/containers/${container_id}/stat",
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
 	
 	function fnProcess()
 	{
 		$.ajax({
 			url: "${prefix}/containers/${container_id}/proc",
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
 	
 	function fnNet()
 	{
 		$.ajax({
 			url: "${prefix}/containers/${container_id}/net",
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
 	
 	function fnEnv()
 	{
 		$.ajax({
 			url: "${prefix}/containers/${container_id}/env",
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
 	
 	function fnVolume()
 	{
 		$.ajax({
 			url: "${prefix}/containers/${container_id}/vol",
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
 	
 	function fnLogs()
 	{ 		
 		$.ajax({
 			url: "${prefix}/containers/${container_id}/log",
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