
function validateGuest(){
	let un = document.forms["loginpage"]["username"].value;
    let pw = document.forms["loginpage"]["password"].value;
    let fn = document.forms["loginpage"]["firstname"].value;
    let ln = document.forms["loginpage"]["lastname"].value;
    let button = document.getElementById("accButton").value;
	let xhr = new XMLHttpRequest();
    let data = "username=" + encodeURIComponent(un) + "&password=" + encodeURIComponent(pw)
    			+ "&action=" + encodeURIComponent(button) + "&fname=" + encodeURIComponent(fn)
    			+ "&lname=" + encodeURIComponent(ln);
	xhr.onreadystatechange = function() {
		if(this.readyState == 4) {
			if (this.status == 200) {
				document.getElementById("message").innerHTML = xhr.responseText;
			} else {
				alert("error " + this.status);
			}
		}
	}
    xhr.open("POST","../../../ReservationSystem/createaccount",true);
    xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    xhr.send(data);
    
}
function validateAdmin(){
   let un = document.forms["loginpage"]["username"].value;
   let pw = document.forms["loginpage"]["password"].value;
   let button = document.getElementById("adminLogin").value;
   let xhr = new XMLHttpRequest();
   let data = "username=" + encodeURIComponent(un) + "&password=" + encodeURIComponent(pw)
   			+ "&action=" + encodeURIComponent(button);
	xhr.onreadystatechange = function() {
		if(this.readyState == 4) {
			if (this.status == 200) {
				document.getElementById("message").innerHTML = xhr.responseText;
			} else {
				alert("error " + this.status);
			}
		}
	}
   xhr.open("POST","../../../ReservationSystem/createaccount",true);
   xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
   xhr.send(data);
}
