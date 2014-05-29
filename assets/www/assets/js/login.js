$(document).ready(function() {
    //redirectOnLoggedIn();

    if ($.cookie('usrname') !== null && $.cookie('usrname') !== undefined) {
        $('.username').val($.cookie('usrname'));
        $('.remembermechkbox').prop('checked', true);
    }

    $('.login').click(function() {
        //Save email
        if ($('.remembermechkbox').is(':checked')) {
            $.cookie('usrname', $('.username').val());
        } else {
            $.removeCookie('usrname');
        }

        //Do validation
        $('.alert').hide();
        if (!doValidation()) {
            return;
        }

        //Do login
        $.getJSON("api/v1/user", {
            action: 'login',
            email: $('.username').val(),
            pwd: $('.password').val()
        }).done(function(json) {
            if (json.success) {
                if (json.result.isAuth) {
                    $.cookie('authid', json.result.authid);
                    window.location.href = "chat";
                } else {
                    showError("Error in logging in");
                }
            } else {
                console.log(json.error);
                if (json.error === 'NOT_ACTIVATED') {
                    showError(json.result + " <a href='#' class='resendActivationEmail'>Resend activation Email</a>");
                    $('.resendActivationEmail').click(sendActivationEmail)
                } else {
                    showError(json.error);
                }
            }
        });
    });
});

function sendActivationEmail() {
    $('.alert').hide();
    //Do login
    $.getJSON("api/v1/user", {
        action: 'rsndActEml',
        email: $('.username').val()
    }).done(function(json) {
        if (json.success) {
            showSuccess('Activation Email sent');
        } else {
            console.log(json.error);
            showError('An error occured');
        }
    });
}

function doValidation() {

    var email = $('.username').val();
    var pwd = $('.password').val();
    var isValid = true;
    if (email.length === 0) {
        showError('Please enter your BU email');
        isValid = false;
    } else if (!validateEmail(email)) {
        showError("please enter a valid email");
        isValid = false;
    } else if (pwd.length === 0) {
        showError("Please enter your password");
        isValid = false;
    }
    if (!isValid) {
        return false;
    }
    return true;
}

function showError(error) {
    $('.error-text').html(error);
    $('.alert-warning').show();
}

function showSuccess(success) {
    $('.success-text').text(success);
    $('.alert-success').show();
}

function validateEmail(email) {
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}

function redirectOnLoggedIn() {
    var authid = $.cookie('authid');
    if (authid !== null) {
        $.getJSON("api/v1/user", {
            action: 'isOnline',
            authid: authid
        }).done(function(json) {
            console.log("done");
            if (json.success && json.result) {
                window.location.href = "chat";
            } else {
                console.log(json.error);
            }
        });
    }
}

