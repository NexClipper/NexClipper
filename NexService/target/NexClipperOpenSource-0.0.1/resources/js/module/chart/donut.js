function Donut () {
	this.colors;
	this.data;
	this.area;
	this.event;
}
Donut.prototype.colors = function(colors){
	this.colors = [colors];
	return this;
}
Donut.prototype.data = function(data){
	this.data = data;
	return this;
}
Donut.prototype.area = function(area){
	this.area = area;
	return this;
}
Donut.prototype.event = function(event){
	this.event = event;
	return this;
}
Donut.prototype.draw = function(){
	new Morris.Donut({
		element: this.area,
		data: this.data,
		colors: this.colors,
		formatter: function(y,data) {
			return "";
		},
		resize:true
	});
}