document.addEventListener("DOMContentLoaded", function () {
        const passwordField = document.getElementById("password");
        const togglePassword = document.getElementById("togglePassword");
        const confirmPasswordField = document.getElementById("confirmPassword");
        const toggleConfirmPassword = document.getElementById("toggleConfirmPassword");

        togglePassword.addEventListener("click", function () {
            // Toggle the type attribute
            const type = passwordField.type === "password" ? "text" : "password";
            passwordField.type = type;

            // Toggle the icon (optional if using a text icon like 👁️)
            togglePassword.textContent = type === "password" ? "👁️" : "🙈";
        });
        
        toggleConfirmPassword.addEventListener("click", function () {
            // Toggle the type attribute
            const type = confirmPasswordField.type === "password" ? "text" : "password";
            confirmPasswordField.type = type;

            // Toggle the icon (optional if using a text icon like 👁️)
            toggleConfirmPassword.textContent = type === "password" ? "👁️" : "🙈";
        });
    });