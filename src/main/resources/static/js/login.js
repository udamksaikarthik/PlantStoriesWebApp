const form_success_messageId = document.getElementById("form-success-messageId");

if(form_success_messageId!=null){
	if(form_success_messageId.innerText!=""){
		form_success_messageId.style.display = "block";
	}else{
		form_success_messageId.style.display = "none";
	}
}

document.addEventListener("DOMContentLoaded", function () {
        const passwordField = document.getElementById("password");
        const togglePassword = document.getElementById("togglePassword");
		
		if(togglePassword!=null){
        togglePassword.addEventListener("click", function () {
            // Toggle the type attribute
            const type = passwordField.type === "password" ? "text" : "password";
            passwordField.type = type;

            // Toggle the icon (optional if using a text icon like 👁️)
            togglePassword.textContent = type === "password" ? "👁️" : "🙈";
        });
		}
    });