let numMaxAttempts = parseInt(prompt("Ingresa el número maximo de intentos.")); // Número máximo de intentos
let numSecret = Math.floor(Math.random() * numMaxAttempts) + 1; // Número secreto entre 1 y 20
let numUser = 0; 
let attempts = 1;
// let word = 'vez.';
let maxAttempts = 5;

while (numUser != numSecret) {
    numUser = parseInt(prompt( `Me indicas un número entre 1 y ${numMaxAttempts} por favor:`));
    // console.log(typeof numUser);
    //Condición simplificada 
    // word = (attempts === 1) ? 'vez' : 'veces';

    if (numUser == numSecret) {
        alert(`Acertaste, el número secreto es el ${numSecret}. Lo lograstes en ${attempts} ${attempts == 1 ? 'vez' : 'veces'}.`);
    } else {
        if (numUser > numSecret) {
            alert('El número secreto es menor.');
        } else {
            alert('El número secreto es mayor.');
        }
        attempts++;

        if (attempts > maxAttempts) {
            alert(`Lo siento, has agotado tus ${maxAttempts} intentos. El número secreto era ${numSecret}.`);
            break;
        }
        // alert(`Sigue intentando ${numUser}, no es el número secreto.`);
    }
}
