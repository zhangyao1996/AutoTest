layui.use('table', function(){
  var table = layui.table;
  
  //第一个实例
  table.render({
    elem: '#testCase'
    ,toolbar: '#toolbarDemo'
    ,url: '../../interface/testCaseList' //数据接口
    ,page: true //开启分页
    ,limit:9 //每页显示数据数目
    ,cols: [[ //表头
    	 {type:'checkbox'}
      ,{field: 'id', title: 'ID', width:'5%'}
      ,{field: 'caseName', title: '用例名', width:'20%'}
      ,{field: 'projectName', title: '项目名', width:'10%'}
      ,{field: 'modelName', title: '模块名', width:'10%'} 
      ,{field: 'api', title: 'api', width: '20%'}
      ,{field: 'version', title: '版本', width: '6%'}
      ,{field: 'caseDesc', title: '用例描述', width: '20%'}
      ,{field: 'status', title: '状态', width: '11%'}
    ]]
  });
  
  //头工具栏事件
  table.on('toolbar(test)', function(obj){
    var checkStatus = table.checkStatus(obj.config.id);
    switch(obj.event){
      case 'add':
    	  addCase();
      break;
      case 'delete':
        var data = checkStatus.data;
		var ids="";
		if(data.length>0){
			for(var i=0;i<data.length;i++){
				ids+=data[i].id+",";
			}
			layer.confirm('是否删除这'+data.length+'条用例',{icon: 3,title:'提示'},function(index){
				//window.location.href="../../user/deleteUserIds?ids="+ids+"";
				deleteCaseIds(ids);
			});
		}else{
			layer.alert("请选择要删除的用例");
		}
      break;
      case 'update':
    	  var data = checkStatus.data;
  		if(data.length>1){
  			layer.alert("不能同时编辑多条用例");
  		}else if(data.length==0){
  			layer.alert("请选择要编辑的用例");
  		}else {
  			var id=data[0].id;
  			updateCase(id);
		}
      break;
      case 'buildscript':
          layer.msg(checkStatus.isAll ? '全选': '未全选');
        break;
    };
  });
  
  
	// 新增case
	function addCase() {
		layer.open({
			area : [ '493px','424px' ], // 宽高
			title:'增加用例',
			type : 2,
			fix : false, // 不固定
			maxmin : true,
			content : '../../interface/toAddCase'
		});
	}
	
	//编辑用例
	function updateCase(id) {
		layer.open({
			area : [ '493px','424px' ], // 宽高
			title:'编辑用例',
			type : 2,
			fix : false, // 不固定
			maxmin : true,
			content : '../../interface/toUpdateCase?id='+id
		});
	}
	
	//删除case
	function deleteCaseIds(ids) {
		$.ajax({
			url : "../../interface/deleteCase?ids=" + ids,
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