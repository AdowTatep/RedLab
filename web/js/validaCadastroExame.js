/* global Materialize */

function  validaFormCampos(descricao, custo, pessoa, tempoJejum, data, horaEntrega) {
    var timeout = 5000;
    
    var temErro = false;
    if (descricao == "") {
        temErro = true;
        Materialize.toast('A descrição(nome) precisa ser preenchida', timeout);
        $("#descrLabel").text("A descrição precisa ser preenchida");
        $("#descrLabel").addClass("cancelRed");
    } else if (descricao.length>30) {
        temErro = true;
        Materialize.toast('A descrição só pode ter até 30 caracteres', timeout);
        $("#descrLabel").text("A descrição só pode ter até 30 caracteres");
        $("#descrLabel").addClass("cancelRed");
    } else if (descricao.length < 5) {
        temErro = true;
        Materialize.toast('A descrição precisa ter pelo menos 5 caracteres', timeout);
        $("#descrLabel").text("A descrição precisa ter pelo menos 5 caracteres");
        $("#descrLabel").addClass("cancelRed");
    } else {
        $("#descrLabel").addClass("checkGreen");
        $("#descrLabel").text("descricao");
    }
    
    if(custo == "") {
        temErro = true;
        Materialize.toast('O custo não pode estar vazio', timeout);
        $("#custoLabel").text("O custo não pode estar vazio");
        $("#custoLabel").addClass("cancelRed");
    } else if (custo.length >7){
        temErro = true;
        Materialize.toast('O custo não pode ter mais que 7 caracteres', timeout);
        $("#custoLabel").text("O custo não pode ter mais que 7 caracteres");
        $("#custoLabel").addClass("cancelRed");
    } else if (custo.length<1) {
        temErro = true;
        Materialize.toast('O custo precisa ter pelo menos 1 caracteres', timeout);
        $("#custoLabel").text("O custo precisa ter pelo menos 1 caracteres");
        $("#custoLabel").addClass("cancelRed");
    } else {
        $("#custoLabel").addClass("checkGreen");
        $("#custoLabel").text("Senha");
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

function onSubmit() {
    var $form = $("#cadastrarFormulario"),
            login = $form.find('input[name="login"]').val(),
            senha = $form.find('input[name="senha"]').val(),
            nome = $form.find('input[name="nome"]').val(),
            telefone = $form.find('input[name="telefone"]').val(),
            sexo = $('.select-dropdown').val(),
            cpf = $form.find('input[name="cpf"]').val(),
            endereco = $form.find('input[name="endereco"]').val(),
            page = $form.find('button[name="page"]').val(),
            url = $form.attr('action');
            
            console.log(page);
            
            //Cuida do sexo, o framework não deixa no HTML as informações "F" e "M"
            if (sexo == "Feminino") {
                sexo = "F";
            } else if (sexo == "Masculino"){
                sexo = "M";
            }

    if(!validaFormCampos(login, senha, nome, telefone, sexo, cpf, endereco, page, url)){
        console.log("eoq");
        Materialize.toast('Cadastrando informações...', 5000);
        $form.submit();
//        $('.modal-trigger').leanModal();
//        $("#modalConcluido").openModal();
    }
}

$("#cadastrarFormulario").submit(function (e) {
    
});

$("#cadastrarFormulario").keypress(function (e) {
    if (e.keyCode == 13) {
        e.preventDefault();
        onSubmit();
    }
});
$("#btn-cadastrar").click(function (e) {
    e.preventDefault();
    onSubmit();
});

