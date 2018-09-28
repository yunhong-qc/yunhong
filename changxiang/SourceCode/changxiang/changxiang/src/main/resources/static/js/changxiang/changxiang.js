var entity = {
			getCode : true,//是否可以获取code了，如果还没有走出超时时间，就不允许获取
			codeTime : 60,
			codeStart : function() {
				$('#_getCode').css('background-color', '#cccccc');
				$('#_getCode').text('(' + (entity.codeTime--) + ')S后再次获取');
			},
			codeEnd : function() {
				$('#_getCode').css('background-color', '#4cd463');
				$('#_getCode').text('获取验证码');
				entity.getCode = true;
				entity.codeTime = 60;
			},
			codePass : function(dom) {
				dom.parent('div').css('border', '1px solid #4cd463');
			},
			codeNPass : function(dom) {
				entity.errTips(dom.attr('tips'));
				dom.parent('div').css('border', '1px solid red');
				dom.focus();
			},
			err_tips_state : true,
			errTips : function(msg) {
				var istips = $('#_errTips').text();
				if (istips) {
					$('#_errTips').text(msg);

				} else {
					var tipsHtml = '<div id="_errTips" style="border-radius:5px;top:20px;position:fixed;width:80%;left:10%;height:40px;line-height:40px;text-align:center;background-color:#ec5224;">'
							+ msg + '</div>';
					$('body').append(tipsHtml);
				}
				if (entity.err_tips_state) {
					$('#_errTips').show();
					entity.err_tips_state = false;
					setTimeout(function() {
						$('#_errTips').hide();
						entity.err_tips_state = true;
					}, 2000);
				}

			},startloading:function(){
				var isload=$('#_loading').text();
				if(isload){
					$('#_loading').show();
				}else{
					var loadHtml='<div id="_loading" style="border-radius:5px;top:20px;position:fixed;width:100%;height:100%;text-align:center;background-color:black;opacity:0.5;"><div style="margin-top:10%;color:white;">加载中..</div></div>';
					$('body').append(loadHtml);
				}
			},endloading:function(){
				$('#_loading').hide();
			}
		}
		function test() {
			console.log(1);
		}
		function _submit() {
			var params = validateFrom();
			if (params.res) {
				console.log('pass');
				var isInter = $('#isWb').is(':checked');//是否安装宽带
				params.isWb = isInter?0:1;
				params['schoolId']=$('#_shcool').attr('data-value');
				params['userId']=$('#_persents').attr('data-value');
				params['zenpType']=$('#_zenpType').attr('data-value');
				params['ttcode']=$('#_tcode').text() || 1;
				//提交信息
				$.ajax({
					url:'../studentInfo/save',
					type:"post",
					data:params,
					success:function(data){
						if(data.code==0){
							//成功
							alert('保存成功。');
							window.location.href="ch_index";
						}else{
							entity.errTips(data.msg);
						}
					}
				})
			} else {
				console.log('nopass');
			}
			console.log(JSON.stringify(params));
		}
		function validateFrom() {
			var inps = $('#subForm :input');
			var params = {};
			var res = true;
			var thDom;
			for (var i = 0; i < inps.size(); i++) {
				var thDom = $(inps[i]);
				var vthen = thDom.attr('valid');
				var thenVal = thDom.val();
				if (vthen == 'required') {
					if (thenVal == '') {
						res = false;
						break;
					}
					entity.codePass(thDom);
				}
				if (vthen == 'phone') {
					var phoneReg = /(^1[3|4|5|7|8]\d{9}$)|(^09\d{8}$)/;
					if (!phoneReg.test(thenVal)) {
						res = false;
						break;
					}
					entity.codePass(thDom);
				}
				if (vthen == 'ID') {
					var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
					if (reg.test(thenVal) === false) {
						res = false;
						break;
					}
					entity.codePass(thDom);
				}
				if (vthen == 'not') {
					if (thenVal == thDom.attr('v-value')) {
						res = false;
						break;
					}
					entity.codePass(thDom);
				}
				if (thDom.attr('not') != 'not') {
					var thId = thDom.attr('id');
					params[thId] = thenVal;
				}
			}
			if (!res) {
				entity.codeNPass(thDom);
			}
			params['res'] = res;
			return params;
		}
		function getYeValue(s) {
			$.ajax({
				url:'listSchoolInfo/'+s,
				type:"post",
				data:{},
				success:function(param){
					if(param.code="000000"){
						var data=param.data;
						var html="";
						for(var i=0;i<data.length;i++){
							var thda=data[i];
							html+='<li data-value="'+thda.userId+'">'+(thda.name+'-'+thda.userId)+'</li>';
						}
						$('#_persents_ul').html(html);
						$('#_persents').val('选择业务员');
					}else{
						entity.errTips(param.msg);
					}
				}
				
			})
			
		}
		function getShcoolVal(){
			$.ajax({
				url:'listSchoolInfo',
				type:"post",
				data:{},
				success:function(param){
					if(param.code="000000"){
						var data=param.data;
						var html="";
						for(var i=0;i<data.length;i++){
							var thda=data[i];
							html+='<li data-value="'+thda.deptId+'">'+(thda.name)+'</li>';
						}
						$('#_shcool_ul').html(html);
					}else{
						entity.errTips(data.msg);
					}
				}
				
			})
		}
		function getYSchoolValue() {
			var ul = '';
			$('#_shcool').html();
		}
		$(function() {
			//验证框
			$('#drag').drag();
			$(":submit").click(function() {
				_submit();
				return false;
			})
			getShcoolVal();
			$(document).ajaxStart(function () {
		        //正在加载
		        entity.startloading();
		    });
		    $(document).ajaxStop(function () {
		       //删除加载
		       entity.endloading();
		    });
		})

		$('[name="nice-select"]').click(function(e) {

			$('[name="nice-select"]').find('ul').hide();

			$(this).find('ul').show();

			e.stopPropagation();

		});

		$('#_getCode').click(
				function() {
					var thenVal = $('#telephone').val();
					var ttcode=$('#_tcode').text();
					var phoneReg = /(^1[3|4|5|7|8]\d{9}$)|(^09\d{8}$)/;
					if (!phoneReg.test(thenVal)) {
						entity.codeNPass($('#telephone'));
						return;
					}
					if (entity.getCode) {
						//ajax获取验证码
						
						$.ajax({
							url:'getVcode',
							data:{phone:thenVal,'ttcode':ttcode||0},
							type:"post",
							success:function(data){
								if(data.code=="000000"){
									//成功
									$('#_getCode').css('background-color', '#cccccc');
									$('#_getCode').text('(' + (--entity.codeTime) + ')S后再次获取');
									entity.getCode = false;
									var codeInv = setInterval(function() {
										if (entity.codeTime <= 1) {
											entity.codeEnd();
											window.clearInterval(codeInv);
										} else {
											$('#_getCode').text(
													'(' + (--entity.codeTime) + ')S后再次获取');

										}
									}, 1000);
								}else{
									//失败
									entity.errTips(data.msg);
								}
							}
						});
						//获取验证码结束
					}
				})
		$('[name="nice-select"] li').hover(function(e) {

			$(this).toggleClass('on');

			e.stopPropagation();

		});

		$('[name="nice-select"]').on('click', 'li', function(e) {
			var pid = $(this).parent('ul').attr('id');
			var val = $(this).text();
			var vid=$(this).attr('data-value');
			$(this).parents('[name="nice-select"]').find('input').val(val).attr('data-value',vid);
			$('[name="nice-select"] ul').hide();
			e.stopPropagation();

			if (pid == '_shcool_ul') {
				if(val!="选择学校"){
					getYeValue(vid);
				}
			}

		});

		$(document).click(function() {

			$('[name="nice-select"] ul').hide();

		});