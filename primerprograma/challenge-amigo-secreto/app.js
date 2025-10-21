// El principal objetivo de este desafío es fortalecer tus habilidades en lógica de programación. Aquí deberás desarrollar la lógica para resolver el problema.
let namesFriends = []
// Genera un número aleatorio entre 1 y 100

function agregarAmigo() {
    // Se valida que el nombre no sea un número y no esté vacío.
    const friend = document.getElementById("amigo").value;
    if (friend === "" || !isNaN(friend)) {
        alert(`El dato ${friend} ${friend === "" ? 'está vacío,' : 'es numérico,'} no es válido.`);
        clearInput(); // Se limpia el campo de entrada.
        return;
    }
    namesFriends.push(friend);
    friendList('listaAmigos', namesFriends);

    /*** Otra opción para mostrar los nombre:  
    let elemHMTL = document.getElementById('listaAmigos');
    elemHMTL.innerHTML = namesFriends.map(n => `<li><strong>${n}</strong></li>`).join('');
    ***/
    clearInput(); // Se limpia el campo de entrada.
}

//Para limpiar el input 
function clearInput() {
    document.getElementById('amigo').value = '';
}

function friendList(id, list) {
    const item = document.getElementById(id);
    item.innerHTML = ""; // Se limpia el contenido

    for (let i = 0; i < list.length; i++) {
        const li = document.createElement("li");
        li.textContent = list[i];
        item.appendChild(li);
    }
}

function sortearAmigo() {
    // se valida que la lista no esté vacía o tenga un solo elemento.
    if (namesFriends.length === 0) {
        alert('La lista esta vacía, no se puede sortear.');
        return;
    } else if (namesFriends.length === 1) {
        alert('En la lista solo hay un amigo, no se puede sortear.');
        return;
    }

    // Se selecciona un amigo al azar de la lista.
    const index = Math.floor(Math.random() * namesFriends.length);
    const select = namesFriends[index];

    // Se muestra el amigo seleccionado en el HTML.
    const result = document.getElementById('resultado');
    result.innerHTML = `<p>El amigo seleccionado es: <strong>${select}</strong></p>`;
}

