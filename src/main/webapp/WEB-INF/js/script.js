$(document).ready(function () {
    $('#formRegistrazione').on('submit', function (event) {
        event.preventDefault();
        //$('.errore').remove();
        $('.form-control').css('border-color', '#DD862C');
        let nome = $('#nome').val()
        let cognome = $('#cognome').val()
		let email = $('#email').val()
        let password = $('#password').val()
        if (nome.length < 2) {
            $('#nome').css('border-color', 'red');
            $('#nome').after('<small class="text-danger errore text-small">Il campo non e valido. Deve contenere almeno 2 caratteri<small>');
        }
        if (cognome.length < 2) {
            $('#cognome').css('border-color', 'red');
            $('#cognome').after('<small class="text-danger errore text-small">Il campo non e valido. Deve contenere almeno 2 caratteri<small>');
        }
        if (password.length < 8 || !password.includes(".") && !password.includes("!")) {
            $('#password').css('border-color', 'red');
            $('#password').after('<small class="text-danger errore text-small">Il campo non e valido. Deve contenere almeno 8 caratteri e contenere . oppure ! oppure ? oppure @<small>');
        }
        // $('#data').css('color', 'red');
        // 
        // $('#mail').after('<small class="text-danger errore text-small">Il campo non e valido. Deve contenere almeno 2 caratteri @ 2 caratteri<small>');
        // 
        // $('#voto').after('<small class="text-danger errore text-small">Il campo non e valido. Bisogna scegliere un voto<small>');
        // $('#data').after('<small class="text-danger errore text-small">Il campo non e valido. Devi avere almeno 14 anni<small>');
        // $('#tbox').after('<small class="text-danger errore text-small">Il campo non e valido. Deve contenere al massimo 255 caratteri<small>');
        // $('#genere').after('<small class="text-danger errore text-small">Il campo non e valido <small>');
    })




})