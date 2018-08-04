
function validate(){
	let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
		if(this.readyState == 4) {
			if (this.status == 200) {
				document.getElementById("divout").innerHTML = xhr.responseText;
			} else {
				alert("error " + this.status);
			}
		}
	}
    xhr.open("POST","LoginController.do",true);
    xhr.send();
    let un = document.forms["loginpage"]["username"].value;
    let pw = document.forms["loginpage"]["password"].value;
    let username = "123";
    let password = "456";
    
    if(un == username && pw == password){
       window.location = "http://localhost:8085/ReservationSystem/Hotel%20Reservations/User%20Main/Guest.html";
    }
    else{
        alert("Username or password is incorrect");
    }
}
function validateAdmin(){
   let un = document.forms["loginpage"]["username"].value;
    let pw = document.forms["loginpage"]["password"].value;
    let username = "admin";
    let password = "stuff";
    if(un == username && pw == password){
       window.location = "http://localhost:8085/ReservationSystem/Hotel%20Reservations/Admin%20Main/Host.html";
    }
    else{
        alert("Admin username or password is incorrect");
    } 
}
//document.getElementById("User Login").addEventListener("click", validate);
//document.getElementById("Admin Login").addEventListener("click",validateAdmin);
