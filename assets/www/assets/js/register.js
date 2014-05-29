$(document).ready(function() {

    $('.register').click(function() {
        //Do validation
        $('.alert').hide();
        if (!doValidation()) {
            return;
        }
        //Do login
        $.getJSON("api/v1/user", {
            action:     'register',
            username:   $('.username').val(),
            email:      $('.email').val(),
            password    : $('.password').val(),
            passwordcon : $('.repassword').val(),
        }).done(function(json) {
            if (json.success) {
                showSuccess("Account created! Please check your email to activate it")
            } else {
                console.log(json.error);
                showError(json.error);
            }
        });
    });
    
    
});

function doValidation() {

    var username = $('.username').val();
    var email = $('.email').val();
    var pwd = $('.password').val();
    var repwd = $('.repassword').val();

    var isValid = true;
    if (username.length === 0) {
        showError('Please enter a username');
        isValid = false;
    } else if (email.length === 0) {
        showError('Please enter your BU email');
        isValid = false;
    } else if (!validateEmail(email)) {
        showError("please enter a valid email");
        isValid = false;
    } else if (pwd.length === 0) {
        showError('Please enter a password');
        isValid = false;
    } else if (repwd.length === 0) {
        showError('Please enter the password confirmation');
        isValid = false;
    } else if (pwd !== repwd) {
        showError("Password doesn't match password confirmation");
        isValid = false;
    } else if (!$('.termofservice').is(':checked')){
        showError("You must agree to the Terms Of Service");
        isValid = false;
    }
    if (!isValid) {
        return false;
    }
    return true;
}

function showError(error) {
    $('.error-text').text(error);
    $('.alert-warning').show();
}

function showSuccess(success){
    $('.success-text').text(success);
    $('.alert-success').show();
}

function validateEmail(email) {
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}

