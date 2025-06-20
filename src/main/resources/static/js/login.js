document.addEventListener('DOMContentLoaded', function() {
    console.log('=== LOGIN DEBUG START ===');
    
    // Password toggle only
    const togglePassword = document.getElementById('togglePassword');
    const passwordInput = document.getElementById('passwordInput');
    
    if (togglePassword && passwordInput) {
        togglePassword.addEventListener('click', function() {
            const type = passwordInput.getAttribute('type') === 'password' ? 'text' : 'password';
            passwordInput.setAttribute('type', type);
            this.classList.toggle('fa-eye');
            this.classList.toggle('fa-eye-slash');
        });
    }
    
    // Floating label effects
    const floatingInputs = document.querySelectorAll('.form-floating .form-control');
    floatingInputs.forEach(input => {
        if (input.value) {
            input.classList.add('has-value');
        }
        
        input.addEventListener('input', function() {
            if (this.value) {
                this.classList.add('has-value');
            } else {
                this.classList.remove('has-value');
            }
        });
        
        input.addEventListener('focus', function() {
            this.parentElement.classList.add('focused');
        });
        
        input.addEventListener('blur', function() {
            this.parentElement.classList.remove('focused');
        });
    });
    
    // Form debug - CHỈ LOG, KHÔNG CAN THIỆP
    const loginForm = document.getElementById('loginForm');
    const emailInput = document.getElementById('emailInput');
    
    if (loginForm) {
        loginForm.addEventListener('submit', function(e) {
            console.log('=== FORM SUBMIT DEBUG ===');
            console.log('Email value:', emailInput ? emailInput.value : 'N/A');
            console.log('Password length:', passwordInput ? passwordInput.value.length : 'N/A');
            console.log('Form action:', this.action);
            console.log('Form method:', this.method);
            
            // Log all form data
            const formData = new FormData(this);
            console.log('=== FORM DATA ===');
            for (let [key, value] of formData.entries()) {
                if (key === 'password') {
                    console.log(key + ':', '*'.repeat(value.length));
                } else if (key.includes('csrf')) {
                    console.log(key + ':', value.substring(0, 10) + '...');
                } else {
                    console.log(key + ':', value);
                }
            }
            
            // KHÔNG preventDefault - để form submit bình thường
            console.log('✓ Form submitting normally...');
        });
    }
    
    // Social buttons
    const socialButtons = document.querySelectorAll('.btn-social');
    socialButtons.forEach(button => {
        button.addEventListener('click', function(e) {
            e.preventDefault();
            const type = this.classList.contains('btn-google') ? 'Google' : 'Facebook';
            console.log(type + ' login clicked');
            alert(type + ' login chưa được cấu hình');
        });
    });
    
    // Auto-dismiss alerts
    const alerts = document.querySelectorAll('.alert');
    alerts.forEach(alert => {
        setTimeout(() => {
            if (alert && alert.parentNode) {
                alert.style.opacity = '0';
                setTimeout(() => alert.remove(), 300);
            }
        }, 5000);
    });
    
    // Debug elements
    setTimeout(() => {
        console.log('=== ELEMENT STATUS ===');
        console.log('Form found:', !!document.getElementById('loginForm'));
        console.log('Email input found:', !!document.getElementById('emailInput'));
        console.log('Password input found:', !!document.getElementById('passwordInput'));
        console.log('CSRF token found:', !!document.querySelector('input[name="_csrf"]'));
        
        const form = document.getElementById('loginForm');
        if (form) {
            console.log('Form action URL:', form.action);
            console.log('Form method:', form.method);
        }
        
        console.log('=== READY FOR LOGIN ===');
        console.log('Nhập email và password từ database để test');
    }, 100);
});

console.log('Minimal login script loaded');
