// JavaScript Document

/*
***Generate random numbers
*/
function random1(min1,max1){
    return Math.floor(min1+Math.random()*(max1-min1));
}


/**
  *Add to Favorites
  **/
function add_collect(gid,uri){
	$.ajax({
		url : 'member.php',
		async: false,
		type: "get",
		dataType: "json",
		data:{act:'add_collect',gid:gid,uri:uri,rn:random1(0,100000)},
		success:function(data){
              if(data==0){
			window.location.href='member.php?act=login&from='+uri;  
			  }else if(data==1){
				alert('Collection success');  
			  }else if(data==2){
			    alert('Collection failed'); 
			  }else if(data==3){
			    alert('You have already favorited this product');  
			  }else{
				alert('Error'); 
			 }
			 
		}
	});	
}


function jia()
{	
var housenum=parseInt($("#show_housenum").val());
	mum = parseInt($("#goods_num").val());
	var num=parseInt(mum)+1;
	if(num>housenum)
	{
		num=housenum;
	
		$('#show_kc').html("Overstock").show().fadeOut(4000);
	}
	$("#goods_num").val(num);
	
}
function jian()
{
	
	mum = $("#goods_num").val();
	if(mum>1)
	{
		$("#goods_num").val(parseInt(mum)-1);
	}
	
}
function num_blur()
{
	
	if($("#goods_num").val())
	{
		mum = parseInt($("#goods_num").val());
	}else
	{
		mum=1;
	}
	
	var housenum=parseInt($("#show_housenum").val());
	if(mum>housenum)
	{
		
		$("#goods_num").val(housenum);
	}else
	{
		$("#goods_num").val(mum);
	}
	
}

/**
 *Add to shopping cart
**/
function AddShopCart(divid)
{
	if($("#goods_num").val()=="" || $("#goods_num").val()<=0)
	{
		alert("Please enter the correct quantity！");
		return false;
	}
	goods_norm = '';
	if($("input[name^='goods_norm']").length>0)
	{
		norminput = false;
		$("input[name^='goods_norm']").each(function(index){
			if($(this).val()=="")
			{
				norminput = true;
			}
			if(index==0)
			{
				goods_norm = $(this).val();
			}
			else
			{
				goods_norm += ','+$(this).val();
			}
		});
		if(norminput)
		{
			$("#goodsnum").show().fadeOut(4000);
			$("#goodsnum").html("Please select the complete specification!");
			if(sign!='pc')
			{
				$("#before_attr").css("display",'block');
			}
			return false;
		}
	}
	$("#goodsnum").html("&nbsp;");	
	$.ajax({
		url : 'shopcart.php',
		async: false,
		type: "get",
		dataType: "json",
		data:{act:'add', goods_id:$("#goods_id").val(), goods_num:$("#goods_num").val(), goods_price:$("#goods_price").val(),housenum:$("#show_housenum").val(), goods_norm:goods_norm, rn:random1(0,100000)},
		success:function(data){

			if (data.act == '1')
			{	

				if(sign=='pc')
				{
				$("#"+divid).html(SCSuccess+"<br>"+SCQuantity1+"<span>&nbsp;"+data.nums+"&nbsp;</span>"+SCQuantity2+"<br><span onclick='window.open(\"shopcart.php?act=list\",\"_self\")'>"+SCSettlement+"</span>&nbsp;&nbsp;<span onclick=\"javascript:fadeOut('"+divid+"')\">"+SCContinue+"</span>"); 
				$("#shop_item_num").html(data.nums);
				$("#"+divid).css("display",'block');
				}else
				{
				$("#goodsnum").html(SCSuccess+'!'+SCQuantity1+data.nums+SCQuantity2).show().fadeOut(4000,function(){$('#buy').animate({height:'0px'},'slow');$("#gray").fadeOut(2000);});
				$('#gmnums').html(data.nums).show();
				}
				
			}
		}
	});	
}

