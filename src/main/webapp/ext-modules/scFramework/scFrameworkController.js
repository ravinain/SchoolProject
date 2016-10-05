/**
 * 
 */
"use strict";
angular.module("scFramework").controller("scFrameworkController", ['$scope', '$location', '$rootScope', function($scope, $location, $rootScope) {
	
	$scope.$on('sc-menu-selected-item-event', function(evt, data){
//		$scope.routeString = data.route;
		$location.path(data.route);
	});
	
}]);