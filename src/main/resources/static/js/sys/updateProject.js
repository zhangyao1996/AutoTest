layui.use([ 'form', 'jquery' ], function() {
	var form = layui.form;
	var $ = layui.jquery;
	
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


	
	/*form.on('select(roleId)', function(data){
		  $("#roleId").val(data.value)
		});*/
	
	
	form.on('submit(update)', function(data) {
		// console.log(data.field);
		var id=$("#id").val();
		var projectname = $("#projectname").val();
		var projectdesc = $("#projectdesc").val();
		var testId = $("#testId").val();
		var devId = $("#devId").val();
		//var status = $("#status").val();
		var status = $("input[name='status']:checked").val();
		
			$.ajax({
				url : '../../interface/updateProject',
				type : 'post',
				dataType : 'json',
				contentType : 'application/json;charset=UTF-8',
				data : JSON.stringify({
					id:id,
					projectName:projectname,
					projectDesc:projectdesc,
					testId:testId,
					devId:devId,
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