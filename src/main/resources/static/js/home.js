// Home Page JavaScript
// Đảm bảo Bootstrap dropdown hoạt động
document.addEventListener('DOMContentLoaded', function() {
    // Khởi tạo tất cả dropdown
    var dropdownElementList = [].slice.call(document.querySelectorAll('.dropdown-toggle'));
    var dropdownList = dropdownElementList.map(function (dropdownToggleEl) {
        return new bootstrap.Dropdown(dropdownToggleEl);
    });
    
    // Xử lý banner
    handleBannerImageSwitching();
    
    // Đảm bảo dropdown không bị đóng khi click vào
    document.querySelectorAll('.dropdown-menu').forEach(function(dropdown) {
        dropdown.addEventListener('click', function(e) {
            e.stopPropagation();
        });
    });
});

// Hàm xử lý banner (giữ nguyên code cũ)
function handleBannerImageSwitching() {
    const images = document.querySelectorAll('.banner-images img');
    const prev = document.getElementById('prev');
    const next = document.getElementById('next');
    let current = 0;

    if (!images.length || !prev || !next) {
        console.error('Banner elements not found');
        return;
    }

    function updateImages() {
        images.forEach((img, index) => {
            img.classList.toggle('active', index === current);
        });
    }

    prev.addEventListener('click', function () {
        current = (current === 0) ? images.length - 1 : current - 1;
        updateImages();
    });

    next.addEventListener('click', function () {
        current = (current === images.length - 1) ? 0 : current + 1;
        updateImages();
    });

    updateImages();

    setInterval(function() {
        current = (current === images.length - 1) ? 0 : current + 1;
        updateImages();
    }, 5000);
}
// Advanced Search Functionality
document.addEventListener('DOMContentLoaded', function() {
    // initializeAdvancedSearch();
    initializeVoiceSearch();
    initializeCameraSearch();
    initializeSearchSuggestions();
});

// // Khởi tạo tìm kiếm nâng cao
// function initializeAdvancedSearch() {
//     const advancedToggleBtn = document.querySelector('.advanced-toggle-btn');
//     const advancedPanel = document.querySelector('.advanced-search-panel');
//     const searchForm = document.querySelector('.main-search-form');
    
//     if (!advancedToggleBtn || !advancedPanel) return;
    
//     // Toggle advanced search panel
//     advancedToggleBtn.addEventListener('click', function(e) {
//         e.preventDefault();
//         const isVisible = advancedPanel.style.display !== 'none';
        
//         if (isVisible) {
//             advancedPanel.style.display = 'none';
//             advancedToggleBtn.innerHTML = '<i class="fas fa-sliders-h"></i> Tìm kiếm nâng cao';
//             advancedToggleBtn.classList.remove('active');
//         } else {
//             advancedPanel.style.display = 'block';
//             advancedToggleBtn.innerHTML = '<i class="fas fa-times"></i> Đóng tìm kiếm nâng cao';
//             advancedToggleBtn.classList.add('active');
//         }
//     });
    
//     // Xử lý submit form với filters
//     if (searchForm) {
//         searchForm.addEventListener('submit', function(e) {
//             handleAdvancedSearch(e);
//         });
//     }
    
//     // Auto-fill từ URL parameters
//     fillAdvancedSearchFromURL();
// }

// // Xử lý tìm kiếm nâng cao
// function handleAdvancedSearch(e) {
//     const formData = new FormData(e.target);
//     const params = new URLSearchParams();
    
//     // Lấy tất cả các giá trị từ form
//     for (let [key, value] of formData.entries()) {
//         if (value && value.trim() !== '') {
//             params.append(key, value.trim());
//         }
//     }
    
//     // Validate price range
//     const minPrice = formData.get('minPrice');
//     const maxPrice = formData.get('maxPrice');
    
//     if (minPrice && maxPrice && parseFloat(minPrice) > parseFloat(maxPrice)) {
//         e.preventDefault();
//         showNotification('Giá tối thiểu không thể lớn hơn giá tối đa!', 'error');
//         return;
//     }
    
//     // Lưu tìm kiếm gần đây
//     const keyword = formData.get('keyword');
//     if (keyword) {
//         saveRecentSearch(keyword);
//     }
    
//     // Update URL
//     const newURL = `/home?${params.toString()}`;
//     window.location.href = newURL;
// }

// // Điền thông tin từ URL parameters
// function fillAdvancedSearchFromURL() {
//     const urlParams = new URLSearchParams(window.location.search);
    
//     // Điền các trường input
//     const fields = ['keyword', 'minPrice', 'maxPrice', 'location', 'condition'];
//     fields.forEach(field => {
//         const value = urlParams.get(field);
//         const element = document.querySelector(`[name="${field}"]`);
//         if (element && value) {
//             element.value = value;
//         }
//     });
    
