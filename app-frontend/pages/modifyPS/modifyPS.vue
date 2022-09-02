<!-- <template>
	<view >
		<u-input :height="100" placeholder="原密码" border="surround" v-model="oldPS" @change="change" type="password" clearable :custom-style="customStyle"></u-input>
		<u-input :height="100" placeholder="新密码" border="surround" v-model="newPS" @change="change" type="password" clearable :custom-style="customStyle"></u-input>
		<u-input placeholder="确认新密码" border="surround" v-model="doublenewPS" @change="change" type="password" clearable></u-input>	
			
	</view>
	
</template>
<script>
	export default {
		data() {
			return {
				oldPS:"",
				newPS:"",
				doublenewPS:"",
				customStyle: {
					"padding-left": "40rpx",
					// 使光标消失（iOS无效果）
					"color": "transparent",
					"text-shadow": "0 0 0 #000"
				}
			}
		},
		methods: {
			
		}
	}
</script>

<style>

</style> -->

<template>
	<view class="container">
		<view class="input-area" :style="(ispdFocus || isuserFocus)?containerHeight.focus:containerHeight.blur">


			<view class="input-text" :style="isuserFocus?input_boder_style.focus:input_boder_style.blur">
				<view :style="isuserFocus?label_style.focus:label_style.blur">
					旧密码
				</view>

				<u-input :focus="un" v-model="userLoginInfo.oldPS" type="password" :height="100" placeholder=" "
					:custom-style="customStyle" :clearable="true" @focus="userFocus" @blur="userBlur" />

			</view>

			<!-- 密码输入框 -->
			<view class="input-text" :style="ispdFocus?input_boder_style.focus:input_boder_style.blur">
				<view :style="ispdFocus?label_style.focus:label_style.blur">
					新密码
				</view>

				<u-input :focus="pd" v-model="userLoginInfo.newPS" type="password" :password-icon="false" :height="100"
					placeholder=" " :maxlength="pdMaxLength" :custom-style="customStyle" :clearable="true"
					@focus="pdFocus" @blur="pdBlur" />

			</view>

			<!-- <view class="input-text" :style="ispdFocus?input_boder_style.focus:input_boder_style.blur">
				<view :style="ispdFocus?label_style.focus:label_style.blur">
					确认新密码
				</view>

				<u-input :focus="pd" v-model="userLoginInfo.doublenewPS" type="password" :password-icon="false" :height="100"
					placeholder=" " :maxlength="pdMaxLength" :custom-style="customStyle" :clearable="true"
					@focus="pdFocus" @blur="pdBlur" />

			</view> -->

			<!-- ripple是水波纹样式 -->
			<view class="btn">
				<u-button size="default" :loading="false" :ripple="true"
					:custom-style="isDisabledBtn?login_btn_style.disabled:login_btn_style.able"
					:disabled="isDisabledBtn" @click="login" text="保存"></u-button>
			</view>



			<u-toast ref="uToast"></u-toast><!-- /通过ref来开启此组件 -->
		</view>
		<view class="copy-right">CopyRight 2022.08.17 SEUCS</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				// ------------- 其他 -------------

				userLoginInfo: {
					oldPS: '',
					newPS: ''
					// doublenewPS: ''
				},

				// 整体容器高度，单位 rpx

				// containerHeight: 1000,
				containerHeight: {
					focus: 'height:800rpx;transition:0.2s',
					blur: 'height:1300rpx;transition:0.2s' // transition是css的过渡，更丝滑
				},

				// 获取焦点时，整体上移的动画效果
				animationData: {},

				// 登录界面的用户图标
				userImg: '../../static/pic.png',

				// 是否正在清除
				isClearing: false,

				// ------------- 输入框 -------------
				pdMaxLength: 10,
				// 输入框是否获取到焦点
				isuserFocus: false,
				ispdFocus: false,

				// 输入框自定义样式
				customStyle: {
					"padding-left": "40rpx",
					// 使光标消失（iOS无效果）
					"color": "transparent",
					"text-shadow": "0 0 0 #000"
				},

				// 清除按钮图标
				clearImg: '../../static/clear.png',

				// 输入框 label 样式
				label_style: {
					focus: 'width:100rpx;display:flex;justify-content:center;color:#7B68EE;font-weight:bold;transform:scale(1.1)',
					blur: 'width:100rpx;display:flex;justify-content:center;color:#aaaaaa;transform:scale(1)'
				},

				input_boder_style: {
					focus: 'border-bottom: 1px solid #7B68EE;',
					blur: 'border-bottom: 1px solid #e4e4e4;'
				},

				// ------------- 登录按钮 -------------
				// 登录按钮自定义样式
				login_btn_style: {
					able: {
						"width": "100%",
						"height": "100rpx",
						"border-radius": "20rpx",
						"border": "#e4e4e4",
						"background-color": "#7B68EE",
						"color": "#fff"
					},
					disabled: {
						"width": "100%",
						"height": "100rpx",
						"border-radius": "20rpx",
						"border": "#e4e4e4",
						"background-color": "#7B68EE",
						"color": "#fff",
						"opacity": "0.5"
					}
				},

				// 点击登录按钮后，接口返回数据前，对该操作上锁
				isLogining: false,

				un: false,
				pd: false,

				isDisabledBtn: true
			}
		},
		methods: {
			userFocus() {
				// 是否在焦点上
				this.isuserFocus = true
			},
			userBlur() {
				setTimeout(() => {
					this.isuserFocus = false
				}, 1)
			},

			pdFocus() {
				this.ispdFocus = true
			},
			pdBlur() {
				// 失去焦点事件先于清除事件触发，因此让其延迟即可先触发 clearInput 事件
				setTimeout(() => {
					this.ispdFocus = false
				}, 1)
			},

			// 清除 input 内容
			clearInput(value) {
				switch (value) {
					case 'userName':
						// setTimeout(() => {
						this.userLoginInfo.userName = ''
						// 清空内容之后保持焦点
						this.un = false
						this.$nextTick(() => {
							this.un = true
						})
						// }, 2)
						break
					case 'password':
						// setTimeout(() => {
						this.userLoginInfo.password = ''
						this.pd = false
						this.$nextTick(() => {
							this.pd = true
						})
						// }, 2)
						break
				}
			},
			
			login() {
				// 密码正确
				this.isLogining = true
				var id = uni.getStorageSync("myid")
				//向后端发送请求获取我的旧密码...
				 this.$myRequest({
					url:'/employee/password/' + id,
					method:'POST',
					data:{
						oldPassword:this.userLoginInfo.oldPS,
						newPassword:this.userLoginInfo.newPS
					}
				})
			

			}
		},
		watch: {
			userLoginInfo: {
				handler(newVal, oldVal) {
					if ((newVal.oldPS !== '') && (newVal.newPS !== '')) {
						this.isDisabledBtn = false
					} else {
						this.isDisabledBtn = true
					}
				},
				deep: true //深度监听
			}
		},
		async onShow() {
			var access = uni.getStorageSync("Access_token")
			
			
			if(access){
				this.$ISAUTHORIZATION = false;
				
			}else{
				this.$ISAUTHORIZATION = true;
				//跳转到登录界面
				uni.navigateTo({
					url:'/pages/login/login'
				})
			}
			
		}
	}
</script>

<style lang="scss">
	$screen-height:1334rpx;

	.container {
		display: flex;
		flex-wrap: wrap;
		justify-content: center;
		align-items: flex-end;
		width: 100%;


		.input-area {
			width: 80%;


			.input-text {
				display: flex;
				flex-wrap: nowrap;
				align-items: center;
				margin-top: 20rpx;

				.clear {
					height: 100rpx;
					display: flex;
					align-items: center;

					.img {
						height: 36rpx;
						width: 36rpx;
					}
				}
			}

			.btn {
				margin-top: 40rpx;

				.custom-style {
					width: 100%;
					border-radius: 20rpx;
					border: "#7B68EE";
					background-color: "#7B68EE";
					color: "#7B68EE";
				}
			}
		}

		.copy-right {
			// bottom: 100rpx;
			bottom: 0;
			width: 100%;
			color: $uni-text-color-grey;
			text-align: center;
			font-size: 24rpx;
		}
	}
</style>
