 <!-- 
  Copyright 2019 NexCloud Co.,Ltd.

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at
 
      http://www.apache.org/licenses/LICENSE-2.0
 
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
-->
<div id="widgetEvent">
            <div class="m-portlet m-portlet--head-sm m-portlet--tabs" style="background-color: #C8C9E3"> 
                <div class="m-portlet__head">
                    <div class="m-portlet__head-caption">
                        <div class="m-portlet__head-title">
                            <h3 class="m-portlet__head-text" id="toggle_evt" style="cursor:pointer">Event</h3>
                        </div>
                    </div>
                    <div class="m-portlet__head-tools">
                        <ul class="nav nav-tabs m-tabs-line m-tabs-line--primary m-tabs-line--2x m-tabs-line--right">
                            <li class="nav-item m-tabs__item">
                                <a href="#" class="nav-link m-tabs__link active" style="cursor:">
                                    All
                                </a>
                            </li>
                            <li class="nav-item m-tabs__item">
                                <a href="#" class="nav-link m-tabs__link" style="cursor:default">
                                    Critical <span class="m-badge m-badge--danger" id="critical">0</span>
                                </a>
                            </li>
                            <li class="nav-item m-tabs__item">
                                <a href="#" class="nav-link m-tabs__link" style="cursor:default">
                                    Warning <span class="m-badge m-badge--warning" id="warning">0</span>
                                </a>
                            </li>
                            <li class="nav-item m-tabs__item">
                                <a href="#" class="nav-link m-tabs__link" style="cursor:default">
                                    Prediction <span class="m-badge m-badge--secondary" id="prediction">0</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="m-portlet__body" id="widgetEventList" style="display:none;">
                    <div class="m-scrollable mCustomScrollbar _mCS_5 mCS-autoHide" data-scrollbar-shown="true" data-scrollable="true" data-max-height="100" style="overflow: visible; height: 100px; max-height: 100px; position: relative;">

                        <!--Begin::Timeline 3 -->
                        <div class="m-list-timeline m-list-timeline--skin-light">
                            <div class="m-list-timeline__items" id="widgetEventList_items">
                                
                            </div>
                        </div>
                        <!--End::Timeline 3 -->

                    </div>

                </div>
                
                <!-- Time Line Chart Test Start -->
                <div class="m-portlet__body" id="widgetEventChart" style="display:none;">
                    
                    <div class="m-list-timeline__items" id="timeline_chart_task" style="height:80px"></div>
                    
                    <div class="m-list-timeline__items" id="timeline_chart_node" style="height:80px"></div>
                    
                    <div  class="m-scrollable mCustomScrollbar _mCS_5 mCS-autoHide" data-scrollbar-shown="true" data-scrollable="true" data-max-height="100" style="display:none; overflow: visible; height: 100px; max-height: 100px; position: relative;">
                        <!--Begin::Timeline 3 -->
                        <div class="m-list-timeline m-list-timeline--skin-light">
                            <div class="m-list-timeline__items" id="timeline_chart_host"></div>
                        </div>
                    </div>

                </div>
                <!-- Time Line Chart Test End -->
                
            </div>
        </div><!--//Widget#widgetEvent -->