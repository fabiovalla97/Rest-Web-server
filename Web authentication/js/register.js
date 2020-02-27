$(function() {
    //$("#submit").click(function() {
        function miafunzione(){
        alert("entrato in reg");
        $("#messaggioRegister").html("Attendere il caricamento...");
        var username = $("#username").val().trim();
        var email = $("#email").val().trim();
        var password = $("#password").val().trim();
        var repassword = $("#repassword").val().trim();
        var birthday = $("#birthday").val().trim();
        if (email && username && password && repassword && birthday) {
            if (validateEmail(email)) {
                if (password == repassword) {
                    $.ajax({
                        type: 'POST',
                        url: '../php/registrazione.php',
                        data: { Email: email, Password: password, Username: username, Birthday: birthday},
                        success: function(response) {
                            if (response.includes("ok")) {
                                location.assign("../code.php");
                            } else {
                                $("#messaggioRegister").html("Registrazione fallita: "+response);
                            }
                        }
                    });
                } else {
                    $("#messaggioRegister").html("Password differenti");
                }
            } else {
                $("#messaggioRegister").html("Mail non valida");
            }
        } else {
            $("#messaggioRegister").html("Non hai inserito tutti i campi");
        }
    }
});