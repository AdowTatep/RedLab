/* global Materialize */

function  validaFormCampos(descricao, custo, pessoa, tempoJejum, horaEntrega) {
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
        $("#descrLabel").text("Descricao(nome)");
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
        $("#custoLabel").text("Custo");
    }
    
    if(pessoa=="Pessoa"){
        temErro = true;
        Materialize.toast('Por favor, escolha um cliente.', timeout);
        $("#pessoaCampo").text("O campo pessoa precisa estar selecionado");
        $("#pessoaCampo").addClass("cancelRed");
    } else {
        $("#pessoaCampo").addClass("checkGreen");
        $("#pessoaCampo").text("Escolha uma pessoa");
    }
    
    if(tempoJejum == ""){
        temErro = true;
        Materialize.toast('O tempo de Jejum precisa ser preenchido', timeout);
        $("#jejumLabel").text("O tempo de Jejum precisa ser preenchido");
        $("#jejumLabel").addClass("cancelRed");
    } else if (tempoJejum.length > 2) {
        temErro = true;
        Materialize.toast('O campo tempo de Jejum pode ter no máximo 2 caracteres', timeout);
        $("#jejumLabel").text("O campo tempo de Jejum pode ter no máximo 2 caracteres");
        $("#jejumLabel").addClass("cancelRed");
    } else if (tempoJejum.length < 1) {
        temErro = true;
        Materialize.toast('O campo tempo de Jejum precisa ter no mínimo 1 caracter', timeout);
        $("#jejumLabel").text("O campo tempo de Jejum precisa ter no mínimo 1 caracter");
        $("#jejumLabel").addClass("cancelRed");
    } else {
        $("#jejumLabel").addClass("checkGreen");
        $("#jejumLabel").text("Tempo de jejum(hrs)");
    }
    
    if(horaEntregaLabel == ""){
        temErro = true;
        Materialize.toast('A Hora de entrega precisa ser preenchido', timeout);
        $("#horaEntregaLabel").text("A Hora de entrega precisa ser preenchido");
        $("#horaEntregaLabel").addClass("cancelRed");
    } else if (horaEntregaLabel.length > 2) {
        temErro = true;
        Materialize.toast('A Hora de entrega pode ter no máximo 2 caracteres', timeout);
        $("#horaEntregaLabel").text("A Hora de entrega pode ter no máximo 2 caracteres");
        $("#horaEntregaLabel").addClass("cancelRed");
    } else if (horaEntregaLabel.length < 1) {
        temErro = true;
        Materialize.toast('A Hora de entrega precisa ter no mínimo 1 caracter', timeout);
        $("#horaEntregaLabel").text("A Hora de entrega precisa ter no mínimo 1 caracter");
        $("#horaEntregaLabel").addClass("cancelRed");
    } else {
        $("#horaEntregaLabel").addClass("checkGreen");
        $("#horaEntregaLabel").text("Hora de entrega(Hrs)");
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

