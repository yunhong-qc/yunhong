var _loading;
//全站ajax加载提示
(function ($) {
    _loading={
    		show:function(){
    			var host = window.location.host;
    			var port=window.location.port;
    			if(port=='' || port=="" || port==null || port==undefined){
    				host='http//'+host+'/img/wx/loading.gif';
    			}else{
    				host='http//'+host+':'+port+'/img/wx/loading.gif';
    			}
    			//新建一个div元素节点
    			var div=document.createElement("div");
    			div.id='_loading_feng';
    			div.innerHTML = '<div  style="width:100%;height:100%;opacity: 0.3;text-align:center;top:0px;left:0px;background-color:black;position:fixed;"><img onclick="_loading.hide()" src="'+host+'" style="display: inline-block;vertical-align: middle;margin-top:20%;" /></div>';
    			//把div元素节点添加到body元素节点中成为其子节点，但是放在body的现有子节点的最后
    			document.body.appendChild(div);
    		},hide:function(){
    			var div=document.getElementById('_loading_feng');
    			document.body.removeChild(div);
    		}
    }
    $(document).ajaxStart(function () {
    	_loading.show();
    });
    $(document).ajaxStop(function () {
    	_loading.hide();
    });
})(jQuery);



