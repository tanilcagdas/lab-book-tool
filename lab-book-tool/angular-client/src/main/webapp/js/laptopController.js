app.controller('laptopCtrl', function($scope, $http) {
	var req =
	{ 
			method: 'GET',
			url: 'http://localhost:8080/rest/webresources/labBook/laptop',
			headers: {
			   'Content-Type': undefined,
			   'username' : 'admin'
			 },
			 data: { test: 'test' }
			}
//	$http.get("http://localhost:8080/rest/webresources/labBook/device").
	$http(req).then(function(response) {
		$scope.laptops = response.data.laptopList;
		console.log($scope.laptops)
		
	}, function errorCallback(response) {
	    // called asynchronously if an error occurs
	    // or server returns response with an error status.
	  });
});