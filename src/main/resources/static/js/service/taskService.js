(function() {
	'use strict';

	var pomodoroApp = angular.module('pomodoroApp');

	pomodoroApp.factory('TaskService', ['$http', function($http) {
		var baseUrl = '/pomodoro/api/tasks';

		return {
			all: function() {
				return $http.get(baseUrl);
			},

			task: function(taskId) {
				var url = baseUrl + '?taskId=' + taskId;
				return $http.get(url);
			},

			byText: function(text) {
				var url = baseUrl + '?text=' + text;
				return $http.get(url);
			},

			meta: function(taskId) {
				var url = baseUrl + '/meta?taskId=' + taskId;
				return $http.get(url);
			},

			save: function(user) {
				var url = baseUrl + '/save';
				return $http.post(url, JSON.stringify(task));
			},

			update: function(task) {
				var url = baseUrl + '/update';
				return $http.post(url, JSON.stringify(task));
			}
		};
	}]);
}());
