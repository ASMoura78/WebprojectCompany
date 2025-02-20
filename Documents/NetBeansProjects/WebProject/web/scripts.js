function validarMotorista() {
    // Validação de exemplo para CPF e CNH
    var cpf = document.getElementById("cpf").value;
    var cnh = document.getElementById("cnh").value;
    if (cpf.length != 11) {
        alert("CPF deve ter 11 dígitos.");
        return false;
    }
    if (cnh.length != 11) {
        alert("CNH deve ter 11 dígitos.");
        return false;
    }
    return true;
}

function validarVeiculo() {
    // Validação de exemplo para Placa
    var placa = document.getElementById("placa").value;
    if (placa.length != 7) {
        alert("Placa deve ter 7 caracteres.");
        return false;
    }
    return true;
}



