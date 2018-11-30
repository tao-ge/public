/**
 * 设施编辑页面js
 */

$(function($){
   
	//所属工程下拉列表初始化
	$("#seleProId").tinyselect({ dataUrl: path+"/json/project/List.htm" , dataParser: dataParserA });

	//所属工程选中设置工程id
	$("#seleProId").on("change",function() {
		
		$("#proId").val($(this).val());
	});

	//表单验证
	$("#editForm").Validform(
			{
				btnSubmit:"#btnSave",
				ajaxPost:true,
				tiptype:function(msg,o,cssctl){
					if(!o.obj.is("form")){
						layer.tips(msg, '#'+o.obj.attr('id'));

					}					
				},
				callback:function(data){
					var result=eval(data);
					  if(result.r=1)
						{
							layer.confirm('保存成功，选择下一步操作？', {
								  btn: ['继续编辑','返回查询列表'] //按钮
								}, function(){			 			
									layer.msg('进入数据编辑状态...', {icon: 1});
								}, function(){
									parent.window.submitQuery(); 
									var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
									parent.layer.close(index);
									
		                        });
						}
						else
						{
							layer.msg(eval(data).r_content, {icon: 1});
						}
				}
				

			}

		);
	
	$("#btnClose").on('click',function(){
		                   parent.window.submitQuery(); 
						   var index = parent.layer.getFrameIndex(window.name);
						   });
    		
    }) 
    	
    /**
     * 绑定带查询的下拉列表框
     */
	function dataParserA(data, selected) {
		retval = [ { val: "-1" , text: "---" } ];

		data.forEach(function(v){
			if(selected == "-1" && v.val == 3)
				v.selected = true;
			retval.push(v); 
		});

		return retval;
	}


	
	