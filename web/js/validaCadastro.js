/* global Materialize */

function  validaFormCampos(login, senha, nome, telefone, sexo, cpf, endereco, url) {
    var timeout = 5000;
    
    var temErro = false;
    if (login == "") {
        temErro = true;
        Materialize.toast('O login precisa ser preenchido', timeout);
        $("#loginLabel").text("O login precisa ser preenchido");
        $("#loginLabel").addClass("cancelRed");
    } else if (login.length>20) {
        temErro = true;
        Materialize.toast('O campo de usuário só pode ter até 20 caracteres', timeout);
        $("#loginLabel").text("O campo de usuário só pode ter até 20 caracteres");
        $("#loginLabel").addClass("cancelRed");
    } else if (login.length < 5) {
        temErro = true;
        Materialize.toast('Login precisa ter pelo menos 5 caracteres', timeout);
        $("#loginLabel").text("Login precisa ter pelo menos 5 caracteres");
        $("#loginLabel").addClass("cancelRed");
    } else {
        $("#loginLabel").addClass("checkGreen");
        $("#loginLabel").text("Login");
    }
    
    if(senha == "") {
        temErro = true;
        Materialize.toast('A senha não pode estar vazia', timeout);
        $("#senhaLabel").text("A senha não pode estar vazia");
        $("#senhaLabel").addClass("cancelRed");
    } else if (senha.length >50){
        temErro = true;
        Materialize.toast('A senha não pode ter mais que 50 caracteres', timeout);
        $("#senhaLabel").text("A senha não pode ter mais que 50 caracteres");
        $("#senhaLabel").addClass("cancelRed");
    } else if (senha.length<6) {
        Materialize.toast('A senha precisa ter pelo menos 6 caracteres', timeout);
        temErro = true;
        $("#senhaLabel").text("A senha precisa ter pelo menos 6 caracteres");
        $("#senhaLabel").addClass("cancelRed");
    } else {
        $("#senhaLabel").addClass("checkGreen");
        $("#senhaLabel").text("Senha");
    }
    
    if(nome==""){
        temErro = true;
        Materialize.toast('O campo nome não pode estar vazio', timeout);
        $("#nomeLabel").text("O campo nome não pode estar vazio");
        $("#nomeLabel").addClass("cancelRed");
    } else if (nome.length>150) {
        temErro = true;
        Materialize.toast('O campo nome só pode ter 150 caracteres', timeout);
        $("#nomeLabel").text("O campo nome só pode ter 150 caracteres");
        $("#nomeLabel").addClass("cancelRed");
    } else {
        $("#nomeLabel").addClass("checkGreen");
        $("#nomeLabel").text("Nome");
    }
    
    if(telefone == ""){
        temErro = true;
        Materialize.toast('O campo telefone precisa ser preenchido', timeout);
        $("#telefoneLabel").text("O campo telefone precisa ser preenchido");
        $("#telefoneLabel").addClass("cancelRed");
    } else if (telefone.length > 20) {
        temErro = true;
        Materialize.toast('O campo de telefone só pode ter no máximo 20 caracteres', timeout);
        $("#telefoneLabel").text("O campo de telefone só pode ter no máximo 20 caracteres");
        $("#telefoneLabel").addClass("cancelRed");
    } else if (telefone.length < 8) {
        temErro = true;
        Materialize.toast('O telefone precisa ter no mínimo 8 caracteres', timeout);
        $("#telefoneLabel").text("O telefone precisa ter no mínimo 8 caracteres");
        $("#telefoneLabel").addClass("cancelRed");
    } else {
        $("#telefoneLabel").addClass("checkGreen");
        $("#telefoneLabel").text("Telefone");
    }
    
    if(sexo=="" || (sexo != "M" && sexo != "F")){
        temErro = true;
        Materialize.toast('O valor do sexo é inválido', timeout);
        $("#sexoLabel").text("O valor do sexo é inválido");
        $("#sexoLabel").addClass("cancelRed");
    } else {
        $("#sexoLabel").addClass("checkGreen");
        $("#sexoLabel").text("Escolha um sexo");
    }
    
    if (cpf == "") {
        temErro = true;
        Materialize.toast('O campo CPF não pode estar vazio', timeout);
        $("#cpfLabel").text("O campo CPF não pode estar vazio");
        $("#cpfLabel").addClass("cancelRed");
    } else if (cpf.length != 11) {
        temErro = true;
        Materialize.toast('O campo CPF precisa de 11 caracteres', timeout);
        $("#cpfLabel").text("O campo CPF precisa de 11 caracteres");
        $("#cpfLabel").addClass("cancelRed");
    } else {
        $("#cpfLabel").addClass("checkGreen");
        $("#cpfLabel").text("CPF");
    }
    
    if(endereco.length > 255){
        temErro = true;
        Materialize.toast('Endereço só pode ter 255 caracteres', timeout);
        $("#enderecoLabel").text("Endereço só pode ter 255 caracteres");
        $("#enderecoLabel").addClass("cancelRed");
    }
    
    return temErro;
    
}

$("#cadastrarFormulario").onsubmit(function (e) {
    e.preventDefault();

    var $form = $(this),
            login = $form.find('input[name="login"]').val(),
            senha = $form.find('input[name="senha"]').val(),
            nome = $form.find('input[name="nome"]').val(),
            telefone = $form.find('input[name="nome"]').val(),
            sexo = $('.select-dropdown').val(),
            cpf = $form.find('input[name="cpf"]').val(),
            endereco = $form.find('input[name="endereco"]').val(),
            page = $form.find('input[name="page"]').val(),
            url = $form.attr('action');
            
            //Cuida do sexo, o framework não deixa no HTML as informações "F" e "M"
            if (sexo == "Feminino") {
                sexo = "F";
            } else if (sexo == "Masculino"){
                sexo = "M";
            }

    if(!validaFormCampos(login, senha, nome, telefone, sexo, cpf, endereco, page, url)){
        $form.submit();
        Materialize.toast('Cadastrando informações...', 5000);
    }
});

$("#cadastrarFormulario").keypress(function (e) {
    if (e.keyCode == 13) {
        e.preventDefault();
        $(this).onsubmit();
    }
});
$("#btn-cadastrar").click(function (e) {
    e.preventDefault();
    $("#cadastrarFormulario").onsubmit();
});

