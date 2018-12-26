
  function signup()
  {
  	var number = /^[0-9]+$/;
	var pattern = new RegExp(/[~.`!#$%\^&*+=\-\[\]\\';,/{}|\\":<>\?]/);

	var firstname = $('#firstname').val();
	var lastname = $('#lastname').val();
	var street= $('#street').val();
	var city= $('#city').val();
	var province= $('#province').val();
	var postalCode= $('#postalCode').val();	

	var e = document.getElementById("gender");
	var strOptions = e.options[e.selectedIndex].value;

	if(firstname === ''||firstname.match(number)||firstname.length>15)
	{
		$('#Firstname-msg').show();
		$('#failure-msg').show();
		$('html, body').animate({ scrollTop: $('#firstname').offset().top }, 'slow');
		return false;			
	}
	else if(lastname === ''||lastname.match(number)||lastname.length>15)
	{
		$('#Lastname-msg').show();
		$('#failure-msg').show();
		$('html, body').animate({ scrollTop: $('#lastname').offset().top }, 'slow');
		return false;	
	}
	else if(strOptions != 'Male'||strOptions!= 'Female')
	{
		$('#failure-msg').show();
		return false;				
	}
	else if(street === ''||street.match(pattern))
	{
		$('#Street-msg').show();
		$('#failure-msg').show();
		$('html, body').animate({ scrollTop: $('#Street-msg').offset().top }, 'slow');
		return false;	
	}
	else if(city === ''||city.match(number))
	{
		$('#City-msg').show();
		$('#failure-msg').show();
		$('html, body').animate({ scrollTop: $('#City-msg').offset().top }, 'slow');
		return false;			
	}		
	else if(province === ''||province.match(number))
	{
		$('#province-msg').show();
		$('#failure-msg').show();
		$('html, body').animate({ scrollTop: $('#province-msg').offset().top }, 'slow');
		return false;			
	}
	else if(postalCode === '')
	{
		$('#Postal-code-msg').show();
		$('#failure-msg').show();
		$('html, body').animate({ scrollTop: $('#Postal-code-msg').offset().top }, 'slow');
		return false;		
	}
	else 
	{				
		$('#success-msg').show();	
		return true;
	}
  }
