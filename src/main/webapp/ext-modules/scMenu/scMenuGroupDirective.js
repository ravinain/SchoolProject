/**
 * 
 */
"use strict";

angular.module("scMenu").directive("scMenuGroup", function(){
	return {
		require: '^scMenu',
		transclude: true,
		scope: {
			label: '@',
			icon: '@'
		},
		templateUrl: 'ext-modules/scMenu/scMenuGroupTemplate.html',
		link: function(scope, el, attrs, ctrl) {
			scope.isOpen = false;
			scope.closeMenu = function() {
				scope.isOpen = false;
			};
			scope.clicked = function() {
				scope.isOpen = !scope.isOpen;
			}
		}
	};
});