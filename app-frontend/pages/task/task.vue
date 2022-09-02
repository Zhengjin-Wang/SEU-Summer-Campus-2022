<template>
	<view >
		<u-cell-group>
			
			<u-cell v-for="(item,index) in templist" icon="volume-fill" :title="`巡检任务${index+1}`" :value="`倒计时${item.startWork}分钟`" 
			:label="`任务时间:${item.taskPeriod}`" 
			@click="detailTask(index)"></u-cell>
			
		</u-cell-group>
	</view>
	
</template>

<script>
	export default {
		data() {
			return {
				list:[],
				templist:[]
			}
		},
		methods: {
			detailTask:function(ind){
				uni.setStorageSync("itemId",this.list[ind].id)
				uni.navigateTo({
					url: '/pages/device/device?index='+this.list[ind].id
				});

			}
		},
		async onShow() {
			var access = uni.getStorageSync("Access_token")
			var empID = uni.getStorageSync("myid")
			// console.log("我的ID",empID)
			if(access){
				this.$ISAUTHORIZATION = false;
				//向后端发送请求获取我的巡检任务
				const mytask = await this.$myRequest({
					url:'/task/' + empID,
					method:'GET'
				})
				console.log("测试我的待完成任务", mytask)
				
				//将task赋值给页面信息...
				this.list = mytask.data.data;
				console.log("测试我的列表",mytask.data.data[0].endTime)
				
				var len = this.list.length;
				var temp = new Array(len);
				for (var i = 0;i<len;i++)
				{
					temp[i]= {
						"taskPeriod":this.list[i].startTime.slice(12,19) + " - " + this.list[i].endTime.slice(12,19),
						"startWork":this.$calTime({
							
							startTime:this.list[i].startTime,
							
						})
					}
					
				}//end for
				this.templist = temp;
				console.log("测试数组",temp[1])
			}else{
				this.$ISAUTHORIZATION = true;
				//跳转到登录界面
				uni.navigateTo({
					url:'/pages/login/login'
				})
			}
			
		},
		onReachBottom(){
			console.log("页面触底了")
		uni.startPullDownRefresh()
		}
	}
</script>

<style lang="scss">
	
</style>
