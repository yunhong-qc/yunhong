
var prefix = "/pack/studentInfo"
$(function() {
	load();
//    getShcollList();
});

function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/list", // 服务器数据的加载地址
					//	showRefresh : true,
					//	showToggle : true,
					//	showColumns : true,
						iconSize : 'outline',
						toolbar : '#exampleToolbar',
						striped : true, // 设置为true会有隔行变色效果
						dataType : "json", // 服务器返回的数据类型
						pagination : true, // 设置为true会在底部显示分页条
						// queryParamsType : "limit",
						// //设置为limit则会发送符合RESTFull格式的参数
						singleSelect : false, // 设置为true将禁止多选
						// contentType : "application/x-www-form-urlencoded",
						// //发送到服务器的数据编码类型
						pageSize : 10, // 如果设置了分页，每页数据条数
						pageNumber : 1, // 如果设置了分布，首页页码
						//search : true, // 是否显示搜索框
						showColumns : false, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset,
					            isSuccess:$('#isSuccess').val(),
//					            schoolId:$('#schoolId').val(),
					            isPay:$('#isPay').val(),
                                name:$("#name").val(),
                                beginTime:$("#beginTime").val(),
                                endTime:$("#endTime").val(),
					            sort:'create_time',
					            order:'asc'
							};
						},
						// //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
						// queryParamsType = 'limit' ,返回参数必须包含
						// limit, offset, search, sort, order 否则, 需要包含:
						// pageSize, pageNumber, searchText, sortName,
						// sortOrder.
						// 返回false将会终止请求
						columns : [{
									field : 'name', 
									title : '学生姓名' 
								},
																{
									field : 'cardNo', 
									title : '身份证号'
								},
																{
									field : 'telephone', 
									title : '电话'
								},{
									field : 'createTime', 
									title : '录入日期'
								},{
	                                field : 'isPay',
	                                title : '是否支付' ,
	                                formatter:function (value, row, index) {
	                                    if (value == 1) {
	                                        return '<span class="label label-danger">未支付</span>';
	                                    }else{
	                                        return '<span class="label label-primary">已支付</span>';
	                                    }
	                                }
	                            },
																{
									field : 'isSuccess', 
									title : '是否办理' ,
									formatter:function (value, row, index) {
                                        if (value == 1) {
                                            return '<span class="label label-danger">未办理</span>';
                                        }else if(value == 0){
                                            return '<span class="label label-primary">已办理</span>';
										}else{
                                            return '<span class="label label-danger">办理失败</span>';
										}
									}
								},
								{
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm '+s_yes_h+'" href="#" mce_href="#" title="办理成功" onclick="yes(\''
												+ row.id
												+ '\')"><i class="fa fa-check"></i></a> ';
										var d = '<a class="btn btn-warning btn-sm '+s_no_h+'" href="#" mce_href="#" title="办理失败"  onclick="no(\''
												+ row.id
												+ '\')"><i class="fa fa-remove"></i></a> ';
										return e + d ;
									}
								} ]
					});
}

function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
	
}

function resert() {
	$("#name").val("");
	$("#beginTime").val("");
	$("#endTime").val("");
	$("#isSuccess").val("");
    $('#exampleTable').bootstrapTable('refresh');
}

//办理成功
function yes(id){
    layer.confirm('确定已经办理成功？', {
        btn : [ '确定', '取消' ]
    }, function() {
        $.ajax({
            cache : true,
            type : "POST",
            url : "/pack/studentInfo/update",
            data : {isSuccess:0,id:id},// 你的formid
            async : false,
            error : function(request) {
                parent.layer.alert("Connection error");
            },
            success : function(data) {
                if (data.code == 0) {
                    layer.msg("操作成功");
                    reLoad();
                } else {
                    layer.alert(data.msg)
                }

            }
        });
    })
}
/**
 * 获取学校列表
 * @returns
 */
function getShcollList(){
	$.ajax({
        cache : true,
        type : "POST",
        url : "/pack/common/listSchoolInfo",
        async : false,
        error : function(request) {
            parent.layer.alert("Connection error");
        },
        success : function(data) {
            if (data.code == '000000') {
            	var html='<option value="">所有学校</option>';
               var schs=data.data;
               for(var i=0;i<schs.length;i++){
            	   var sch=schs[i];
            	   html+='<option value="'+sch.deptId+'">'+sch.name+'</option>';
               }
               $('#schoolId').html(html);
            } else {
                layer.alert(data.msg)
            }

        }
    });
}
//办理失败
function no(id){
    layer.confirm('确定已经办理失败？', {
        btn : [ '确定', '取消' ]
    }, function() {
        $.ajax({
            cache : true,
            type : "POST",
            url : "/pack/studentInfo/update",
            data : {isSuccess:-1,id:id},// 你的formid
            async : false,
            error : function(request) {
                parent.layer.alert("Connection error");
            },
            success : function(data) {
                if (data.code == 0) {
                    layer.msg("操作成功");
                    reLoad();
                } else {
                    layer.alert(data.msg)
                }

            }
        });
    })
}