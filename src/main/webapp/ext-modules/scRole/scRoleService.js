/**
 * 
 */
"use strict";
var app = angular.module("scRole");

var roleService = function($http) {
	
	var getAllRoles = function() {
		return $http.get("roles.json");
	};
	
	var getRole = function(role) {
		if(!role) {
			return getAllRoles();
		}
		return $http.get("role/"+role+".json");
	};
	
	var addRole = function(roleObj) {
		roleObj = roleObj?roleObj:{};
		return $http.post("role/add.json", roleObj);
	};
	
	var updateRole = function(roleObj) {
		roleObj = roleObj?roleObj:{};
		return $http.put("role/update/"+roleObj.id+".json", roleObj);
	};
	
	var deleteRole = function(roleId) {
		return $http.delete("role/delete/"+roleId+".json");
	};
	
	return {
		getAllRoles: getAllRoles,
		getRole: getRole,
		addRole: addRole,
		updateRole: updateRole,
		deleteRole: deleteRole
	};
};

app.factory("scRoleService", roleService);