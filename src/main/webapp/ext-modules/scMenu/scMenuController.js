/**
 * 
 */
"use strict";
angular.module("scMenu").controller("scMenuController", ['$scope', '$rootScope', function($scope, $rootScope) {
	
	this.setActiveElement = function(el) {
		$scope.activeElement = el;
	};
	
	this.setRoute = function(route) {
		$rootScope.$broadcast('sc-menu-selected-item-event', {route: route});
	};
	
	/*$scope.$on('active-element-event', function(evt, data){
		$scope.activeElement = data.activeElement;
	});*/
	
	this.getActiveElement = function() {
		return $scope.activeElement;
	}
}])