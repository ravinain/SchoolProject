/**
 * 
 */
"use strict";

angular.module("scMenu").directive("scMenu", function() {
	return {
		transclude: true,
		templateUrl: "ext-modules/scMenu/scMenuTemplate.html",
		controller: "scMenuController",
		link: function(scope, el, attr) {
			
		}
	};
});