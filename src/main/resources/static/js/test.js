// SCRIPT CỰC KỲ ĐỠN GIẢN - KHÔNG CAN THIỆP GÌ
document.addEventListener('DOMContentLoaded', function() {
    console.log('Login page loaded');
    
    // Chỉ password toggle
    const togglePassword = document.getElementById('togglePassword');
    const passwordInput = document.getElementById('passwordInput');
    
    if (togglePassword && passwordInput) {
        togglePassword.addEventListener('click', function() {
            const type = passwordInput.type === 'password' ? 'text' : 'password';
            passwordInput.type = type;
            this.classList.toggle('fa-eye');
            this.classList.toggle('fa-eye-slash');
        });
    }
    
    // Debug form khi submit
    const form = document.getElementById('loginForm');
    if (form) {
        form.addEventListener('submit', function() {
            console.log('Form submitting...');
            console.log('Email:', document.getElementById('emailInput').value);
            console.log('Password length:', document.getElementById('passwordInput').value.length);
            // KHÔNG preventDefault - để form submit bình thường
        });
    }
});
