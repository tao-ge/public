/**
 * 设施状态查询js脚本
 */
$(function($){
    		
		    $(".infolist li a").each(function(i){  
		    	var content = $(this).html();
		    	if(content.length>30){
		    		content = content.substring(0,40);
		    		$(this).html(content+"...");
		    	}		    			          
		     });  
    		
    	})    	    	
    	