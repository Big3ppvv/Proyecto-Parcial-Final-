$(document).ready(function() {
    cargarPersonas();
    $('#personas').DataTable();
});

async function cargarPersonas() {
    const request = await fetch('api/personas', {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
    });
    const personas = await request.json();

    let listadoHTML = '';
    for (let persona of personas) {
        let botonEliminar = '<a href="#" onclick="eliminarPersona(' + persona.id + ')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';

        let telefonoTexto = persona.telefono == null ? '-' : persona.telefono;

        let personaHtml = '<tr><td>' + persona.id + '</td><td>'
                        + persona.nombre + ' ' + persona.apellido + '</td><td>'
                        + persona.email + '</td><td>'
                        + telefonoTexto + '</td><td>'
                        + botonEliminar + '</td></tr>';

        listadoHTML += personaHtml;
    }

    document.querySelector('#personas tbody').innerHTML = listadoHTML;
}

async function eliminarPersona(id) {

    if (!confirm('¿Desea eliminar esta persona?')) {
        return;
    }

    const request = await fetch('api/personas/' + id, {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
    });

    location.reload();
}