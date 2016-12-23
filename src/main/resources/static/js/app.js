(function() {
	'use strict';

	var pomodoroApp = angular.module('pomodoroApp', ['ngRoute']);

	pomodoroApp.config(['$routeProvider', '$locationProvider', function($routeProvider, $locationProvider) {
		$routeProvider
			.when('/login', {
				templateUrl : 'templates/login.html'
			})
			.when('/', {
				templateUrl : 'templates/login.html'
			})
			.when('/dashboard', {
				templateUrl : 'templates/dashboard.html',
				controller : 'dashboardController'
			})
			.when('/history', {
				templateUrl : 'templates/history.html',
				controller : 'historyController'
			})
			.when('/profile', {
				templateUrl : 'templates/profile.html',
				controller : 'profileController'
			});

		$locationProvider.html5Mode(true);
	}]);
}());
