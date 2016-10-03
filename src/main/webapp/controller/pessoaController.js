var app = angular.module('springMvcAngular')
    .controller('PessoaController', ['$scope', '$http', PessoaController]);

function PessoaController($scope, $http) {
    iniciarPessoa();
    listar($scope, $http);

    $scope.salvar = function() {
        $http.post('/pessoa/cadastrar', $scope.pessoa)
            .success(function (data) {
                iniciarPessoa();
                listar($scope, $http);
            })
            .error(function(response){
                $scope.erros = response;
            });
    };

    $scope.editar = function(id) {
        $scope.pessoa = $http.get("/pessoa/buscar/" + id)
            .then(function(response) {
                $scope.pessoa = response.data;
        });
    };

    function iniciarPessoa() {
        $scope.pessoa = {nome:'', cpf:''};
    }

    function listar($scope, $http) {
        $http.get('/pessoa/listar').then(function(response) {
            $scope.pessoas = response.data;
        });
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