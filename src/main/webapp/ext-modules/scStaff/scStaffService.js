/**
 * 
 */
"use strict";
var app = angular.module("scStaff");

var staffService = function($http) {
	
	var getAllStaffs = function() {
		return $http.get("staffs.json");
	};
	
	var getStaff = function(staff) {
		if(!staff) {
			return getAllStaffs();
		}
		return $http.get("staff/"+staff+".json");
	};
	
	var addStaff = function(staffObj) {
		staffObj = staffObj?staffObj:{};
		return $http.post("staff/add.json", staffObj);
	};
	
	var updateStaff = function(staffObj) {
		staffObj = staffObj?staffObj:{};
		return $http.put("staff/update/"+staffObj.id+".json", staffObj);
	};
	
	var deleteStaff = function(staffId) {
		return $http.delete("staff/delete/"+staffId+".json");
	};
	
	return {
		getAllStaffs: getAllStaffs,
		getStaff: getStaff,
		addStaff: addStaff,
		updateStaff: updateStaff,
		deleteStaff: deleteStaff
	};
};

app.factory("scStaffService", staffService);