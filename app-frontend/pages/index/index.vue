

<template>
	<!-- #ifndef APP-NVUE || MP-TOUTIAO -->
	<view class="myindex">
		<!-- 轮播图 -->
		<view class="swiper">

			<u-swiper :list="list3" previousMargin="30" nextMargin="30" circular :autoplay="true" radius="10"
				bgColor="#ffffff" height=200px imgMode="aspectFit"></u-swiper>

		</view>
		<!-- kpi -->
		<view class="container">

			<uni-section title="我的工作速览" type="line">
				<view class="charts-box">
					<qiun-data-charts type="word" :opts="opts" :chartData="chartData" />
				</view>
				<!-- <view class="example-body">

					<uni-row class="demo-uni-row" :gutter="gutter" :width="nvueWidth">
						<uni-col :span="8" :offset="2">
							<view class="demo-uni-col dark">
								<view class="cont">
									<view>工作总天数</view>
									<view>workTime</view>
								</view>
							</view>
						</uni-col>
						<uni-col :span="8" :offset="4">
							<view class="demo-uni-col light">
								<view class="cont">
									<view>巡检总次数</view>
									<view>taskNum</view>
								</view>
							</view>
						</uni-col>
					</uni-row>

					<uni-row class="demo-uni-row" :gutter="gutter" :width="nvueWidth">
						<uni-col :span="8" :offset="2">
							<view class="demo-uni-col dark">
								<view class="cont">
									<view>完成巡检次数</view>
									<view>sucNum</view>
								</view>
							</view>
						</uni-col>
						<uni-col :span="8" :offset="4">
							<view class="demo-uni-col light">
								<view class="cont">
									<view>未完成巡检次数</view>
									<view>failNum</view>
								</view>
							</view>
						</uni-col>
					</uni-row>


					<uni-row class="demo-uni-row" :gutter="gutter" :width="nvueWidth">
						<uni-col :span="8" :offset="2">
							<view class="demo-uni-col dark">
								<view class="cont">
									<view>巡检完成率</view>
									<view>rate</view>
								</view>
							</view>
						</uni-col>
						<uni-col :span="8" :offset="4">
							<view class="demo-uni-col light">
								<view class="cont">
									<view>已发现隐患数</view>
									<view>found</view>
								</view>
							</view>
						</uni-col>
					</uni-row>


				</view> -->
			</uni-section>

			<uni-section title="我的工作量化" type="line">
				<view class="charts-box-1">
					<qiun-data-charts type="pie" :opts="opts_1" :chartData="chartData_1"  />
				</view>
			</uni-section>
		</view>


	</view>
	<!-- #endif -->
</template>

