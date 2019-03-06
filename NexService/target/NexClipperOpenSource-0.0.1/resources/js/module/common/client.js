var spinnerCount = 0;
function Client () {
	this.url;
	this.type = 'get';
	this.param;
	this.callback;
	this.bindData;
}
Client.prototype.startSpinner = function() {
	$.preloader.start({
		modal: true,
		src : 'sprites2.png'
	});
}
Client.prototype.stopSpinner = function() {
	$.preloader.stop();
}
Client.prototype.url = function(url) {
	this.url = url;
	return this;
}
Client.prototype.method = function(method) {
	this.type = method;
	return this;
}
Client.prototype.param = function(param) {
	this.param = param;
	return this;
}
Client.prototype.callback = function(callback) {
	this.callback = callback;
	return this;
}
Client.prototype.bindData = function(bindData) {
	this.bindData = bindData;
	return this;
}
Client.prototype.get = function() {
	/*var date = new Date();
	var offset = date.getTimezoneOffset();
	console.log(date);
	console.log(date.toString());
	console.log(offset);*/
	var _this = this;
	if (spinnerCount === 0) {
		_this.startSpinner();
	}
	spinnerCount++;
	$.ajax({
		url: this.url,
		type: this.type,
		//async : false,
		data: this.param,
		dataType: "json",
		success: function(data){
			if (data != null && typeof data.responseCode != "undefined" && data.responseCode == 200
					&& typeof data.responseBody != "undefined")
				_this.callback(JSON.parse(data.responseBody), _this.bindData);
			// influx timezone setting logic..
		},
		error : function (jqXHR, textStatus, errorThrown) {
			console.log('error \n[' + textStatus + ']\n' + errorThrown);
		},
		complete	: function() {
			spinnerCount--;
			if (spinnerCount === 0) {
				_this.stopSpinner();
			}
			// console.log("complete");
		}
	});
}
