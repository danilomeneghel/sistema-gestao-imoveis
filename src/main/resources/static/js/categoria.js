$(document).ready(function () {
    dataTable = $('#data-table').DataTable({
        responsive:true,
        dom: 'frtilpB',
        buttons:[
            {
                extend:    'excelHtml5',
                text:      '<i class="fas fa-file-excel"></i> ',
                titleAttr: 'Exportar para Excel',
                className: 'btn btn-success',
                orientation:'landscape',
                exportOptions: {
                    columns: ':visible :not(:last-child)'
                }
            },
            {
                extend:    'pdfHtml5',
                text:      '<i class="fas fa-file-pdf"></i> ',
                titleAttr: 'Exportar para PDF',
                className: 'btn btn-danger',
                orientation:'landscape',
                exportOptions: {
                    columns: ':visible :not(:last-child)'
                },
                customize: function (doc) {
                    doc.content[1].table.widths = "*";
                }
            },
            {
                extend:    'print',
                text:      '<i class="fa fa-print"></i> ',
                titleAttr: 'Imprimir',
                className: 'btn btn-info',
                orientation:'landscape',
                exportOptions: {
                    columns: ':visible :not(:last-child)'
               }
            }
        ],
        "ajax": {
            "url": '/api/classificador/categorias',
            "type": "GET",
            "datatype": "json",
            dataSrc:""
        },
        "columns": [
            { "data": "id" },
            { "data": "nome" },
            {
                "data": null, "render": function (data) {
                    return '<a class="btn btn-primary" href="/classificador/categoria/editar/'+data.id+'"><i class="fas fa-pen"></i> Editar</a>'+
                    '<a class="btn btn-danger" href="/classificador/categoria/excluir/'+data.id+'"><i class="fas fa-trash"></i> Excluir</a>'
                }
            }
        ],
        "language": {
            "url": "../json/Portuguese.json"
        }
    });
});
