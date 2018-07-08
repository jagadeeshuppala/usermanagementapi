var app = angular.module("UserManagement", []);

// Controller Part
app.controller("UserController", function($scope, $http) {


    $scope.users = [];
    /*$scope.userForm = {
        id: 1,
        firstName: "",
        lastName: ""
    };*/

    // Now load the data from server
    _refreshUserData();

    // HTTP POST/PUT methods for add/edit user
    // Call: http://localhost:8080/users
    $scope.submitUser = function() {

        var method = "POST";
        var url = "/users";

        $http({
            method: method,
            url: url,
            data: angular.toJson($scope.userForm),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(_success, _error);
        _clearFormData();
    };

    /*$scope.createUser = function() {
        _clearFormData();
    }*/



    // Private Method
    // HTTP GET- get all users collection
    // Call: http://localhost:8080/users
    function _refreshUserData() {
        $http({
            method: 'GET',
            url: '/users'
        }).then(
            function(res) { // success
                $scope.users = res.data;
            },
            function(res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }

    function _success(res) {
        _refreshUserData();
        _clearFormData();
    }

    function _error(res) {
        var data = res.data;
        var status = res.status;
        var header = res.header;
        var config = res.config;
        alert("Error: " + status + ":" + data);
    }

    // Clear the form
    function _clearFormData() {
        $scope.userForm.firstName = "";
        $scope.userForm.lastName = "";
        $scope.userForm.email = "";
        $scope.userForm.imageUrl = "";
    };
});