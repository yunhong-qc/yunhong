//支付结束
var httpurl = "http://119.29.162.110:1345"
var smit = null;
var smitPosSdkWx = null;
var openid = null;
var sdevicesInfo = [];
var isRenz = false;
var connetState = false;
/**
 * 充值金额选择
 * 
 * @param t
 * @returns
 */
$(document).ready(function() {
	/* smitLogPrintWx("<p>http jswei混淆版本2.1.3qc1</p>"); */
	// 设备初始化
	smitPosSdkWx = new SmitPosSdkWx(0x04, 0x00);
	init();
});

function init() {
	var typeWx = 0x04;
	var company = 0x00;
	smit = new Smit(typeWx, company, 1);
	var str = getTicktData();
	str.user_name = 'gh_05fc6bc0923c';

	openid = str.open_id;
	smitLogPrintWx(JSON.stringify(str))

	smit.start(str, function(msg) {
		smitPosSdkWx = new SmitPosSdkWx(0x00, 0x04);
		$("#initBle").append(
				"<p>device and wx init:" + JSON.stringify(msg) + "</p>");
		var msgObj = JSON.parse(msg);
		if (strToJson(msg).bluetoothState == "off") {
			_alert("请打开蓝牙");
		}
		if (msgObj.event_type === 'init') {
			if (msgObj.status === '00') {
				deviceSn = msgObj.device_id
				// 待初始化完成，进行设备认证流程
				smitLogPrintWx('init success');
				// 检测蓝牙状态
				getBlestate();
				// getConnectedDeviceIds();
			} else {
				smitLogPrintWx('init failure');
			}
		}

		if (msgObj.event_type === 'onWXDeviceBluetoothStateChange'
				&& msgObj.status === '00') {
			// "{\"deviceInfo\":\"disconnected\",\"deviceId\":\"\",\"status\":\"0\",\"eventType\":\"onWXDeviceBluetoothStateChange\",\"bluetoothState\":\"off\"}"
			if (msgObj.bluetooth_state === 'on') {
				// 当发生变化，就重新走一下流程
				stateView('蓝牙已打开', 'green');
				bleStopConn();
				// _alert('蓝牙重新打开');
			} else {
				_alert('蓝牙已关闭');
			}
		}

		if (msgObj.event_type === 'onWXDeviceStateChange'
				&& msgObj.status === '00') {
			// "{\"deviceInfo\":\"disconnected\",\"deviceId\":\"\",\"status\":\"0\",\"eventType\":\"onWXDeviceBluetoothStateChange\",\"bluetoothState\":\"off\"}"
			if (msgObj.device_info === 'connected') {
				// 当发生变化，就重新走一下流程
				getConnectedDeviceIds();
			} else {

			}
		}
		getConnectedDeviceId();
	});
}
function strToJson(str) {
	var json = eval('(' + str + ')');
	return json;
}

// 获取动态tickt数据
function getTicktData() {
	var url = location.href.split('#')[0];
	var str = {};
	jQuery.ajax({
		url : 'initJsApi',
		async : false,
		data : {
			'url' : encodeURIComponent(url)
		},
		success : function(res) {
			if (res.code == '000000') {
				str = res.data;
			}
		}
	});
	return str;
}
/**
 * 更改状态显示的方法
 * 
 * @returns
 */
function stateView(txt, color) {
	if (txt) {
		$('#con_state').html(txt);
	} else {
		$('#con_state').html('正在检测');
	}
	if (color) {
		$('#con_state').css('color', color);
	}

}
/**
 * 统一提示方法
 * 
 * @param txt
 * @returns
 */
function _alert(txt) {
	stateView(txt,'#cccccc');
}
var deviceSn = ''; // sn
var random = ''; // 随机数
// 读取设备信息
function getDeviceInfo() {
	$("#initBle").append("<p>get device info</p>");
	if (!connetState) {
		_alert('请先连接设备..');
	}
	smit.exec(smit.MSG_TYPE_MPOS_DEVICE_INFO, null, function(msg) {
		$("#initBle").append("<p>get device info:" + msg + "</p>");
		var msgJson = JSON.parse(msg);
		if (msgJson.status === '00') {
			deviceSn = msgJson.device_sn;
			stateView('正在读取设备信息','green');
			// 获取随机数
			getRandom();
			// smitLogPrintWx('----- ' + deviceSn)
		} else {
			_alert('请先连接设备。');
		}
	}.bind(this));
}
// 获取随机数
function getRandom() {
	// 获取8位的随机数
	$("#initBle").append("<p>get random</p>");
	var RandomJson = "{\"num\":8}";
	smit.exec(smit.MSG_TYPE_MPOS_GET_RAND, RandomJson, function(msg) {
		$("#initBle").append("<p>get random:" + msg + "</p>");
		random = JSON.parse(msg).random;
		// 下一步：外部认证，上一步：获取设备信息
		externalAuth();
	}.bind(this));
}
// 外部认证
function externalAuth() {
	var externalAuth = {};
	// 使用externalAuthWithSn方法加密随机数后的数据，后台加密
	var paramJson = {};
	paramJson.sn = deviceSn;
	paramJson.random = random;
	paramJson.key = 'DB380BF9FF71B292B0943FEAD1110DBC';
	smitLogPrintWx('smit=' + smit + 'paramJson=' + JSON.stringify(paramJson));
	var encryptData = smit.xlbExternalAuth(JSON.stringify(paramJson));

	smitLogPrintWx('encryptData=' + encryptData);
	externalAuth.encrypt_data = encryptData;
	smit.exec(smit.MSG_TYPE_MPOS_EXTERNAL_AUTH, JSON.stringify(externalAuth),
			function(msg) {
				$("#initBle").append("<p>get external auth:" + msg + "</p>");
				var msgJson = JSON.parse(msg);
				if (msgJson.status === '00') {
					smitLogPrintWx('外部认证成功');
					// 显示状态
					stateView('外部认证成功，待内部认证', 'green');
					// 下一步：设备认证，上一步：获取随机数
					deviceAuth();
				} else {
					stateView('外部认证失败','red');
				}
			});
}

