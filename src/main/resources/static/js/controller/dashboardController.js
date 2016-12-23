(function() {
	'use strict';

	var pomodoroApp = angular.module('pomodoroApp');

	pomodoroApp.controller('dashboardController', ['$scope', 'TaskService', function($scope, TaskService) {
		$scope.tasks = [];

		TaskService.all().then(function(response) {
			$scope.tasks = response.data;
		});

		$scope.getPersonInfo = function() {
			PersonService.getPersonInfo($scope.email).then(function(response) {
				var person = response.data;
				if (person)
		    		$scope.person = response.data;
		    });
		};
	}]);
}());
