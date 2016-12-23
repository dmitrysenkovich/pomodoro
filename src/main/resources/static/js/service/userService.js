(function() {
	'use strict';

	var pomodoroApp = angular.module('pomodoroApp');

	pomodoroApp.factory('UserService', ['$http', function($http) {
		var baseUrl = '/pomodoro/api/users';

		return {
			signIn: function(user) {
				var url = baseUrl + '/signIn?login=' + user.login + '&password=' + user.password;
				return $http.post(url);
			},

			signUp: function(user) {
				var url = baseUrl + '/signUp';
				return $http.post(url, JSON.stringify(user));
			},

			update: function(user) {
				var url = baseUrl + '/update';
				return $http.post(url, JSON.stringify(user));
			}
		};
	}]);
}());
