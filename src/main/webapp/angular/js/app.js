
var dimayor = angular.module("dimayor", []);

var controller = function($scope, $log, $http){
    
    $scope.equipos = [];
    
    $scope.getEquipos = function() {

        $http({
            url:'/equipos',
            method:'get'
        })
        .then(function (res) {
            
            if(res.status == 200){
                $scope.equipos = res.data.equipos;
            }else{
                $log.error(res);
            }

        });

        $http({
            url:'http://172.30.180.60:4567/equipos',
            method:'get'
        })
        .then(function (res) {
            
            if(res.status == 200){
                //$scope.equipos = res.data.equipos;
                $log.info(res.data);
            }else{
                $log.error(res);
            }

        });

    }
  
    $scope.getEquipos();
}

dimayor.controller('appController', controller);

