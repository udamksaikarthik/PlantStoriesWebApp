
// Limit on media files
function validateFileCount(input) {
        const maxFiles = 5;
        const fileCountError = document.getElementById('fileCountError');

        if (input.files.length > maxFiles) {
            fileCountError.style.display = 'block'; // Show error message
            input.value = ''; // Clear the input field
        } else {
            fileCountError.style.display = 'none'; // Hide error message
        }
    }
    
function validateFileSize(input) {
        const maxSize = 10 * 1024 * 1024; // 10 MB
        for (const file of input.files) {
            if (file.size > maxSize) {
                alert(`File ${file.name} exceeds the maximum size of 10MB.`);
                input.value = ''; // Clear the input field
                break;
            }
        }
    }
 
// Preview Media
function previewMedia() {
    const previewContainer = document.getElementById('preview-container');
    const files = document.getElementById('mediaData').files;
    previewContainer.innerHTML = '';

    for (const file of files) {
        const reader = new FileReader();
        reader.onload = function (e) {
            const fileType = file.type.split('/')[0];
            let previewElement;

            if (fileType === 'image') {
                previewElement = document.createElement('img');
                previewElement.src = e.target.result;
                previewElement.onclick = () => showModal(e.target.result, 'image');
            } else if (fileType === 'video') {
                previewElement = document.createElement('video');
                previewElement.src = e.target.result;
                previewElement.controls = true;
                previewElement.onclick = () => showModal(e.target.result, 'video');
            }

            previewContainer.appendChild(previewElement);
        };
        reader.readAsDataURL(file);
    }
}

// Show Full-Screen Modal
function showModal(src, type) {
    const modal = document.getElementById('modal');
    const modalImage = document.getElementById('modalImage');
    const modalVideo = document.getElementById('modalVideo');

    if (type === 'image') {
        modalImage.src = src;
        modalImage.style.display = 'block';
        modalVideo.style.display = 'none';
    } else {
        modalVideo.src = src;
        modalVideo.style.display = 'block';
        modalImage.style.display = 'none';
    }

    modal.style.display = 'flex';
}

// Close Modal
function closeModal() {
    const modal = document.getElementById('modal');
    modal.style.display = 'none';
}
    
 // Toggle tooltip visibility with auto-hide
function toggleTooltip(button, message) {
    // Check if tooltip already exists
    let tooltip = button.nextElementSibling;
    if (!tooltip || !tooltip.classList.contains('tooltip')) {
        // Create tooltip if it doesn't exist
        tooltip = document.createElement('div');
        tooltip.className = 'tooltip';
        tooltip.textContent = message;
        button.after(tooltip);
    }

    // Toggle visibility
    if (tooltip.classList.contains('tooltip-visible')) {
        tooltip.classList.remove('tooltip-visible');
    } else {
        tooltip.classList.add('tooltip-visible');

        // Automatically hide the tooltip after 5 seconds
        setTimeout(() => {
            tooltip.classList.remove('tooltip-visible');
        }, 5000); // 5000 milliseconds = 5 seconds
    }
}