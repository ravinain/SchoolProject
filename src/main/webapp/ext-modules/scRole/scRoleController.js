/**
 * 
 */
angular
		.module('scRole')
		.controller(
				'scRoleController',
				[
						'$scope',
						'scRoleService',
						function($scope, scRoleService) {
							$scope.add = {};
							$scope.add.showMessage = false;
							$scope.add.successFlag = false;
							$scope.addRole = function() {
								$scope.add.showMessage = false;
								var response = scRoleService
										.addRole($scope.add.role);
								response
										.then(
												function(res) {
													$scope.add.successFlag = true;
													$scope.add.showMessage = true;
													$scope.add.message =[{"message":"Role has been added successfully!"}];
												},
												function(res) {
													$scope.add.successFlag = false;
													$scope.add.showMessage = true;
													$scope.add.message = res.data.messages;
												});
							};

							 $scope.fillAllRolesList = function(){
															scRoleService.getAllRoles().then(function(res) {
															$scope.roles = res.data;
														}, function(res) {
															});
							 };
							
							 $scope.fillAllRolesList();
							
							$scope.search = {};
							$scope.search.role = {};
							$scope.search.role.header = [ "ID", "Name", "Update", "Delete" ];
							$scope.search.resultFlag = false;

							$scope.searchRole = function() {
								if (!$scope.search.role.name) {
									$scope.search.role.name = {};
									$scope.search.role.name.name = '';
								}
								$scope.search.resultFlag = false;
								scRoleService
										.getRole(
												$scope.search.role.name.name)
										.then(
												function(res) {
													if (res.data instanceof Array) {
														$scope.search.role.data = res.data;
													} else {
														$scope.search.role.data = [ res.data ];
													}
													$scope.search.resultFlag = true;
												}, function(res) {
												});
							};

							/**
							 * update-role.html page code
							 */
							if (!$scope.update) {
								$scope.update = {};
							}

							$scope.update = $scope.update ? $scope.update : {};
							$scope.update.search = $scope.update.search ? $scope.update.search
									: {};
							$scope.update.search.resultFlag = false;
							$scope.update.errorFlag = false;

							$scope.update.searchRole = function() {
								$scope.update.search.resultFlag = false;
								if ($scope.update
										&& $scope.update.search
										&& $scope.update.search.role
										&& $scope.update.search.role.name
										&& $scope.update.search.role.name.name) {
									scRoleService
											.getRole(
													$scope.update.search.role.name.name)
											.then(
													function(res) {
														if (res.data instanceof Array) {
															$scope.update.role = {};
														} else {
															$scope.update.role = res.data;
														}
														$scope.update.search.resultFlag = true;
													}, function(res) {
													});
								} else {
									alert('select role');
								}
							};

							$scope.update.resultFlag = false;
							$scope.updateRole = function() {
								$scope.update.resultFlag = false;
								scRoleService.updateRole($scope.update.role).then(
										function(res) {
											$scope.update.search.resultFlag = false;
											$scope.update.resultFlag = true;
											$scope.update.resultMessage = [{"message":"Role has updated successfully!"}];
											$scope.update.errorFlag = false;
											$scope.fillAllRolesList();
										}, function(res) {
											$scope.update.resultFlag = true;
											$scope.update.resultMessage = res.data.messages;
											$scope.update.errorFlag = true;
										});
							};
							
							/**
							 * delete-role.html page code
							 */
							if (!$scope.delete) {
								$scope.delete = {};
							}

							$scope.delete = $scope.delete ? $scope.delete : {};
							$scope.delete.search = $scope.delete.search ? $scope.delete.search
									: {};
							$scope.delete.search.resultFlag = false;
							$scope.delete.errorFlag = false;

							$scope.delete.searchRole = function() {
								$scope.delete.search.resultFlag = false;
								if ($scope.delete
										&& $scope.delete.search
										&& $scope.delete.search.role
										&& $scope.delete.search.role.name
										&& $scope.delete.search.role.name.name) {
									scRoleService
											.getRole(
													$scope.delete.search.role.name.name)
											.then(
													function(res) {
														if (res.data instanceof Array) {
															$scope.delete.role = {};
														} else {
															$scope.delete.role = res.data;
														}
														$scope.delete.search.resultFlag = true;
													}, function(res) {
													});
								} else {
									alert('select role');
								}
							};

							$scope.delete.resultFlag = false;
							$scope.deleteRole = function() {
								$scope.delete.resultFlag = false;
								scRoleService.deleteRole($scope.delete.role.id).then(
										function(res) {
											$scope.delete.search.resultFlag = false;
											$scope.delete.resultFlag = true;
											$scope.delete.resultMessage = [{"message":"Role has deleted successfully!"}];
											$scope.delete.errorFlag = false;
											$scope.fillAllRolesList();
										}, function(res) {
											$scope.delete.resultFlag = true;
											$scope.delete.resultMessage = res.data.messages;
											$scope.delete.errorFlag = true;
										});
							};


						} ]);