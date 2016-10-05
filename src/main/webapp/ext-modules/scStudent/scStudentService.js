/**
 * 
 */
"use strict";
var app = angular.module("scStudent");

var studentService = function($http) {
	
	var getAllStudents = function() {
		return $http.get("students.json");
	};
	
	var getStudent = function(student) {
		if(!student) {
			return getAllStudents();
		}
		return $http.get("student/"+student+".json");
	};
	
	var addStudent = function(studentObj) {
		studentObj = studentObj?studentObj:{};
		return $http.post("student/add.json", studentObj);
	};
	
	var updateStudent = function(studentObj) {
		studentObj = studentObj?studentObj:{};
		return $http.put("student/update/"+studentObj.id+".json", studentObj);
	};
	
	var deleteStudent = function(studentId) {
		return $http.delete("student/delete/"+studentId+".json");
	};
	
	return {
		getAllStudents: getAllStudents,
		getStudent: getStudent,
		addStudent: addStudent,
		updateStudent: updateStudent,
		deleteStudent: deleteStudent
	};
};

app.factory("scStudentService", studentService);