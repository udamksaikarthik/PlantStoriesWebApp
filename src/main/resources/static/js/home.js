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