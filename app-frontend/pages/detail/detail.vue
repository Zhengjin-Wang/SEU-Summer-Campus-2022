<!-- <template>
	<view class="content">
		<map id="map" class="map" ref="map" :show-location="true" :latitude="latitude" :longitude="longitude"></map>
	</view>
</template>

<script>
	const img = '../../static/loc_.png';

	export default {
		data() {
			return {
				latitude: 31.882023,
				longitude: 118.826514,
			}
		},
		onReady() {
			//创建
			this.mapContext = uni.createMapContext("map", this);
			this.addMarkers();
			
		},
		methods: {

		},
	}	
</script> -->

<template>
  <view class="page-section page-section-gap">
				<map style="width: 100%; height: 540px;" :latitude="maplatitude" :longitude="maplongitude"
					:markers="markers" :show-location="true" :polyline='mypolyline' :enable-3D="true"
					:show-compass='true' id='map1' ref="map1">
				</map>
</view>
</template>

<script>
  const img = '../../static/loc_.png';

  export default {
    data() {
      return {
      	maplatitude: 31.882023,
      	maplongitude: 118.826514,
		markers: [
				{
				id: 0,
				latitude: 31.884219,
				longitude: 118.818548,
				iconPath: '../../static/loc_.png',
				callout: {
					content: "设备1",
					color: '#ffffff',
					borderRadius: 15,
					bgColor: "#7B68EE"
		
				}
			}, {
				id: 1,
				latitude: 31.882547,
				longitude: 118.821225,
				iconPath: '../../static/loc_.png',
				callout: {
					content: "设备2",
					color: '#ffffff',
					borderRadius: 15,
					bgColor: "#7B68EE"
		
				}
		
			}, {
				id: 2,
				latitude: 31.884575,
				longitude: 118.826397,
				iconPath: '../../static/loc_.png',
				callout: {
					content: "设备3",
					color: '#ffffff',
					borderRadius: 15,
					bgColor: "#7B68EE"
		
				}
			}
		],
		mypolyline: [
				{
				points: [{
					latitude: 31.884219,
					longitude: 118.818548
				}, {
					latitude: 31.882547,
					longitude: 118.821225
				}, {
					latitude: 31.884575,
					longitude: 118.826397
				}],
				arrowLine: true,
				dottedLine: true,
				width: 2,
				color: "#7B68EE"
		
			}
		]
      }
    },
	//加载一次
    onReady() {
      this.mapContext = uni.createMapContext("map1", this);
 //      //获取当前地图中心的经纬度
 
 //          this.mapContext.getCenterLocation({
 //            success: function(res){
 //              console.log(res.longitude)
 //              console.log(res.latitude)
 //            }
 //          })
       
     this.mapContext.translateMarker({
          markerId: 0,
          autoRotate: true,
          duration: 5000,
          destination: {
            latitude:31.882023,
            longitude:118.826514,
          },
          // animationEnd() {
          //   console.log('animation end')
          // }
		  fail:function(){
			  console.log("移动失败")
		  }
        })
 
      },
	methods: {
	  
	      
	    }
    
    }

</script>

<style>
  .content {
    flex: 1;
  }

  .map {
    flex: 1;
  }
</style>

