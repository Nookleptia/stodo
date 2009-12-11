//format the todoitem object to String

//var aItem = {
//	getDesc : function(){return "short desc";},
//	getStatus : function(){return "new";},
//	getTimeout : function(){return "tomorrow morning";}
//}

function formatTooltip(item){
    	var formatted = new java.lang.StringBuffer();
    	
    	fomatted.append("<html>");
    	formatted.append("<b>Description : </b>").append(item.getDesc()).append(", ");
    	formatted.append("<b>Status : </b>").append(item.getStatus()).append(", ");
    	formatted.append("<b>Timeout : </b>").append(item.getTimeout());
    	formatted.append("</html>");

    	return formatted.toString();
}

//println(formatTooltip(aItem));

function alert(message){
	
}

function log(message){
	
}