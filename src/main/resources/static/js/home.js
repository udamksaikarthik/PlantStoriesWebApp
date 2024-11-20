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