$(document).ready(function() {

    let equipos = {};
    let equipoEdit = {};

    $("#refresh_but").click(function() {
        refresh_table_data();
    });

    $("#agregar_but").click(function() {
        const agregarEquipoData = {
            "nombre": $("#agregar_name").val(),
            "anoFundacion": $("#agregar_ano").val(),
            "cantTitulos": $("#agregar_titulos").val()
        };
        agregarEquipo(agregarEquipoData);
    });

    $("#editar_but").click(function() {
        alert("Haz click en el equipo que deseas editar");
        $(".equipo_row").click(onclickEditar);
    });

    $("#eliminar_but").click(function() {
        alert("Haz click en el equipo que deseas eliminar");
        $(".equipo_row").click(onclickEliminar);
    });

    $('#editar_conf_but').click(function () {
       
        const editarEquipoData = {
            "id": equipoEdit.id,
            "nombre": $("#editar_name").val(),
            "anoFundacion": $("#editar_ano").val(),
            "cantTitulos": $("#editar_titulos").val()
        };
        editarEquipo(editarEquipoData);
    });

    $('#editar_modal').on('hidden.bs.modal', function() {
        $(".equipo_row").unbind( "click" );
    });

    function onclickEditar() {

        equipoEdit = equipos[$(this).attr("equipo_id")];
        $("#editar_name").val(equipoEdit.nombre);
        $("#editar_ano").val(equipoEdit.anoFundacion);
        $("#editar_titulos").val(equipoEdit.cantTitulos);
        $('#editar_modal').modal('show');
    }

    function onclickEliminar() {
        $.ajax({
            type: 'delete',
            url: '/equipo/' + $(this).attr("equipo_id"),
            success: function(data, status) {
                
            },
            error: function(data, status) {
                console.log("Status: " + status + " Data:");
                console.log(data);
            },
            complete: function() {
                $(".equipo_row").unbind( "click" );
                refresh_table_data();
            }
        });
    }

    function agregarEquipo(agregarEquipoData) {

        $.ajax({
            type: 'post',
            url: '/equipo',
            data: JSON.stringify(agregarEquipoData),
            contentType: "application/json; charset=utf-8",
            traditional: true,
            success: function(data, status) {                
                refresh_table_data();
            },
            error: function(data, status) {
                console.log("Status: " + status + " Data:");
                console.log(data);
            },
            complete: function() {
                $("#agregar_name").val('');
                $("#agregar_ano").val('');
                $("#agregar_titulos").val('');
                $('#agregar_modal').modal('hide');
            }
        });
    }

    function editarEquipo(editarEquipoData) {
        
        $.ajax({
            type: 'put',
            url: '/equipo',
            data: JSON.stringify(editarEquipoData),
            contentType: "application/json; charset=utf-8",
            traditional: true,
            success: function(data, status) {               
                refresh_table_data();
            },
            error: function(data, status) {
                console.log("Status: " + status + " Data:");
                console.log(data);
            },
            complete: function() {
                $("#editar_name").val('');
                $("#editar_ano").val('');
                $("#editar_titulos").val('');
                $('#editar_modal').modal('hide');
            }
        });
    }

    function refresh_table_data() {

        $.ajax({
            type: 'get',
            url: '/equipos',
            success: function(data, status) {
                const equiposNuevos = data.equipos;
                let table_body = "";
                for (let pos in equiposNuevos) {
                    const equipo = equiposNuevos[pos];
                    equipos["" + equipo.id] = equipo;
                    table_body += '<tr class="equipo_row" equipo_id="' + equipo.id + '"><td>' + equipo.nombre + '</td><td>' + equipo.anoFundacion + '</td><td>' + equipo.cantTitulos + '</td></tr>';
                }
                $("#table_equipos_body").html(table_body);
            },
            error: function(data, status) {
                console.log("Status: " + status + " Data:");
                console.log(data);
            }
        });
    }
    refresh_table_data();
});