//== Class Definition
var SnippetLogin = function() {

    var login = $('#m_login');

    var showErrorMsg = function(form, type, msg) {
        var alert = $('<div class="m-alert m-alert--outline alert alert-' + type + ' alert-dismissible" role="alert">\
			<button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>\
			<span></span>\
		</div>');

        form.find('.alert').remove();
        alert.prependTo(form);
        alert.animateClass('fadeIn animated');
        alert.find('span').html(msg);
    }

    //== Private Functions

    var displaySignUpForm = function() {
        login.removeClass('m-login--forget-password');
        login.removeClass('m-login--signin');

        login.addClass('m-login--signup');
        login.find('.m-login__signup').animateClass('flipInX animated');
    }

    var displaySignInForm = function() {
        login.removeClass('m-login--forget-password');
        login.removeClass('m-login--signup');

        login.addClass('m-login--signin');
        login.find('.m-login__signin').animateClass('flipInX animated');
    }

    var displayForgetPasswordForm = function() {
        login.removeClass('m-login--signin');
        login.removeClass('m-login--signup');

        login.addClass('m-login--forget-password');
        login.find('.m-login__forget-password').animateClass('flipInX animated');
    }

    var handleFormSwitch = function() {
        $('#m_login_forget_password').click(function(e) {
            e.preventDefault();
            displayForgetPasswordForm();
        });

        $('#m_login_forget_password_cancel').click(function(e) {
            e.preventDefault();
            displaySignInForm();
        });

        $('#m_login_signup').click(function(e) {
            e.preventDefault();
            displaySignUpForm();
        });

        $('#m_login_signup_cancel').click(function(e) {
            e.preventDefault();
            displaySignInForm();
        });
    }

    var handleSignInFormSubmit = function() {
        $('#m_login_signin_submit').click(function(e) {
            e.preventDefault();
            var btn = $(this);
            var form = $(this).closest('form');

            form.validate({
                rules: {
                    email: {
                        required: true,
                        email: true
                    },
                    password: {
                        required: true
                    }
                }
            });

            if (!form.valid()) {
                return;
            }

            btn.addClass('m-loader m-loader--right m-loader--light').attr('disabled', true);
            
            $.ajax({
    			url: g_prefix+"/v1/login/check",
    			type: "post",
    			data: {
    					 "user_id"		: $.trim($("#username").val())
    					,"user_passwd" 	: $.trim($("#password").val())
    				  },
    			dataType: "json",
    			success: function(data){
    				btn.removeClass('m-loader m-loader--right m-loader--light').attr('disabled', false);
    				console.log("data:::"+data.result);
    				// Success
    				if( data.result == "success")
    				{
    					//alert(data.message);
    					location.href = g_prefix+"/v1/dashboard";
    				}
    				
    				
    				// Fail
    				else if( data.result == "fail")
    				{
    					alert(data.message);
    					return;
    				}
    			},
    			error		:
    				function (jqXHR, textStatus, errorThrown) {
    					btn.removeClass('m-loader m-loader--right m-loader--light').attr('disabled', false);
    					alert('오류가 발생했습니다\n[' + textStatus + ']\n' + errorThrown);
    			}
    		});
            
            
            /*
            form.ajaxSubmit({
                url: '${prefix}/v1/',
                success: function(response, status, xhr, $form) {
                	// similate 2s delay
                	setTimeout(function() {
	                    btn.removeClass('m-loader m-loader--right m-loader--light').attr('disabled', false);
	                    showErrorMsg(form, 'danger', 'Incorrect username or password. Please try again.');
                    }, 2000);
                }
            });
            */
            
            
        });
    }

    var handleSignUpFormSubmit = function() {
        $('#m_login_signup_submit').click(function(e) {
            e.preventDefault();

            var btn = $(this);
            var form = $(this).closest('form');

            form.validate({
                rules: {
                    company_name: {
                    	required: true
                    },
                    email: {
                        required: true,
                        email: true
                    },
                    password: {
                        required: true
                    },
                    rpassword: {
                        required: true
                    },
                    agree: {
                        required: true
                    }
                }
            });

            if (!form.valid()) {
                return;
            }

            btn.addClass('m-loader m-loader--right m-loader--light').attr('disabled', true);

            /*var formData = form.serialize();
            console.log(form.user_id);*/
            
            var user_id 		= $.trim( $("#user_id").val() );
            var user_passwd 	= $.trim( $("#user_passwd").val() );
            var company_name	= $.trim( $("#company_name").val() );
            var tel				= $.trim( $("#tel").val() );
            var homepage		= $.trim( $("#homepage").val() );
            
            var param = "user_id="+user_id+"&user_passwd="+user_passwd+"&company_name="+company_name+"&tel="+tel+"&homepage="+homepage;
            console.log(param);
            $.ajax({
                //url: g_prefix+"/v1/agent/token?"+param,
            	url: g_prefix+"/v1/agent/token",
                type: 'get',
                data: {
                	"user_id"		: user_id,
                	"user_passwd"	: user_passwd,
                	"company_name"	: company_name,
                	"tel"			: tel,
                	"homepage"		: homepage
                },
                dataType: "json",
                success: function(response, status, xhr, $form) {
                	
                	// similate 2s delay
                	setTimeout(function() {

	                    if(response.type == "publish"){
	                    	btn.removeClass('m-loader m-loader--right m-loader--light').attr('disabled', false);
		                    form.clearForm();
		                    form.validate().resetForm();

		                    // display signup form
		                    displaySignInForm();
		                    var signInForm = login.find('.m-login__signin form');
		                    signInForm.clearForm();
		                    signInForm.validate().resetForm();
		                    
	                    	showErrorMsg(signInForm, 'success', 'Thank you. To complete your registration please check your email.');
	                    	
	                    	//Modal
	                    	$('#docker_cmd').text(response.dockerCmd.cmd);
	                    	$('#dcos_json').html("<pre>"+JSON.stringify(response.dcosJson, null, "\t")+"</pre>");
	                    	// $('#dcos_json').html("<pre style='max-width:700px;'>"+JSON.stringify(response.dcosJson, null, "\t")+"</pre>");
	                    	$('#detailModal').modal('show');
	                    }
	                    else{
	                    	btn.removeClass('m-loader m-loader--right m-loader--light').attr('disabled', false);
	                    	showErrorMsg(form, 'danger', 'Email is already occupied')
	                    }
	                }, 2000);
                }
            });
        });
    }

    var handleForgetPasswordFormSubmit = function() {
        $('#m_login_forget_password_submit').click(function(e) {
            e.preventDefault();

            var btn = $(this);
            var form = $(this).closest('form');

            form.validate({
                rules: {
                    email: {
                        required: true,
                        email: true
                    }
                }
            });

            if (!form.valid()) {
                return;
            }

            btn.addClass('m-loader m-loader--right m-loader--light').attr('disabled', true);

            form.ajaxSubmit({
                url: '',
                success: function(response, status, xhr, $form) { 
                	// similate 2s delay
                	setTimeout(function() {
                		btn.removeClass('m-loader m-loader--right m-loader--light').attr('disabled', false); // remove 
	                    form.clearForm(); // clear form
	                    form.validate().resetForm(); // reset validation states

	                    // display signup form
	                    displaySignInForm();
	                    var signInForm = login.find('.m-login__signin form');
	                    signInForm.clearForm();
	                    signInForm.validate().resetForm();

	                    showErrorMsg(signInForm, 'success', 'Cool! Password recovery instruction has been sent to your email.');
                	}, 2000);
                }
            });
        });
    }

    //== Public Functions
    return {
        // public functions
        init: function() {
            handleFormSwitch();
            handleSignInFormSubmit();
            handleSignUpFormSubmit();
            handleForgetPasswordFormSubmit();
        }
    };
}();

//== Class Initialization
jQuery(document).ready(function() {
    SnippetLogin.init();
});