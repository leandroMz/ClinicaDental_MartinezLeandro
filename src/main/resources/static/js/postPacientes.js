const formulario = document.querySelector('#formulario');

formulario.addEventListener('submit', function (event) {
    event.preventDefault();

    const nombre = document.querySelector('#nombre').value;
    const apellido = document.querySelector('#apellido').value;
    const documento = document.querySelector('#documento').value;
    const fechaIngreso = document.querySelector('#fechaIngreso').value;
    const email = document.querySelector('#email').value;
    const calle = document.querySelector('#calle').value;
    const numeroCalle = document.querySelector('#numeroCalle').value;
    const localidad = document.querySelector('#localidad').value;
    const provincia = document.querySelector('#provincia').value;

    if (nombre.trim() === "" || apellido.trim() === "" || documento.trim() === "") {
        alert("todos los campos son obligatorios")
        return;
    }

    fetch('/pacientes/alta', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            "nombre": nombre,
            "apellido": apellido,
            "documento": documento,
            "fechaIngreso": fechaIngreso,
            "email": email,
            "domicilio": {
                "calle": calle,
                "numeroCalle": numeroCalle,
                "localidad": localidad,
                "provincia": provincia
            }
        })
    })
     .then((response) => {
         if (response.ok) {
             Swal.fire({
                 title: 'Paciente registrado',
                 text: 'El paciente ha sido registrado correctamente',
                 icon: 'success',
                 footer: '<a href="/html/getPaciente.html">Ver Pacientes</a>'
             });
             resetForm();
         } else {
             Swal.fire({
                 title: 'Error al registrar paciente',
                 text: 'No se ha podido registrar el odontólogo. Verifica los campos y vuelve a intentarlo',
                 icon: 'error'
             });
         }
     })
     .catch((error) => {
         console.error(error);
     });
     console.log("enciado a base datos");
     function resetForm() {
             formulario.reset();
         };
 })