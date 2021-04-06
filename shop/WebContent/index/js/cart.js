 
/**
 * Add to Shopping Cart
 */
function buy(goodid){
	$.post("buy.action", {goodid:goodid}, function(data){
		if(data=="ok"){
			layer.msg("Successful!", {time:800}, function(){
				location.reload();
			});
		}else if(data=="login"){
			alert("Please log in to purchase!");
			location.href="login.jsp";
		}else if(data=="empty"){
			alert("Inventory shortage!");
			location.reload();
		}else{
			alert("Request failed!");
		}
	});
}
/**
 * Shopping cart reduction
 */
function lessen(goodid){
	$.post("lessen.action", {goodid:goodid}, function(data){
		if(data=="ok"){
			layer.msg("Successful !", {time:800}, function(){
				location.href="cart.action";
			});
		}else if(data=="login"){
			alert("Please log in to purchase!");
			location.href="login.jsp";
		}else{
			alert("Request failed!");
		}
	});
}
/**
 * Shopping cart delete
 */
function deletes(goodid){
	$.post("delete.action", {goodid:goodid}, function(data){
		if(data=="ok"){
			layer.msg("Successful!", {time:800}, function(){
				location.href="cart.action";
			});
		}else if(data=="login"){
			alert("Please log in to purchase!");
			location.href="login.jsp";
		}else{
			alert("Request failed!");
		}
	});
}