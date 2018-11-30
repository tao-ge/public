/**
 * 设备远程更新js
 * @param $
 * @returns
 */
$(function($){
	$("#upload").on("click",uploadData);//上传事件
	$("#btnSimpleQuery").on("click",submitQuery);//查询事件
});

function submitQuery()
{
	$("#searchlistform")[0].action = path +"/hardwareUpgradList.htm";
	$("#searchlistform")[0].submit();
}

//分页浏览查询
function pageQuery(){
	$("#pageNo").val($(this).attr("pages"));
	submitQuery();
}

/**
 * 文件上传
 * @returns
 */
function uploadData(){
	var did = $(this).attr("did");
	layer.open({
		type:1,
		title:'上传',
		content:$('#div_uploadFile'),
		area:'600px,600px',
		shadeClose : true, // 点击遮罩关闭
		btnAlign: 'c',
		btn:['上传','取消'],
		yes:function(){
			var hardVersion = $("#hardVersion1").val();
			var remark = $("#remark1").val();
			var hardType = $("#hardType").val();
			var crc = $("#crc").val();
			if(hardVersion == null || hardVersion == ''){
				alert("请填写硬件版本号");
				return;
			}
			if(crc == null|| crc == ''){
				alert("请填写crc值");
				return;
			}
			if(hardType == null || hardType == ''){
				alert("请选择硬件类型");
				return;
			}
			var index = layer.load(2);
			var formData = new FormData();
		    formData.append("importFile", document.getElementById("hardFile").files[0]);  
			$.ajax({
				   type: "POST",
				   url: path+"/hardwareUpgradUploadFile.htm?crc="+crc+"&hardVersion="+hardVersion+"&remark="+remark+"&hardType="+hardType,
				   /**
                    *必须false才会自动加上正确的Content-Type
                    */
				   contentType: false,
				   /**
                    * 必须false才会避开jQuery对 formdata 的默认处理
                    * XMLHttpRequest会对 formdata 进行正确的处理
                    */
				   processData: false,
				   data:formData,
				   success: function(data){
					  alert(data.r_content);
					  if(data.r==1){
						  submitQuery();
					  }
					  if(data.r==2){
						  layer.close(index);
					  }
				   },
				   error:function(e){
					   layer.close(index);
					   layer.alert('发生错误:' + e.statusText,{icon:2});
				   }
				});
		}
	})
}