//     // Hiển thị advanced panel nếu có filter parameters
//     const hasAdvancedParams = urlParams.has('minPrice') || 
//                              urlParams.has('maxPrice') || 
//                              urlParams.has('location') || 
//                              urlParams.has('condition');
    
//     if (hasAdvancedParams) {
//         const advancedPanel = document.querySelector('.advanced-search-panel');
//         const advancedToggleBtn = document.querySelector('.advanced-toggle-btn');
//         if (advancedPanel && advancedToggleBtn) {
//             advancedPanel.style.display = 'block';
//             advancedToggleBtn.innerHTML = '<i class="fas fa-times"></i> Đóng tìm kiếm nâng cao';
//             advancedToggleBtn.classList.add('active');
//         }
//     }
// }
// Voice Search functionality
function initializeVoiceSearch() {
    const voiceBtn = document.querySelector('.voice-search-btn');
    const searchInput = document.querySelector('.search-input');
    
    if (!voiceBtn || !searchInput) return;
    
    // Check if browser supports speech recognition
    if (!('webkitSpeechRecognition' in window) && !('SpeechRecognition' in window)) {
        voiceBtn.style.display = 'none';
        return;
    }
    
    const SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
    const recognition = new SpeechRecognition();
    
    recognition.lang = 'vi-VN';
    recognition.continuous = false;
    recognition.interimResults = false;
    
    voiceBtn.addEventListener('click', function() {
        if (voiceBtn.classList.contains('listening')) {
            recognition.stop();
            return;
        }
        
        voiceBtn.classList.add('listening');
        voiceBtn.innerHTML = '<i class="fas fa-microphone-slash"></i>';
        voiceBtn.title = 'Đang nghe... Click để dừng';
        
        recognition.start();
    });
    
    recognition.onresult = function(event) {
        let transcript = event.results[0][0].transcript;
        
        // Làm sạch transcript - loại bỏ dấu câu
        transcript = transcript
            .replace(/[.!?;,]+$/g, '')  // Loại bỏ dấu câu ở cuối
            .trim();                    // Loại bỏ khoảng trắng thừa
        
        searchInput.value = transcript;
        showNotification(`Đã nhận diện: "${transcript}"`, 'success');
    };
    
    recognition.onerror = function(event) {
        console.error('Speech recognition error:', event.error);
        showNotification('Không thể nhận diện giọng nói. Vui lòng thử lại!', 'error');
    };
    
    recognition.onend = function() {
        voiceBtn.classList.remove('listening');
        voiceBtn.innerHTML = '<i class="fas fa-microphone"></i>';
        voiceBtn.title = 'Tìm kiếm bằng giọng nói';
    };
}



// Camera Search functionality
function initializeCameraSearch() {
    const cameraBtn = document.querySelector('.camera-search-btn');
    
    if (!cameraBtn) return;
    
    cameraBtn.addEventListener('click', function() {
        // Create file input for image upload
        const fileInput = document.createElement('input');
        fileInput.type = 'file';
        fileInput.accept = 'image/*';
        fileInput.capture = 'camera'; // Prefer camera on mobile
        
        fileInput.onchange = function(e) {
            const file = e.target.files[0];
            if (file) {
                handleImageSearch(file);
            }
        };
        
        fileInput.click();
    });
}

// Xử lý tìm kiếm bằng hình ảnh
function handleImageSearch(file) {
    const formData = new FormData();
    formData.append('image', file);
    
    // Show loading
    showNotification('Đang xử lý hình ảnh...', 'info');
    
    // Simulate API call (replace with actual endpoint)
    fetch('/api/search/image', {
        method: 'POST',
        body: formData
    })
    .then(response => response.json())
    .then(data => {
        if (data.success && data.keywords) {
            const searchInput = document.querySelector('.search-input');
            if (searchInput) {
                searchInput.value = data.keywords.join(' ');
                showNotification('Đã phân tích hình ảnh thành công!', 'success');
            }
        } else {
            showNotification('Không thể phân tích hình ảnh. Vui lòng thử lại!', 'error');
        }
    })
    .catch(error => {
        console.error('Image search error:', error);
        showNotification('Có lỗi xảy ra khi xử lý hình ảnh!', 'error');
    });
}

