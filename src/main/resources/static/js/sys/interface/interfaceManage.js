layui.use('table', function(){
  var table = layui.table;
  
  //第一个实例
  table.render({
    elem: '#testStep'
    ,toolbar: '#toolbarDemo'
    ,url: '../../interface/testInterfaceList' //数据接口
    ,page: true //开启分页
    ,limit:9//每页显示数据数目
    ,id : 'tableDate'
    ,cols: [[ //表头
    	 {type:'checkbox'}
      ,{field: 'id', title: 'ID', width:'10%'}
      ,{field: 'caseName', title: '用例名', width:'30%'}
      ,{field: 'interfaceName', title: '接口名', width: '45%'}
      ,{field: 'reqMode', title: '方式', width: '10%'}
    ]]
  });
  
 //搜索功能
  var $ = layui.$, active = {
			reload : function() {
				var stepName=$("#stepname").val();
				var reqMode=$("#reqmode").val();
				var index=layer.msg('查询中，请稍后。。。',{icon:16,time:false,shade:0});
				setTimeout(function() {
				 table.reload('tableDate', {//执行table重载
					// 从第 1 页开始
					page : {
						curr : 1
					}
					// 设定异步数据接口的额外参数
					,
					where : {
						stepName:stepName,
						reqMode:reqMode
					}
				});
				 layer.close(index);
				},800);
			}
  };
  
  
//点击搜索
  $('#search').on('click', function() {
		var type = $(this).data('type');
		active[type] ? active[type].call(this) : '';
	});
  
  //头工具栏事件
  table.on('toolbar(test)', function(obj){
    var checkStatus = table.checkStatus(obj.config.id);
    switch(obj.event){
      case 'add':
    	  addStep();
      break;
      case 'delete':
        var data = checkStatus.data;
		var ids="";
		if(data.length>0){
			for(var i=0;i<data.length;i++){
				ids+=data[i].id+",";
			}
			layer.confirm('是否删除这'+data.length+'条步骤？',{icon: 3,title:'提示'},function(index){
				//window.location.href="../../user/deleteUserIds?ids="+ids+"";
				deleteStepIds(ids);
			});
		}else{
			layer.alert("请选择要删除的步骤");
		}
      break;
      case 'use':
    	  var data = checkStatus.data;
  		if(data.length>1){
  			layer.alert("不能同时使用多个接口");
  		}else if(data.length==0){
  			layer.alert("请选择要使用的接口");
  		}else {
  			var id=data[0].id;
  			useInterface(id);
  			
		}
      break;
      case 'update':
    	  var data = checkStatus.data;
  		if(data.length>1){
  			layer.alert("不能同时编辑多条步骤");
  		}else if(data.length==0){
  			layer.alert("请选择要编辑的步骤");
  		}else {
  			var id=data[0].id;
  			updateStep(id);
  			
		}
      break;
    };
  });
  
  
	// 新增步骤
	function addStep() {
		layer.open({
			area : [ '600px','455px' ], // 宽高
			title:'增加步骤',
			type : 2,
			fix : false, // 不固定
			maxmin : true,
			content : '../../interface/toAddInterface'
		});
	}
	
	//编辑步骤
	function updateStep(id) {
		layer.open({
			area : [ '493px','424px' ], // 宽高
			title:'编辑步骤',
			type : 2,
			fix : false, // 不固定
			maxmin : true,
			content : '../../interface/toUpdateInterface?id='+id
		});
	}
	
	//使用接口
	function useInterface(id) {
		$.ajax({
			url : "../../httpclient/useInterface?id=" + id,
			type : "post",
			success : function(data) {
				if (data.result == true) {
					layer.msg(data.msg, {
						icon : 1,
						time : 2000
					}, function() {
						location.reload();
					});
				} else {
					layer.msg(data.msg, {
						icon : 2
					})
				}
			}
		});
	}
	
	//删除步骤
	function deleteStepIds(ids) {
		$.ajax({
			url : "../../interface/deleteInterface?ids=" + ids,
			type : "delete",
			success : function(data) {
				if (data.result == true) {
					layer.msg(data.msg, {
						icon : 1,
						time : 2000
					}, function() {
						location.reload();
					});
				} else {
					layer.msg(data.msg, {
						icon : 2
					})
				}
			}
		});
	}
  
});