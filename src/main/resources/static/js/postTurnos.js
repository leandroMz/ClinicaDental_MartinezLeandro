const formulario = document.querySelector('#formulario');
formulario.addEventListener('submit', function (event) {
    event.preventDefault();
    const fecha = document.querySelector('#fecha').value;
    const paciente = document.querySelector('#pacienteDto').value;
    const odontologo = document.querySelector('#odontologoDto').value;


    if (fecha.trim() === "" || paciente.trim() === "" || odontologo.trim() === "") {
        return;
    }

    fetch('/turnos/alta', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          "fecha": fecha,
            "pacienteDto": paciente,
            "odontologoDto": odontologo
        })
    })
    .then((response) => {
        if (response.ok) {
            Swal.fire({
                title: 'Turno registrado',
                text: 'El turno ha sido registrado correctamente',
                icon: 'success',
                footer: '<a href="/html/getTurno.html">Ver Turnos</a>'

            });
            resetForm();
        } else {
            Swal.fire({
                title: 'Error al registrar turno',
                text: 'No se ha podido registrar el turno. Verifica los campos y vuelve a intentarlo',
                icon: 'error'
            });
        }
    })
    .catch((error) => {
        console.error(error);
    });
    console.log("enviado a base de datos");
});