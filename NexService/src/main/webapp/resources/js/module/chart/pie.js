function Pie () {
	this.colors;
	this.data;
	this.area;
	this.event;
}
Pie.prototype.colors = function(colors){
	this.colors = colors;
	return this;
}
Pie.prototype.data = function(data){
	this.data = data;
	return this;
}
Pie.prototype.area = function(area){
	this.area = area;
	return this;
}
Pie.prototype.event = function(event){
	this.event = event;
	return this;
}
Pie.prototype.draw = function(){
}