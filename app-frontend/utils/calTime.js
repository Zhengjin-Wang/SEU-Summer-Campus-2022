
export const calTime =(options)=>{
	var date3 = new Date(options.startTime).getTime() - new Date().getTime(); //时间差的毫秒数
	
	
	//计算出小时数  
	var leave1 = date3 % (24 * 3600 * 1000) //计算天数后剩余的毫秒数  
	var hours = Math.floor(leave1 / (3600 * 1000))
	
	//计算相差分钟数  
	var leave2 = leave1 % (3600 * 1000) //计算小时数后剩余的毫秒数  
	var minutes = Math.floor(leave2 / (60 * 1000))
	var realtime = minutes + hours*60
	return realtime
}