/**
 *Buy now
**/
function NowBuy(divid,sign)
{
	
	sign=(typeof(sign)=="undefined")?'pc':sign; 
	if($("#goods_num").val()=="" || $("#goods_num").val()<=0)
	{
		$("#goodsnum").html("Please enter the correct quantity!");
		return false;
	}
	goods_norm = '';
	if($("input[name^='goods_norm']").length>0)
	{
		norminput = false;
		$("input[name^='goods_norm']").each(function(index){
			if($(this).val()=="")
			{
				norminput = true;
			}
			if(index==0)
			{
				goods_norm = $(this).val();
			}
			else
			{
				goods_norm += ','+$(this).val();
			}
		});
		if(norminput)
		{    $("#goodsnum").show().fadeOut(4000);
			$("#goodsnum").html("Please select the complete specification!");
			return false;
		}
	}
	$("#goodsnum").html("&nbsp;");	
	$.ajax({
		url : 'shopcart.php',
		async: false,
		type: "get",
		dataType: "json",
		data:{act:'NowBuy', goods_id:$("#goods_id").val(), goods_num:$("#goods_num").val(), goods_price:$("#goods_price").val(),housenum:$("#show_housenum").val(), goods_norm:goods_norm, rn:random1(0,100000)},
		success:function(data){
			if(data.error)
			{
				var from=$("#goods_from").val();
				window.location.href="member.php?act=login&from="+from+"";
			}else
			{
				window.location.href="orderinfo.php?id="+data.cart_id+"";
			}
		}
	});	
}


/**
 *Redeem
**/
function JFBuy(divid,sign)
{
	
	sign=(typeof(sign)=="undefined")?'pc':sign; 
	if($("#goods_num").val()=="" || $("#goods_num").val()<=0)
	{
		$("#goodsnum").html("Please enter the correct quantity!");
		return false;
	}
	goods_norm = '';
	if($("input[name^='goods_norm']").length>0)
	{
		norminput = false;
		$("input[name^='goods_norm']").each(function(index){
			if($(this).val()=="")
			{
				norminput = true;
			}
			if(index==0)
			{
				goods_norm = $(this).val();
			}
			else
			{
				goods_norm += ','+$(this).val();
			}
		});
		if(norminput)
		{    $("#goodsnum").show().fadeOut(4000);
			$("#goodsnum").html("Please select the complete specification!！");
			return false;
		}
	}
	$("#goodsnum").html("&nbsp;");	
	$.ajax({
		url : 'shopcart.php',
		async: false,
		type: "get",
		dataType: "json",
		data:{act:'NowBuy', goods_id:$("#goods_id").val(), goods_num:$("#goods_num").val(), goods_price:$("#goods_price").val(),housenum:$("#show_housenum").val(), goods_norm:goods_norm, rn:random1(0,100000)},
		success:function(data){
			if(data.error)
			{
				var from=$("#goods_from").val();
				window.location.href="member.php?act=login&from="+from+"";
			}else
			{
				window.location.href="orderinfo_jf.php?id="+data.cart_id+"";
			}
		}
	});	
}




/**
 *Redeem
**/
function points_convert(divid,sign)
{
	sign=(typeof(sign)=="undefined")?'pc':sign; 
	if($("#goods_num").val()=="" || $("#goods_num").val()<=0)
	{
		$("#goodsnum").html("Please enter the correct quantity!");
		return false;
	}
	goods_norm = '';
	if($("input[name^='goods_norm']").length>0)
	{
		norminput = false;
		$("input[name^='goods_norm']").each(function(index){
			if($(this).val()=="")
			{
				norminput = true;
			}
			if(index==0)
			{
				goods_norm = $(this).val();
			}
			else
			{
				goods_norm += ','+$(this).val();
			}
		});
		if(norminput)
		{    $("#goodsnum").show().fadeOut(4000);
			$("#goodsnum").html("Please select the complete specification!！");
			return false;
		}
	}

	$("#goodsnum").html("&nbsp;");	
	$.ajax({
		url : 'member.php',
		async: false,
		type: "get",
		dataType: "json",
		data:{act:'points_convert', goods_id:$("#goods_id").val(), goods_num:$("#goods_num").val(), goods_price:$("#goods_price").val(),housenum:$("#show_housenum").val(), goods_norm:goods_norm, rn:random1(0,100000)},
		success:function(data){
			if(data.login)
			{
				var from=$("#goods_from").val();
				window.location.href="member.php?act=login&from="+from+"";
			}else if(data.error)
			{
				$("#goodsnum").html("Abnormal behavior!");
			}else if(data.points)
			{
				$("#goodsnum").html("Your remaining points are not enough！");
			}else
			{
				window.location.href="orderinfo.php?id="+data.cart_id+"";
			}
		}
	});	
}
//Turn off the pop-up layer
function fadeOut(divid){  
   $("#"+divid).css("display",'none');
}

/*Shopping cart loading.. */
function loading(divclass)
{
	
	var SHeight = $("."+divclass).height();
	$(".regist_suc").css("height",SHeight);
	$(".regist_suc").fadeIn(300);//
}