// 设备认证
function deviceAuth() {
	// 设备认证
	var DeviceAuthJson = {};
	// 获取随机数指令获取的随机数
	DeviceAuthJson.random = random;// "{\"random_num\":\""+obj.random+"\",\"device_sn\":\"24cf21c00094\"}";

	$("#initBle").append(
			"<p>get device auth:" + JSON.stringify(DeviceAuthJson) + "</p>");

	smit.exec(smit.MSG_TYPE_MPOS_DEVICE_AUTH, JSON.stringify(DeviceAuthJson),
			function(msg) {
				$("#initBle").append("<p>get device auth:" + msg + "</p>");
				var encryptData = JSON.parse(msg).encrypt_data;
				var paramJson = {};
				paramJson.encrypt = encryptData;
				paramJson.sn = deviceSn;
				paramJson.random = random;
				paramJson.key = '5FD54AF862977CCD4057FBB7F5A2A5C4';
				smitLogPrintWx('paramJson=' + JSON.stringify(paramJson));
				var result = smit.xlbDeviceAuth(JSON.stringify(paramJson));
				smitLogPrintWx('result=' + result);
				if (result === 0) {
					smitLogPrintWx('设备认证成功');
					// 认证成功
					isRenz = true;
					stateView('认证成功', 'green');
					// 认证流程完成，
				} else {
					stateView('设备认证失败','red');
				}
			}.bind(this));
}
var _tsk = '';
// getTSK
function getTsk() {
	var getTsk = {};
	getTsk.pub_key = '4A233C40D189C8226B0CA292C785F169E2E71F241B323C3C2D53F2FBBCD5C4216681F49032437F2FA4517912B2010F19430CB60B5136FC4BC7FC0827D961F3256CDBC883D338A13748E8E583B2AC43E8E0DB3A2A44FAA1B70A531AEC79E6F7658DDB1A44F57BAB45FF3EA59482B9948A48BE313FD8662D6EC50A44AAB1984671';
	$("#initBle").append("<p>_tsk:" + JSON.stringify(getTsk) + "</p>");
	smit
			.exec(
					smit.MSG_TYPE_GET_TSK,
					JSON.stringify(getTsk),
					function(msg) {
						$("#initBle").append("<p>get tsk:" + msg + "</p>");
						var tskbackJson = JSON.parse(msg);
						// tskbackJson.encrypt_tsk 这里得到了tsk的密文，需要使用公私钥解出明文的tsk
						// getTskWithPubkey and prikey
						// _tsk = 解密后的明文 例如：a6db4a14d6e071aa48f163a941f3b06f
						var pri_key = '10E9BEEA2EA35723FAF5F3F4B64DE94835BA7251435F0F7CCEFE72D5593C0F356B74443DCD29B6CF4096519FA13A3A9E91BC499F85549CF410D87F67CFE0D79E37AB81FB9290611F3D42E5C5934CB7C85CBA4B59F22E8771352DC60EBC05D2D920DC02FDFB98F4E338C9378BF4A829ADAA1CC7DD7BD0AFB928587705AA97ABFD';
						var pub_key = '4A233C40D189C8226B0CA292C785F169E2E71F241B323C3C2D53F2FBBCD5C4216681F49032437F2FA4517912B2010F19430CB60B5136FC4BC7FC0827D961F3256CDBC883D338A13748E8E583B2AC43E8E0DB3A2A44FAA1B70A531AEC79E6F7658DDB1A44F57BAB45FF3EA59482B9948A48BE313FD8662D6EC50A44AAB1984671';
						_tsk = smit.xlbGetTsk(tskbackJson.encrypt_tsk, pub_key,
								pri_key);
						smitLogPrintWx('_tsk=' + _tsk);

					}.bind(this));
}
var card_type = null; // 01:442卡， 02:102卡
// 开卡，自动识别102， 442卡：cardtype 01:442卡， 02:102卡
/*
 * function openGasCard(){ var paramJson = {}; paramJson.timeout = 10;
 * smit.exec(smit.MSG_TYPE_XLCARD_OPEN_GAS_CARD,JSON.stringify(paramJson),function(msg){
 * $("#initBle").append("<p>open GasCard:" + msg + "</p>"); var msgJson =
 * JSON.parse(msg); if (msgJson.status === '00') { if (msgJson.card_type ===
 * '01') { card_type = 1; smitLogPrintWx('442卡'); } else if (msgJson.card_type
 * === '02') { card_type = 2; smitLogPrintWx('102卡'); } }
 * 
 * }.bind(this)); }
 */

