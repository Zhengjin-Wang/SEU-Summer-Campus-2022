<template>
	<u-cell-group>
		<u-cell icon="account-fill" title="姓名" :value="name"></u-cell>
		<u-cell icon="integral-fill" title="工号" :value="workid"></u-cell>
		<u-cell icon="phone-fill" title="手机号" :value="tel"></u-cell>
		<u-cell icon="map-fill" title="住址" :value="address"></u-cell>
		<u-cell icon="calendar-fill" title="入职时间" :value="entryTime"></u-cell>
		<u-cell icon="edit-pen-fill" title="修改密码" :isLink="true" url="/pages/modifyPS/modifyPS"></u-cell>
		<view class="logout">
			<u-button type="primary" text="退出登录"  size="normal" color="#9999ff" @click="logout">
			</u-button>
		</view>
	</u-cell-group>
</template>

<script>
	export default {
		data() {
			return {

				name: '',
				workid: "",
				tel: "",
				address:"秣陵街道",
				entryTime:''
				
			}
			
		},
		async onShow() {
			var access = uni.getStorageSync("Access_token")
			// var workNo = uni.getStorageSync("workNo")
			if (access) {
				this.$ISAUTHORIZATION = false;
				//向后端发送请求获取我的信息...
				// const myinfo = await this.$myRequest({
				// 	url: '/employee/' + workNo,
				// 	method: 'GET'
				// })
				//将信息传入个人信息页
				// console.log("测试我的个人信息页", myinfo)
				var n = uni.getStorageSync("myname")
				var mi = uni.getStorageSync("myid")
				var mp = uni.getStorageSync("myphone")
				var ct = uni.getStorageSync("createTime")
				
				this.name = n,
				this.workid = mi, 
				this.tel = mp,
				this.entryTime = ct
			} else {
				this.$ISAUTHORIZATION = true;
				//跳转到登录界面
				uni.navigateTo({
					url: '/pages/login/login'
				})
			}
		
		
		},
		methods: {
			logout(){
				this.$myRequest({
					url:'/logout',
					method:'POST'
				});
			uni.clearStorageSync();	
			uni.navigateTo({
				url:'/pages/login/login'
			})	
			}//退出登录

		}
	}
</script>
