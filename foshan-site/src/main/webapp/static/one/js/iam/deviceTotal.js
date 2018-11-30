	/**
	 * 设备安装统计JS
	 * @param $
	 * @returns
	 */
	$(function($){
		$("#btnSimpleQuery").on('click', queryByYear);// 查询确认
		start();
		$(".areadevCode").on("change",function(){
			changeArea($(this));
		})
		$(".areadevCode").change();
	});
	
	function queryByYear(){
		start();
	}
	
	/**
	 * 选择不同视图
	 * @returns
	 */
	function SwitchingFigure(){
		var check = $("#check").val();
		if (check==0) {
			window.location.href= path+"/queryDeviceTotal.htm";
		}
		if (check==1) {
			window.location.href= path+"/queryDeviceCount.htm";
		}
		if (check==2) {
			window.location.href= path +"/queryDisinfoCount.htm";
		}
		
	}
	
	/**
	 * 
	 * @param districtObj
	 * @returns
	 */
	function changeArea(districtObj){
		var areaCode1=$("#areadevCode").val();
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
					 if(areaCode1==value.name)
					{
						 
						 //回显
						areas.append($("<option>").prop("value",data[index].name).prop("selected",true)
						.append(data[index].value));
						
    					areas.append($("<option>").prop("value",data[index].name).append(data[index].value));
    					//删除重复数据
    					var obj=document.getElementById("areaCode2");
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