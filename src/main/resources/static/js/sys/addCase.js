layui.use([ 'form', 'jquery' ], function() {
	var form = layui.form;
	var $ = layui.jquery;

	// 验证规则
	// form.verify({
	// username : [ /^[a-zA-Z0-9]{4,12}$/, '用户名必须是4-16位的字母与数字' ],
	// password : [ /^[a-zA-Z0-9]{4,16}$/, '密码必须是4-16位的字母与数字' ],
	// realname : [ /^([\u4e00-\u9fa5]){2,5}$/, '只能是中文姓名' ]
	// });
	// 添加表单失焦事件
	// 验证表单
	// $('#username').blur(function() {
	// var username = $("#username").val();
	// console.log("用户名" + username);
	// $.ajax({
	// url : '../../user/checkUser?userName=' + username,
	// type : 'post',
	// // dataType:'json',
	// // data:JSON.stringify({username:username}),
	// // 验证用户名是否可用
	// success : function(data) {
	// if (data.result == false) {
	// layer.tips('用户名已存在！', '#username', {
	// tips : [ 2, '#FF3030' ],
	// time : 2000
	// });
	// }
	// }
	// })
	//
	// });

	/*
	 * form.on('select(roleId)', function(data){ $("#roleId").val(data.value)
	 * });
	 */

	form.on('select(projectId)', function(data) {
		var projectId = $("#projectId").val();
		var test = $("#casename").val();
		console.log("value" + data.value);
		console.log("projectId:" + projectId);
		console.log("test:" + test);
		$.ajax({
			type : 'POST',
			url : '../../interface/toGetModelListByProId',
			data : {
				projectId : projectId
			},
			dataType : 'json',
			async : true,
			success : function(datas) {
				if (datas.length > 0) {
					var option = '<option value="">请选择</option>';
					for (var i = 0; i < datas.length; i++) {
						option += "<option value='" + datas[i].id + "'>"
								+ datas[i].modelName + "</option>";
					}
				} else {
					var option = '<option value="">请选择</option>'; // 默认值
				}

				$("#modelId").html("");
				$("#modelId").append(option);
console.log(option);
				form.render('select');
			}

		})
	})

	form.on('submit(add)', function(data) {
		// console.log(data.field);

		var casename = $("#casename").val();
		var projectId = $("#projectId").val();
		var modelId = $("#modelId").val();
		var api = $("#api").val();
		var version = $("#version").val();
		var casedesc = $("#casedesc").val();
		// var status = $("#status").val();
		//var status = $("input[name='status']:checked").val();

		$.ajax({
			url : '../../interface/addCase',
			type : 'post',
			dataType : 'json',
			contentType : 'application/json;charset=UTF-8',
			data : JSON.stringify({
				caseName : casename,
				projectId : projectId,
				modelId : modelId,
				api : api,
				version : version,
				caseDesc : casedesc,
//				status : status
			}),
			success : function(data) {
				if (data.result == true) {
					layer.msg(data.msg, {
						icon : 1,
						time : 2000
					}, function() {
						parent.location.reload(); // 父页面刷新
						var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
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