// 卡检测 （用于102卡，442卡卡检测）
function cardCheck() {
	smit.exec(smit.MSG_TYPE_XLCARD_DETECT_GAS_CARD, null, function(msg) {
		$("#initBle").append("<p>card check:" + msg + "</p>");
		var msgJson = JSON.parse(msg);
		if (msgJson.status === '00') {
			if (msgJson.inserted === 1) {
				smitLogPrintWx('已插卡');
			} else if (msgJson.inserted === 0) {
				smitLogPrintWx('未插卡');
			}
		}

	}.bind(this));
}
// 设置燃气卡类型442卡
function gasCardSetType442() {
	var gasSetType = {};
	gasSetType.card_type = 16;
	smit.exec(smit.MSG_TYPE_EX_IC_SET_TYPE, JSON.stringify(gasSetType),
			function(msg) {
				$("#initBle").append("<p>set card Type:" + msg + "</p>");
				var msgJson = JSON.parse(msg);
				if (msgJson.status === '00') {
					smitLogPrintWx('设置442卡类型成功');
					card_type = 1;
				}
			}.bind(this));
}
// 设置102卡类型
function gasCardSetType102() {
	var gasSetType = {};
	gasSetType.card_type = 17;
	smit.exec(smit.MSG_TYPE_EX_IC_SET_TYPE, JSON.stringify(gasSetType),
			function(msg) {
				$("#initBle").append("<p>set card Type:" + msg + "</p>");
				var msgJson = JSON.parse(msg);
				if (msgJson.status === '00') {
					smitLogPrintWx('设置102卡类型成功');
					card_type = 2;
				}
			}.bind(this));
}
// 设置153卡
function gasCardSetType153() {
	var gasSetType = {};
	gasSetType.card_type = 18;
	smit.exec(smit.MSG_TYPE_EX_IC_SET_TYPE, JSON.stringify(gasSetType),
			function(msg) {
				$("#initBle").append("<p>set card Type:" + msg + "</p>");
				var msgJson = JSON.parse(msg);
				if (msgJson.status === '00') {
					smitLogPrintWx('设置153卡成功');
				}
			}.bind(this));
}
// 设置1608卡
function gasCardSetType1608() {
	var gasSetType = {};
	gasSetType.card_type = 19;
	smit.exec(smit.MSG_TYPE_EX_IC_SET_TYPE, JSON.stringify(gasSetType),
			function(msg) {
				$("#initBle").append("<p>set card Type:" + msg + "</p>");
				var msgJson = JSON.parse(msg);
				if (msgJson.status === '00') {
					smitLogPrintWx('设置1608卡成功');
				}
			}.bind(this));
}
// 密码校验
function gasCardCheckPwd() {
	var params = {};
	var password = null;
	if (card_type === 1) { // 442
		password = 'FFFFFF';
	} else if (card_type === 2) { // 102
		password = 'F0F0FF';
	}
	;
	// 密码使用TSK加密密文密码，再下发
	var encryptParams = {};
	encryptParams.data = password;
	encryptParams.key = _tsk;
	params.data = smit.encryptWithKey(JSON.stringify(encryptParams)); // 用TSK加密下发的密码密文
	smit.exec(smit.MSG_TYPE_XLCARD_VERIFY_GAS_CARD, JSON.stringify(params),
			function(msg) {
				$("#initBle").append("<p>password check:" + msg + "</p>");
				var msgJson = JSON.parse(msg);
				if (msgJson.status === '00') {
					smitLogPrintWx('密码校验成功');
				} else if (msgJson.status === '02') {
					smitLogPrintWx('废卡');
				} else {
					smitLogPrintWx('密码校验失败');
				}

			}.bind(this));
}

// 153.1608密码校验
function CardCheckPwd153() {
	var params = {};
	var pos = 'BB';
	var password = 'FFFFFF';
	// 密码使用TSK加密密文密码，再下发
	var encryptParams = {};
	encryptParams.data = pos + password;
	encryptParams.key = _tsk;
	smitLogPrintWx(encryptParams.data)
	params.data = smit.encryptWithKey(JSON.stringify(encryptParams)); // 用TSK加密下发的密码密文
	smit.exec(smit.MSG_TYPE_XLCARD_VERIFY_153_CARD, JSON.stringify(params),
			function(msg) {
				$("#initBle").append("<p>password check:" + msg + "</p>");
				var msgJson = JSON.parse(msg);
				if (msgJson.status === '00') {
					smitLogPrintWx('密码校验成功');
				} else if (msgJson.status === '02') {
					smitLogPrintWx('废卡');
				} else {
					smitLogPrintWx('密码校验失败');
				}

			}.bind(this));
}

