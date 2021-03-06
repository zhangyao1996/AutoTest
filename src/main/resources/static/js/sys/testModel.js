layui.use('table', function(){
  var table = layui.table;
  
  //第一个实例
  table.render({
    elem: '#testModel'
    ,toolbar: '#toolbarDemo'
    ,url: '../../interface/testModelList' //数据接口
    ,page: true //开启分页
    ,limit:9//每页显示数据数目
    ,id : 'tableDate'
    ,cols: [[ //表头
    	 {type:'checkbox'}
      ,{field: 'id', title: 'ID', width:'5%'}
      ,{field: 'modelName', title: '模块名', width:'20%'} 
      ,{field: 'projectName', title: '项目名', width:'20%'}
      ,{field: 'testName', title: '测试人员', width: '20%'}
      ,{field: 'devName', title: '开发人员', width: '20%'}
      ,{field: 'modelDesc', title: '模块描述', width: '20%'}
   //  ,{field: 'status', title: '状态', width: '10%'}
    ]]
  });
  
 //搜索功能
  var $ = layui.$, active = {
			reload : function() {
				var projectId=$("#projectId").val();
				var modelName=$("#modelName").val();
				var testId=$("#testId").val();
				var devId=$("#devId").val();
				//var status=$("#status").val();
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
						modelName:modelName,
						projectId:projectId,
						//status:status,
						testId:testId,
						devId:devId
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
    	  addModel();
      break;
      case 'delete':
        var data = checkStatus.data;
		var ids="";
		if(data.length>0){
			for(var i=0;i<data.length;i++){
				ids+=data[i].id+",";
			}
			layer.confirm('是否删除这'+data.length+'条模块,这可能与该模块相关的测试用例也被删除？',{icon: 3,title:'提示'},function(index){
				//window.location.href="../../user/deleteUserIds?ids="+ids+"";
				deleteModelIds(ids);
			});
		}else{
			layer.alert("请选择要删除的用例");
		}
      break;
      case 'update':
    	  var data = checkStatus.data;
  		if(data.length>1){
  			layer.alert("不能同时编辑多条模块");
  		}else if(data.length==0){
  			layer.alert("请选择要编辑的模块");
  		}else {
  			var id=data[0].id;
  			
  			layer.confirm('编辑模块可能导致该模块相关测试用例信息改变，确定继续吗？',{icon: 3,title:'提示'},function(index){
				//window.location.href="../../user/deleteUserIds?ids="+ids+"";
  				updateModel(id);
  				layer.close(index);
			});
  			
		}
      break;
    };
  });
  
  
	// 新增model
	function addModel() {
		layer.open({
			area : [ '493px','424px' ], // 宽高
			title:'增加模块',
			type : 2,
			fix : false, // 不固定
			maxmin : true,
			content : '../../interface/toAddModel'
		});
	}
	
	//编辑model
	function updateModel(id) {
		layer.open({
			area : [ '493px','424px' ], // 宽高
			title:'编辑模块',
			type : 2,
			fix : false, // 不固定
			maxmin : true,
			content : '../../interface/toUpdateModel?id='+id
		});
	}
	
	//删除model
	function deleteModelIds(ids) {
		$.ajax({
			url : "../../interface/deleteModel?ids=" + ids,
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