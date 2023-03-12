function init() {
    get_contatos()
}

function get_contatos() {
    let req = new XMLHttpRequest();
    req.open('GET', 'http://localhost:8080/api/contato', true);
    req.send();
    let contatos = []
    let table = document.getElementById("table-body")

    req.onreadystatechange = function () {
        if (req.readyState == 4) {
            if (req.status = 200) {
                contatos = JSON.parse(req.responseText)
            }
            if (contatos.length > 0) {
                table.innerHTML = ""
                contatos.forEach(contato => {
                    table.innerHTML += `<tr>
                        <td scope="row" id="tb-col">${contato.name}</td>
                        <td id="tb-col">${contato.telefone}</td>
                        <td id="tb-col">${contato.endereco}</td>
                        <td>
                            <button id="edit-btn" type="button" onclick="toggle_form('${contato.id}', '${contato.name}', '${contato.endereco}', '${contato.telefone}')">
                                <img src="./images/edit.png" id="icon-btn">
                            </button>
                            <button id="del-btn" type="button" onclick="delete_contato('${contato.id}')">
                                <img src="./images/cross.png" id="icon-btn">
                            </button>
                        </td>
                        </tr>`
                });
            }
        }
    }
}

function toggle_form(id, nome, end, tel) {
    var div = document.getElementById("div-form");
    var titulo = "Novo Contato"
    if(id != undefined){
        titulo = "Atualizar Contato"
    }
    if(nome == undefined){
        nome = ""
    }
    if(end == undefined){
        end = ""
    }
    if(tel == undefined){
        tel = ""
    }
    
    var form = `
            <h3>${titulo}</h3>
            <form>  
            <label for="nome">Nome: </label>
                    <input type="text" id="nome" name="nome" value="${nome}" placeholder="nome do contato"> <br>  
            <label for="telefone">Telefone: </label>
                    <input type="tel" id="telefone" name="telefone" value="${tel}" placeholder="número do contato"> <br>  
            <label for="endereco">Endereço: </label>  
                    <input type="text" id="endereco" name="endereco" value="${end}" placeholder="endereço do contato"> <br> 
            </form>
            <button id="submit-btn" onclick="submit(${id})">
                Enviar
            </button> `

    if (div.innerHTML == "" || div.innerHTML != form) {
        div.innerHTML = form
    } else {
        div.innerHTML = ""
    }
}

function submit(id){
    let req = new XMLHttpRequest();
    let url = "http://localhost:8080/api/contato/";
    if(id != undefined && Number.isInteger(id) ){
        url += id;
    }
    let nome = document.getElementById("nome").value;
    let telefone = document.getElementById("telefone").value;
    let endereco = document.getElementById("endereco").value;
    let json = {
        nome: `${nome}`,
        endereco: `${endereco}`,
        telefone: `${telefone}`
    }
    
    console.log(json);
    req.open('POST', url, true);
    req.setRequestHeader('Content-Type', 'application/json');
    req.send(json);

    req.onreadystatechange = function () {
        if (req.readyState == 4) {
            console.log(req.responseText);
            get_contatos();
        }
    }
}

function delete_contato(id) {

    if(id == undefined || Number.isInteger(id) ){
        console.log("id errado");
        return;
    }
    
    let req = new XMLHttpRequest();
    let url = "http://localhost:8080/api/contato/";
    url += id;
    req.open('DELETE', url, true);
    req.send();

    req.onreadystatechange = function () {
        if (req.readyState == 4) {
            console.log(req.responseText);
            get_contatos();
        }
    }

}
