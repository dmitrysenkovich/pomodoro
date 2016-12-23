(function() {
	'use strict';

	var pomodoroApp = angular.module('pomodoroApp');

	pomodoroApp.controller('authorizationController', ['$scope', '$location', 'UserService', function($scope, $location, UserService) {
		$scope.user = { login: '', password: '', email: '' };

		$scope.signIn = function() {
			UserService.signIn($scope.user)
				.success(function(data) {
					console.log(data);
					$scope.userId = data.userId;
			    	$scope.errorMessage = '';
			    	$scope.authenticated = true;
			    	$location.path('/dashboard');
			    })
			    .error(function() {
			    	$scope.errorMessage = 'Invalid login or password';
			    });
		};

		$scope.signUp = function() {
			UserService.signUp($scope.user)
				.success(function(userId) {
					$scope.userId = userId;
			    	$scope.errorMessage = '';
			    	$scope.authenticated = true;
			    	$location.path('/dashboard');
			    })
			    .error(function() {
			    	$scope.errorMessage = 'Login or email exists';
			    });
		};

		$scope.signOut = function() {
	    	$scope.authenticated = false;
	    	$location.path('/login');
		};
	}]);
}());