/**
 * Calculate the discounted price
 *
 * The return value can be positive or negative, and the total price is added
 * 
**/
function Preferential()
{
	return 0;
}

/* Shopping cart select all */
function cart_chk_all(checked,n)
{	
	
	setTimeout(function () 
	{	
	
	
	
		if(checked != 'checked')
		{
			//unselect all
			
			$("input[type='checkbox'][name^='id'][disabled!='true']").removeAttr("checked");
			$("#totalnum").html("0");
			$("#totalgoodsprice").html("0.00");
			$("#totalprice").html("0.00");
			$("#jiesuan").css("display",'none');
		}
		else
		{
			//select all
			$('.s_checkbox').addClass("s_checked");
			$("input[type='checkbox'][name^='id'][disabled!='true']").attr("checked","checked");
			var totalgoodsprice =0;
			var totalprice = 0; 
			var actual_num=$(".gwc_actual_z").length;
			for (var i = 0 ; i < n ; i++ )
			{
				tmp=$("#total"+i).html();
				if(tmp!=null)
				{
					totalgoodsprice += parseInt(tmp);
				}
			 
			}
			
			//The total price of the product = the total price of all the products + the discount price (the return value can be positive or negative, and the total price is added)
			totalprice = totalgoodsprice + Preferential();
			
			$("#totalnum").html(actual_num);
			$('#gmnums').html(actual_num);
			$("#totalgoodsprice").html(totalgoodsprice.toFixed(2));
			$("#totalprice").html(totalprice.toFixed(2));	
			$("#jiesuan").css("display",'');
			
		}
		$(".regist_suc").fadeOut(300);//
	},1000);		
}
/* Shopping cart select single */
function cart_chk_one(n)
{
	loading("gwc");
	setTimeout(function () 
	{
		var gwczs = $("input[type='checkbox'][name='id[]']").size();//Quantity of all items in the shopping cart
		
		if (document.getElementById("id"+n).checked == false)
		{
			var totalnum = $("#totalnum").html() - 1;
			if ( totalnum < 0 ) { totalnum = 0;}
			$("#totalnum").html( totalnum );
			var totalgoodsprice = $("#totalgoodsprice").html() - $("#total"+n).html();
			if ( totalgoodsprice < 0 ) { totalgoodsprice = 0;}
			$("#totalgoodsprice").html( totalgoodsprice.toFixed(2) );
			var totalprice = $("#totalprice").html() - $("#total"+n).html();
			if ( totalprice < 0 ) { totalprice = 0;}
			$("#totalprice").html( totalprice.toFixed(2) );
			document.getElementById("checkall").checked = false;
			$('#checkall').removeClass("s_checked");
		}
		else
		{
			var totalnum = parseInt($("#totalnum").html())+1;
			$("#totalnum").html( totalnum );
			var totalgoodsprice = parseFloat($("#totalgoodsprice").html()) + parseFloat($("#total"+n).html());
			$("#totalgoodsprice").html( totalgoodsprice.toFixed(2) );
			var totalprice = parseFloat($("#totalprice").html()) + parseFloat($("#total"+n).html());
			$("#totalprice").html( totalprice.toFixed(2) );		
			
		}	
		
		
		if(totalnum==gwczs){
			$('#checkall').attr('checked','checked');
			$('#checkall').addClass('s_checked');
		}
		if(totalnum==0)
		{
		
			$("#jiesuan").css("display",'none');
			
		}else{
			
			if(totalprice.toFixed(2)<=0){
				$("#jiesuan").css("display",'none');
			}else{
				$("#jiesuan").css("display",'');
			}
			
		}
		$('#gmnums').html(totalnum);
		$(".regist_suc").fadeOut(300);//
	},1000);
}
/*gmnums*/


