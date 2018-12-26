function resetform() {
	//alert("reset clicked");
 document.getElementById("myForm").reset();
}

$(document).ready(function() {
                $('form[name=myForm]').submit(function() {
                    
					var letters = /^[A-Za-z]+$/; 
					 var number = /^[0-9]+$/;
					 var letterNumber = /^[0-9a-zA-Z]+$/;
					 var pattern = new RegExp(/[~.`!#$%\^&*+=\-\[\]\\';,/{}|\\":<>\?]/);
					 var filter ="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{6,15}";
					 var regEx = /[a-zA-Z][0-9][a-zA-Z](-| |)[0-9][a-zA-Z][0-9]/;
					
					var studentId = $('#studentid').val();
					var firstname = $('#firstname').val();
					var lastname = $('#lastname').val();
					var username = $('#username').val();
					var email = $('#email').val();
					var street= $('#street').val();
					var city= $('#city').val();
					var province= $('#province').val();
					var	postalCode= $('#postalCode').val();
					//alert(postalCode);
					var FirstThreeLetters = studentId.substring(0,2);
					var FourthPartStudent = studentId.substring(3);
					var NextDigits = studentId.substring(4,7);
					var dotindex =studentId.indexOf('.');
					var lastdigits = studentId.substring(9,14);
					var secondDot = studentId.substring(7,8);

					var password = $('#password').val();
					var e = document.getElementById("gender");
					var strOptions = e.options[e.selectedIndex].value;
 
					
					 var phnno = $('#phone').val();
					 var bracketindex =phnno.indexOf('(');
					 var firstphnno =phnno.substring(1,4);
					  var firsthyphen =phnno.substring(4,5);
					  var secondphnno=phnno.substring(5,8);
					  var secondhyphen =phnno.substring(8,9);
					  var lastphnno =phnno.substring(9,13);
					  var lastbracket= phnno.substring(13,14);
					

					
					// checking for student id//
					if(studentId === ''||FirstThreeLetters.match(number)||FirstThreeLetters.match(pattern)||dotindex!=3||NextDigits.match(pattern)||NextDigits.match(letters)||secondDot!='.'||lastdigits.match(pattern)||lastdigits.match(letters))
					{	
						//alert(studentID_three);
						$('#failure-msg').show();
						$('#student-id-msg').show();
						$('html, body').animate({ scrollTop: $('#studentid').offset().top }, 'slow');
						return false;
					}
					// for first name
					else if(firstname === ''||firstname.match(number)||firstname.length>15)
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

					else if(username === ''||username.match(number)||username.length<=6||username.length>=10)
					{
						$('#Username-msg').show();
						$('#failure-msg').show();
						$('html, body').animate({ scrollTop: $('#Username-msg').offset().top }, 'slow');
						return false;
						
					}
					else if(strOptions != 'Male'||strOptions!= 'Female')
					{
						$('#failure-msg').show();
						return false;
						
					}
					
					
					
					else if(phnno === ''||bracketindex!=0||firstphnno.match(letters)||firstphnno.match(pattern)||firsthyphen!='-'||secondphnno.match(letters)||secondphnno.match(pattern)||secondhyphen!='-'||lastphnno.match(letters)||lastphnno.match(pattern)||lastbracket!=')')
					{
						$('#phone-msg').show();
						$('#failure-msg').show();
						$('html, body').animate({ scrollTop: $('#phone-msg').offset().top }, 'slow');
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
					else if(postalCode === ''||!password.match(regEx))
					{
						$('#Postal-code-msg').show();
						$('#failure-msg').show();
						$('html, body').animate({ scrollTop: $('#Postal-code-msg').offset().top }, 'slow');
						return false;
						
					}
					else if(password === ''||password.length<6||password.length>15||!password.match(filter))
					{
						$('#password-msg').show();
						$('#failure-msg').show();
						$('html, body').animate({ scrollTop: $('#password-msg').offset().top }, 'slow');
						return false;
						
					}
					
					
					else {
						
						$('#success-msg').show();
						
						return true;
					}

						
                });
            });