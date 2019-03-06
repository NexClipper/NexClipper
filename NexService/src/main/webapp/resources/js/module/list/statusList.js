function StatusList () {
	this.data;
	this.area;
	this.fields;
	this.event;
}
StatusList.prototype.data = function(data){
	this.data = data;
	return this;
}
StatusList.prototype.area = function(area){
	this.area = area;
	return this;
}
StatusList.prototype.fields = function(fields){
	this.fields = fields;
	return this;
}
StatusList.prototype.event = function(event){
	this.event = event;
	return this;
}
StatusList.prototype.draw = function(){
	var _this = this;
	var html = "";
	this.data.forEach(function(item){
		var color;
		if(item.status.toLowerCase() == "ACTIVE".toLowerCase()) color = "success";
		else color = "danger";
		
		html += "<div class='m-widget4__item'><div class='m-widget4__ext'>";
		html += "<span class='m-badge m-badge--"+color+" m-badge--wide'></span></div>";
		
		_this.fields.forEach (function (field){
			html += "<div class='m-widget4__info'><strong>"+item[field]+"</strong></div>";
		})
		
		html += "<div class='m-widget4__info'><strong class='m--font-bold m--font-"+color+"'>"+item.status+"</strong></div></div>";
	})
	$("#" + this.area).append(html);
}