// 153.1608密码校验
function CardCheckPwd1608() {
	var params = {};
	params.data = '00';
	smit.exec(smit.MSG_TYPE_XLCARD_SET_1608_CARD, JSON.stringify(params),
			function(msg) {
				smitLogPrintWx('设置1608卡成功' + msg);
				CardCheckPwd1608();
			}.bind(this))
}

// 153.1608密码校验
function CardCheckPwd1608() {
	var params = {};
	var password = null;
	var pos = '00';
	password = 'FFFFFF';
	// 密码使用TSK加密密文密码，再下发
	var encryptParams = {};
	encryptParams.data = pos + password;
	encryptParams.key = _tsk;
	smitLogPrintWx(encryptParams.data)
	params.data = smit.encryptWithKey(JSON.stringify(encryptParams)); // 用TSK加密下发的密码密文
	smit.exec(smit.MSG_TYPE_XLCARD_VERIFY_153_CARD, JSON.stringify(params),
			function(msg) {
				$("#initBle").append("<p>password check:" + msg + "</p>");
				var msgJson = JSON.parse(msg);
				if (msgJson.status === '00') {
					smitLogPrintWx('密码校验成功');
				} else if (msgJson.status === '02') {
					smitLogPrintWx('废卡');
				} else {
					smitLogPrintWx('密码校验失败');
				}

			}.bind(this));
}
// 读燃气卡
function gasCardRead() {
	var area = "30"; // 30区域 // 442卡才有区域直说，此参数102卡填默认参数30即可
	var pos = '00';// 00 位置(16进制表示)偏移
	var len = 'ff';// 数据长度--255 102卡数据最大长度178
	if (card_type === 2) {// 102卡操作
		len = 'B2'
	}
	;

	var data = area + pos + len;

	smitLogPrintWx('data=' + data);
	var params = {};
	// 使用tsk加密data （area+pos+len）
	var encryptParams = {};
	encryptParams.data = data;
	encryptParams.key = _tsk;

	params.data = smit.encryptWithKey(JSON.stringify(encryptParams)); // 用TSK加密下发的密码密文;
	smit.exec(smit.MSG_TYPE_XLCARD_READ_GAS_CARD, JSON.stringify(params),
			function(msg) {
				$("#initBle").append("<p>read Gas card:" + msg + "</p>");
				var msgJson = JSON.parse(msg);
				if (msgJson.status === '00') {
					var encryptData = msgJson.data;
					var backParams = {};
					backParams.data = encryptData;
					backParams.key = _tsk;
					// 使用tsk解密读取出来的数据，得到明文数据
					var plainData = smit.decryptWithKey(JSON
							.stringify(backParams));
					smitLogPrintWx('读燃气卡成功');
					smitLogPrintWx('读燃气卡数据data=' + plainData);

				} else if (msgJson.status === '59') {
					smitLogPrintWx('卡片被移除');
				}
			}.bind(this));
}

// 读153卡
function CardRead153() {
	var area = "B1"; // 30区域 // 442卡才有区域直说，此参数102卡填默认参数30即可
	var pos = '00';// 00 位置(16进制表示)偏移
	var len = '60';// 数据长度--255 102卡数据最大长度178

	var data = area + pos + len;

	smitLogPrintWx('data=' + data);
	var params = {};
	// 使用tsk加密data （area+pos+len）
	var encryptParams = {};
	encryptParams.data = data;
	encryptParams.key = _tsk;

	params.data = smit.encryptWithKey(JSON.stringify(encryptParams)); // 用TSK加密下发的密码密文;
	smitLogPrintWx('params=' + params.data);
	smit.exec(smit.MSG_TYPE_XLCARD_READ_GAS_CARD, JSON.stringify(params),
			function(msg) {
				$("#initBle").append("<p>read Gas card:" + msg + "</p>");
				var msgJson = JSON.parse(msg);
				if (msgJson.status === '00') {
					var encryptData = msgJson.data;
					var backParams = {};
					backParams.data = encryptData;
					backParams.key = _tsk;
					// 使用tsk解密读取出来的数据，得到明文数据
					var plainData = smit.decryptWithKey(JSON
							.stringify(backParams));
					smitLogPrintWx('读卡成功');
					smitLogPrintWx('读卡数据data=' + plainData);

				} else if (msgJson.status === '59') {
					smitLogPrintWx('卡片被移除');
				}
			}.bind(this));
}
// 读1608卡
function CardRead1608() {
	var params = {};
	params.data = '00';
	smit.exec(smit.MSG_TYPE_XLCARD_SET_1608_CARD, JSON.stringify(params),
			function(msg) {
				smitLogPrintWx('设置1608卡成功' + msg);
				CardRead153();
			}.bind(this))
}

