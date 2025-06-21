// Khởi tạo các chức năng khi DOM đã được tải hoàn toàn
document.addEventListener('DOMContentLoaded', function () {
    initPasswordToggle();
    initFormValidation();
    initAlertDismiss();
    initFormSubmitHandler();
});

// Toggle hiển thị/ẩn mật khẩu
function initPasswordToggle() {
    const togglePassword = document.getElementById('togglePassword');
    const toggleConfirmPassword = document.getElementById('toggleConfirmPassword');
    const passwordInput = document.getElementById('passwordInput');
    const confirmPasswordInput = document.getElementById('confirmPasswordInput');

    if (togglePassword && passwordInput) {
        togglePassword.addEventListener('click', function () {
            togglePasswordVisibility(passwordInput, togglePassword);
        });
    }

    if (toggleConfirmPassword && confirmPasswordInput) {
        toggleConfirmPassword.addEventListener('click', function () {
            togglePasswordVisibility(confirmPasswordInput, toggleConfirmPassword);
        });
    }
}

function togglePasswordVisibility(input, toggleIcon) {
    const type = input.getAttribute('type') === 'password' ? 'text' : 'password';
    input.setAttribute('type', type);
    
    if (type === 'text') {
        toggleIcon.classList.remove('fa-eye');
        toggleIcon.classList.add('fa-eye-slash');
    } else {
        toggleIcon.classList.remove('fa-eye-slash');
        toggleIcon.classList.add('fa-eye');
    }
}

// Validation form - CHỈ KIỂM TRA FORMAT PHÍA CLIENT
function initFormValidation() {
    const fullNameInput = document.getElementById('fullNameInput');
    const emailInput = document.getElementById('emailInput');
    const passwordInput = document.getElementById('passwordInput');
    const confirmPasswordInput = document.getElementById('confirmPasswordInput');

    if (fullNameInput) {
        fullNameInput.addEventListener('blur', function () {
            validateFullName(this);
        });
        fullNameInput.addEventListener('input', function () {
            clearFieldError(this);
        });
    }

    if (emailInput) {
        emailInput.addEventListener('blur', function () {
            validateEmail(this);
        });
        emailInput.addEventListener('input', function () {
            clearFieldError(this);
        });
    }

    if (passwordInput) {
        passwordInput.addEventListener('blur', function () {
            validatePassword(this);
        });
        passwordInput.addEventListener('input', function () {
            clearFieldError(this);
            if (confirmPasswordInput && confirmPasswordInput.value) {
                validateConfirmPassword(confirmPasswordInput);
            }
        });
    }

    if (confirmPasswordInput) {
        confirmPasswordInput.addEventListener('blur', function () {
            validateConfirmPassword(this);
        });
        confirmPasswordInput.addEventListener('input', function () {
            clearFieldError(this);
        });
    }
}

// Validation chính - CHỈ KIỂM TRA FORMAT
function validateForm() {
    const fullNameInput = document.getElementById('fullNameInput');
    const emailInput = document.getElementById('emailInput');
    const passwordInput = document.getElementById('passwordInput');
    const confirmPasswordInput = document.getElementById('confirmPasswordInput');

    let isValid = true;

    if (!validateFullName(fullNameInput)) isValid = false;
    if (!validateEmail(emailInput)) isValid = false;
    if (!validatePassword(passwordInput)) isValid = false;
    if (!validateConfirmPassword(confirmPasswordInput)) isValid = false;

    return isValid;
}

function validateFullName(input) {
    if (!input) return false;
    
    const value = input.value.trim();
    
    if (!value) {
        showFieldError(input, 'Vui lòng nhập họ tên');
        return false;
    }
    
    if (value.length < 2) {
        showFieldError(input, 'Họ tên phải có ít nhất 2 ký tự');
        return false;
    }
    
    if (value.length > 100) {
        showFieldError(input, 'Họ tên không được vượt quá 100 ký tự');
        return false;
    }

    const nameRegex = /^[a-zA-ZÀ-ỹ\s]+$/;
    if (!nameRegex.test(value)) {
        showFieldError(input, 'Họ tên chỉ được chứa chữ cái và khoảng trắng');
        return false;
    }

    clearFieldError(input);
    return true;
}

