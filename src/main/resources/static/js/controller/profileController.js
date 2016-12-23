(function() {
	'use strict';

	var pomodoroApp = angular.module('pomodoroApp');

	pomodoroApp.controller('profileController', ['$scope', 'UserService', 'TaskService', function($scope, UserService, TaskService) {
		$scope.email = '';

		$scope.getPersonInfo = function() {
			PersonService.getPersonInfo($scope.email).then(function(response) {
				var person = response.data;
				if (person)
		    		$scope.person = response.data;
		    });
		};
	}]);
}());