// 写燃气卡
function gasCardWrite(callBack) {
	var params = {};
	switch (card_type) {
	case 0:
		break;
	case 1: // 442
	{
		var area = "30"; // 442卡才有区域直说，此参数102卡填默认参数30即可
		var pos = '00'; // 00 位置(16进制表示)偏移
		var password = "FFFFFF";
		var d = "8122e33000A3a2131091ffff8115423030323630";
		var l = (d.length / 2);
		var len = l.toString(16);
		var d1 = password + area + pos + len + d;// [NSString
		// stringWithFormat:@"%@%@%@%@%@",password,area,pos,len,d];

		var encryptParams = {};
		encryptParams.data = d1;
		encryptParams.key = _tsk;

		params.data = smit.encryptWithKey(JSON.stringify(encryptParams)); // 用TSK加密下发的密码密文;
	}
		break;
	case 2: // 102卡
	{
		var area = "30"; // default
		var pos = 'B0'; // postion
		var password = "F0F0FF";
		var d = "8122";
		var l = (d.length / 2);
		var len = '0' + l.toString(16);
		var d1 = password + area + pos + len + d;// [NSString
		// stringWithFormat:@"%@%@%@%@%@",password,area,pos,len,d];

		smitLogPrintWx(d1)
		var encryptParams = {};
		encryptParams.data = d1;
		encryptParams.key = _tsk;

		params.data = smit.encryptWithKey(JSON.stringify(encryptParams)); // 用TSK加密下发的密码密文;
	}
		break;
	default:
		break;

	}
	smit.exec(smit.MSG_TYPE_XLCARD_WRITE_GAS_CARD, JSON.stringify(params),
			function(msg) {
				$("#initBle").append("<p>gasCardWrite:" + msg + "</p>");
				var msgJson = JSON.parse(msg);
				if (msgJson.status === '00') {
					//发送ajax请求增加卡写入记录
					if(callBack){
						var cdata={};
						cdata.deviceNo=deviceSn;
						callBack(cdata);
					}
					smitLogPrintWx('写卡成功');
				}
				
			}.bind(this));
}

function gasCardWrite153() {
	var params = {};
	var pwdPos = 'B3';
	var area = "B0"; // 30区域 // 442卡才有区域直说，此参数102卡填默认参数30即可
	var pos = '00';// 00 位置(16进制表示)偏移
	var password = "FFFFFF";
	var d = random;
	var l = (d.length / 2);
	var len = '08';
	var d1 = pwdPos + password + area + pos + len + d;// [NSString
	// stringWithFormat:@"%@%@%@%@%@",password,area,pos,len,d];

	smitLogPrintWx(d1)
	var encryptParams = {};
	encryptParams.data = d1;
	encryptParams.key = _tsk;

	params.data = smit.encryptWithKey(JSON.stringify(encryptParams)); // 用TSK加密下发的密码密文;

	smit.exec(smit.MSG_TYPE_XLCARD_WRITE_153_CARD, JSON.stringify(params),
			function(msg) {
				$("#initBle").append("<p>gasCardWrite:" + msg + "</p>");
				var msgJson = JSON.parse(msg);
				if (msgJson.status === '00') {
					smitLogPrintWx('写卡成功');
				}
			}.bind(this));
}

function gasCardWrite1608() {
	var params = {};
	params.data = '00';
	smit.exec(smit.MSG_TYPE_XLCARD_SET_1608_CARD, JSON.stringify(params),
			function(msg) {
				smitLogPrintWx('设置1608卡成功' + msg);
				gasCardWrite1608();
			}.bind(this))
}

function gasCardWrite1608() {
	var params = {};
	var pwdPos = '00';
	var area = "B0"; // 30区域 // 442卡才有区域直说，此参数102卡填默认参数30即可
	var pos = '00';// 00 位置(16进制表示)偏移
	var password = "FFFFFF";
	var d = random
	var l = (d.length / 2);
	var len = '08';
	var d1 = pwdPos + password + area + pos + len + d;// [NSString
	// stringWithFormat:@"%@%@%@%@%@",password,area,pos,len,d];

	smitLogPrintWx(d1)
	var encryptParams = {};
	encryptParams.data = d1;
	encryptParams.key = _tsk;

	params.data = smit.encryptWithKey(JSON.stringify(encryptParams)); // 用TSK加密下发的密码密文;

	smit.exec(smit.MSG_TYPE_XLCARD_WRITE_153_CARD, JSON.stringify(params),
			function(msg) {
				$("#initBle").append("<p>gasCardWrite:" + msg + "</p>");
				var msgJson = JSON.parse(msg);
				if (msgJson.status === '00') {
					smitLogPrintWx('写卡成功');
				}
			}.bind(this));
}

