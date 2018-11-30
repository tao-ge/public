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
					  if(result.r==1)
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
							layer.msg(result.r_content, {icon: 1});
						}
				}
				

			}

		);
	
	$("#btnClose").on('click',function(){
		                   parent.window.submitQuery(); 
						   var index = parent.layer.getFrameIndex(window.name);
						   });
	
	$(".areaCode1").on("change",function(){
		changeArea1($(this));
	})
	$(".areaCode1").change();
    		
    }) 
    	
    /**
     * 绑定带查询的下拉列表框
     */
	function dataParserA(data, selected) {
			retval = [ { val: -1 , text: '---' } ];
			
			data.forEach(function(v){
				if(selected == -1 && v.val == 3)
					v.selected = true;
				retval.push(v); 
			});
		return retval;
	}
	//二级汇聚区  	
	function changeArea1(districtObj){
		var areaCode2=$("#areaCode2").val();
		var  value = $(districtObj).val();
		var areas = $(districtObj).parents("div").find("select.hjq");
		areas.children("option").remove();
		areas.append($("<option>").prop("value",'').append("==请选择=="));
		$.ajax({
			type:"POST",
			url: path+"/ajax/getAreas.htm?areaRank=4&parentAreaCode="+value,
			dataType:"json",
			success:function(data)
			{
				$.each(data,function(index,value){
					 if(areaCode2==value.name)
					{
						 //回显
						areas.append($("<option>").prop("value",data[index].name).prop("selected",true)
						.append(data[index].value));
						areas.append($("<option>").prop("value",data[index].name).append(data[index].value));
						//删除重复数据
					var obj=document.getElementById("areaCode");
						//index,要删除选项的序号，这里取当前选中选项的序号
						var index=obj.selectedIndex;
						obj.options.remove(index+1); 
					}else{
					 areas.append($("<option>").prop("value",data[index].name).append(data[index].value));
					} 
				});
			}
		});
	}
	
	
		
		
