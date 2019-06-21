layui.use([ 'form', 'jquery' ], function() {
	var form = layui.form;
	var $ = layui.jquery;
	
	//监听开关
	form.on('switch(switchAst)',function (data) {
        //开关是否开启，true或者false
        var checked = data.elem.checked;
       if(checked==="true"){
    	 //  $("#ast").attr("style","display:block");
    	   $("#ast").show();
    	   alert(checked);
       }else {
    	   //$("#ast").attr("style","display:none");
    	   $("#ast").hide();
    	   alert(checked);
       }
        form.render();
    });

	
	form.on('submit(add)', function(data) {
		// console.log(data.field);
//interfacename reqmode header param body ast
		var casename = $("#casename").val();
		var interfacename = $("#interfacename").val();
		var header = $("#header").val();
		var param = $("#param").val();
		var body = $("#body").val();
		var ast = $("#ast").val();
		var reqmode = $("input[name='reqmode']:checked").val();
		
			$.ajax({
				url : '../../interface/addInterface',
				type : 'post',
				dataType : 'json',
				contentType : 'application/json;charset=UTF-8',
				data : JSON.stringify({
					caseName : casename,
					interfaceName : interfacename,
					header : header,
					param : param,
					body : body,
					ast : ast,
					reqMode:reqmode
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