<script>
	export default {
		data() {
			return {
				list3: [
					'/static/t9.png',
					'/static/tt.png',
					'/static/t7.png'
				],
				// testname:"小王",
				myid: "",
				workTime: "",
				taskNum: "",
				sucNum: "",
				failNum: "",
				rate: '',
				found: "",
				gutter: 0,
				nvueWidth: 730,
				chartData: {},
				mypassword: "",
				myname: "",
				myphone: "",

				//这里的 opts 是图表类型 type="word" 的全部配置参数，您可以将此配置复制到 config-ucharts.js 文件中下标为 ['word'] 的节点中来覆盖全局默认参数。实际应用过程中 opts 只需传入与全局默认参数中不一致的【某一个属性】即可实现同类型的图表显示不同的样式，达到页面简洁的需求。
				opts: {
					timing: "easeOut",
					duration: 1000,
					rotate: false,
					rotateLock: false,
					color: ["#1890FF", "#91CB74", "#FAC858", "#EE6666", "#73C0DE", "#3CA272", "#FC8452", "#9A60B4",
						"#ea7ccc"
					],
					padding: undefined,
					fontSize: 13,
					fontColor: "#666666",
					dataLabel: true,
					dataPointShape: true,
					dataPointShapeType: "solid",
					touchMoveLimit: 60,
					enableScroll: false,
					enableMarkLine: false,
					extra: {
						word: {
							type: "normal",
							autoColors: false
						}
					}
				},

				//第二个
				chartData_1: {},
				opts_1: {
					timing: "easeOut",
					duration: 1000,
					rotate: false,
					rotateLock: false,
					color: ["#1890FF", "#91CB74", "#FAC858", "#EE6666", "#73C0DE", "#3CA272", "#FC8452", "#9A60B4",
						"#ea7ccc"
					],
					padding: [5, 5, 5, 5],
					fontSize: 13,
					fontColor: "#666666",
					dataLabel: true,
					dataPointShape: true,
					dataPointShapeType: "solid",
					touchMoveLimit: 60,
					enableScroll: false,
					enableMarkLine: false,
					legend: {
						show: true,
						position: "bottom",
						float: "center",
						padding: 5,
						margin: 5,
						backgroundColor: "rgba(0,0,0,0)",
						borderColor: "rgba(0,0,0,0)",
						borderWidth: 0,
						fontSize: 13,
						fontColor: "#666666",
						lineHeight: 11,
						hiddenColor: "#CECECE",
						itemGap: 10
					},
					extra: {
						pie: {
							activeOpacity: 0.5,
							activeRadius: 10,
							offsetAngle: 0,
							labelWidth: 15,
							border: true,
							borderWidth: 3,
							borderColor: "#FFFFFF",
							customRadius: 0,
							linearType: "none"
						},
						tooltip: {
							showBox: true,
							showArrow: true,
							showCategory: false,
							borderWidth: 0,
							borderRadius: 0,
							borderColor: "#000000",
							borderOpacity: 0.7,
							bgColor: "#000000",
							bgOpacity: 0.7,
							gridType: "solid",
							dashLength: 4,
							gridColor: "#CCCCCC",
							fontColor: "#FFFFFF",
							splitLine: true,
							horizentalLine: false,
							xAxisLabel: false,
							yAxisLabel: false,
							labelBgColor: "#FFFFFF",
							labelBgOpacity: 0.7,
							labelFontColor: "#666666"
						}
					}
				}
			}
		},
		onReady() {
			

		},
		methods: {
			getServerData() {
				//模拟从服务器获取数据时的延时
				setTimeout(() => {

					//模拟服务器返回数据，如果数据格式和标准格式不同，需自行按下面的格式拼接
					let res = {
						series: [{
								name: "工作总天数" + this.workTime,
								textSize: 25,
								data: undefined
							},
							{
								name: "巡检总次数" + this.taskNum,
								textSize: 20,
								data: undefined
							},
							{
								name: "完成巡检次数" + this.sucNum,
								textSize: 20,
								data: undefined
							},
							{
								name: "未完成巡检次数" + this.failNum,
								textSize: 20,
								data: undefined
							},
							{
								name: "巡检完成率" + this.rate,
								textSize: 20,
								data: undefined
							},
							{
								name: "已发现隐患数" + this.found,
								textSize: 20,
								data: undefined
							},
							{
								name: "已发现隐患数" + this.found,
								textSize: 20,
								data: undefined
							},
							{
								name: "巡检完成率" + this.rate,
								textSize: 20,
								data: undefined
							},
							{
								name: "未完成巡检次数" + this.failNum,
								textSize: 10,
								data: undefined
							},
							{
								name: "巡检总次数" + this.taskNum,
								textSize: 12,
								data: undefined
							},
							{
								name: "工作总天数" + this.workTime,
								textSize: 10,
								data: undefined
							},
							{
								name: "巡检完成率" + this.rate,
								textSize: 12,
								data: undefined
							},
							{
								name: "已发现隐患数" + this.found,
								textSize: 10,
								data: undefined
							},
							{
								name: "未完成巡检次数" + this.failNum,
								textSize: 12,
								data: undefined
							},
							{
								name: "工作总天数" + this.workTime,
								textSize: 10,
								data: undefined
							},
							{
								name: "巡检总次数" + this.taskNum,
								textSize: 12,
								data: undefined
							}
						]
					};
					this.chartData = JSON.parse(JSON.stringify(res));
				}, 500);

				setTimeout(() =>{
					let res_1 = {
						series: [{
							data: [{
								"name": "完成巡检次数",
								"value": this.sucNum
								//sucNum
							}, {
								"name": "未完成巡检次数",
								"value": this.failNum
								//failNum
							}]
						}]
					};
					this.chartData_1 = JSON.parse(JSON.stringify(res_1));
				},500);
				

			}
		},
		async onShow() {
			var access = uni.getStorageSync("Access_token")
			var workNo = uni.getStorageSync("workNo")
			if (access) {
				this.$ISAUTHORIZATION = false;
				//向后端发送请求获取我的业绩...
				// const mykpi = await this.$myRequest({
				// 	url: '/employee/' + workNo,
				// 	method: 'GET'
				// })
				//将kpi赋值给页面信息...
				// console.log("测试我的首页", mykpi)
				//获取本地存储的信息
				var wt = uni.getStorageSync("workTime")
				var ct = uni.getStorageSync("createTime")
				var pan = uni.getStorageSync("postAnomalNum")
				var mi = uni.getStorageSync("myid")
				var tn = uni.getStorageSync("taskNum")
				var sn = uni.getStorageSync("sucNum")
				var f = uni.getStorageSync("found")
				var mpw = uni.getStorageSync("mypassword")
				var mn = uni.getStorageSync("myname")
				var mf = uni.getStorageSync("myphone")
				var fail = uni.getStorageSync("failNum")
				var r = uni.getStorageSync("rate")
				
				
				this.myid = mi;
				this.taskNum = tn;
				this.sucNum = sn;
				this.found = f;
				this.mypassword = mpw;
				this.myname = mn;
				this.myphone = mf;
				this.failNum = fail;
				this.rate = r ;
				this.workTime = wt;
				
				console.log("测试工时", this.workTime);
				console.log("测试rate",this.rate);
				console.log("测试failNum",this.failNum);
				
			} else {
				this.$ISAUTHORIZATION = true;
				//跳转到登录界面
				uni.navigateTo({
					url: '/pages/login/login'
				})
			}

			this.getServerData();
		}
	}
