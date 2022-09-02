<template>
	<view>
		<scroll-view :scroll-top="scrollTop" scroll-y="true" class="scroll-Y" @scrolltoupper="upper"
			@scrolltolower="lower" @scroll="scroll">
			<uni-section title="基础信息" type="line">
				<u-cell-group>
					<u-cell icon="home-fill" title="设备号" :value="deviceID"></u-cell>
					<u-cell icon="tags-fill" title="设备类型" :value="deviceType"></u-cell>
					<u-cell icon="map-fill" title="纬度" :value="deviceLa"></u-cell>
					<u-cell icon="map" title="经度" :value="deviceLon"></u-cell>
				</u-cell-group>
			</uni-section>
			<uni-section title="是否正常运行" type="line">
				<u-cell-group>
					<u-cell icon="home-fill" title="设备状态" :isLink="true" arrow-direction="down" @click="changeShow"
						:value="deviceStatus"></u-cell>
				</u-cell-group>
				<u-picker :show="show" :columns="columns" :closeOnClickOverlay="true" @confirm="confirm"
					@cancel="cancel">
				</u-picker>
			</uni-section>
			<uni-section title="备注" type="line">
				<u--textarea class="textPS" v-model="devicePS" placeholder="请输入内容" :adjustPosition="true"
					confirm-type="done"></u--textarea>
			</uni-section>
			<uni-section title="上传照片" type="line">
				<view class="example-body">
					<uni-file-picker v-model="imageValue" fileMediatype="image" mode="grid" :autoUpload='false'
						file-mediatype="image" :sizeType="sizeType" @select="Select" @delete="Delete" limit="9"
						title="最多选择9张图片" />
				</view>
			</uni-section>
			<uni-section title="上传视频" type="line">
				<view class="example-body">
					<uni-file-picker v-model="videoValue" mode="grid" :autoUpload='false' limit="9"
						file-mediatype="video" title="最多选择9个视频" />

				</view>
			</uni-section>
			<view class="example-body-1">
				<u-button type="primary" text="保存" :disabled="disabled" size="normal" color="#9999ff" @click="save">
				</u-button>
			</view>
			<view class="example-body-1">
				<u-button type="primary" text="取消" :disabled="disabled" size="normal" color="#9999ff" @click="cancel_0">
				</u-button>
			</view>

		</scroll-view>

	</view>
</template>

<script>
	export default {
		data() {
			return {
				mess:"",
				pointId: "",
				disabled: true,
				index: 0,
				deviceID: "",
				deviceType: "",
				deviceLon: "",
				deviceLa: "",
				deviceFlag: '',
				devicePS: "",
				show: false,
				deviceStatus: "",
				columns: [
					['正常', '异常']
				],
				imageValue: {},
				videoValue: [],
				scrollTop: 0,
				old: {
					scrollTop: 0
				},
				sizeType: ['compressed'],
				file: []
				
			}
		},

		methods: {
			changeShow() {
				this.show = !this.show;

			},
			//回调函数，接受事件confirm（点击确认按钮后）返回的参数
			confirm(e) {
				this.show = !this.show;
				this.deviceStatus = e.value[0];
				if (e.value[0] == '异常') {
					this.deviceFlag = 1;
				} else {
					this.deviceFlag = 0;
				}

				// console.log("康康有什么",e.value[0]);

			},
			cancel() {
				this.show = !this.show;
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
			},

			//选择照片
			Select(e) {
				console.log('看看图片保存了个啥',e)
				console.log("测试图片路径", e.tempFilePaths)
				console.log("测试图片文件", e.tempFiles)
				this.file.push(e.tempFilePaths[0])//存路径
			},

			// 删除照片
			Delete(e) {
				console.log("测试删除图片")
				console.log('看看图片删除了个啥',e)
			},

			save() {
				//传递参数给后端			
				console.log('打印', this.devicePS)
				console.log('打印所有路径', this.file)
				//先上传文件
				// uni.uploadFile({
				// 		url: '/pointLog/upload', 
				// 		files: this.file,
				// 		fileType: "image",
				// 		header: {
				// 			'content-type': 'multipart/form-data',
				// 			"token":uni.getStorageSync("Access_token")
				// 		},
				// 		success(res){
				// 			uni.showToast({
				// 				title: '上传成功',
				// 				icon: "success"
				// 			});
				// 		},//success
													
				// 		fail(err) {
				// 			uni.showToast({
				// 				title: '上传失败',
				// 				icon: "error"
				// 			});
				// 		} //fail
						
				// 	})



				//再上传设备其他信息
				var myid = uni.getStorageSync("myid")
				this.$myRequest({
					url: '/pointLog/submit',
					method: 'POST',
					data: {
						"description": this.devicePS, //备注
						"photoPath": 'path', ///upload返回的文件夹路径
						"ifAbnormal": this.deviceFlag, //0为无异常，1为有异常
						"inspectPointId": this.pointId, //巡检点id
						"inspectEmployeeId": myid //巡检人员id
					}
				})
				//跳转页面（由于有两个扫一扫入口，因此需要回退到上一个页面）
				uni.navigateBack({
					delta: 1
				});
			},
			cancel_0() {
				uni.navigateBack({
					delta: 1
				});
			},
			// 上传成功
			success(e) {
				console.log('上传成功',e)
			},

			// 上传失败
			fail(e) {
				console.log('上传失败：', e)
			},
			onLoad:function(option){
				this.mess = option.index;
			}
		},
		watch: {
			deviceStatus: {
				handler(newVal, oldVal) {
					if (newVal !== '') {
						this.disabled = false
					} else {
						this.isDisabledBtn = true
					}
				},
				deep: true //深度监听
			}
		},
		
		async onShow() {
			var access = uni.getStorageSync("Access_token")

			if (access) {
				this.$ISAUTHORIZATION = false;
				//向后端发送请求获取设备信息...
				const mydeviceinfo = await this.$myRequest({
					url: '/point/asd',//这里修改一下换为this.mess
					method: 'GET'
				})
				//将设备信息赋值给页面...
				console.log("测试我的具体设备信息页", mydeviceinfo)
				this.deviceID = mydeviceinfo.data.data.number,
					this.deviceType = mydeviceinfo.data.data.type,
					this.deviceLon = mydeviceinfo.data.data.longitude,
					this.deviceLa = mydeviceinfo.data.data.latitude,
					this.pointId = mydeviceinfo.data.data.id,
					console.log("测试我的具体设备类型", mydeviceinfo.data.data.type)
			} else {
				this.$ISAUTHORIZATION = true;
				//跳转到登录界面
				uni.navigateTo({
					url: '/pages/login/login'
				})
			}

		}
	}
</script>

<style>
	.example-body {
		padding: 10px;
		padding-top: 0;
	}

	.example-body-1 {
		padding-left: 40px;
		padding-right: 40px;
		padding-top: 0;
		padding-bottom: 10px;
	}
</style>
