window.addEventListener("load", function () {

    consultarTareas();
  
    function consultarTareas() {
        console.log("Consultando odobntologos...");
        fetch("/pacientes")
            .then(response => response.json())
            .then(tareas => {
                console.log("Tareas");
                console.table(tareas);
  
                renderizarTareas(tareas);
                //botonesCambioEstado();
                botonBorrarTarea();
            })
            .catch(error => console.log(error));
    };
  
  
    function renderizarTareas(listado) {
  
        const tablaOdontologos = document.querySelector('#tablaOdontologos');
        tablaOdontologos.innerHTML = '';
  
        listado.forEach(paciente => {
            const fila = document.createElement('div');
            fila.classList.add('row', 'row-cols-12', 'mt-1', 'mb-1');
            fila.innerHTML = `
            <div class="col result pt-1">${paciente.id}</div>
            <div class="col result pt-1">${paciente.nombre}</div>
            <div class="col result pt-1">${paciente.apellido}</div>
            <div class="col result pt-1">${paciente.documento}</div>
            <div class="col result pt-1">${paciente.fechaIngreso}</div>
            <div class="col result pt-1">${paciente.email}</div>
            <div class="col result pt-1">${paciente.domicilio.calle}</div>
            <div class="col result pt-1">${paciente.domicilio.numeroCalle}</div>
            <div class="col result pt-1">${paciente.domicilio.localidad}</div>
            <div class="col result pt-1">${paciente.domicilio.provincia}</div>
            <div class="col"><button type="button" id="${paciente.id}" class="btn buton-pri">🔄</button></div>
            <div class="col"><button type="button" id="${paciente.id}" class="btn buton-dan">❌</button></div>`;

            const btnActualizar = fila.querySelector('.buton-pri');
            btnActualizar.addEventListener('click', event => {
                const id = event.target.id;

                const formulario = document.createElement('form');
                formulario.classList.add('row', 'mt-2', 'mb-2');
                formulario.innerHTML = `<div class="col">
          <label for="nombre">Nombre:</label>
          <input type="text" class="form-control" id="nombre" required>
        </div>
        <div class="col">
          <label for="apellido">Apellido:</label>
          <input type="text" class="form-control" id="apellido" required>
        </div>
        <div class="col">
          <label for="documento">Documento:</label>
          <input type="text" class="form-control" id="documento" required>
        </div>
        <div class="col">
          <label for="fechaIngreso">fecha:</label>
          <input type="date" class="form-control" id="fechaIngreso" required>
        </div>
        <div class="col">
          <label for="email">Email:</label>
          <input type="text" class="form-control" id="email" required>
        </div>
        <div class="col">
          <label for="calle">Calle:</label>
          <input type="text" class="form-control" id="calle" required>
        </div>
        <div class="col">
          <label for="numeroCalle">Numero:</label>
          <input type="number" class="form-control" id="numeroCalle" required>
        </div>
        <div class="col">
          <label for="localidad">Localidad:</label>
          <input type="text" class="form-control" id="localidad" required>
        </div>
        <div class="col">
          <label for="provincia">Provincia:</label>
          <input type="text" class="form-control" id="provincia" required>
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
                    const nuevoNombre = formulario.querySelector('#nombre').value;
                    const nuevoApellido = formulario.querySelector('#apellido').value;
                    const nuevoDocumento = formulario.querySelector('#documento').value;
                    const nuevaFecha = formulario.querySelector('#fechaIngreso').value;
                    const nuevoEmail = formulario.querySelector('#email').value;
                    const nuevaCalle = formulario.querySelector('#calle').value;
                    const nuevoNumeroCalle = formulario.querySelector('#numeroCalle').value;
                    const nuevaLocalidad = formulario.querySelector('#localidad').value;
                    const nuevaProvincia = formulario.querySelector('#provincia').value;


                    if (!nuevoNombre || !nuevoApellido) {
                        alert('Todos los campos son obligatorios.');
                        return;
                    }

                    fetch('/pacientes/actualizar', {
                      method: 'PUT',
                      headers: {
                        'Content-Type': 'application/json'
                      },
                      body: JSON.stringify({
                        "id":id,
                        nombre: nuevoNombre,
                        apellido: nuevoApellido,
                        documento: nuevoDocumento,
                        fechaIngreso: nuevaFecha,
                        email: nuevoEmail,
                        domicilio:{
                          calle: nuevaCalle,
                          numeroCalle: nuevoNumeroCalle,
                          localidad: nuevaLocalidad,
                          provincia: nuevaProvincia
                        }
                      })
                    }).then(response => {
                      if(response.ok) {

                        consultarTareas();
                      } else {
                        throw new Error('Error en la petición');
                      }
                    }).catch(error => {
                      console.error('Error:', error);
                    });

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
  
                fetch(`/pacientes/eliminar/${id}`, {
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