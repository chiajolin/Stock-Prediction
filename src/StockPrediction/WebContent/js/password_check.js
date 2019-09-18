/**
 * written by: CHIAJO LIN
 */
//check if confirm password equals to password
function passwordCheck(){	
	var pwd = document.getElementById("pwd").value;
	var cpwd=document.getElementById("cpwd").value;

	if(pwd!=cpwd){
		document.getElementById("cpwd").setCustomValidity('Password Mismatch');
	}
	else{
		document.getElementById("cpwd").setCustomValidity('');	
	}
}

//check if password is not smaller than 6 characters
function rangeCheck(){
	if(document.getElementById('pwd').value.length < 6){
		document.getElementById("pwd").setCustomValidity('Password should not be less than 6 characters');
	}
	else{
		document.getElementById("pwd").setCustomValidity('');
	}	
}