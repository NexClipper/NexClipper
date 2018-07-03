<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="m_datatable" id="dockerList"></div>

<script>

    var DatatableDataLoad = function () {
        //== Private functions

        // demo initializer
        var dataLoad = function () {
			var taskData = new Array();
        	var dataJSONArray = JSON.parse(JSON.stringify(${con_list}));
        	
        	// Store the data to be displayed in the table.
        	for( var i=0;i<dataJSONArray.length;i++)
        	{
				var container_type = "";
        		var container_name = "";
        		
        		if (dataJSONArray[i].Labels.MESOS_TASK_ID ){
        			container_type = "Mesos";
        			container_name = dataJSONArray[i].Labels.MESOS_TASK_ID;
        		}
        		else {
        			container_type = "Docker";
        			container_name = dataJSONArray[i].Names[0].substr(1);
        		}
        		
        		if (dataJSONArray[i].cpuPercent == null || dataJSONArray[i].memPercent == null) {
        			dataJSONArray[i].cpuPercent = 0.00;
        			dataJSONArray[i].memPercent = 0.00;
        		}
      
        		taskData.push(
        				{
        					'type' : container_type,
                            'id' : dataJSONArray[i].Id,
                            'name' : container_name,
                            'image' : dataJSONArray[i].Image,
                            'cpu' : parseFloat(dataJSONArray[i].cpuPercent).toFixed(2),
                            'mem' : parseFloat(dataJSONArray[i].memPercent).toFixed(2),
                            'state' : dataJSONArray[i].State,
                            'status' : dataJSONArray[i].Status,
                            'created' : new Date(dataJSONArray[i].Created*1000).toLocaleString()
        					
                        }
        		);
        	}

            var dataJSONArray = JSON.parse(JSON.stringify(taskData));
			
            var datatable = $('#dockerList').mDatatable({
                // datasource definition
                data: {
                    type: 'local',
                    source: dataJSONArray,
                    pageSize: 10
                },

                // layout definition
                layout: {
                    theme: 'default', // datatable theme
                    class: '', // custom wrapper class
                    scroll: true, // enable/disable datatable scroll both horizontal and vertical when needed.
                    // height: 450, // datatable's body's fixed height
                    footer: false // display/hide footer
                },

                // column sorting
                sortable: true,
                pagination: true,
                
                // columns definition
                columns: [ {
                        field: "type",
                        title: "Type",
                        textAlign: 'center',
                        width:100
                    }, {
                    field: "id",
                    title: "Container ID",
                    textAlign: 'center',
                    template: function (row) {
                        return "<a href=\"javascript:fnDetailView('"+row.id+"')\" class=\"font-weight-bold\">" + row.id + "</a>";
                    }
                }, {
                    field: "name",
                    title: "Container Name",
                    textAlign: 'left',
                    template: function (row) {
                        return "<a href=\"javascript:fnDetailView('"+row.id+"')\" class=\"font-weight-bold\">" + row.name + "</a>";
                    }
                }, {
                    field: "image",
                    title: "Image",
                    textAlign: 'center',
                },{
                    field: "cpu",
                    title: "CPU(%)",
                    textAlign: 'center',
                    width:100,
                    template: function (row) {
                        return '<div class="m-table__progress"><div class="m-table__progress-sm progress m-progress--sm"> <div class="m-table__progress-bar progress-bar bg-brand" role="progressbar" aria-valuenow="' + row.cpu + '" aria-valuemin="0" aria-valuemax="100" style="width:' + row.cpu + '%;"></div></div><span class="m-table__stats">' + row.cpu + '</span> </div>';
                    }
                }, {
                    field: "mem",
                    title: "MEM(%)",
                    textAlign: 'center',
                    width:100,
                    template: function (row) {
                    	return '<div class="m-table__progress"><div class="m-table__progress-sm progress m-progress--sm"> <div class="m-table__progress-bar progress-bar bg-danger" role="progressbar" aria-valuenow="' + row.mem + '" aria-valuemin="0" aria-valuemax="100" style="width:' + row.mem + '%;"></div></div><span class="m-table__stats">' + row.mem + '</span> </div>';
                    }
                },  {
                    field: "state",
                    title: "State",
                    textAlign: 'center',
                    width:100
                }, {
                    field: "status",
                    title: "Status",
                    textAlign: 'center',
                    width:200
                }, {
                    field: "created",
                    title: "Created",
                    textAlign: 'center',
                    width:300
                }
                ]
            });
        };

        return {
            //== Public functions
            init: function () {
                // init dmeo
                dataLoad();
            }
        };
    }();

    jQuery(document).ready(function () {
    	DatatableDataLoad.init();
        $(window).trigger('resize');
    });

</script>