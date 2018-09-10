//定时执行，实时恒泰金
function PhoneShishihtj() {
	//=0.4048465126288867
	var url1="http://47.93.119.90:80/Mobile/mobile_htj?n="+Math.random();
	var url='hengtai';
	$.ajax({
	   url:url,
	   data:{url:url1},
			success:function(val)
			{
				
		    $('#mobile_htj').html(val);
	       
			}
			});
	

  //setTimeout("PhoneShishihtj()",2000);

}


//定时执行，实时行情
/*
function PhoneShishihangqing() {

	$.ajax({
	   url:root+"/Mobile/phone_hangqing?n="+Math.random(),
			success:function(val)
			{
				
		    $('#phone_hangqing').html(val);
	       
			}
			});
  setTimeout("PhoneShishihangqing()",2000);
}


 setTimeout("PhoneShishihangqing()",200);
 */
 //setTimeout("PhoneShishihtj()",100);
 PhoneShishihtj();
setInterval(PhoneShishihtj, 2000);