</script>

<style lang="scss">
	//首页
	.myindex {

		//轮播图
		.swiper {
			width: 100%;
			height: 50%;
			padding-top: 5%;

		}

		//kpi
		.container {
			padding: 5%;
		}

		.demo-uni-row {

			margin-bottom: 10px;
			height: 20%;
			// 组件在小程序端display为inline
			// QQ、字节小程序文档写有 :host，但实测不生效
			// 百度小程序没有 :host
			/* #ifdef MP-TOUTIAO || MP-QQ || MP-BAIDU */
			display: block;
			/* #endif */
		}

		// 支付宝小程序没有 demo-uni-row 层级
		// 微信小程序使用了虚拟化节点，没有 demo-uni-row 层级
		/* #ifdef MP-ALIPAY || MP-WEIXIN */
		::v-deep .uni-row {
			margin-bottom: 10px;
		}

		/* #endif */

		.demo-uni-col {
			height: 72px;
			border-radius: 10px;
		}

		.dark_deep {
			background-color: #CCCCFF;
			opacity: 60%;
			color: #fff;
			font-size: 16px;
			text-align: center;
			position: relative;

		}

		.dark {
			background-color: #9999ff;
			font-weight: 700;
			color: #fff;
			font-size: 16px;
			text-align: center;

			.cont {
				// margin-top: 10px;
				// margin-bottom: 10px;
				padding-top: 10px;
				font-weight: 700;
				color: #fff;
				font-size: 16px;
				text-align: center;
			}

		}


		.light {
			background-color: #9999ff;



			.cont {
				// margin-top: 10px;
				// margin-bottom: 10px;
				padding-top: 10px;
				font-weight: 700;
				color: #fff;
				font-size: 16px;
				text-align: center;
			}

		}

		.example-body {
			/* #ifndef APP-NVUE */
			display: block;
			/* #endif */
			padding: 5rpx 10rpx 0;
			overflow: hidden;
		}

	}

	// .test{
	// 	background: #9999ff;
	// 	  border: 0.1875em solid #fff;
	// 	  border-radius: 50%;
	// 	  box-shadow: 0.375em 0.375em 0 0 rgba(15, 28, 63, 0.125);
	// 	  height: 5em;
	// 	  width: 5em;

	// }
	.charts-box {
		width: 100%;
		height: 300px;
	}

	.charts-box-1 {
		width: 100%;
		height: 300px;
	}
</style>
