/**
 * ui-choose通用选择插件
 * 基于jQuery
 */
(function($) {
	
})(jQuery);

/**
 * 充值金额选择
 * @param t
 * @returns
 */
$('#uc_01 li').click(function(t){
	alert(2);
	$('#uc_01 li').removeClass('selected');
	$(this).addClass('selected');
	var price=$(this).chlidren('.am-radio-inline').attr('price');
	$('.rechnum').html(price+'.00 元');
});