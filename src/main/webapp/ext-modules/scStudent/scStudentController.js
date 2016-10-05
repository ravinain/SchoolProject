/**
 * 
 */
angular
		.module('scStudent')
		.controller(
				'scStudentController',
				[
						'$scope',
						'scStudentService',
						'scCourseService',
						'scSubjectService',
						function($scope, scStudentService, scCourseService, scSubjectService) {
							$scope.add = {};
							$scope.add.showMessage = false;
							$scope.add.successFlag = false;
							$scope.addStudent = function() {
								$scope.add.showMessage = false;
								var response = scStudentService
										.addStudent($scope.add.student);
								response
										.then(
												function(res) {
													$scope.add.successFlag = true;
													$scope.add.showMessage = true;
													$scope.add.message =[{"message":"Student has been added successfully!"}];
												},
												function(res) {
													$scope.add.successFlag = false;
													$scope.add.showMessage = true;
													$scope.add.message = res.data.messages;
												});
							};
							
						    $scope.fillAllStudentsList = function(){
															scStudentService.getAllStudents().then(function(res) {
															$scope.students = res.data;
														}, function(res) {
															});
							 };
							
							 $scope.fillAllStudentsList();
							 
							 scCourseService.getAllCourses().then(function(res){
								 $scope.courses = res.data;
							 }, function(res){});
							 
							 scSubjectService.getAllSubjects().then(function(res){
								 $scope.subjects = res.data;
							 }, function(res){});
							 
							$scope.search = {};
							$scope.search.student = {};
							$scope.search.student.header = [ "ID", "Name", "Age", "Gender", "Roles", "Subjects", "Update", "Delete" ];
							$scope.search.resultFlag = false;

							$scope.searchStudent = function() {
								if (!$scope.search.student.name) {
									$scope.search.student.name = {};
									$scope.search.student.name.id = '';
								}
								$scope.search.resultFlag = false;
								scStudentService
										.getStudent(
												$scope.search.student.name.id)
										.then(
												function(res) {
													if (res.data instanceof Array) {
														$scope.search.student.data = res.data;
													} else {
														$scope.search.student.data = [ res.data ];
													}
													$scope.search.resultFlag = true;
												}, function(res) {
												});
							};

							/**
							 * update-student.html page code
							 */
							if (!$scope.update) {
								$scope.update = {};
							}

							$scope.update = $scope.update ? $scope.update : {};
							$scope.update.search = $scope.update.search ? $scope.update.search
									: {};
							$scope.update.search.resultFlag = false;
							$scope.update.errorFlag = false;

							$scope.update.searchStudent = function() {
								$scope.update.search.resultFlag = false;
								if ($scope.update
										&& $scope.update.search
										&& $scope.update.search.student
										&& $scope.update.search.student.name
										&& $scope.update.search.student.name.id) {
									scStudentService
											.getStudent(
													$scope.update.search.student.name.id)
											.then(
													function(res) {
														if (res.data instanceof Array) {
															$scope.update.student = {};
														} else {
															$scope.update.student = res.data;
														}
														$scope.update.search.resultFlag = true;
													}, function(res) {
													});
								} else {
									alert('select student');
								}
							};

							$scope.update.resultFlag = false;
							$scope.updateStudent = function() {
								$scope.update.resultFlag = false;
								scStudentService.updateStudent($scope.update.student).then(
										function(res) {
											$scope.update.search.resultFlag = false;
											$scope.update.resultFlag = true;
											$scope.update.resultMessage = [{"message":"Student has updated successfully!"}];
											$scope.update.errorFlag = false;
											$scope.fillAllStudentsList();
										}, function(res) {
											$scope.update.resultFlag = true;
											$scope.update.resultMessage = res.data.messages;
											$scope.update.errorFlag = true;
										});
							};
							
							/**
							 * delete-student.html page code
							 */
							if (!$scope.delete) {
								$scope.delete = {};
							}

							$scope.delete = $scope.delete ? $scope.delete : {};
							$scope.delete.search = $scope.delete.search ? $scope.delete.search
									: {};
							$scope.delete.search.resultFlag = false;
							$scope.delete.errorFlag = false;

							$scope.delete.searchStudent = function() {
								$scope.delete.search.resultFlag = false;
								if ($scope.delete
										&& $scope.delete.search
										&& $scope.delete.search.student
										&& $scope.delete.search.student.name
										&& $scope.delete.search.student.name.id) {
									scStudentService
											.getStudent(
													$scope.delete.search.student.name.id)
											.then(
													function(res) {
														if (res.data instanceof Array) {
															$scope.delete.student = {};
														} else {
															$scope.delete.student = res.data;
														}
														$scope.delete.search.resultFlag = true;
													}, function(res) {
													});
								} else {
									alert('select student');
								}
							};

							$scope.delete.resultFlag = false;
							$scope.deleteStudent = function() {
								$scope.delete.resultFlag = false;
								scStudentService.deleteStudent($scope.delete.student.id).then(
										function(res) {
											$scope.delete.search.resultFlag = false;
											$scope.delete.resultFlag = true;
											$scope.delete.resultMessage = [{"message":"Student has deleted successfully!"}];
											$scope.delete.errorFlag = false;
											$scope.fillAllStudentsList();
										}, function(res) {
											$scope.delete.resultFlag = true;
											$scope.delete.resultMessage = res.data.messages;
											$scope.delete.errorFlag = true;
										});
							};


						} ]);