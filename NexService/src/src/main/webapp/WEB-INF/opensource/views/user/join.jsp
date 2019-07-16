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
			<h3 class="m-login__title">Sign Up</h3>
			<div class="m-login__desc">Enter your details to create your account:</div>
		</div>
		<form class="m-login__form m-form" method="post">
			<div id = "msgDiv">
			</div>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<div class="form-group m-form__group">
				<input class="form-control m-input" type="text" placeholder="Email" name="userId" id = "userId" autocomplete="off">
			</div>
			<div class="form-group m-form__group">
				<input class="form-control m-input" type="text" placeholder="Company Name" name="companyName" autocomplete="off">
			</div>
			<div class="form-group m-form__group">
				<input class="form-control m-input" type="password" placeholder="Password" name="password">
			</div>
			<div class="form-group m-form__group">
				<input class="form-control m-input m-login__form-input--last" type="password" placeholder="Confirm Password" name="rpassword">
			</div>
			<div class="m-login__form-sub">
				<label class="m-checkbox m-checkbox--focus">
					<input type="checkbox" name="agree" id="agree">I Agree the
					<a href="#" class="m-link m-link--focus">term of use</a>.<span></span>
				</label>
				<span class="m-form__help"></span>
			</div>
			<div class="m-login__form-action">
				<button type="submit" class="btn btn-focus m-btn m-btn--pill m-btn--custom m-btn--air">Sign Up</button>
				<a href="/user/login" class="btn btn-outline-focus m-btn m-btn--pill m-btn--custom" > Sign In </a>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
function msgBox (msg) {
	var color = "danger";
	if (msg == "success") {
		color = "success";
	}
	var html = '<div class="m-alert m-alert--outline alert alert-' + color + ' alert-dismissible" role="alert">';
	html += '<button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>';
	html += '<span>' + msg + '</span>';
	// Incorrect username or password. Please try again.
	html += '</div>';
	return html;
}
var msg = "${msg}";
if (msg != "") {
	document.getElementById("msgDiv").innerHTML = msgBox(msg);
}
</script>
