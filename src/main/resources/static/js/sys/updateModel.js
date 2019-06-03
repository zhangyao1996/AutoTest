layui.use([ 'form', 'jquery' ], function() {
	var form = layui.form;
	var $ = layui.jquery;
	
//	var staval = $("#state").val(); //hidden 隐藏框的数据
//	$("#devId option[value = '"+staval+"']").attr("selected",true);
//	var devId=$("#devId").val();
//	alert(devId);

	$("#devId").find("option[value='"+$("#devPerson").val()+"']").attr("selected",true);
	$("#testId").find("option[value='"+$("#testPerson").val()+"']").attr("selected",true);
	$("#projectId").find("option[value='"+$("#projectName").val()+"']").attr("selected",true);
	form.render('select');
//	var devName="${model.devName}";
//	alert(devName);
//	 $("#devId").atr("你好");
	 
	
	// 验证规则
//	form.verify({
//		username : [ /^[a-zA-Z0-9]{4,12}$/, '用户名必须是4-16位的字母与数字' ],
//		password : [ /^[a-zA-Z0-9]{4,16}$/, '密码必须是4-16位的字母与数字' ],
//		realname : [ /^([\u4e00-\u9fa5]){2,5}$/, '只能是中文姓名' ]
//	});
	// 添加表单失焦事件
	// 验证表单
//	$('#username').blur(function() {
//		var username = $("#username").val();
//		console.log("用户名" + username);
//		$.ajax({
//			url : '../../user/checkUser?userName=' + username,
//			type : 'post',
//			// dataType:'json',
//			// data:JSON.stringify({username:username}),
//			// 验证用户名是否可用
//			success : function(data) {
//				if (data.result == false) {
//					layer.tips('用户名已存在！', '#username', {
//						tips : [ 2, '#FF3030' ],
//						time : 2000
//					});
//				}
//			}
//		})
//
//	});

	form.on('submit(update)', function(data) {
		// console.log(data.field);
		var id=$("#id").val();
		var modelname = $("#modelname").val();
		var projectId = $("#projectId").val();
		var testId = $("#testId").val();
		var devId = $("#devId").val();
		var modeldesc = $("#modeldesc").val();
		//var status = $("#status").val();
		var status = $("input[name='status']:checked").val();
		
		console.log(modelname+projectId);
			$.ajax({
				url : '../../interface/updateModel',
				type : 'post',
				dataType : 'json',
				contentType : 'application/json;charset=UTF-8',
				data : JSON.stringify({
					id:id,
					modelName : modelname,
					projectId : projectId,
					testId : testId,
					devId : devId,
					modelDesc : modeldesc,
					status : status
				}),
				success : function(data) {
					if (data.result == true) {
						layer.msg(data.msg, {
							icon : 1,
							time : 2000
						},
							function() {
								parent.location.reload(); // 父页面刷新
								var index = parent.layer
										.getFrameIndex(window.name); // 获取窗口索引
								parent.layer.close(index);
							});

					} else {
						layer.msg(data.msg, {
							icon : 2
						})

					}
				}
			})
			return false;
	})

});