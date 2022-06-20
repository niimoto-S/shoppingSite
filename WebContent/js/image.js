/**
 * ここでは選択した画像を表示する
 */
function previewImage(obj) {
	let fileReader = new FileReader();
	fileReader.onload = (function() {
		document.getElementById('preview').src = fileReader.result;
	});
	fileReader.readAsDataURL(obj.files[0]);
}