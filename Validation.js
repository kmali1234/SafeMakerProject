afterEach(function () {
	browser.manage().logs().get('username').then(function (username) {
		expect(username.length).toEqual(8);
		if expect(username.length).toEqual(7)) 
		{  
		element(By.id('username')).click();
	   	console.error('log: ' + JSON.stringify(username));
		}
	else
		if expect(username.length).toEqual(9)) 
		{  
		element(By.id('username')).click();
	   	console.error('log: ' + JSON.stringify(username));
		}
else
	{
	element(By.id('username')).click();
    console.log('in the if')
}}
	});
});