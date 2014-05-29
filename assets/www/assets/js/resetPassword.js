$(document).ready(function() {

    $('.reset').click(function() {
        $.getJSON("api/v1/user", {
            action: "rsteml",
            email: $('.email').val()
        }).done(function(json) {
            if (json.success) {
                console.log(json.result);
                showSuccess("We have sent you an email containing your password reset link at " + $('.email').val());
            } else {
                console.log(json.error);
            }
        });
    });
    
    $('.reset2').click(function(){
        if($('.password').val() !== $('.passwordcon').val()){
            showError("Password doesn't match conformation");
            return;
        }
        
        $.getJSON("api/v1/user", {
            action: "rstpwd",
            email: $('.email').val(),
            key: $('.key').val(),
            pwd: $('.password').val()
        }).done(function(json) {
            if (json.success) {
                console.log(json.result);
                if(json.result){
                    showSuccess("Your password has been successfully reseted to your new one");
                }
            } else {
                console.log(json.error);
            }
        });
    });
});



function showError(error) {
    $('.error-text').text(error);
    $('.alert-warning').show();
}

function showSuccess(success){
    $('.success-text').text(success);
    $('.alert-success').show();
}