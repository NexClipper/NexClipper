function Picker(){
	this.timeString = "1h:10s";
	this.refreshFunction;
}
Picker.prototype.refreshFunction = function(rf){
	this.refreshFunction = rf;
	return this;
}
Picker.prototype.refresh = function(startTime, time){
	this.refreshFunction(startTime, time);
	return this;
}
Picker.prototype.draw = function(){
	var _this = this;
	if ($('#m_dashboard_daterangepicker').length == 0) {
		return;
	}
	var picker = $('#m_dashboard_daterangepicker');
	var start = moment();
	var end = moment();
	var time = '';
	function cb(start, end, label) {
		if( label == "" )
			label = "Last 1 hour";
		if (label == "Last 1 hour") time = "1h:5s";
		else if  (label == "Last 3 hours") time = "3h:15s";
		else if  (label == "Last 6 hours") time = "6h:30s";
		else if  (label == "Last 12 hours") time = "12h:1m";
		else if  (label == "Last 24 hours") time = "24h:2m";
		else if  (label == "Last 2 days") time = "2d:5m";
		else if  (label == "Last 7 days") time = "7d:15m";
		else if  (label == "Last 30 days") time = "30d:1h";
		else time = "1h:5s";
		_this.refresh(time.split(":")[0], time.split(":")[1]);
		picker.find('.m-subheader__daterange-title').html(label);
    }
	picker.daterangepicker({
		startDate: start,
		endDate: end,
		opens: 'left',
		ranges: {
			'Last 1 hour': [moment(), moment()],
			'Last 3 hours': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
			'Last 6 hours': [moment().subtract(6, 'days'), moment()],
			'Last 12 hours': [moment().subtract(29, 'days'), moment()],
			'Last 24 hours': [moment().startOf('month'), moment().endOf('month')],
			'Last 2 days': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
			'Last 7 days': [moment().subtract(2, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
			'Last 30 days': [moment().subtract(3, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
		}
	}, cb);
	cb(start, end, '');
	return this;
}
/*

if( "${params.sel_time}" == "1h:5s" && isPickerFirst )
	label = "Last 1 hour";
else if( "${params.sel_time}" == "3h:15s" && isPickerFirst )
	label = "Last 3 hours";
else if( "${params.sel_time}" == "6h:30s" && isPickerFirst )
	label = "Last 6 hours";
else if( "${params.sel_time}" == "12h:1m" && isPickerFirst )
	label = "Last 12 hours";
else if( "${params.sel_time}" == "24h:2m" && isPickerFirst )
	label = "Last 24 hours";
else if( "${params.sel_time}" == "2d:5m" && isPickerFirst )
	label = "Last 2 days";
else if( "${params.sel_time}" == "7d:15m" && isPickerFirst )
	label = "Last 7 days";
else if( "${params.sel_time}" == "30d:1h" && isPickerFirst )
	label = "Last 30 days";
*/
