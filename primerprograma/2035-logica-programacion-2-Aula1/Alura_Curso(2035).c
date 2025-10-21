// Ejercicios paracticos Clase 2

// Aula 2 
// 1. Crear una función que muestre "¡Hola, mundo!" en la consola.
function saludar() {
  console.log("¡Hola, mundo!");
}

saludar(); // Llamada de ejemplo

// 2. Crear una función que reciba un nombre como parámetro y muestre "¡Hola, [nombre]!" en la consola.
function saludarNombre(nombre) {
  console.log(`¡Hola, ${nombre}!`);
}

saludarNombre("Carlos"); // Llamada de ejemplo

// 3. Crear una función que reciba un número como parámetro y devuelva el doble de ese número.
function doble(numero) {
  return numero * 2;
}

console.log(doble(5)); // Resultado: 10

// 4. Crear una función que reciba tres números como parámetros y devuelva su promedio.
function promedio(a, b, c) {
  return (a + b + c) / 3;
}

console.log(promedio(4, 7, 10)); // Resultado: 7

// 5. Crear una función que reciba dos números como parámetros y devuelva el mayor de ellos.
function mayor(a, b) {
  return a > b ? a : b;
}

console.log(mayor(10, 20)); // Resultado: 20

// 6. Crear una función que reciba un número como parámetro y devuelva el resultado de multiplicar ese número por sí mismo.
function cuadrado(numero) {
  return numero * numero;
}

console.log(cuadrado(6)); // Resultado: 36

// Aula 3
// 1. Crea una función que calcule el índice de masa corporal (IMC) de una persona a partir de su altura en metros y peso en kilogramos, que se recibirán como parámetros.
function calcularIMC(peso, altura) {
  const imc = peso / (altura * altura);
  return imc.toFixed(2); // Limita a 2 decimales
}

console.log("IMC:", calcularIMC(70, 1.75)); // Ejemplo: 70kg y 1.75m

// 2. Crea una función que calcule el valor del factorial de un número pasado como parámetro.
function factorial(n) {
  if (n < 0) return "No definido para negativos";
  let resultado = 1;
  for (let i = 1; i <= n; i++) {
    resultado *= i;
  }
  return resultado;
}

console.log("Factorial:", factorial(5)); // Resultado: 120

// 3. Crea una función que convierta un valor en dólares, pasado como parámetro, y devuelva el valor equivalente en reales(moneda brasileña,si deseas puedes hacerlo con el valor del dólar en tu país). Para esto, considera la cotización del dólar igual a R$4,80.
function convertirDolaresAReales(dolares) {
  const tasa = 4.80;
  return (dolares * tasa).toFixed(2);
}

console.log("Reales:", convertirDolaresAReales(100)); // Resultado: R$480.00

// 4. Crea una función que muestre en pantalla el área y el perímetro de una sala rectangular, utilizando la altura y la anchura que se proporcionarán como parámetros.
function salaRectangular(altura, ancho) {
  const area = altura * ancho;
  const perimetro = 2 * (altura + ancho);
  console.log(`Área: ${area} m²`);
  console.log(`Perímetro: ${perimetro} m`);
}

salaRectangular(5, 3); // Ejemplo

// 5. Crea una función que muestre en pantalla el área y el perímetro de una sala circular, utilizando su radio que se proporcionará como parámetro. Considera Pi = 3,14.
function salaCircular(radio) {
  const pi = 3.14;
  const area = pi * radio * radio;
  const perimetro = 2 * pi * radio;
  console.log(`Área: ${area.toFixed(2)} m²`);
  console.log(`Perímetro: ${perimetro.toFixed(2)} m`);
}

salaCircular(2); // Ejemplo

// 6. Crea una función que muestre en pantalla la tabla de multiplicar de un número dado como parámetro.
function tablaMultiplicar(numero) {
  console.log(`Tabla del ${numero}`);
  for (let i = 1; i <= 10; i++) {
    console.log(`${numero} x ${i} = ${numero * i}`);
  }
}

tablaMultiplicar(7); // Ejemplo: tabla del 7

Aula 4
// 1. Crea una lista vacía llamada "listaGenerica".
let listaGenerica = [];

// 2. Crea una lista de lenguajes de programación con algunos elementos.
let lenguagesDeProgramacion = ['JavaScript', 'C', 'C++', 'Kotlin', 'Python'];

// 3. Agrega más lenguajes a la lista.
lenguagesDeProgramacion.push('Java', 'Ruby', 'GoLang');

// 4. Función para mostrar todos los elementos de la lista.
function mostrarLenguajes() {
  console.log("Lista de lenguajes:");
  lenguagesDeProgramacion.forEach((lenguaje) => console.log(lenguaje));
}

// 5. Función para mostrar la lista en orden inverso.
function mostrarLenguajesInverso() {
  console.log("Lista en orden inverso:");
  lenguagesDeProgramacion.slice().reverse().forEach((lenguaje) => console.log(lenguaje));
}

// 6. Función que calcula el promedio de los elementos en una lista de números.
function calcularPromedio(listaNumeros) {
  if (listaNumeros.length === 0) return 0;
  let suma = listaNumeros.reduce((acc, num) => acc + num, 0);
  return suma / listaNumeros.length;
}

// 7. Función que muestra el número más grande y el más pequeño de una lista.
function mostrarMayorYMenor(listaNumeros) {
  if (listaNumeros.length === 0) {
    console.log("Lista vacía");
    return;
  }
  let mayor = Math.max(...listaNumeros);
  let menor = Math.min(...listaNumeros);
  console.log("Mayor:", mayor, "| Menor:", menor);
}

// 8. Función que devuelve la suma de todos los elementos de una lista.
function sumarElementos(listaNumeros) {
  return listaNumeros.reduce((acc, num) => acc + num, 0);
}

// 9. Función que devuelve la posición de un elemento o -1 si no existe.
function buscarElemento(lista, elemento) {
  return lista.indexOf(elemento);
}

// 10. Función que recibe dos listas y devuelve una nueva con la suma elemento a elemento.
function sumarListas(lista1, lista2) {
  if (lista1.length !== lista2.length) {
    console.log("Las listas deben tener el mismo tamaño");
    return [];
  }
  return lista1.map((num, i) => num + lista2[i]);
}

// 11. Función que devuelve una lista con el cuadrado de cada número.
function cuadradoDeElementos(listaNumeros) {
  return listaNumeros.map(num => num * num);
}



