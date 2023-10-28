$(document).ready(function () {
    dataTable = $('#data-table').DataTable({
        responsive: true,
        dom: 'Blfrtip',
        buttons:[
            {
                text:      '<i class="fas fa-plus"></i> Adicionar',
                titleAttr: 'Adicionar',
                className: 'btn btn-success buttons-add',
                action: function (e, dt, node, config) {
                    window.location.href = '/classificador/categoria/cadastro';
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
                    return '<div class="actions"><a class="btn btn-primary" href="/classificador/categoria/editar/'+data.id+'">'+
                    '<i class="fas fa-pen"></i> Editar</a><a class="btn btn-danger" '+
                    'onclick="return confirm(\'Tem certeza que deseja excluir esse registro?\');" '+
                    'href="/classificador/categoria/excluir/'+data.id+'"><i class="fas fa-trash"></i> Excluir</a></div>'
                }
            }
        ],
        "language": {
            "url": "../json/Portuguese.json"
        }
    });
});
