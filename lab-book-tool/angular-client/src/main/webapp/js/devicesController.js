app.controller('devicesCtrl', function($scope, $http) {
	var req =
	{ 
			method: 'GET',
			url: 'http://localhost:8080/rest/webresources/labBook/device',
			headers: {
			   'Content-Type': undefined,
			   'username' : 'admin'
			 },
			 data: { test: 'test' }
			}
	$http(req).then(function(response) {
		$scope.devices = response.data.deviceList;
		console.log($scope.devices)
		
	}, function errorCallback(response) {
		alert();
	    // called asynchronously if an error occurs
	    // or server returns response with an error status.
	  });
	
	var reserveReq =
	{ 
			method: 'GET',
			url: 'http://localhost:8080/rest/webresources/labBook/reservedevice?name=SR',
			headers: {
			   'Content-Type': undefined,
			   'username' : 'admin'
			 },
			 data: { test: 'test' }
			}

	
	 $scope.reserve = function() {
		 $http(reserveReq).then(function(response) {
				$scope.devices = response.data.deviceList;
				console.log($scope.devices)
				
			}, function errorCallback(response) {
			    // called asynchronously if an error occurs
			    // or server returns response with an error status.
				alert();
			  });
	    };

	    var releaseReq =
	    { 
	    		method: 'GET',
	    		url: 'http://localhost:8080/rest/webresources/labBook/releasedevice?name=SR',
	    		headers: {
	    			'Content-Type': undefined,
	    			'username' : 'admin'
	    		},
	    		data: { test: 'test' }
	    }
	    
	    
	    $scope.release = function() {
	    	$http(releaseReq).then(function(response) {
	    		$scope.devices = response.data.deviceList;
	    		console.log($scope.devices)
	    		
	    	}, function errorCallback(response) {
	    		// called asynchronously if an error occurs
	    		// or server returns response with an error status.
	    		alert();
	    	});
	    };
	    
});