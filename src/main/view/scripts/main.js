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
            <h2>${titulo}</h2>
            <form>  
            <label for="nome-input" id="nome-label">Nome: </label>
                    <input type="text" id="nome-input" name="nome" value="${nome}" placeholder="nome do contato"> <br>  
            <label for="telefone-input" id="telefone-label">Telefone: </label>
                    <input type="tel" id="telefone-input" name="telefone" value="${tel}" placeholder="número do contato"> <br>  
            <label for="endereco-input" id="endereco-label">Endereço: </label>  
                    <input type="text" id="endereco-input" name="endereco" value="${end}" placeholder="endereço do contato"> <br> 
            </form>
            <div id="div-btn-submit">
                <button id="submit-btn" onclick="submit(${id})">
                    Enviar
                </button>
            </div>`

    if (div.innerHTML == "" || div.innerHTML != form) {
        
        div.style.backgroundColor = "#7c349256";
        div.innerHTML = form
    } else {
        div.style.backgroundColor = "";
        div.innerHTML = ""
    }
}

function submit(id){
    let req = new XMLHttpRequest();
    let url = "http://localhost:8080/api/contato";
    
    if(id != undefined && Number.isInteger(id) ){
        url += "/" + id;
    }
    
    req.open("POST", url, true);
    req.setRequestHeader('Content-Type', 'application/json');
    let nomeValue = document.getElementById("nome-input").value;
    let telefoneValue = document.getElementById("telefone-input").value;
    let enderecoValue = document.getElementById("endereco-input").value;

    req.send(JSON.stringify({
        nome: nomeValue,
        endereco: enderecoValue,
        telefone: telefoneValue
    }))

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
