<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		
<div class="m_datatable" id="procList">

</div>

<script>

    var DatatableDataLoad = function () {
        //== Private functions

        // demo initializer
        var dataLoad = function () {
			var taskData = new Array();
        	var dataJSONArray = JSON.parse(JSON.stringify(${proc}));
        	
        	for( var i=0;i<dataJSONArray.Processes.length;i++)
        	{
        		taskData.push(
        				{
                            'uid' : dataJSONArray.Processes[i][0],
                            'pid' : dataJSONArray.Processes[i][1],
                            'ppid' : dataJSONArray.Processes[i][2],
                            'c' : dataJSONArray.Processes[i][3],
                            'stime' : dataJSONArray.Processes[i][4],
                            'tty' : dataJSONArray.Processes[i][5],
                            'time' : dataJSONArray.Processes[i][6],
                            'cmd' : dataJSONArray.Processes[i][7]
                        }
        		);
        	}

            var dataJSONArray = JSON.parse(JSON.stringify(taskData));
            
            var datatable = $('#procList').mDatatable({
                // datasource definition
                data: {
                    type: 'local',
                    source: dataJSONArray,
                    pageSize: 20
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
                columns: [{
                    field: "uid",
                    title: "UID",
                    textAlign: 'center',
                    width:50
                }, {
                    field: "pid",
                    title: "PID",
                    textAlign: 'left',
                    width:50
                }, {
                    field: "ppid",
                    title: "PPID",
                    textAlign: 'left',
                    width:50
                },{
                    field: "c",
                    title: "C",
                    textAlign: 'center',
                    width:50
                },{
                    field: "stime",
                    title: "STIME",
                    textAlign: 'center',
                    width:50
                }, {
                    field: "tty",
                    title: "TTY",
                    textAlign: 'center',
                    width:50
                },  {
                    field: "time",
                    title: "TIME",
                    textAlign: 'center',
                    width:50
                }, {
                    field: "cmd",
                    title: "CMD",
                    textAlign: 'center',
                    width:700
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