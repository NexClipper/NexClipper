function MDT () {
	this.area;
	this.options = {
		data : {
			type : 'local',
			source : [],
			pageSize : 5
		},
		layout : {
			theme : "default",
			"class" : "",
			scroll : false,
			footer : false
		},
		sortable: true,
		pagination: true,
		toolbar: {
			items: {
				// pagination
				pagination: {
					// page size select
					pageSizeSelect: [5,10]
				}
			}
		},
		columns : []
	};
	this.event;
}
MDT.prototype.area = function (area) {
	this.area = area;
	return this;
}
MDT.prototype.pageSize = function (pageSize) {
	this.options.data.pageSize = pageSize;
	return this;
}
MDT.prototype.columns = function (columns) {
	var c = [];
	columns.forEach(function(column){
		var m = {
			textAlign : 'center'
		};
		Object.keys(column).forEach(function(key){
			m[key] = column[key];
		})
		c.push(m)
	})
	this.options.columns = c;
	return this;
}
MDT.prototype.data = function (data) {
	this.options.data.source = data;
	return this;
}
MDT.prototype.event = function (event) {
	this.event = event;
	return this;
}
MDT.prototype.draw = function () {
	$("#" + this.area).mDatatable(this.options);
	this.event();
	//$(window).trigger('resize');
}