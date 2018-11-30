	/**
	 * 设备安装数量JS
	 * @param $
	 * @returns
	 */
	$(function($){
		$("#btnSimpleQuery").on('click', queryCount);// 查询确认
		$(".areadevCode").on("change",function(){
			changeArea($(this));
		})
		$(".areadevCode").change();
	});
	
	/**
	 * 查询
	 * @returns
	 */
	function queryCount(){
		var areaCode1 = $("#areadevCode").val();
		var areaCode2 = $("#areaCode2").val();
		window.location.href= path+"/queryDisinfoCount.htm?areaCode2="+areaCode2+"&areaCode1="+areaCode1;
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
		var areaCode1=$("#areaCode").val();
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
//				$.each(data,function(index,value){
//					if(areaCode1==value.name)
//					{
//						 areas.append("<option value='"+data[index].name+"'' selected>"+data[index].value+"</option>");
//					}else{
//						areas.append("<option value='"+data[index].name+"''>"+data[index].value+"</option>");
//					}
//				});
				if (data!=null) {
					for(var i=0;i<data.length;i++){
						if (areaCode1 == data[i].name) {
							areas.append("<option value='"+data[i].name+"'' selected>"+data[i].value+"</option>");
						}else{
							areas.append("<option value='"+data[i].name+"'' >"+data[i].value+"</option>");
						}
					}
				}
			}
		});
		
	}
	