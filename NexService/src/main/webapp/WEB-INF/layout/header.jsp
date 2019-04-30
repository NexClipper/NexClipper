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
<div class="m-container m-container--fluid m-container--full-height">
	<div class="m-stack m-stack--ver m-stack--desktop">
		<div class="m-stack__item m-brand  m-brand--skin-light ">
			<div class="m-stack m-stack--ver m-stack--general">
				<div class="m-stack__item m-stack__item--middle m-brand__logo">
					<a href="/" class="m-brand__logo-wrapper">
						<img alt="NEXCLOUD" src="/resources/image/logo1_blackwall2.png" height="60px">
					</a>
				</div>
				<div class="m-stack__item m-stack__item--middle m-brand__tools">
					<a href="javascript:;" id="m_aside_left_minimize_toggle" class="m-brand__icon m-brand__toggler m-brand__toggler--left m--visible-desktop-inline-block">
						<span></span>
					</a>
					<a href="javascript:;" id="m_aside_left_offcanvas_toggle" class="m-brand__icon m-brand__toggler m-brand__toggler--left m--visible-tablet-and-mobile-inline-block">
						<span></span>
					</a>
					<a href="javascript:;" id="m_aside_header_menu_mobile_toggle" class="m-brand__icon m-brand__toggler m--visible-tablet-and-mobile-inline-block">
						<span></span>
					</a>
				</div>
			</div>
		</div>
		<div class="m-stack__item m-stack__item--fluid m-header-head" id="m_header_nav">
				<!-- BEGIN: Horizontal Menu -->
			<button class="m-aside-header-menu-mobile-close  m-aside-header-menu-mobile-close--skin-light " id="m_aside_header_menu_mobile_close_btn">
				<i class="la la-close"></i>
			</button>
			<div id="m_header_menu" class="m-header-menu m-aside-header-menu-mobile m-aside-header-menu-mobile--offcanvas  m-header-menu--skin-light m-header-menu--submenu-skin-light m-aside-header-menu-mobile--skin-light m-aside-header-menu-mobile--submenu-skin-light "  >
				<ul class="m-menu__nav  m-menu__nav--submenu-arrow ">
					<li class="m-menu__item  m-menu__item--submenu m-menu__item--rel">
						<a  href="/" class="m-menu__link">
							<i class="m-menu__link-icon flaticon-dashboard"></i>
							<span class="m-menu__link-text">Dashboard</span>
						</a>
					</li>
					<li class="m-menu__item  m-menu__item--submenu m-menu__item--rel">
						<a  href="/infrastructure/host" class="m-menu__link">
							<i class="m-menu__link-icon flaticon-network"></i>
							<span class="m-menu__link-text">Host</span>
						</a>
					</li>
					<li class="m-menu__item  m-menu__item--submenu m-menu__item--rel">
						<a  href="/infrastructure/container" class="m-menu__link"> 
							<i class="m-menu__link-icon flaticon-open-box"></i>
							<span class="m-menu__link-text">Container</span>
						</a>
					</li>
					<li class="m-menu__item  m-menu__item--submenu m-menu__item--rel">
						<a  href="/infrastructure/resource" class="m-menu__link">
							<i class="m-menu__link-icon flaticon-layers"></i>
							<span class="m-menu__link-text">Resource</span>
						</a>
					</li>
				</ul>
			</div>
			<div id="m_header_topbar" class="m-topbar  m-stack m-stack--ver m-stack--general m-stack--fluid" style="display: none;">
				<div class="m-stack__item m-topbar__nav-wrapper">
					<ul class="m-topbar__nav m-nav m-nav--inline">
						<li class="	m-nav__item m-dropdown m-dropdown--large m-dropdown--arrow m-dropdown--align-center m-dropdown--mobile-full-width m-dropdown--skin-light	m-list-search m-list-search--skin-light">
							<span class="m-nav__link m-dropdown__toggle">
								<span class="m-nav__link-icon" id="dispaly_user_id" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="width:100%; cursor: pointer;"></span>
                         <div class="dropdown-menu dropdown-menu-left" x-placement="bottom-start" style="position: absolute; will-change: transform; top: 0px; left: 0px; transform: translate3d(0px, 38px, 0px);">
                             <a href="/user/logout" class="dropdown-item m-dropdown__toggle"><span class="m-nav__link-icon"><i class="flaticon-logout"></i>logout</span></a>
                         </div>
							</span>
						</li>
						<!-- <li class="	m-nav__item m-dropdown m-dropdown--large m-dropdown--arrow m-dropdown--align-center m-dropdown--mobile-full-width m-dropdown--skin-light	m-list-search m-list-search--skin-light">
							<a href="#" class="m-nav__link m-dropdown__toggle">
								<span class="m-nav__link-icon"><i class="flaticon-info"></i>Help</span>
							</a>
						</li> -->	
						<!-- <li class="	m-nav__item m-dropdown m-dropdown--large m-dropdown--arrow m-dropdown--align-center m-dropdown--mobile-full-width m-dropdown--skin-light	m-list-search m-list-search--skin-light">
							<a href="/user/logout" class="m-nav__link m-dropdown__toggle">
								<span class="m-nav__link-icon"><i class="flaticon-logout"></i>logout</span>
							</a>	
						</li> -->						
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>