/**
 *Modify shopping cart
 *
 *@id           Id in the shopping cart data table
 *@goods_num    Its value can be -1,0,1. When the value is 0, it is calculated by the value in num. When it is -1 or 1, the number of goods is added or subtracted by 1 respectively.
 *@totalid      Product serial number
 *@n            Number of items in the shopping cart
 *
**/
function UpdateShopCart(id,goods_num,totalid,n)
{
	if (document.getElementById("id"+totalid).checked == false)
	{
		document.getElementById("id"+totalid).checked = true;
		$("#totalnum").html(parseInt($("#totalnum").html())+1);
	}
	
	$.ajax({
		url : 'shopcart.php',
		async: false,
		type: "get",
		dataType: "json",
		data:{act:'update', id:id,num:$("#num"+totalid).val(), goods_num:goods_num, housenum:$("#housenum"+totalid).val(), rn:random1(0,100000)},
		success:function(data){
			if ( data.More == '1' )
			{
				$('#kc'+totalid).html("Overstock").show().fadeOut(4000);
			}
			
			
			$("#num"+totalid).val(data.num);
			if (data.num == 0)
			{
				//$("#tr"+id).hide();
				//document.getElementById("id"+totalid).checked = false;
				//$("#totalnum").html($("#totalnum").html()-1);
			}
			per_price=parseFloat($("#Unit_price"+totalid).html());
			var total =  data.num * per_price;
			$("#total"+totalid).html(total.toFixed(2));
			var totalgoodsprice = 0 ;
			var totalprice = 0 ;
			for (var i = 0 ; i < n ; i++ )
			{
				if(document.getElementById("id"+i).checked==false){
				    per_price=parseFloat(0);
				}else{
				    per_price=parseFloat($("#total"+i).html());
				}
				totalgoodsprice += per_price;
			}
			if($("#jiesuan").css('display')=='none'){
				$("#jiesuan").css("display",'');
			}
			//Total product price = total price of all products + discount price
			totalprice = totalgoodsprice + Preferential();
			$("#totalgoodsprice").html(totalgoodsprice.toFixed(2));
			$("#totalprice").html(totalprice.toFixed(2));						
		}
	});
}

/**
 *Delete a single item in the shopping cart
 *
 *@id           Id in the shopping cart data table
 *@totalid      Product serial number
 *
**/
function DelShopCart(id,totalid)
{
	
	loading("gwc");
	setTimeout(function () 
	{	
		$.ajax({
			url : 'shopcart.php',
			async: false,
			type: "get",
			dataType: "json",
			data:{act:'del', id:id, rn:random1(0,100000)},
			success:function(data){
				if (data.Success == 1)
				{
					$("#tr"+id).hide();
					node = document.getElementById("tr"+id);
					node.parentNode.removeChild(node);
					$("#totalnum").html( data.totalnum );
					$("#totalgoodsprice").html( data.totalgoodsprice );
					$("#totalprice").html(data.totalprice);
					$("#gmnums").html(data.totalnum);
					if(data.totalnum==0)
					{
						$("#jiesuan").css("display",'none');
					}else{
						$("#jiesuan").css("display",'');
					}
				}
			}
		});
		$(".regist_suc").fadeOut(300);
	},1000);
}
/**
 *Delete the selected item in the shopping cart
**/
function DelShopCartAll()
{
	loading("gwc");
	setTimeout(function () 
	{		
		$.ajax({
			cache: true,
			type: "POST",
			url:"shopcart.php?act=del",
			data:$('#form1').serialize(),// 你的formid
			async: false,
			dataType: "json",
			success: function(data) {
				var arr=new Array();
				var str = data.ids;
				arr = str.split(",");
				for (i = 0 ; i < arr.length ; i++)
				{
					$("#tr"+arr[i]).hide();
					node = document.getElementById("tr"+arr[i]);
					node.parentNode.removeChild(node);
				}
				data.totalnum=0;
				$("#totalnum").html( data.totalnum );
				if(data.totalnum==0)
				{
					$("#jiesuan").css("display",'none');
				}else{
					$("#jiesuan").css("display",'');
				}
				data.totalgoodsprice=0;
				data.totalprice=0;
				$("#totalgoodsprice").html( data.totalgoodsprice );
				$("#totalprice").html( data.totalprice );			
			}
		});
		$(".regist_suc").fadeOut(300);
	},1000);		
}

/**
 *Empty shopping cart
**/
function DelShopCartAll1(n)
{
	loading("gwc");
	setTimeout(function () 
	{		
		$.ajax({
			url : 'shopcart.php',
			async: false,
			type: "get",
			dataType: "json",
			data:{act:'delall', rn:random1(0,100000)},			
			success: function(data) {
				if (data.Success == 1)
				{
					$("tr[id^='tr']").hide();
					$("#totalnum").html("0");
					$("#totalgoodsprice").html("0.00");
					$("#totalprice").html("0.00");
					$('#jiesuan').hide();	
				}
			}
		});
		$(".regist_suc").fadeOut(300);
	},1000);		
}
function Getphp(url,divid)
{
	
		$.ajax({
			url : url,
			async: false,
			type: "get",
			dataType: 'text',
			data:{rn:random1(0,100000)},			
			success: function(data) {
				$("#"+divid).html(data);
			}
		});
		//$(".regist_suc").fadeOut(300);
}
/* Verify the order submission page */
function checkorderpost()
{
	if ($('#aid').val() == '0') 
	{
		$('#contact').html(content);
		$("#souhuoren").ScrollTo(800);
		$("#souhuoren").css("background","#FFF2E6");
		return false;
	}
	if ($('#cartids').val() == '') 
	{
		$('#itemlist').html(itemlist);
		$("#goods").ScrollTo(800);
		$("#goods").css("background","#FFF2E6");
		return false;
	}
	if (!$('input[name="postmode"]:checked').val()) 
	{
		$('#postmode').html(postmode);
		$("#peisong").ScrollTo(800);
		$("#peisong").css("background","#FFF2E6");
		return false;
	}	
}

