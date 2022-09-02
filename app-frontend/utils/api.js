const BASE_URL = "http://101.132.107.178:8089"
export const myRequest =(options)=>{
	const header_tmp={
		"token":uni.getStorageSync("Access_token") || ""
	};
	return new Promise((resolve,reject)=>{
		console.log(BASE_URL)
		console.log(BASE_URL+options.url)
		
		uni.request({
			url:BASE_URL+options.url,
			method:options.method || "GET",
			data:options.data,
			header:header_tmp,
			success: (res) => {
				console.log(res);
				if(res.statusCode>=300){
					return uni.showToast({
						title:"请求失败1"
					})
				};
				resolve(res)
				
			},
			fail:(err)=>{
				console.log(err);
				return uni.showToast({
					title:"请求失败2"
				})
				reject(err)
			},
		})
	})
}