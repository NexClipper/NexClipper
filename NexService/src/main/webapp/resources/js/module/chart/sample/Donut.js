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