const formulario = document.querySelector('#formulario');

formulario.addEventListener('submit', function (event) {
    event.preventDefault();
    const numeroMatricula = document.querySelector('#numeroMatricula').value;
    const nombre = document.querySelector('#nombre').value;
    const apellido = document.querySelector('#apellido').value;
    console.log("enviado => matricula: "+ numeroMatricula+"  ,nombre: "+ nombre  +"  ,apellido: "+apellido);

    if (numeroMatricula.trim() === "" || nombre.trim() === "" || apellido.trim() === "") {
        alert("Completar totos los campos")
        return;
    }

    fetch('/odontologos/alta', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            "matricula": numeroMatricula,
            "nombre": nombre,
            "apellido": apellido
        })
    })
    .then((response) => {
        if (response.ok) {
            Swal.fire({
                title: 'Odontólogo registrado',
                text: 'El odontólogo ha sido registrado correctamente',
                icon: 'success',
                footer: '<a href="/html/getOdontologo.html">Ver Odontologos</a>'

            });
            resetForm();
        } else {
            Swal.fire({
                title: 'Error al registrar odontólogo',
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