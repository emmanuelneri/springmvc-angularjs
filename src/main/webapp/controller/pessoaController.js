var app = angular.module('pessoaController', []);

app.controller('listarPessoas', function ($scope, $http) {
    $http.get('http://localhost:8080/springmvc-angularjs/pessoa/listar').then(function(response) {
        $scope.pessoas = response.data;
    });
});

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