// 检测电卡
function elecCardCheck() {
	smit.exec(smit.MSG_TYPE_EX_IC_CHECK, null, function(msg) {
		$("#initBle").append("<p>elecCardCheck:" + msg + "</p>");
		var msgJson = JSON.parse(msg);
		if (msgJson.status === '00') {
			if (msgJson.card_status_1 === '01') {
				smitLogPrintWx('已插卡');
			} else if (msgJson.card_status_1 === '02') {
				smitLogPrintWx('已插卡且上电');
			} else if (msgJson.card_status_1 === '00') {
				smitLogPrintWx('未插卡');
			}
		}
	}.bind(this));
}
// 设置电卡类型
function elecCardSetType() {
	var params = {};
	params.card_type = 0;
	smit.exec(smit.MSG_TYPE_EX_IC_SET_TYPE, JSON.stringify(params), function(
			msg) {
		$("#initBle").append("<p>elecCardSetType:" + msg + "</p>");
		var msgJson = JSON.parse(msg);
		if (msgJson.status === '00') {
			smitLogPrintWx('设置电卡类型成功');
		}
	}.bind(this));
}
// 电卡上电
function elecCardPoweron() {
	var params = {};
	params.card_type = 0;
	smit.exec(smit.MSG_TYPE_EX_IC_POWER_ON, JSON.stringify(params), function(
			msg) {
		$("#initBle").append("<p>elecCardPoweron:" + msg + "</p>");
		var msgJson = JSON.parse(msg);
		if (msgJson.status === '00') {
			smitLogPrintWx('设置电卡类型成功');
		}
	}.bind(this));
}
// 电卡通讯
function elecCardComunication() {
	var params = {};
	params.card_type = 0;
	params.data = "00B09F0580";
	smit
			.exec(smit.MSG_TYPE_EX_IC_COMMUNICATION, JSON.stringify(params),
					function(msg) {
						$("#initBle").append(
								"<p>elecCardComunication:" + msg + "</p>");
						var msgJson = JSON.parse(msg);
						if (msgJson.status === '00') {
							smitLogPrintWx('电卡通讯成功');
						}
					}.bind(this));
}
// 电卡下电
function elecCardPoweroff() {
	var params = {};
	params.card_type = 0;
	smit.exec(smit.MSG_TYPE_EX_IC_POWER_OFF, JSON.stringify(params), function(
			msg) {
		$("#initBle").append("<p>elecCardPoweroff:" + msg + "</p>");
		var msgJson = JSON.parse(msg);
		if (msgJson.status === '00') {
			smitLogPrintWx('电卡下电成功');
		}
	}.bind(this));
}
// 读取固件信息
function getFirmwareInfo() {
	smit.exec(smit.MSG_TYPE_GET_FIRMWARE_INFO, null, function(msg) {
		$("#initBle").append("<p>get firmware info:" + msg + "</p>");
		var msgJson = JSON.parse(msg);
		if (msgJson.status === '00') {
			smitLogPrintWx('读取固件信息成功');
		}
	}.bind(this));
}

/*
 * function getUpdateMainKey(){
 * smit.exec(smit.MSG_TYPE_UPDATE_MAIN_KEY,null,function(msg){
 * $("#initBle").append("<p>get firmware info:" + msg + "</p>"); }); }
 */

// 获取设备信息（微信公众号绑定设备的信息）
function getConnectedDeviceId() {
	OpenId = document.getElementById('OpenId').innerHTML;
	smitPosSdkWx
			.getDevices(function(msg) {
				if (msg.deviceInfos.length > 1) {
					for (var i = 0; i < msg.deviceInfos.length; i++) {
						if (msg.deviceInfos[i].state == "disconnected") {
							unbindBle(msg.deviceInfos[i].deviceId, OpenId);
						}
					}
					window.setTimeout(getConnectedDeviceId, 2000);
				}

				for (var i = 0; i < msg.deviceInfos.length; i++) {
					if (msg.deviceInfos[i].state == "connected") {
						document.getElementById('connectedsuccess').innerHTML = "";
						document.getElementById('connecteddevice').innerHTML = msg.deviceInfos[i].deviceId;
						$("#connectedsuccess")
								.append(
										"<p class=\"p_input p_input_yzm\"><em class=\"fa fa-envelope\"></em>"
												+ msg.deviceInfos[i].deviceId
												+ "<span style=\"float:right\">已连接</span></p>");
						document.getElementById('msg.deviceInfos[i].deviceId').innerHTML = "已连接";
					} else {
						document.getElementById('connecteddevice').innerHTML = "";
						document.getElementById('connectedsuccess').innerHTML = "";
						$("#connectedsuccess")
								.append(
										"<p class=\"p_input p_input_yzm\"><em class=\"fa fa-envelope\"></em>"
												+ msg.deviceInfos[i].deviceId
												+ "<span style=\"float:right\">未连接</span></p>");
					}
				}
			});
}

