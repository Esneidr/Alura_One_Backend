import getDatos from "./getDatos.js";

const params = new URLSearchParams(window.location.search);
const serieId = params.get('id');
const listaTemporadas = document.getElementById('temporadas-select');
const fichaSerie = document.getElementById('temporadas-episodios');
const fichaDescripcion = document.getElementById('ficha-descripcion');

// Funcion para cargar temporadas
function cargarTemporadas() {
    getDatos(`/series/${serieId}/temporadas/todas`)
        .then(data => {
            // obtener temporadas únicas según seasonNumber
            const temporadasUnicas = [...new Set(data.map(temporada => temporada.seasonNumber))];

            listaTemporadas.innerHTML = ''; // Limpia las opciones existentes

            // opción por defecto
            const optionDefault = document.createElement('option');
            optionDefault.value = '';
            optionDefault.textContent = 'Seleccione la temporada';
            listaTemporadas.appendChild(optionDefault);

            // agregar temporadas individuales
            temporadasUnicas.forEach(temp => {
                const option = document.createElement('option');
                option.value = temp;
                option.textContent = `Temporada ${temp}`;
                listaTemporadas.appendChild(option);
            });

            // opción para ver todas juntas
            const optionTodos = document.createElement('option');
            optionTodos.value = 'todas';
            optionTodos.textContent = 'Todas las temporadas';
            listaTemporadas.appendChild(optionTodos);
        })
        .catch(error => {
            console.error('Error al obtener temporadas:', error);
        });
}

// Funcion para cargar episodios de una temporada
function cargarEpisodios() {
    getDatos(`/series/${serieId}/temporadas/${listaTemporadas.value}`)
        .then(data => {
            // Extrae temporadas únicas usando seasonNumber
            const temporadasUnicas = [...new Set(data.map(ep => ep.seasonNumber))];

            fichaSerie.innerHTML = ''; 

            temporadasUnicas.forEach(temp => {
                const ul = document.createElement('ul');
                ul.className = 'episodios-lista';

                // Filtra por seasonNumber
                const episodiosTemporada = data.filter(ep => ep.seasonNumber === temp);

                const listaHTML = episodiosTemporada.map(ep => `
                    <li>${ep.episodeNumber} - ${ep.title}</li>
                `).join('');

                ul.innerHTML = listaHTML;
                
                const tituloTemporada = document.createElement('p');
                tituloTemporada.textContent = `Temporada ${temp}`;

                fichaSerie.appendChild(tituloTemporada);
                fichaSerie.appendChild(ul);
                fichaSerie.appendChild(document.createElement('br'));
            });
        })
        .catch(error => console.error('Error al obtener episodios:', error));
}


// Funcion para cargar informaciones de la serie
function cargarInfoSerie() {
    getDatos(`/series/${serieId}`)
        .then(data => {
            fichaDescripcion.innerHTML = `
                <img src="${data.poster}" alt="${data.title}" />
                <div>
                    <h2>${data.title}</h2>
                    <div class="descricao-texto">
                        <p><b>Média de evaluaciones:</b> ${data.rating}</p>
                        <p>${data.synopsis}</p>
                        <p><b>Actores:</b> ${data.actors}</p>
                    </div>
                </div>
            `;
        })
        .catch(error => {
            console.error('Error al obtener informaciones de la serie:', error);
        });
}


// Adiciona escuchador de evento para el elemento select
listaTemporadas.addEventListener('change', cargarEpisodios);

// Carga las informaciones de la série y las temporadas cuando la página carga
cargarInfoSerie();
cargarTemporadas();
