window.addEventListener("load", function () {
    consultarTareas();
    function consultarTareas() {
        fetch("/turnos")
            .then(response => response.json())
            .then(tareas => {
                renderizarTareas(tareas);
                botonBorrarTarea();
            })
            .catch(error => console.log(error));
    };

    function renderizarTareas(listado) {
        const tablaTurnos = document.querySelector('#tablaTurnos');
        tablaTurnos.innerHTML = '';

        listado.forEach(turno => {
            const fila = document.createElement('div');
            fila.classList.add('row', 'row-cols-6', 'mt-1', 'mb-1');
            fila.innerHTML = `<div class="col result pt-1">${turno.id}</div>
          <div class="col result pt-1">${turno.fecha}</div>
            <div class="col result pt-1">${turno.pacienteDto}</div>
          <div class="col result pt-1">${turno.odontologoDto}</div>
          <div class="col result"><button type="button" id="${turno.id}" class="btn buton-pri">🔄</button></div>
          <div class="col result"><button type="button" id="${turno.id}" class="btn buton-dan">❌</button></div>`;

            const btnActualizar = fila.querySelector('.buton-pri');
            btnActualizar.addEventListener('click', event => {
                const id = event.target.id;

                const formulario = document.createElement('form');
                formulario.classList.add('row', 'mt-2', 'mb-2');
                formulario.innerHTML = `<div class="col">
                <label for="fecha">fecha de Turno:</label>
          <input type="date" class="form-control" id="fecha" required>
        </div>
        <div class="col">
        <label for="pacienteDto">Paciente:</label>
        <input type="text" class="form-control" id="pacienteDto" required>
        </div>
        <div class="col">
          <label for="odontologoDto">Odontologo:</label>
          <input type="text" class="form-control" id="odontologoDto" required>
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
                    const nuevaFechaTurno = formulario.querySelector('#fecha').value;
                    const nuevoPaciente = formulario.querySelector('#pacienteDto').value;
                    const nuevoOdontologo = formulario.querySelector('#odontologoDto').value;

                    if (!nuevaFechaTurno || !nuevoPaciente || !nuevoOdontologo) {
                        alert('Todos los campos son obligatorios.');
                        return;
                    }

                    fetch(`/turnos/actualizar`, {
                        method: 'PUT',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify({ "id": id, fecha: nuevaFechaTurno, id, paciente: nuevoPaciente, odontologo: nuevoOdontologo })
                    })
                        .then(response => {
                            console.log(response.status);
                            if (response.ok) {
                              Swal.fire({
                                  title: 'Turno Actualizado',
                                  text: 'El turno a sido actualizado con exito!',
                                  icon: 'success'});
                          }consultarTareas();
                        })
                });
            });

            tablaTurnos.appendChild(fila);
        })
    }

    function botonBorrarTarea() {
        const btnBorrarTarea = document.querySelectorAll('.buton-dan');

        btnBorrarTarea.forEach(btn => {
            btn.addEventListener('click', event => {
                const id = event.target.id;
                //const id = this.dataset.id;

                fetch(`/turnos/eliminar/${id}`, {
                    method: 'DELETE',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                })
                    .then(response => {
                        console.log(response.status);
                        if (response.ok) {
                          Swal.fire({
                              title: 'Turno Eliminado',
                              text: 'El turno a sido eliminado :( ',
                              icon: 'success'});
                      }
                        consultarTareas();
                    })
            });
        });

    };
  })

