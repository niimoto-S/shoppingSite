/**
 * ここは確認ダイアログを司るjsファイル
 */

function test(a) {
	var checked = window.confirm("商品名:" + a.getAttribute("data-delete") + "を削除します。\nよろしいでしょうか？");
	if(checked == true){
		return true;
	} else {
		return false;
	}
}

function test1(b) {
	var checked = window.confirm("商品名:" + b.getAttribute("data-update") + "を変更します。\nよろしいでしょうか？");
	if(checked == true){
		return true;
	} else {
		return false;
	}
}

function test2(c) {
	var checked = window.confirm("ユーザID:" + c.getAttribute("data-delete") + "を削除します。\nよろしいでしょうか？");
	if(checked == true){
		return true;
	} else {
		return false;
	}
}

function test3() {
	var checked = window.confirm("カート内の商品を購入します。\nよろしいでしょうか？");
	if(checked == true){
		return true;
	} else {
		return false;
	}
}
