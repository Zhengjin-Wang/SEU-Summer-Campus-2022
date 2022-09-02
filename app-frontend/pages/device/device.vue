<template>
	<view>

		<scroll-view :scroll-top="scrollTop" scroll-y="true" class="scroll-Y" @scrolltoupper="upper"
			@scrolltolower="lower" @scroll="scroll">

			<!-- 显示地图 -->
			<view class="page-section page-section-gap">
				<map style="width: 100%; height: 540px;" :latitude="maplatitude" :longitude="maplongitude"
					:markers="covers" :show-location="true" :polyline='mypolyline' :enable-3D="true"
					:show-compass='true'>
				</map>
			</view>

			<!-- 	<view class="test">
				<camera v-show="cameraShow" mode="scanCode" device-position="back" flash="off" @error="error"
					style="width: 100%; height: 300px;"></camera>
			</view> -->


			<!-- 显示二维码扫描 -->
			<view class="mybutton">
				<u-button icon="scan" type="primary" text="扫描设备二维码" size="normal" shape="circle" color="#7B68EE"
					@click="scanCode">
				</u-button>
			</view>

		</scroll-view>

	</view>
</template>

<script>
	export default {
		data() {
			return {
				myla:"",
				mylo:'',
				maplatitude: 31.882023,
				maplongitude: 118.826514,
				itemId: "",
				allDeviceInfo: [],
				place: [],
				// covers:[],
				covers: [
					// {
					// 	id: 0,
					// 	latitude: 31.884219,
					// 	longitude: 118.818548,
					// 	iconPath: '../../static/loc_.png',
					// 	callout: {
					// 		content: "设备1",
					// 		color: '#ffffff',
					// 		borderRadius: 15,
					// 		bgColor: "#7B68EE"

					// 	}
					// 	},
					// // },
					// {
					// 	id: 1,
					// 	latitude: 31.882547,
					// 	longitude: 118.821225,
					// 	iconPath: '../../static/loc_.png',
					// 	callout: {
					// 		content: "设备2",
					// 		color: '#ffffff',
					// 		borderRadius: 15,
					// 		bgColor: "#7B68EE"

					// 	}

					// }, 
					// {
					// 	id: 2,
					// 	latitude: 31.884575,
					// 	longitude: 118.826397,
					// 	iconPath: '../../static/loc_.png',
					// 	callout: {
					// 		content: "设备3",
					// 		color: '#ffffff',
					// 		borderRadius: 15,
					// 		bgColor: "#7B68EE"

					// 	}
					// },
					// {
					// 	id: 3,
					// 	latitude: maplatitude,
					// 	longitude: maplongitude,
					// 	iconPath: '../../static/loc_.png',
					// 	callout: {
					// 		content: "设备4",
					// 		color: '#ffffff',
					// 		borderRadius: 15,
					// 		bgColor: "#000"

					// 	},
					// }
				],
				// mypolyline:[],
				mypolyline: [
					// {
					// points: [{
					// 		latitude: 31.884219,
					// 		longitude: 118.818548
					// 	}, {
					// 		latitude: 31.882547,
					// 		longitude: 118.821225
					// 	}, {
					// 		latitude: 31.884575,
					// 		longitude: 118.826397
					// 	},
					// 	// 	latitude: maplatitude,
					// 	// 	longitude: maplongitude
					// 	// }],
					// ],
					// // mypolyline:[],
					// arrowLine: true,
					// dottedLine: true,
					// width: 2,
					// color: "#7B68EE"

				// }
				],
				cameraShow: false,
				// list: ["1", "2"],
				scrollTop: 0,
				old: {
					scrollTop: 0
				}
				// mypolygon:[
				// 		{
				// 		points: [{
				// 			latitude: 31.884219,
				// 			longitude: 118.818548
				// 		}, {
				// 			latitude: 31.882547,
				// 			longitude: 118.821225
				// 		}, {
				// 			latitude: 31.884575,
				// 			longitude: 118.826397
				// 		}],
				// 		// arrowLine: true,
				// 		// dottedLine: true,
				// 		// width: 2,
				// 		fillColor: "#7B68EE"

				// 	},
				// ]
			}
		},
		methods: {
			scanCode() {
				// 允许从相机和相册扫码
				uni.scanCode({
					success: function(res) {
						console.log('条码类型：' + res.scanType);
						console.log('条码内容：' + res.result);
						var tempData = res.result;
						uni.navigateTo({
							url: '/pages/deviceInfo/deviceInfo?index=' + tempData
						});
					}
				});
			},
			scroll: function(e) {
				console.log(e)
				this.old.scrollTop = e.detail.scrollTop
			},
			goTop: function(e) {
				// 解决view层不同步的问题
				this.scrollTop = this.old.scrollTop
				this.$nextTick(function() {
					this.scrollTop = 0
				});
				uni.showToast({
					icon: "none",
					title: "纵向滚动 scrollTop 值已被修改为 0"
				})
			}

		},
		// onLoad: function(option) { //option为object类型，会序列化上个页面传递的参数
		// 	console.log("测试一下任务跳转map", option.index); //打印出上个页面传递的参数。
		// 	this.itemId = option.index;
		// },
		onReady() {
			this.covers.push( {
				id: 8,
				latitude: 31.884312,
				longitude: 118.818567,
				iconPath: '../../static/mylocation.png',
				callout: {
					content: 'sui',
					color: '#ffffff',
					borderRadius: 15,
					bgColor: "#7B68EE"
				
				}
			})
			
			console.log("open")
			setInterval(()=>{
				let _this = this
				uni.getLocation({
					type: 'gcj02',
					altitude: true,
					isHighAccuracy: true,
					success: (res) => {
						console.log('当前位置的经度：' + res.longitude);
						console.log('当前位置的纬度：' + res.latitude);
						const latitude = res.latitude;
						const longitude = res.longitude;
						
						this.myla = latitude;
						this.mylo = longitude;
						console.log('pos,employee_id='+uni.getStorageSync('myid')+' lat='+this.myla+',lng='+this.mylo)
						uni.request({
							url:"http://101.132.107.178:8086/api/v2/write?org=SEU&bucket=pos-bucket&precision=s",
							method:"POST",
							data:'pos,employee_id='+uni.getStorageSync('myid')+' lat='+this.myla+',lng='+this.mylo,
							header:{
								"Authorization": "Token FMLGu8oMjMZgWF_nczsp2NMtGiQeVL4VegkomG-KQ4qtMILD0pqzbG4ay6_wBn75hrfk4nn47insmGRhAU5CSQ==" ,
								"Content-Type": "text/plain; charset=utf-8",
								"Accept": "application/json"
							},
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
						// this.$myRequest({
						// 	url: '/task/points/' + iid,
						// 	method: 'POST',
						// 	data:{
						// 		latitude:this.myla,
						// 		longitude:this.mylo
						// 	}
						// })
						
						
						
						
						this.covers.pop()
						this.covers.push( {
							id: 8,
							latitude: this.myla,
							longitude: this.mylo,
							iconPath: '../../static/mylocation.png',
							callout: {
								content: 'sui',
								color: '#ffffff',
								borderRadius: 15,
								bgColor: "#7B68EE"
							
							}
						})
						//this.covers.shift()
						// console.log(_this.myla)
						// console.log(this.myla)
					
					}
				})
				
			},5000)
			// uni.getLocation({
			// 	type: 'gcj02',
			// 	altitude: true,
			// 	isHighAccuracy: true,
			// 	success: function(res) {
			// 		console.log('当前位置的经度：' + res.longitude);
			// 		console.log('当前位置的纬度：' + res.latitude);
			// 		const latitude = res.latitude;
			// 		const longitude = res.longitude;
					
			// 		// uni.openLocation({
			// 		// 	latitude: latitude,
			// 		// 	longitude: longitude,
			// 		// 	success: function() {
			// 		// 		console.log('success成功获取地理位置');
			// 		// 	},
			// 		// 	fail() {
			// 		// 		console.log('打开失败')
			// 		// 	}
			// 		// })
					
			// 		// uni.openLocation({
			// 		// 	latitude: latitude,
			// 		// 	longitude: longitude,
			// 		// 	success: function() {
			// 		// 		console.log('success成功获取地理位置');
			// 		// 	},
			// 		// 	fail() {
			// 		// 		console.log('打开失败')
			// 		// 	}
			// 		// });
			// 		// let then = this;
			// 		// 	then.longitude = res.longitude;
			// 		// 	then.latitude = res.latitude;
			// 		// 	then.covers=[{
			// 		// 		longitude:then.longitude,
			// 		// 		latitude:then.latitude,
			// 		// 		height:100,
			// 		// 		width:100,
			// 		// 		iconPath: '../../static/loc_.png',
			// 		// 	}

			// 		// 	]
			// 	}
			// })


		},


		async onLoad() {
			var access = uni.getStorageSync("Access_token")
			var iid = uni.getStorageSync("itemId")
			this.itemId = iid;
			if (access) {
				this.$ISAUTHORIZATION = false;
				//向后端发送请求获取巡检点
				const mydevice = await this.$myRequest({
					url: '/task/points/' + iid,
					method: 'GET'
				})
				//将设备赋值给map...
				console.log('测试设备：',mydevice)
				console.log("测试我的map页", mydevice.data.data)

				this.allDeviceInfo = mydevice.data.data;
				var len = mydevice.data.data.length;

				//把获取到的经纬度给标记点
				for (var i = 0; i < len; i++) {
					this.covers.push( {
							id: i+1,
							latitude: mydevice.data.data[i].latitude,
							longitude: mydevice.data.data[i].longitude,
							iconPath: '../../static/devicelocation.png',
							callout: {
								content: mydevice.data.data[i].type,
								color: '#ffffff',
								borderRadius: 15,
								bgColor: "#7B68EE"

							}

						});
						this.place.push( {
							latitude: mydevice.data.data[i].latitude,
							longitude: mydevice.data.data[i].longitude
						})

				} //end for
				
				
				console.log('测试cover是否传值成功：',this.covers)
				this.mypolyline[0] = {
					points: this.place,
					arrowLine: true,
					dottedLine: true,
					width: 2,
					color: "#000"
				}
				console.log('测试mypolyline是否传值成功',this.mypolyline)
			} else {
				this.$ISAUTHORIZATION = true;
				//跳转到登录界面
				uni.navigateTo({
					url: '/pages/login/login'
				})
			}
		}
		// }
		// onLoad() {
		// 	uni.getLocation({
		// 		success: function (res) {
		// 			console.log("获取定位成功")
		// 			console.log('当前位置的经度：' + res.longitude);
		// 			console.log('当前位置的纬度：' + res.latitude);
		// 			this.mylatitude = res.latitude ;
		// 			this.mylongitude = res.longitude;
		// 			console.log('当前位置的经度：' + this.mylongitude);
		// 			console.log('当前位置的纬度：' + this.mylatitude);
		// 			// uni.openLocation({
		// 			// 			latitude: res.latitude,
		// 			// 			longitude: res.longitude,
		// 			// 			success: function () {
		// 			// 				console.log('success');
		// 			// 			}
		// 			// 		});
		// 		},
		// 		fail() {
		// 			console.log('获取失败')
		// 		}
		// 	});
		// }
	}
</script>

<style>
	.mybutton {
		padding-top: 20px;
		padding-left: 50px;
		padding-right: 50px;
		color: "#7B68EE"
	}
</style>
