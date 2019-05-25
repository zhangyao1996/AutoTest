layui.use('table', function(){
  var table = layui.table;
  
  //第一个实例
  table.render({
    elem: '#testProject'
    ,toolbar: '#toolbarDemo'
    ,url: '../../interface/testProjectList' //数据接口
    ,page: true //开启分页
    ,limit:9//每页显示数据数目
    ,id : 'tableDate'
    ,cols: [[ //表头
    	 {type:'checkbox'}
      ,{field: 'id', title: 'ID', width:'5%'}
      ,{field: 'projectName', title: '项目名', width:'20%'}
      ,{field: 'testName', title: '测试人员', width: '20%'}
      ,{field: 'devName', title: '开发人员', width: '20%'}
      ,{field: 'projectDesc', title: '项目描述', width: '20%'}
      ,{field: 'status', title: '状态', width: '12%'}
    ]]
  });
  
 //搜索功能
  var $ = layui.$, active = {
			reload : function() {
				var projectName=$("#projectname").val();
				var testId=$("#testId").val();
				var devId=$("#devId").val();
				var status=$("#status").val();
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
						projectName:projectName,
						status:status,
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
    	  addProject();
      break;
      case 'delete':
        var data = checkStatus.data;
		var ids="";
		if(data.length>0){
			for(var i=0;i<data.length;i++){
				ids+=data[i].id+",";
			}
			layer.confirm('是否删除这'+data.length+'条项目,这可能与该项目相关的测试模块与用例也被删除？',{icon: 3,title:'提示'},function(index){
				//window.location.href="../../user/deleteUserIds?ids="+ids+"";
				deleteProjectIds(ids);
			});
		}else{
			layer.alert("请选择要删除的项目");
		}
      break;
      case 'update':
    	  var data = checkStatus.data;
  		if(data.length>1){
  			layer.alert("不能同时编辑多个项目");
  		}else if(data.length==0){
  			layer.alert("请选择要编辑的项目");
  		}else {
  			var id=data[0].id;
  			
  			layer.confirm('编辑项目可能导致该项目相关测试模块与用例信息改变，确定继续吗？',{icon: 3,title:'提示'},function(index){
				//window.location.href="../../user/deleteUserIds?ids="+ids+"";
  				updateProject(id);
  				layer.close(index);
			});
  			
		}
      break;
    };
  });
  
  
	// 新增项目
	function addProject() {
		layer.open({
			area : [ '493px','424px' ], // 宽高
			title:'增加项目',
			type : 2,
			fix : false, // 不固定
			maxmin : true,
			content : '../../interface/toAddProject'
		});
	}
	
	//编辑项目
	function updateProject(id) {
		layer.open({
			area : [ '493px','424px' ], // 宽高
			title:'编辑项目',
			type : 2,
			fix : false, // 不固定
			maxmin : true,
			content : '../../interface/toUpdateProject?id='+id
		});
	}
	
	//删除项目
	function deleteProjectIds(ids) {
		$.ajax({
			url : "../../interface/deleteProject?ids=" + ids,
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