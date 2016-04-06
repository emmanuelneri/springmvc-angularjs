var app = angular.module('pessoaController', []);

app.controller('listarPessoas', function ($scope, $http) {
    $http.get('http://localhost:8080/springmvc-angularjs/pessoa/listar').then(function(response) {
        $scope.pessoas = response.data;
    });
});