/**
 * 
 */
angular
		.module('scStaff')
		.controller(
				'scStaffController',
				[
						'$scope',
						'scStaffService',
						'scRoleService',
						'scSubjectService',
						function($scope, scStaffService, scRoleService, scSubjectService) {
							$scope.add = {};
							$scope.add.showMessage = false;
							$scope.add.successFlag = false;
							$scope.addStaff = function() {
								$scope.add.showMessage = false;
								var response = scStaffService
										.addStaff($scope.add.staff);
								response
										.then(
												function(res) {
													$scope.add.successFlag = true;
													$scope.add.showMessage = true;
													$scope.add.message =[{"message":"Staff has been added successfully!"}];
												},
												function(res) {
													$scope.add.successFlag = false;
													$scope.add.showMessage = true;
													$scope.add.message = res.data.messages;
												});
							};
							
							$scope.genders = [{id: 1, text:'Male'}, {id:2, text:'Female'}];

						    $scope.fillAllStaffsList = function(){
															scStaffService.getAllStaffs().then(function(res) {
															$scope.staffs = res.data;
														}, function(res) {
															});
							 };
							
							 $scope.fillAllStaffsList();
							
							 scRoleService.getAllRoles().then(function(res){
								 $scope.roles = res.data;
							 }, function(res){});
							 
							 scSubjectService.getAllSubjects().then(function(res){
								 $scope.subjects = res.data;
							 }, function(res){});
							 
							$scope.search = {};
							$scope.search.staff = {};
							$scope.search.staff.header = [ "ID", "Name", "Age", "Salary", "Gender", "Roles", "Subjects", "Update", "Delete" ];
							$scope.search.resultFlag = false;

							$scope.searchStaff = function() {
								if (!$scope.search.staff.name) {
									$scope.search.staff.name = {};
									$scope.search.staff.name.id = '';
								}
								$scope.search.resultFlag = false;
								scStaffService
										.getStaff(
												$scope.search.staff.name.id)
										.then(
												function(res) {
													if (res.data instanceof Array) {
														$scope.search.staff.data = res.data;
													} else {
														$scope.search.staff.data = [ res.data ];
													}
													$scope.search.resultFlag = true;
												}, function(res) {
												});
							};

							/**
							 * update-staff.html page code
							 */
							if (!$scope.update) {
								$scope.update = {};
							}

							$scope.update = $scope.update ? $scope.update : {};
							$scope.update.search = $scope.update.search ? $scope.update.search
									: {};
							$scope.update.search.resultFlag = false;
							$scope.update.errorFlag = false;

							$scope.update.searchStaff = function() {
								$scope.update.search.resultFlag = false;
								if ($scope.update
										&& $scope.update.search
										&& $scope.update.search.staff
										&& $scope.update.search.staff.name
										&& $scope.update.search.staff.name.id) {
									scStaffService
											.getStaff(
													$scope.update.search.staff.name.id)
											.then(
													function(res) {
														if (res.data instanceof Array) {
															$scope.update.staff = {};
														} else {
															$scope.update.staff = res.data;
														}
														$scope.update.search.resultFlag = true;
													}, function(res) {
													});
								} else {
									alert('select staff');
								}
							};

							$scope.update.resultFlag = false;
							$scope.updateStaff = function() {
								$scope.update.resultFlag = false;
								scStaffService.updateStaff($scope.update.staff).then(
										function(res) {
											$scope.update.search.resultFlag = false;
											$scope.update.resultFlag = true;
											$scope.update.resultMessage = [{"message":"Staff has updated successfully!"}];
											$scope.update.errorFlag = false;
											$scope.fillAllStaffsList();
										}, function(res) {
											$scope.update.resultFlag = true;
											$scope.update.resultMessage = res.data.messages;
											$scope.update.errorFlag = true;
										});
							};
							
							/**
							 * delete-staff.html page code
							 */
							if (!$scope.delete) {
								$scope.delete = {};
							}

							$scope.delete = $scope.delete ? $scope.delete : {};
							$scope.delete.search = $scope.delete.search ? $scope.delete.search
									: {};
							$scope.delete.search.resultFlag = false;
							$scope.delete.errorFlag = false;

							$scope.delete.searchStaff = function() {
								$scope.delete.search.resultFlag = false;
								if ($scope.delete
										&& $scope.delete.search
										&& $scope.delete.search.staff
										&& $scope.delete.search.staff.name
										&& $scope.delete.search.staff.name.id) {
									scStaffService
											.getStaff(
													$scope.delete.search.staff.name.id)
											.then(
													function(res) {
														if (res.data instanceof Array) {
															$scope.delete.staff = {};
														} else {
															$scope.delete.staff = res.data;
														}
														$scope.delete.search.resultFlag = true;
													}, function(res) {
													});
								} else {
									alert('select staff');
								}
							};

							$scope.delete.resultFlag = false;
							$scope.deleteStaff = function() {
								$scope.delete.resultFlag = false;
								scStaffService.deleteStaff($scope.delete.staff.id).then(
										function(res) {
											$scope.delete.search.resultFlag = false;
											$scope.delete.resultFlag = true;
											$scope.delete.resultMessage = [{"message":"Staff has deleted successfully!"}];
											$scope.delete.errorFlag = false;
											$scope.fillAllStaffsList();
										}, function(res) {
											$scope.delete.resultFlag = true;
											$scope.delete.resultMessage = res.data.messages;
											$scope.delete.errorFlag = true;
										});
							};


						} ]);