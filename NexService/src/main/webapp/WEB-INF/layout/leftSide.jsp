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
<div id="m_aside_left" class="m-grid__item	m-aside-left  m-aside-left--skin-light ">
	<div id="m_ver_menu" class="m-aside-menu  m-aside-menu--skin-light m-aside-menu--submenu-skin-light " data-menu-vertical="true" data-menu-scrollable="true" data-menu-dropdown-timeout="500">
		<ul class="m-menu__nav  m-menu__nav--dropdown-submenu-arrow ">
			<li class="m-menu__item  m-menu__item">
				<a href="/dashboard/default" class="m-menu__link">
					<i class="m-menu__link-icon flaticon-dashboard"></i>
					<span class="m-menu__link-text">Dashboard</span>
				</a>
			</li>
			<li class="m-menu__item  m-menu__item--submenu " aria-haspopup="true"  data-menu-submenu-toggle="hover">
				<a  href="#" class="m-menu__link m-menu__toggle">
					<i class="m-menu__link-icon flaticon-map"></i>
					<span class="m-menu__link-text">Kubernetes</span>
					<i class="m-menu__ver-arrow la la-angle-right"></i>
				</a>
				<div class="m-menu__submenu ">
					<span class="m-menu__arrow"></span>
					<ul class="m-menu__subnav">
						<li class="m-menu__item  m-menu__item--parent" aria-haspopup="true" >
							<span class="m-menu__link">
								<span class="m-menu__link-text">
									Kubernetes
								</span>
							</span>
						</li>
						<li class="m-menu__item " aria-haspopup="true" >
							<a href="/kubernetes/cluster" class="m-menu__link ">
								<i class="m-menu__link-bullet m-menu__link-bullet--dot">
									<span></span>
								</i>
								<span class="m-menu__link-text">Cluster</span>
							</a>
						</li>
						<li class="m-menu__item " aria-haspopup="true" >
							<a  href="/kubernetes/node" class="m-menu__link ">
								<i class="m-menu__link-bullet m-menu__link-bullet--dot">
									<span></span>
								</i>
								<span class="m-menu__link-text">Node</span>
							</a>
						</li> 
						<li class="m-menu__item " aria-haspopup="true" >
							<a href="/kubernetes/pod" class="m-menu__link ">
								<i class="m-menu__link-bullet m-menu__link-bullet--dot">
									<span></span>
								</i>
								<span class="m-menu__link-text">Pod</span>
							</a>
						</li>
					</ul>
				</div>
			</li>
			<li class="m-menu__item  m-menu__item--submenu" aria-haspopup="true"  data-menu-submenu-toggle="hover">
				<a href="#" class="m-menu__link m-menu__toggle">
					<i class="m-menu__link-icon flaticon-network"></i>
					<span class="m-menu__link-text">Infrastructure</span>
					<i class="m-menu__ver-arrow la la-angle-right"></i>
				</a>
				<div class="m-menu__submenu">
					<span class="m-menu__arrow"></span>
					<ul class="m-menu__subnav">
						<li class="m-menu__item  m-menu__item--parent" aria-haspopup="true" >
							<span class="m-menu__link">
								<span class="m-menu__link-text">
									Infrastructure
								</span>
							</span>
						</li>
						<li class="m-menu__item" aria-haspopup="true" >
							<a href="/infrastructure/host" class="m-menu__link">
								<i class="m-menu__link-bullet m-menu__link-bullet--dot">
									<span></span>
								</i>
								<span class="m-menu__link-text">Host</span>
							</a>
						</li>
						<li class="m-menu__item" aria-haspopup="true" >
							<a href="/infrastructure/container" class="m-menu__link">
								<i class="m-menu__link-bullet m-menu__link-bullet--dot">
									<span></span>
								</i>
								<span class="m-menu__link-text">Container</span>
							</a>
						</li>						
						<li class="m-menu__item " aria-haspopup="true" >
							<a  href="/infrastructure/resource" class="m-menu__link ">
								<i class="m-menu__link-bullet m-menu__link-bullet--dot">
									<span></span>
								</i>
								<span class="m-menu__link-text">Resource</span>
							</a>
						</li>
					</ul>
				</div>
			</li>
			<li class="m-menu__item  m-menu__item--submenu " aria-haspopup="true"  data-menu-submenu-toggle="hover">
				<a  href="#" class="m-menu__link m-menu__toggle">
					<i class="m-menu__link-icon flaticon-alert-1"></i>
					<span class="m-menu__link-text">Incident Manager</span>
					<i class="m-menu__ver-arrow la la-angle-right"></i>
				</a>
				<div class="m-menu__submenu ">
					<span class="m-menu__arrow"></span>
					<!-- <ul class="m-menu__subnav">
						<li class="m-menu__item  m-menu__item--parent" aria-haspopup="true" >
							<span class="m-menu__link">
								<span class="m-menu__link-text">
									Incident Manager
								</span>
							</span>
						</li>
						<li class="m-menu__item " aria-haspopup="true" >
							<a href="/incident/trace" class="m-menu__link ">
								<i class="m-menu__link-bullet m-menu__link-bullet--dot">
									<span></span>
								</i>
								<span class="m-menu__link-text">Event Trace</span>
							</a>
						</li>
						<li class="m-menu__item " aria-haspopup="true" >
							<a  href="/incident/map" class="m-menu__link ">
								<i class="m-menu__link-bullet m-menu__link-bullet--dot">
									<span></span>
								</i>
								<span class="m-menu__link-text">Event Map</span>
							</a>
						</li>								
						<li class="m-menu__item " aria-haspopup="true" >
							<a  href="/incident/rule" class="m-menu__link ">
								<i class="m-menu__link-bullet m-menu__link-bullet--dot">
									<span></span>
								</i>
								<span class="m-menu__link-text">Rule Set Manager</span>
							</a>
						</li>
					</ul>-->
				</div> 
			</li>
			<!-- <li class="m-menu__item  m-menu__item">
				<a href="/agent/default" class="m-menu__link">
					<i class="m-menu__link-icon flaticon-dashboard"></i>
					<span class="m-menu__link-text">Agent</span>
				</a>
			</li> -->
			<!-- <li class="m-menu__item  m-menu__item--submenu " aria-haspopup="true"  data-menu-submenu-toggle="hover">
				<a  href="#" class="m-menu__link m-menu__toggle">
					<i class="m-menu__link-icon flaticon-settings"></i>
					<span class="m-menu__link-text">Admin</span>
					<i class="m-menu__ver-arrow la la-angle-right"></i>
				</a>

				<div class="m-menu__submenu ">
					<span class="m-menu__arrow"></span>
					<ul class="m-menu__subnav">
						<li class="m-menu__item  m-menu__item--parent" aria-haspopup="true" >
							<span class="m-menu__link">
								<span class="m-menu__link-text">
									Admin
								</span>
							</span>
						</li>
						<li class="m-menu__item " aria-haspopup="true" >
							<a  href="/admin/agent" class="m-menu__link ">
								<i class="m-menu__link-bullet m-menu__link-bullet--dot">
									<span></span>
								</i>
								<span class="m-menu__link-text">Agent List</span>
							</a>
						</li>
						<li class="m-menu__item" aria-haspopup="true" >
							<a  href="/admin/account" class="m-menu__link ">
								<i class="m-menu__link-bullet m-menu__link-bullet--dot">
									<span></span>
								</i>
								<span class="m-menu__link-text">Account</span>
							</a>
						</li>
					</ul>
				</div>
			</li> -->
		</ul>
	</div>
</div>
<script type="text/javascript">
$(function(){
	var winLocation = window.location.href;
	var aList = $("ul.m-menu__nav--dropdown-submenu-arrow").find("a.m-menu__link");
	aList.each(function(idx, item) {
		var itemHref = $(item).attr("href");
		if (winLocation.indexOf(itemHref) != -1) {
			var parents = $(item).parents("li");
			parents.each(function(i, p){
				$(p).addClass("m-menu__item--open");
			})
		}
			
	})
	
})
</script>
