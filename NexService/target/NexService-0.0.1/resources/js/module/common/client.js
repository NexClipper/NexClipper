/*
* Copyright 2019 NexCloud Co.,Ltd.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
var spinnerCount = 0;
function Client () {
	this.url;
	this.async = true;
	this.clusterFlag = true;
	this.clusterId = '1';
	this.result;
	this.type = 'get';
	this.refresh = true;
	this.data;
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
Client.prototype.refreshFlag = function(rf) {
	this.refresh = rf;
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
Client.prototype.setClusterId = function(clusterId) {
	this.clusterId = clusterId;
	return this;
}
Client.prototype.getNotCluster = function() {
	this.clusterFlag = false;
	this.get();
}
Client.prototype.asyncFalse = function() {
	this.async = false;
	return this;
}

Client.prototype.get = function() {
	var _this = this;
	if (spinnerCount === 0 && this.refresh) {
		_this.startSpinner();
	}
	spinnerCount++;
	var urlSplit = _this.url;
	if (this.clusterFlag)
		urlSplit = "/api/v1/cluster/" + _this.clusterId + _this.url.split("/api/v1")[1];
	$.ajax({
		url: urlSplit,
		type: _this.type,
		async : _this.async,
		data: this.param,
		dataType: "json",
		success: function(data){
			if (data != null && typeof data.responseCode != "undefined" && data.responseCode == 200
					&& typeof data.responseBody != "undefined") {
				try {
					if (_this.async)
						_this.callback(JSON.parse(data.responseBody), _this.bindData);
					else
						_this.result = JSON.parse(data.responseBody), _this.bindData;
				} catch (e) {
					console.log(e);
				}
			}
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
	return _this.result;
}
Client.prototype.post = function(){
	var _this = this;
	$.ajax({
		url: this.url,
		type: 'POST',
		data: this.data,
		dataType: 'text',
		beforeSend: function(jqXHR) {},
		success: function(data) {
			_this.callback(data, _this.bindData);
		}, 
		error : function (jqXHR, textStatus, errorThrown) {
			console.log('error \n[' + textStatus + ']\n' + errorThrown);
		},
		complete: function(jqXHR) {
			//console.log("complete");
		} 
	});
}

Client.prototype.data = function(data){
	this.data = data;
	return this;
}

