var app = angular.module('springMvcAngular')
    .factory('PessoaService', ['$http', PessoaService]);

function PessoaService($http) {

    return {
        salvar : function(pessoa) {
            return $http.post('/pessoas', pessoa);
        },

        listar : function() {
            return $http.get('/pessoas/');
        },

        buscarPorId : function(id) {
            return $http.get('/pessoas/' + id );
        }

    }
}