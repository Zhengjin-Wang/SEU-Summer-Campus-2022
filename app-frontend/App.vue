<script>//每个页面的入口文件
	export default {
		onLaunch: function() {
			console.log('App Launch')
		},
		onShow: function() {
			console.log('App Show')
		},
		onHide: function() {
			console.log('App Hide')
		},
		onError:function(err){
			console.log("出现异常了",err)
		}
	}
</script>

<style lang="scss">
	/*每个页面公共css */
	@import"uview-ui/index.scss"
</style>
