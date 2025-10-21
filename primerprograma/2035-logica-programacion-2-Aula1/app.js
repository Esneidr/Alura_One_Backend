let secretNumber = 0;
let attempt = 0;
let listNumbers = [];
let numberMax = Math.floor(Math.random() * 100) + 1; // Número máximo aleatorio entre 1 y 100

function assignTextElemnt(element, text) {
    let elemHMTL = document.querySelector(element);
    elemHMTL.innerHTML = text;
    return;
}

function userAttempt() {
    let userNumber = parseInt(document.getElementById('userNumber').value);

    if (userNumber === secretNumber) {
        assignTextElemnt('p', `¡Felicidades! Adivinaste el número. en ${attempt} ${attempt === 1 ? 'intento' : 'intentos'}.`);
        document.getElementById('restart').disabled = false;
        //document.getElementById('restart').removeAttribute('disabled');
    } else {
        if (userNumber > secretNumber) {
            assignTextElemnt('p', 'El número es menor. Intenta de nuevo.');
        } else {
            assignTextElemnt('p', 'El número es mayor. Intenta de nuevo.');
        }
        attempt++;
        clearInput();
    }
    return;
}

function clearInput() {
    document.getElementById('userNumber').value = '';
}

function generateSecretNumber() {
    let seacrhNumber =  Math.floor(Math.random() * numberMax) + 1;

    if (listNumbers.length === numberMax) {
        assignTextElemnt('p', `Ya se sortearon todos los números posibles entre 1 y ${numberMax}.`);
        
    } else {
            if (listNumbers.includes(seacrhNumber)) {
        return generateSecretNumber();
    } else {
        listNumbers.push(seacrhNumber);
        return seacrhNumber;
    }

    }
}

function initialConditions() {
    assignTextElemnt('h1', '¡Bienvenido al juego!');
    assignTextElemnt('p', `Adivina el número entre 1 y ${numberMax}.`);
    secretNumber = generateSecretNumber(); // Se genera un nuevo número secreto
    attempt = 1; // Se reinicia el contador de intentos
    document.getElementById('restart').disabled = true;
}

function restartGame() {
    clearInput(); // Se limpia el campo de entrada
    initialConditions(); 
}

initialConditions();