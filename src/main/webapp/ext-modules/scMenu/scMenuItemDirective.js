/**
 * 
 */
"use strict";

angular.module("scMenu").directive("scMenuItem", function() {
	return {
		require:'^scMenu',
		scope: {
			label: '@',
			icon: '@',
			route: '@'
		},
		templateUrl: "ext-modules/scMenu/scMenuItemTemplate.html",
		link: function(scope, el, attr, ctrl) {
			scope.isActive = function() {
				return el == ctrl.getActiveElement();
			}
			
			el.on('click', function(evt){
				evt.stopPropagation();
				evt.preventDefault();
				scope.$apply(function() {
					ctrl.setActiveElement(el);
					ctrl.setRoute(scope.route);
				});
			});
		}
	};
});