// 获取设备信息
function getConnectedDeviceIds() {
	smitLogPrintWx('开始获取连接设备')
	smitPosSdkWx.getDevices(function(msg) {
		try {

			/* $("#initBle").append("<p>send msg:" + JSON.stringify(msg) + "</p>"); */
			smitLogPrintWx(JSON.stringify(msg))
			// if(msg.err_msg.indexOf("ok")!=-1){
			// 获取成功
			msg=eval('('+msg+')')
			if (msg.deviceInfos != []) {
				var devIds = [];
				// 给全局赋值
				sdevicesInfo = msg.deviceInfos;
				smitLogPrintWx(JSON.stringify(sdevicesInfo)+"--");
				var lsize=sdevicesInfo.length;
				for (var i = 0; i < lsize; i++) {

					var dev = sdevicesInfo[i];
					if (dev.state == 'connected') {
						devIds[i] = dev.deviceId;
					} else {
						_alert('请先连接设备。');
					}
				}
				if (devIds == []) {
					// 没有，则提示未连接任何设备。
					smitLogPrintWx('设备未连接')
					return;
				}
				// 已经连接
				// 开始进行设备认证流程
				// 获取设备信息-》随机数-》外部认证-》设备认证
				getDeviceInfo();
				// 根据设备Id去数据库获取记录
				/*
				 * $.ajax({ url:'', data:{'devIds':devIds}, async:false,
				 * success:function(res){ //展示信息 if(res.code=='000000'){ // } } })
				 */
			} else {
				_alert('没有连接过任何设备。');
			}
		} catch (e) {
			_alert('认证异常。');
		}
		// }
	});
}

var readFlashJson = {};
readFlashJson.addr = 0x0001E000;
readFlashJson.len = 0x00000008;

// 发送text指令
function encrySendData() {
	var data = document.getElementById("texts").value;
	$("#initBle").append("<p>发送的数据： " + data + "</p>");
	// bleManage.bleInit_sendData('24cf21c00092',encrydata);
	smitPosSdkWx.sendMsg(data, function(msg) {
		$("#initBle").append("<p>send msg:" + msg + "</p>");
	});
}

function LogClear() {
	document.getElementById('initBle').innerHTML = "";
}

// 扫描蓝牙
function ScanBlus() {
	smitPosSdkWx
			.blestate(function(msg) {
				if (msg == "off") {
					_alert("请先打开蓝牙！");
				} else {
					document.getElementById('connectmodel').innerHTML = "";
					smit
							.scanTimeout(
									5,
									function(msg) {
										document.getElementById('connectmodel').innerHTML = '';
										smitLogPrintWx(msg);
										var macid = '';
										for (var i = 0; i < strToJson(msg).devices.length; i++) {
											if (strToJson(msg).devices[i].deviceId !== undefined) {
												macid = strToJson(msg).devices[i].deviceId;
												smitLogPrintWx(macid);
												smitLogPrintWx(document
														.getElementById('connecteddevice').innerHTML);
												if (document
														.getElementById('connecteddevice').innerHTML == "") {
													$("#connectmodel")
															.append(
																	"<li> <p class=\"p_input p_input_yzm\"><em class=\"fa fa-envelope\"></em>"
																			+ strToJson(msg).devices[i].deviceId
																			+ "<span id="
																			+ strToJson(msg).devices.deviceId
																			+ " class=\"sp_btn\"  onclick=\"bindBle('"
																			+ strToJson(msg).devices[i].deviceId
																			+ "','"
																			+ openid
																			+ "')\">连接</span></p> </li>");
												} else if (document
														.getElementById('connecteddevice').innerHTML == macid) {
													$("#connectmodel")
															.append(
																	"<li> <p class=\"p_input p_input_yzm\"><em class=\"fa fa-envelope\"></em>"
																			+ strToJson(msg).devices[i].deviceId
																			+ "<span id="
																			+ strToJson(msg).devices.deviceId
																			+ " class=\"sp_btn\">已连接</span></p> </li>");
												}
											} else {
												$("#connectmodel")
														.append(
																"<li> <p class=\"p_input p_input_yzm\"><em class=\"fa fa-envelope\"></em>"
																		+ strToJson(msg).devices[i].deviceId
																		+ "<span id="
																		+ strToJson(msg).devices.deviceId
																		+ " class=\"sp_btn\">请修改蓝牙名</span></p> </li>");
											}
										}

									});
					SetTime();
				}
			});
}

// 断开连接
function bleDisConnect() {
	var deviceid = '001100000061'; // 微信公众号授权设备时，定义的deviceID
	smit.disconnect(deviceid, function(msg) {
		$("#initBle").append("<p>connect blu:" + msg + "</p>");
	});
}

// 连接蓝牙
function bleConn(deviceid) {
	smit.connect(deviceid, function(msg) {
		$("#initBle").append("<p>connect blu:" + msg + "</p>");
	});
}

// 获取蓝牙的状态
function getBlestate() {
	/*
	 * smit.stopScan(function(msg){ $("#initBle").append("<p>stop scan blu:" +
	 * msg + "</p>"); });
	 */
	smitPosSdkWx.blestate(function(msg) {
		$("#initBle").append("<p>get blestate:" + msg + "</p>");
		// _alert(JSON.stringify(msg));
		if (msg == 'on') {
			// 已打开蓝牙
			// 检测连接状态
			bleStopConn();
		} else {
			// 未打开蓝牙
			// 更改显示状态
			stateView('蓝牙未打开', 'red');
		}
	});
	// smitPosSdkWx.blestate(function(msg){
	// $("#initBle").append("<p>get connected11111111:" + msg + "</p>");
	// });
}

/**
 * 连接状态
 * 
 * @returns
 */