// Search Suggestions functionality
function initializeSearchSuggestions() {
    const searchInput = document.querySelector('.search-input');
    if (!searchInput) return;
    
    let searchTimeout;
    
    searchInput.addEventListener('input', function() {
        clearTimeout(searchTimeout);
        const query = this.value.trim();
        
        if (query.length < 2) {
            hideSuggestions();
            return;
        }
        
        searchTimeout = setTimeout(() => {
            fetchSearchSuggestions(query);
        }, 300);
    });
    
    // Hide suggestions when clicking outside
    document.addEventListener('click', function(e) {
        if (!e.target.closest('.search-container')) {
            hideSuggestions();
        }
    });
}

// Lấy gợi ý tìm kiếm
function fetchSearchSuggestions(query) {
    fetch(`/api/search/suggestions?q=${encodeURIComponent(query)}`)
        .then(response => response.json())
        .then(data => {
            if (data.suggestions && data.suggestions.length > 0) {
                showSuggestions(data.suggestions);
            } else {
                hideSuggestions();
            }
        })
        .catch(error => {
            console.error('Suggestions fetch error:', error);
            hideSuggestions();
        });
}

// Hiển thị gợi ý
function showSuggestions(suggestions) {
    let suggestionBox = document.querySelector('.search-suggestions-dropdown');
    
    if (!suggestionBox) {
        suggestionBox = document.createElement('div');
        suggestionBox.className = 'search-suggestions-dropdown';
        document.querySelector('.search-container').appendChild(suggestionBox);
    }
    
    suggestionBox.innerHTML = suggestions.map(suggestion => 
        `<div class="suggestion-item" data-value="${suggestion}">
            <i class="fas fa-search"></i>
            <span>${suggestion}</span>
        </div>`
    ).join('');
    
    suggestionBox.style.display = 'block';
    
    // Add click handlers
    suggestionBox.querySelectorAll('.suggestion-item').forEach(item => {
        item.addEventListener('click', function() {
            const searchInput = document.querySelector('.search-input');
            searchInput.value = this.dataset.value;
            hideSuggestions();
            searchInput.form.submit();
        });
    });
}

// Ẩn gợi ý
function hideSuggestions() {
    const suggestionBox = document.querySelector('.search-suggestions-dropdown');
    if (suggestionBox) {
        suggestionBox.style.display = 'none';
    }
}

// Lưu tìm kiếm gần đây
function saveRecentSearch(keyword) {
    let recentSearches = JSON.parse(localStorage.getItem('recentSearches') || '[]');
    
    // Remove if already exists
    recentSearches = recentSearches.filter(search => search !== keyword);
    
    // Add to beginning
    recentSearches.unshift(keyword);
    
    // Keep only last 10 searches
    recentSearches = recentSearches.slice(0, 10);
    
    localStorage.setItem('recentSearches', JSON.stringify(recentSearches));
}

// Hiển thị thông báo
function showNotification(message, type = 'info') {
    // Remove existing notifications
    const existingNotifications = document.querySelectorAll('.search-notification');
    existingNotifications.forEach(notification => notification.remove());
    
    const notification = document.createElement('div');
    notification.className = `search-notification alert alert-${type === 'error' ? 'danger' : type === 'success' ? 'success' : 'info'}`;
    notification.style.cssText = `
        position: fixed;
        top: 20px;
        right: 20px;
        z-index: 9999;
        min-width: 300px;
        animation: slideInRight 0.3s ease;
    `;
    notification.innerHTML = `
        <div class="d-flex align-items-center">
            <i class="fas fa-${type === 'error' ? 'exclamation-triangle' : type === 'success' ? 'check-circle' : 'info-circle'} me-2"></i>
            <span>${message}</span>
            <button type="button" class="btn-close ms-auto" onclick="this.parentElement.parentElement.remove()"></button>
        </div>
    `;
    
    document.body.appendChild(notification);
    
    // Auto remove after 5 seconds
    setTimeout(() => {
        if (notification.parentElement) {
            notification.remove();
        }
    }, 5000);
}

// Clear all filters
function clearAllFilters() {
    const form = document.querySelector('.main-search-form');
    if (form) {
        // Clear all inputs except keyword
        const inputs = form.querySelectorAll('input:not([name="keyword"]), select');
        inputs.forEach(input => {
            input.value = '';
        });
        
        // Hide advanced panel
        const advancedPanel = document.querySelector('.advanced-search-panel');
        const advancedToggleBtn = document.querySelector('.advanced-toggle-btn');
        if (advancedPanel && advancedToggleBtn) {
            advancedPanel.style.display = 'none';
            advancedToggleBtn.innerHTML = '<i class="fas fa-sliders-h"></i> Tìm kiếm nâng cao';
            advancedToggleBtn.classList.remove('active');
        }
        
        showNotification('Đã xóa tất cả bộ lọc', 'success');
    }
}

// Export functions for global use
window.clearAllFilters = clearAllFilters;
window.showNotification = showNotification;