function validateEmail(input) {
    if (!input) return false;
    
    const value = input.value.trim();
    
    if (!value) {
        showFieldError(input, 'Vui lòng nhập email');
        return false;
    }
    
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(value)) {
        showFieldError(input, 'Email không hợp lệ');
        return false;
    }
    
    if (value.length > 255) {
        showFieldError(input, 'Email không được vượt quá 255 ký tự');
        return false;
    }

    clearFieldError(input);
    return true;
}

function validatePassword(input) {
    if (!input) return false;
    
    const value = input.value;
    
    if (!value) {
        showFieldError(input, 'Vui lòng nhập mật khẩu');
        return false;
    }
    
    if (value.length < 6) {
        showFieldError(input, 'Mật khẩu phải có ít nhất 6 ký tự');
        return false;
    }
    
    if (value.length > 255) {
        showFieldError(input, 'Mật khẩu không được vượt quá 255 ký tự');
        return false;
    }

    const strongPasswordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).+$/;
    if (!strongPasswordRegex.test(value)) {
        showFieldError(input, 'Mật khẩu phải chứa ít nhất 1 chữ hoa, 1 chữ thường và 1 số');
        return false;
    }

    clearFieldError(input);
    return true;
}

function validateConfirmPassword(input) {
    if (!input) return false;
    
    const value = input.value;
    const passwordInput = document.getElementById('passwordInput');
    const passwordValue = passwordInput ? passwordInput.value : '';
    
    if (!value) {
        showFieldError(input, 'Vui lòng xác nhận mật khẩu');
        return false;
    }
    
    if (value !== passwordValue) {
        showFieldError(input, 'Mật khẩu xác nhận không khớp');
        return false;
    }

    clearFieldError(input);
    return true;
}

function showFieldError(input, message) {
    if (!input) return;
    
    clearFieldError(input);
    
    input.classList.add('is-invalid');
    
    const errorDiv = document.createElement('div');
    errorDiv.className = 'invalid-feedback';
    errorDiv.textContent = message;
    
    input.parentNode.appendChild(errorDiv);
}

function clearFieldError(input) {
    if (!input) return;
    
    input.classList.remove('is-invalid');
    
    const errorDiv = input.parentNode.querySelector('.invalid-feedback');
    if (errorDiv) {
        errorDiv.remove();
    }
}

// Tự động đóng alert
function initAlertDismiss() {
    const alerts = document.querySelectorAll('.alert');
    alerts.forEach(alert => {
        setTimeout(() => {
            if (alert && alert.classList.contains('show')) {
                try {
                    const bsAlert = new bootstrap.Alert(alert);
                    bsAlert.close();
                } catch (error) {
                    alert.style.display = 'none';
                }
            }
        }, 5000);
    });
}

function showLoading() {
    const submitBtn = document.querySelector('button[type="submit"]');
    if (submitBtn) {
        submitBtn.disabled = true;
        submitBtn.innerHTML = '<i class="fas fa-spinner fa-spin me-2"></i>Đang xử lý...';
    }
}

function hideLoading() {
    const submitBtn = document.querySelector('button[type="submit"]');
    if (submitBtn) {
        submitBtn.disabled = false;
        submitBtn.innerHTML = '<i class="fas fa-user-plus me-2"></i>Đăng ký';
    }
}

// Xử lý submit form - CHỈ VALIDATION, KHÔNG CAN THIỆP API
function initFormSubmitHandler() {
    const form = document.querySelector('form[action*="/security/register"]');
    if (form) {
        form.addEventListener('submit', function (e) {
            if (validateForm()) {
                showLoading();
                // Để backend xử lý submit
            } else {
                e.preventDefault();
            }
        });
    }
}

window.addEventListener('load', function () {
    hideLoading();
});

function resetForm() {
    const form = document.querySelector('form[action*="/security/register"]');
    if (form) {
        form.reset();
        
        const errorDivs = form.querySelectorAll('.invalid-feedback');
        errorDivs.forEach(div => div.remove());
        
        const invalidInputs = form.querySelectorAll('.is-invalid');
        invalidInputs.forEach(input => input.classList.remove('is-invalid'));
    }
}
