<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="tlog">

</div>

<script>
	
///////////////////////////////////////////////////////
var idx_log = 0;
var success_flag = 'N';
var m_log_last_time;
	 
function fnAjaxLog(){
	idx_log++;
		
	if( idx_log == 100 && success_flag == 'N' ){
		fnLog();
	} else if( (idx_log % 150) == 0 ){
		fnLog();
	}
}

function fnLog (){
	success_flag = 'Y';
	$.ajax({
		url			: "${prefix}/containers/${container_id}/realtime_log",
		type		: "POST",
		data		: {"time": m_log_last_time},
		dataType	: "json",
		success		: function (data){
			if( data.result == "SUCCESS" )
			{
				var obj;
				if ( data.log.length > 0 ) {
					for ( var i = 0; i < data.log.length; i++) {
						obj = JSON.parse(data.log[i]);
						
						if (obj.stream == "stderr") {
							$("#tlog").append("<font color=red>" + "<h6>" +obj.log +"</h6>" + "</font>");
						}
						else {
							$("#tlog").append("<h6>" +obj.log +"</h6>");
						}
					}
				}
				
				m_log_last_time = obj.time;
				$('html, body').animate({ scrollTop: document.body.scrollHeight }, 1000);
			}
		},
		error		: function(request, status, error) {
			console.log("code:" + request.status + "\n message:" 
					+ request.responseText + "\n error:" + error);
		},
		complete	: function() {
			success_flag = 'N';
			idx_log = 0;
		}
	})
}


///////////////////////////////////////////////////////
var log_timer;

function start(){
	log_timer = setInterval("fnAjaxLog()", 100);
}
 
function stop(){
	clearInterval(log_timer);
}

jQuery(document).ready(
		function() {
			var con_log = ${con_log};
			var html="";
			
			html += "<!DOCTYPE html>";
			html += "<html>";
			html += "<body>";
			
			html += "<style>";
			html += "h6 { font-family: 'Nanum Gothic'}";
			html += "</style>";
				
			for (var i=0; i<con_log.length; i++) {
				if(con_log[i].stream == "stderr") {
					html += "<font color=red>";
					html += "<h6>" +con_log[i].log +"</h6>";
					html += "</font>";
				}
				else {
					html += "<h6>" +con_log[i].log +"</h6>";
				}
			}
			html += "</body>";
			html += "</html>";
			
			$("#tlog").html(html);
			$('html, body').animate({ scrollTop: document.body.scrollHeight }, 1000);
			m_log_last_time = con_log[con_log.length-1].time;
			
			stop();
			start();
		});
</script>