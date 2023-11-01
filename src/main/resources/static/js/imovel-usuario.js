$(document).ready(function () {
    var dataTable = $('#data-table').DataTable({
        responsive: true,
        ordering: false,
        dom: 'lfrtip',
        ajax: {
            url: '/imovel/listar/imoveis-usuario',
            type: 'GET',
            datatype: 'json',
            dataSrc: ''
        },
        deferRender: true,
        initComplete: function() {
            var api = this.api();
            $("#data-table thead .filters .filter").each( function ( colIdx ) {
                var cell = $('.filters .filter').eq($(api.column(colIdx).header()).index());
                var title = $(cell).text();
                var select = null;
                if(title == 'Status' || title == 'Negócio' || title == 'Categoria' || title == 'Quarto') {
                    select = $('<select><option value="">'+title+'</option></select>')
                    .appendTo( $(this).empty() )
                    .on( 'change', function () {
                        var val = $(this).val();
                        api.column( colIdx )
                            .search( val ? '^'+$(this).val()+'$' : val, true, false )
                            .draw();
                    } );
                    api.column( colIdx ).data().unique().sort().each( function ( d, j ) {
                        if(title == 'Status') {
                            d = ((d == true) ? 'Ativo' : 'Inativo');
                            select.append( '<option value="'+d+'">'+d+'</option>' );
                        } else {
                            select.append( '<option value="'+d+'">'+d+'</option>' );
                        }
                    } );
                } else {
                    $(cell).html( '<input type="text" placeholder="'+title+'" />' );
                    $('input', $('.filters .filter').eq($(api.column(colIdx).header()).index()) )
                    .off('keyup change')
                    .on('keyup change', function (e) {
                        e.stopPropagation();
                        $(this).attr('title', $(this).val());
                        var regexr = '({search})';
                        var cursorPosition = this.selectionStart;
                        api
                            .column(colIdx)
                            .search((this.value !== "") ? regexr.replace('{search}', '((('+this.value+')))') : "", this.value !== "", this.value === "")
                            .draw();
                        $(this).focus()[0].setSelectionRange(cursorPosition, cursorPosition);
                    });
                }
            });
        },
        columns: [
            { "data": "negocio.nome" },
            { "data": "categoria.nome" },
            { "data": "quarto.quantidade" },
            {
                "data": null, "render": function ( data, type, row ) {
                    return row.bairro.municipio.nome + "/" + row.bairro.municipio.estado.uf;
                 }
            },
            {
                "data": "valor", "render": function ( data ) {
                    return "R$" + data;
                 }
            },
            {
                "data": "ativo", "render": function ( data ) {
                    if (data) {
                        return '<span class="badge bg-success">Ativo</span>'
                    } else {
                        return '<span class="badge bg-danger">Inativo</span>'
                    }
                }
            },
            {
                "data": null, "render": function ( data ) {
                    return '<div class="actions"><a class="btn btn-success" href="/imovel/visualizar/imovel-usuario/'+data.id+'">'+
                    '<i class="fas fa-eye"></i> Ver</a></div>';
                }
            }
        ],
        language: {
            "url": "../json/Portuguese.json"
        }
    });
});