function bleStopConn() {
	/*
	 * var data = document.getElementById('DeviceId').innerHTML; if(data.length >
	 * 0){ smit.disconnect(data,function(msg){ $("#initBle").append("<p>disconnect
	 * blu:" + msg + "</p>"); }); }else{ $("#initBle").append("<p>not
	 * connect blu:</p>"); } document.getElementById('Device').innerHTML = "";
	 */
	// _alert(smitPosSdkWx.connectstate);
	// smitPosSdkWx.getconnected();
	smitPosSdkWx
			.getconnected(function(msg) {
				$("#initBle")
						.append("<p>get connected11111111:" + msg + "</p>");
				if (msg == 'disconnected') {
					stateView('未连接', 'red');
					// 未连接
					_alert('点击设备连接获取连接提示.');
				} else {
					// 链接成功
					connetState = true;
					stateView(
							'<a href="javascript:void(0);" onclick="getConnectedDeviceIds()">已连接,正在自动认证</a>',
							'green');
					getConnectedDeviceIds();
				}
			});
}

// 绑定设备
function bindBle(deviceid, openid) {
	smitLogPrintWx('2222222' + deviceid)
	smitLogPrintWx('222222' + openid)
	// 先去后台验证一下当前用户的openid有效
	$(function() {
		// var url = "/SmitPayWechat/bindDevice";
		// var args =
		// {"openid":openid,"deviceid":deviceid,"isBind":"0","time":new Date()};
		var url = "/SmitPayWechat/bindDevice";
		var args = {
			"url" : httpurl,
			openid : openid,
			deviceid : deviceid,
			isBind : "0",
			"time" : new Date()
		};
		$
				.post(
						url,
						args,
						function(data) {
							if (data != "") {
								$("#initBle").append(
										"<p>bind       :" + data + "</p>");
								var dataJson = JSON.parse(data);
								var base_resp = dataJson.base_resp;

								if (base_resp.errcode === 0
										&& base_resp.errmsg === 'ok') {
									$("#initBle").append(
											"<p>设备：" + deviceid
													+ "已绑定成功，正在连接</p>");
									document.getElementById('connectedsuccess').innerHTML = "";
									$("#connectedsuccess")
											.append(
													"<p class=\"p_input p_input_yzm\"><em class=\"fa fa-envelope\"></em>"
															+ deviceid
															+ "<span style=\"float:right\">已绑定</span></p>");

								}
							}
						});
	});
}

// 获取设备信息
function getbindDevice() {
	// OpenId = document.getElementById('OpenId').innerHTML;
	smitPosSdkWx.getDevices(function(msg) {
		$("#initBle").append(
				"<p>send msg getbindDevice:" + JSON.stringify(msg) + "</p>");
		$("#initBle").append("<p>-------开始解绑设备----OpenId--" + OpenId + "</p>");
		// smitLogPrintWx('-------开始解绑设备------'+msg.deviceInfos.length+';'+msg);
		var msgJson = JSON.parse(msg);
		if (msgJson.deviceInfos.length > 0) {
			for (var i = 0; i < msgJson.deviceInfos.length; i++) {

				smitLogPrintWx('-------开始解绑设备------');
				unbindBle(msgJson.deviceInfos[i].deviceId, OpenId);
			}
		}
	}.bind(this));
}

// 解绑设备
function unbindBle(deviceid, openid) {
	// 先去后台验证一下当前用户的openid有效
	$(function() {
		// var url = "/SmitPayWechat/bindDevice";
		// var args =
		// {"openid":openid,"deviceid":deviceid,"isBind":"1","time":new Date()};
		var url = "/SmitPayWechat/bindDevice";
		var args = {
			"url" : httpurl,
			openid : openid,
			deviceid : deviceid,
			isBind : "1",
			"time" : new Date()
		};
		$
				.post(
						url,
						args,
						function(data) {
							if (data != "") {
								$("#initBle").append(
										"<p>bind       :" + data + "</p>");
								var dataJson = JSON.parse(data);
								var base_resp = dataJson.base_resp;

								if (base_resp.errcode === 0
										&& base_resp.errmsg === 'ok') {
									$("#initBle").append(
											"<p>设备：" + deviceid + "已解绑成功</p>");
									// window.setTimeout(getConnectedDeviceId,3000);
									$("#unbindsuccess")
											.append(
													"<li> <p class=\"p_input p_input_yzm\"><em class=\"fa fa-envelope\"></em>"
															+ deviceid
															+ "<span style=\"float:right;\">解绑成功</span></p></li>");
								}
							}
						});
	});
}

function smitLogPrintWx(data) {
	$("#initBle").append("<p>" + data + "</p><br/>");
}

var wait = 5;
function SetTime() {
	if (wait == 0) {
		document.getElementById('scanbtn').innerHTML = "<a onclick=\"ScanBlus()\" class=\"c_4\">扫描</a>";
		wait = 5;
	} else {
		document.getElementById('scanbtn').innerHTML = "<a style=\"background:#BCBCBC;\">"
				+ wait + "秒后可以重新扫描</a>";
		wait--;
		setTimeout(function() {
			SetTime();
		}, 1000);
	}
	;
}