function selectaddr(id,k)
{
	$("#aid"+id).attr("checked",true);
	for (var i=0; i<=k; i++)
	{
		$("#div"+i).attr("class","addrlist");
		$("#div"+i).attr("onmouseover", 'this.className="addrlist1"');
		$("#div"+i).attr("onmouseout", 'this.className="addrlist"');
	}
	$("#div"+id).attr("class","addrlist2");
	$("#div"+id).attr("onmouseover", 'this.className="addrlist2"');
	$("#div"+id).attr("onmouseout", 'this.className="addrlist2"');
}

function areashow()
{
	$("#area").css("display","block");
}

function areahide()
{
	$("#area").css("display","none");
}
function dis(id)
{
	$("div[id^=goods_content]").hide();
	$("div[id=goods_content_"+id+"]").show();
	
	$("a[id^=goods_nav]").removeClass("on");
	$("a[id=goods_nav_"+id+"]").addClass("on");

}
function pay()
{
	t = true;
	$("input[name='paytype']").each(function() {
        if($(this).attr("checked"))
		{
			t = false;
		}
    });
	if(t)
	{
		alert('Please select the payment method！');
		return false;
	}
	
	$("#face").css("display","block");
}

function facehide()
{
	$("#face").css("display","none");
}

/* 计算运费 */
function Calculate()
{
	var phone = $('#phone').val();

	var aid = $('input[name="aid"]:checked').val();//Member receipt information form ID
    
	
	var pid 		= $('input[name="postmode"]:checked').val();//Delivery method ID
	var sid 		= $("#sid").val();//Shipping template ID
	var TemPricing  = $("#TemPricing").val();//Valuation method
	var cartids		= $("#cartids").val();//Shopping cart table ID

	$.ajax({
		url : "orderinfo.php",
		async: false,
		type: "get",
		dataType: 'json',
		data:{"act":"Calculate" , "aid":aid , "pid":pid , "sid":sid , "TemPricing":TemPricing , "cartids":cartids , rn:random1(0,100000) ,intotal:$('#intotal').val()},
		success: function(data) {
				  if (data.con == 'aideq0') 
				  {
					  alert(content);
					  return false;
				  }	
				  else if (data.con == 'pideq0')
				  {
					  alert(postmode);
					  return false;
				  }
				  else if (data.con == 'cartideq0')
				  {
					  alert(itemlist);
					  return false;
				  }
				  else
				  {
					  $('#yunfei').html(data.con);
					  $('#inyunfei').val(data.decon);
					  $('#total').html(data.deintotal);
					  $('#total1').html(data.deintotal);
					  $('#total2').html(data.deintotal);
					  $('#total3').html(data.deintotal);
					  if(data.jxs==1){
					  $('#jxz').html("七折");
					  }
					  return false;					  
				  }
		}
	});	
}


/**
 *Delete the selected item in the shopping cart
**/
function DelShopOrder(id)
{
	$.ajax({
		url : 'member.php',
		async: false,
		type: "get",
		dataType: "json",
		data:{act:'orderdel', orderid:id, rn:random1(0,100000)},
		success:function(data){
			$("#tr"+id).hide();
			 return false;
		}
	});	
				
}

/* Verify the order submission page */
function checkorderpost_m()
{
	if ($('#aid').val() == '0') 
	{
		$('#dialog_ext').html("Please fill in the delivery address");
		$('#dialog').show().fadeOut(1000);
		return false;
	}
	if ($('#cartids').val() == '') 
	{
		$('#itemlist').html(itemlist);
		$("#goods").ScrollTo(800);
		$("#goods").css("background","#FFF2E6");
		return false;
	}
	if (!$('input[name="postmode"]:checked').val()) 
	{
		$('#dialog_ext').html("Please choose a delivery method");
		$('#dialog').show().fadeOut(1000);
		return false;
	}	
}