window.addEventListener("load", function () {
    consultarTareas();
    function consultarTareas() {
        fetch("/odontologos")
            .then(response => response.json())
            .then(tareas => {
                renderizarTareas(tareas);
                botonBorrarTarea();
            })
            .catch(error => console.log(error));
    };

    function renderizarTareas(listado) {
        const tablaOdontologos = document.querySelector('#tablaOdontologos');
        tablaOdontologos.innerHTML = '';

        listado.forEach(odontologo => {
            const fila = document.createElement('div');
            fila.classList.add('row', 'row-cols-6', 'mt-1', 'mb-1');
            fila.innerHTML = `<div class="col result pt-1">${odontologo.id}</div>
            <div class="col result pt-1">${odontologo.numeroMatricula}</div>
          <div class="col result pt-1">${odontologo.nombre}</div>
          <div class="col result pt-1">${odontologo.apellido}</div>
          <div class="col result"><button type="button" id="${odontologo.id}" class="btn buton-pri">🔄</button></div>
          <div class="col result"><button type="button" id="${odontologo.id}" class="btn buton-dan">❌</button></div>`;

            const btnActualizar = fila.querySelector('.buton-pri');
            btnActualizar.addEventListener('click', event => {
                const id = event.target.id;

                const formulario = document.createElement('form');
                formulario.classList.add('row', 'mt-2', 'mb-2');
                formulario.innerHTML = `<div class="col">
                <label for="numeroMatricula">Nro. Matrícula:</label>
          <input type="text" class="form-control" id="numeroMatricula" required>
        </div>
        <div class="col">
        <label for="nombre">Nombre:</label>
        <input type="text" class="form-control" id="nombre" required>
        </div>
        <div class="col">
          <label for="apellido">Apellido:</label>
          <input type="text" class="form-control" id="apellido" required>
        </div>
        <div class="col">
          <button type="button" class="btn btn-primary guardar-cambios">Guardar</button>
        </div>
        <div class="col">
          <button type="button" class="btn btn-secondary cancelar">Cancelar</button>
        </div>
        `
                fila.parentNode.insertBefore(formulario, fila.nextSibling);

                const btnCancelar = formulario.querySelector('.cancelar');
                btnCancelar.addEventListener('click', event => {
                    fila.parentNode.removeChild(formulario);
                });

                const btnGuardarCambios = formulario.querySelector('.guardar-cambios');
                btnGuardarCambios.addEventListener('click', event => {
                    const nuevaMatricula = formulario.querySelector('#numeroMatricula').value;
                    const nuevoNombre = formulario.querySelector('#nombre').value;
                    const nuevoApellido = formulario.querySelector('#apellido').value;

                    if (!nuevaMatricula || !nuevoNombre || !nuevoApellido) {
                        alert('Todos los campos son obligatorios.');
                        return;
                    }

                    fetch(`/odontologos/actualizar`, {
                        method: 'PUT',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify({ "id": id,
                         matricula: nuevaMatricula,
                         nombre: nuevoNombre,
                         apellido: nuevoApellido})
                    })
                        .then(response => {
                            console.log(response.status);

                            consultarTareas();
                        })
                });
            });

            tablaOdontologos.appendChild(fila);
        })
    }

    function botonBorrarTarea() {
        const btnBorrarTarea = document.querySelectorAll('.buton-dan');

        btnBorrarTarea.forEach(btn => {
            btn.addEventListener('click', event => {
                const id = event.target.id;


                fetch(`/odontologos/eliminar/${id}`, {
                    method: 'DELETE',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                })
                    .then(response => {
                        console.log(response.status);

                        consultarTareas();
                    })
            });
        });

    };
})