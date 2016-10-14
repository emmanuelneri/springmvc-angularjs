var app = angular.module('springMvcAngular')
    .controller('PessoaController', ['$scope', 'PessoaService', PessoaController]);

function PessoaController($scope, PessoaService) {
    iniciarPessoa();
    listar();

    $scope.salvar = function() {
        PessoaService.salvar($scope.pessoa).then(function(response) {
            iniciarPessoa();
            listar();
        }, function(response) {
            response.data.forEach(function(error) {
                $scope.erros = response;
            });
        });
    };

    $scope.editar = function(id) {
        PessoaService.buscarPorId(id).then(function(response) {
            $scope.pessoa = response.data;
        }, function(response) {
            console.info("Não foi possível buscar a empresa ", id);
        });
    };

    function iniciarPessoa() {
        $scope.pessoa = {nome:'', cpf:''};
    }

    function listar() {
        PessoaService.listar().then(function (response) {
           $scope.pessoas = response.data;
        })
    }
}

app.filter('cpf', function(){
    return function(cpf){
        return cpf.substr(0, 3) + '.' + cpf.substr(3, 3) + '.' + cpf.substr(6, 3) + '-' + cpf.substr(9,2);
    };
});

app.filter("toDate", function () {
    return function (x) {
        return new Date(x);
    };
});