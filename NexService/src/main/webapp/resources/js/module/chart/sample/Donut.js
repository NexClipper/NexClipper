$('#daemonSetsChartArea').highcharts({
    chart: {
        type: 'pie',
        options3d: {
            enabled: false,
            alpha: 0
        }
    },
    colors: [colors.cpuUtilization, colors.memoryUtilization],
    title: {
        style: {
            fontSize: '25px',
            fontWeight: 'bold'
        },
        verticalAlign: 'middle',
        text : "sss"
    },tooltip: {
        enabled: false
    },
    plotOptions: {
        pie: {
            innerSize: 70,
            depth: 45
        }
    },
    series: [{
    	dataLabels: {
            enabled: false
           
        },        data: [
            ['56%', 56],
            ['44%', 44]
        ]
    }]
});