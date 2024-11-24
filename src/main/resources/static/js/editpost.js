function updateTotalMediaCount() {
    const existingMediaCount = document.querySelectorAll('.media-container img, .media-container video').length;
    const newFilesCount = document.getElementById('mediaData').files.length;
    const totalMediaCount = existingMediaCount + newFilesCount;

    document.getElementById('totalMediaCount').value = totalMediaCount;
}

// Attach this function to relevant events
document.getElementById('mediaData').addEventListener('change', updateTotalMediaCount);
document.querySelectorAll('.delete_media_btn_class').forEach(btn => {
    btn.addEventListener('click', updateTotalMediaCount);
});
