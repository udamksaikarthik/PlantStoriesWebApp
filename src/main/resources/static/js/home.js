const mobile_menu_img_id = document.getElementById('mobile_menu_img_id')
const mobile_menu_container_id = document.getElementById('mobile_menu_container_id')
const mobile_greeding_container_id = document.getElementById('mobile-left-alignment-header-container-id')
function menuBtn(){
	console.log('Inside menuBtn function')
	
	const absoluteUrl = new URL('/images/mobile_menu_icon.png', window.location.origin).href;
	
	if(mobile_menu_img_id.src === absoluteUrl){
		mobile_menu_img_id.src = "/images/close.png"
		mobile_menu_container_id.style.display = "flex"
		mobile_greeding_container_id.style.marginTop = "5%"
	}else{
		mobile_menu_img_id.src = "/images/mobile_menu_icon.png"
		mobile_menu_container_id.style.display = "none"
		mobile_greeding_container_id.style.marginTop = "40%"
	}
}

function showMediaFullscreen(element) {
    const modal = document.getElementById('fullscreen-modal');
    const modalImage = document.getElementById('modal-image');
    const modalVideo = document.getElementById('modal-video');
    
    // Clear previous content
    modalImage.style.display = 'none';
    modalVideo.style.display = 'none';

    if (element.tagName === 'IMG') {
        modalImage.src = element.src;
        modalImage.style.display = 'block';
    } else if (element.tagName === 'VIDEO') {
        modalVideo.src = element.querySelector('source').src;
        modalVideo.style.display = 'block';
    }

    modal.style.display = 'flex';
}

function closeMediaFullscreen() {
    const modal = document.getElementById('fullscreen-modal');
    const modalImage = document.getElementById('modal-image');
    const modalVideo = document.getElementById('modal-video');
    
    modal.style.display = 'none';
    modalImage.src = '';
    modalVideo.src = '';
}

function shareStory(button) {
    const title = button.getAttribute('data-title');
    const description = button.getAttribute('data-description');
    const relativeUrl = button.getAttribute('data-url');

    // Construct the full URL
    const fullUrl = `${window.location.origin}${relativeUrl}`;

    // Check if Web Share API is supported
    if (navigator.share) {
        navigator.share({
            title: title,
            text: description,
            url: fullUrl,
        })
        .then(() => {
            console.log('Story shared successfully!');
            alert('Your story has been shared!');
        })
        .catch((error) => {
            console.error('Error sharing the story:', error);
            alert('Unable to share the story.');
        });
    } else {
        // Fallback: Copy the full URL to the clipboard
        copyToClipboard(fullUrl);
        alert('The story link has been copied to your clipboard! You can share it manually.');
    }
}

// Helper function to copy text to clipboard
function copyToClipboard(text) {
    const tempInput = document.createElement('input');
    document.body.appendChild(tempInput);
    tempInput.value = text;
    tempInput.select();
    document.execCommand('copy');
    document.body.removeChild(tempInput);
}

function toggleComments(postId) {
    const commentContainer = document.getElementById(`comments-container-${postId}`);
    if (commentContainer.style.display === "none") {
        commentContainer.style.display = "block";
    } else {
        commentContainer.style.display = "none";
    }
}

document.addEventListener("DOMContentLoaded", function () {
    const lazyVideos = document.querySelectorAll("video[loading='lazy']");

    if ("IntersectionObserver" in window) {
        const videoObserver = new IntersectionObserver((entries, observer) => {
            entries.forEach(entry => {
                if (entry.isIntersecting) {
                    const video = entry.target;
                    const source = video.querySelector("source");
                    if (source && !source.src) {
                        source.src = video.dataset.src;
                        video.load();
                    }
                    observer.unobserve(video);
                }
            });
        });

        lazyVideos.forEach(video => videoObserver.observe(video));
    }
});
