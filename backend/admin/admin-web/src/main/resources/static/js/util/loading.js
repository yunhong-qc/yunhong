var _loading;
//全站ajax加载提示
(function ($) {
    _loading={
    		show:function(){
    			var div=document.getElementById('_loading_feng');
    			if(div){
    				//已有，关闭
    				_loading.hide();
    			}
    			var host = window.location.host;
    			var port=window.location.port;
    			if(port=='' || port=="" || port==null || port==undefined){
    				host='http://'+host+'/img/wx/loading.gif';
    			}else{
    				host='http://'+host+':'+port+'/img/wx/loading.gif';
    			}
    			var top=window.document.body.offsetHeight/3;
    			
    			//新建一个div元素节点line-height:30px;
    			var div=document.createElement("div");
    			div.style.cssText='z-index:99999999;width:100%;height:100%;opacity: 0.6;text-align:center;top:0px;left:0px;background-color:black;position:fixed;';
//    			<div  style="width:100%;height:100%;opacity: 0.3;text-align:center;top:0px;left:0px;background-color:black;position:fixed;">
    			var _html='<div style="width:100px;height:30px;color:white;margin-left:auto; margin-right:auto;margin-top:'+top+'px;"><img onclick="_loading.hide();" src="'+host+'" style="display: inline-block;vertical-align: middle;" /><label>&nbsp;&nbsp;加载中...</label><div>';
    			div.id='_loading_feng';
    			div.innerHTML =_html ;
    			//把div元素节点添加到body元素节点中成为其子节点，但是放在body的现有子节点的最后
    			document.body.appendChild(div);
    		},hide:function(){
    			var div=document.getElementById('_loading_feng');
    			if(div){
    				document.body.removeChild(div);
    			}
    		}
    }
//    $(document).ajaxStart(function () {
//    	_loading.show();
//    });
//    $(document).ajaxStop(function () {
//    	_loading.hide();
//    });
})(jQuery);



