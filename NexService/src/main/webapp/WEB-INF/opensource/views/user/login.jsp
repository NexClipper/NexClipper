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
<div class="m-login__contanier">
	<div class="m-login__signin" style="margin-bottom: 130px;">
		<div class="m-login__head">
			<h3 class="m-login__title"> Login To Your Account </h3>
		</div>
		<form class="m-login__form m-form" method="post">
			<div id = "msgDiv">
			</div>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<div class="form-group m-form__group">
				<input class="form-control m-input" type="text" placeholder="id" name="id" id="id" autocomplete="off">
			</div>
			<div class="form-group m-form__group">
				<input class="form-control m-input m-login__form-input--last" type="password" placeholder="password" name="password" id="password">
			</div>
			<input type="hidden" placeholder="text" name="timezone" value="12344321">
			<div class="row m-login__form-sub">
				<div class="col m--align-left">
					<label class="m-checkbox m-checkbox--focus">
						<input type="checkbox" name="remember"> Remember me 
					</label>
				</div>
				<div class="col m--align-right">
					<a href="javascript:;" id="m_login_forget_password" class="m-link"> Forget Password ?</a>
				</div>
			</div>
			<div class="m-login__form-action">
			    <button type="submit" class="btn btn-focus m-btn m-btn--pill m-btn--custom m-btn--air"> Sign In </button>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
function msgBox (msg) {
	var html = '<div class="m-alert m-alert--outline alert alert-danger alert-dismissible" role="alert">';
	html += '<button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>';
	html += '<span>' + msg + '</span>';
	html += '</div>';
	return html;
}
if (document.URL.split("?error=")[1] == "true") {
	document.getElementById("msgDiv").innerHTML = msgBox("Incorrect username or password. Please try again.");
}
</script>